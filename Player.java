import java.util.*; 
import java.util.Scanner;
import java.security.SecureRandom; 
public class Player{
	
	private int id;
	private int turns;
	private Vector<Card> playerCards;

	private Vector<Integer> aliveUsers;
	
	/* Set default value */
	public Player(int id)
	{
		this.id = id;
		this.playerCards = new Vector<Card>(5);
		this.turns = 0;

		this.aliveUsers = new Vector<Integer>(2);
	}
	

	
	/* Get alive users */
	public void copyAliveUsers(Vector<Integer> alivePlayers)
	{
		this.aliveUsers.clear();
		this.aliveUsers.addAll(alivePlayers);
	}
	
	/* Get initial cards from dealer*/	 
	public void getInitialCards(Card initialCards)
	{
		this.playerCards.add(initialCards);
		//System.out.println("Clone of v: " + this.playerCards); 
	}
	
	public void printCards()
	{
		System.out.println("Player " + this.id + " deck is " + this.playerCards + "this player turn left " + this.turns);
	}
	

	/* Draw a card and handle if getting boom 
	 * if getting a normal card, get that card */
	public Boolean getSingleCard(DeckofCards gameDecks, int cardPos, Scanner sc)
	{
		// ask if player want to get a card
		System.out.println("Do you want to get a card (y/n)? : ");
		String answer = "";		
		Scanner letScan = sc;
		answer = letScan.next();

		if(answer.equals("y")) // handle get a card
		{
			/* Getting boom, handle boom using defuse */
			if(gameDecks.dealCard(cardPos).getCardName().equals("Exploding Kitten"))
			{
				System.out.println("The drawn card is: " + gameDecks.dealCard(cardPos).getCardName());
				gameDecks.removeCard(cardPos);
				//System.out.println("Temp deck AFTER GETTING BOOOOOM have:----------------------------------\n " + gameDecks.cardsLeft());
				// handle boom using defuse
				if(existenceOfCard("Defuse"))
				{
					System.out.println("Do you want to use Defuse (y/n)? :");
					answer = letScan.next();
					if(answer.equals("y"))
					{
						useDefuse(sc, gameDecks);
						return true;
					}
					else
					{
						this.turns = 0;
						this.aliveUsers.remove((Integer) this.id);						
						return true;
					}
				}
				this.turns = 0;
				this.aliveUsers.remove((Integer) this.id);	
				return true;
			}
			/* If not, take card */
			System.out.println("The drawn card is: " + gameDecks.dealCard(cardPos).getCardName());
			this.playerCards.add(gameDecks.dealCard(cardPos));
			gameDecks.removeCard(cardPos);
			System.out.println("Player cards AFTER GETTING a card:----------------------------------\n " + this.playerCards);
			//System.out.println("Remain cards AFTER GETTING a card:----------------------------------\n " + gameDecks.cardsLeft());
			this.turns--;			
			return true;
		}
		else
		{
			return false;
		}
	}
	/*-----------------------------------------------------------------------------*/
	
	/* Check for existence of a card in player collection */
	public Boolean existenceOfCard(String cardName)
	{
		for(int i=0; i<this.playerCards.size(); i++)
		{
			if(this.playerCards.elementAt(i).getCardName().equals(cardName))
			{
				return true;
			}
		}
		return false;
	}
	
	/* Choose one player and attack them */
	public void useAttack(Status currentStatus, Vector<Integer> listIDs, Scanner sc)
	{
		System.out.println("List IDs are " + listIDs);
		int victim = 0;
		boolean flag = true;
		while(flag)
		{
			System.out.println("Please choose a victim: ");
			victim = sc.nextInt();
			flag = (listIDs.contains(victim) && victim != this.id) ? false : true;
		}
		currentStatus.setID(victim);
		currentStatus.setTurns(this.turns > 1 ? this.turns+2 : this.turns+1);
		this.turns = 0;
		removeCard("Attack");		
	}
	/*-----------------------------------------------------------------------------*/
	
	/* Skip one turn of this player */
	public void useSkip(Status currentStatus, int nextUser)
	{
		this.turns--;
		if(this.turns == 0)
		{
			currentStatus.setID(nextUser);
			currentStatus.setTurns(1);
			this.turns = 0;
			removeCard("Skip");
			return;
		}
		removeCard("Skip");
	}
	/*-----------------------------------------------------------------------------*/
	
	/* Skip all turns of this player */
	public void useSuperSkips(Status currentStatus, int nextUser)
	{
		currentStatus.setID(nextUser);
		currentStatus.setTurns(1);
		this.turns = 0;
		removeCard("Super Skip");
	}
	/*-----------------------------------------------------------------------------*/
	
	/* Swap top and bottom card of remain game deck */
	public void useSwapTopBottom(DeckofCards gameDeck)
	{
		String temp = gameDeck.cardsLeft().lastElement().getCardName();
		gameDeck.cardsLeft().lastElement().setCardName(gameDeck.cardsLeft().firstElement().getCardName());
		gameDeck.cardsLeft().firstElement().setCardName(temp);
		removeCard("Swap Top And Bottom");
	}
	/*-----------------------------------------------------------------------------*/
	
	/* Shuffle remain game deck */
	public void useShuffle(DeckofCards gameDeck)
	{
		gameDeck.shuffle();
		removeCard("Shuffle");
	}
	/*-----------------------------------------------------------------------------*/
	
	/* See top 3 cards */
	public void useSeeTheFuture(DeckofCards gameDeck)
	{
		if(gameDeck.cardsLeft().size() >= 3)
		{
			 System.out.println("Card 1: " + gameDeck.cardsLeft().elementAt(0));
			 System.out.println("Card 2: " + gameDeck.cardsLeft().elementAt(1));
			 System.out.println("Card 3: " + gameDeck.cardsLeft().elementAt(2));
		}
		else
		{
			for(int i=0; i<gameDeck.cardsLeft().size(); i++)
			{
				System.out.println("Card " + i + " : " + gameDeck.cardsLeft().elementAt(i));
			}
		}
		removeCard("See the future");
	}
	/*-----------------------------------------------------------------------------*/
	
	/*-----------------------------------------------------------------------------*/
	public void useDefuse(Scanner sc, DeckofCards gameDeck)
	{
		removeCard("Defuse");		
		System.out.println("Where do you want to put the boom (0 for random)? (from 1 to " + (gameDeck.cardsLeft().size()+1) + " ): "); 
		int position = sc.nextInt()-1;
		if(position == -1)
		{
			SecureRandom rand = new SecureRandom();
			position = rand.nextInt(gameDeck.cardsLeft().size()+1);
		}		
		gameDeck.addCard(position, "Exploding Kitten");
		//System.out.println("Remain cards after add BOoooOM have:----------------------------------\n " + gameDeck.cardsLeft());
		this.turns--;
	}
	/*-----------------------------------------------------------------------------*/
	
	/* remove card from player's cards */
	public void removeCard(String cardname)
	{
		for(int i=0; i<this.playerCards.size(); i++)
		{
			if(this.playerCards.elementAt(i).getCardName().equals(cardname))
			{
				this.playerCards.removeElementAt(i);
				break;
			}
		}
	}
	/*-----------------------------------------------------------------------------*/

	
	
	
	/* (a)Ask for getting card(s) (1) or using card(s) (2)
	 * (1) getCard then depending on turns to return or repeat step (a)
	 * (2) choose which card(s) to use:
	 * 		(2.1) Defuse
	 * 		(2.2) Exploding
	 * 		(2.3) Attack
	 * 		(2.4) Skip
	 * 		(2.5) Super skip
	 * 		(2.3) Favor
	 * 		(2.4) Shuffle
	 * 		(2.5) See the future
	 * (2*) depend on player turns to decide return, or getCard, or repeat (2) 
	 * */
	public Status action(Status currentStatus, int nextID, DeckofCards gameDecks)
	{
		Scanner myscan = new Scanner(System.in);
		
		// get player's turns and all alive players
		this.turns = currentStatus.getTurns();
		copyAliveUsers(currentStatus.getAlivePlayers());
		// print ALL THE REMAIN CARDS 
		//System.out.println("All the remain cards: \n" + gameDecks.cardsLeft());	
		System.out.println("Deck size: " + gameDecks.cardsLeft().size());
		// get card or use card
		do
		{			
			printCards();	// print player's information
			/* if get card, check if alive or not
			 * if death, change currentStatus and return*/
			if(getSingleCard(gameDecks,0, myscan))
			{
				// if this player is out of turn, move to next player
				if(this.turns == 0)
				{
					currentStatus.setID(nextID);
					currentStatus.setTurns(1);
					currentStatus.setAlivePlayer(this.aliveUsers);
					return currentStatus;
				}
			}
			else if(this.turns>0)
			{
				// choose card to use
				System.out.println("Do you want to use a card (y/n)? :");
				String answer = myscan.next();
				
				if(answer.equals("y"))
				{
					System.out.println("Please choose a card: ");
					answer = myscan.next();

					switch(answer)
					{
						case "Attack":
							useAttack(currentStatus, this.aliveUsers, myscan);
							//printCards();
							//myscan.next();
							break;
						case "Skip":
							useSkip(currentStatus, nextID);
							break;
						case "SuperS":
							useSuperSkips(currentStatus, nextID);
							break;
						case "SwapTB":
							useSwapTopBottom(gameDecks);
							break;
						case "Shuffle":
							useShuffle(gameDecks);
							break;
						case "SeeTF":
							useSeeTheFuture(gameDecks);
							break;
					}
				}				
			}
		} while(this.turns>0);
		//myscan.close();
		return currentStatus;
	}
	

}
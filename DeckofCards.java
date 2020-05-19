//import java.security.SecureRandom;
import java.util.Vector;
import java.util.Collections;
public class DeckofCards
{
	private Vector<Card> deck; // array of Card objects
	//private int currentCard; // index of next Card to be dealt (0-35)
	private static final int NUMBER_OF_CARDS =  27;// 36; // constant # of Cards
	// random number generator
	//private static final SecureRandom randomNumbers = new SecureRandom();
	
	// constructor fill deck of Cards
	public DeckofCards()
	{
		String[] names = {"Defuse", "Exploding Kitten", "Attack", "Skip", "Super Skip", "Swap Top And Bottom", "Shuffle", "See the future"};
		Integer[] countEachCard = {6,4,4,4,2,2,3,2};	 //{6,4,4,4,5,4,4,5};
		deck = new Vector<Card>(NUMBER_OF_CARDS); // create array of Card objects
		//currentCard = 0;
		for(int i=10, j=2; i<NUMBER_OF_CARDS; i++)
		{
		    if(countEachCard[j]>0)
		    {
		        deck.addElement(new Card(names[j]));
		        countEachCard[j]--;
		    }
		    else
		    {
		        j++;
		        deck.addElement(new Card(names[j]));
		        countEachCard[j]--;
		    }
		}
		shuffle();
	}
	
	public Vector<Card> shuffle()
	{
		Collections.shuffle(this.deck);
		return this.deck;
	}
	
	public Card dealCard(int cardPosition)
	{
		return this.deck.elementAt(cardPosition);
	}
	
	public void addDefuse(int count)
	{
		for(int i=0; i<count; i++)
		{
			this.deck.add(new Card("Defuse"));
		}
		shuffle();
	}
	
	public void addBoom(int count)
	{
		for(int i=0; i<count; i++)
		{
			this.deck.add(new Card("Exploding Kitten"));
		}
		shuffle();
	}
	
	public void removeCard(int cardPostion)
	{
		this.deck.remove(cardPostion);
	}
	
	public void addCard(int cardPosition, String cardName)
	{
		this.deck.add(cardPosition, new Card(cardName));
	}
	
	public int deckSize()
	{
		return this.deck.size();
	}
	
	public Vector<Card> cardsLeft()
	{
		return this.deck;
	}
	
	public void setDeck(Vector<Card> edittedDeck)
	{
		this.deck.clear();
		this.deck.addAll(edittedDeck);
	}
}
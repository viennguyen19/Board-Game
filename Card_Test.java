import java.util.*;
public class Card_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	   		
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		Vector<Player> listPlayers = new Vector<Player>(2);
		listPlayers.add(player1);
		listPlayers.add(player2);
		Vector<Integer> listIDs = new Vector<Integer>(2);
		listIDs.add(1);
		listIDs.add(2);

	    DeckofCards myDeck = new DeckofCards();

	    player1.getInitialCards(new Card("Defuse"));
	    	
	    player2.getInitialCards(new Card("Defuse"));
	    
	    for(int i=0, j=0; i<4; i++, j++)
	    {
	    	player1.getInitialCards(myDeck.dealCard(j));
	    	myDeck.removeCard(j);
	    	player2.getInitialCards(myDeck.dealCard(++j));
	    	myDeck.removeCard(j);
	    }
	    player1.printCards();
	    player2.printCards();
	    myDeck.addDefuse(1);
	    myDeck.addBoom(1);

	    //System.out.println("Cards left size are " + myDeck.cardsLeft().size());
	    //System.out.println("Cards left are \n" + myDeck.cardsLeft());
	    Status currentStatusIS = new Status();
	    //Status nextStatusIS = new Status();
	    currentStatusIS.setID(1);
	    currentStatusIS.setTurns(1);
	    currentStatusIS.setDeck(myDeck.cardsLeft());
	    currentStatusIS.addAlivePlayer(1);
	    currentStatusIS.addAlivePlayer(2);
	    int nextUserIS = 0;
	    nextUserIS = currentStatusIS.getID()+1;
	    while(currentStatusIS.countAlivePlayers() > 1)
	    {
	    	System.out.println("---------------------------------------------------------------------------");
	    	currentStatusIS = listPlayers.elementAt(currentStatusIS.getID()-1).action(currentStatusIS, nextUserIS, myDeck);

	    	nextUserIS = currentStatusIS.findNextID();
	    	System.out.println("---------------------------------------------------------------------------");
	    }
	    
	    
	}
}

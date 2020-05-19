import java.util.Vector;

public class Status {
	private int playerID;
	private int playerTurns;
	private Vector<Card> edittedDeck;
	private Vector<Integer> alivePlayers;
	public Status()
	{
		this.playerID = -1;
		this.playerTurns = 0;
		this.alivePlayers = new Vector<Integer>(2);
		this.edittedDeck =  new Vector<Card>(36);
	}
	
	public int findNextID()
	{
		int nextIndex = (alivePlayers.indexOf(playerID) + 1) % alivePlayers.size();
		return alivePlayers.elementAt(nextIndex);
	}
	
	public int countAlivePlayers()
	{
		return this.alivePlayers.size();
	}
	
	public void addAlivePlayer(int aliveID)
	{
		this.alivePlayers.add(aliveID);
	}
	
	public void removeDeathPlayer(int deathID)
	{
		this.alivePlayers.removeElement((Integer) deathID);
	}
	
	public Vector<Integer> getAlivePlayers()
	{
		return this.alivePlayers;
	}
	
	public void setAlivePlayer(Vector<Integer> aliveID)
	{
		this.alivePlayers.clear();
		this.alivePlayers.addAll(aliveID);
	}
	
	public int getID()
	{
		return this.playerID;
	}
	
	public int getTurns()
	{
		return this.playerTurns;
	}

	public void setID(int id)
	{
		this.playerID =  id;
	}
	
	public void setTurns(int turns)
	{
		this.playerTurns = turns;
	}

	public void setDeck(Vector<Card> newDeck)
	{
		this.edittedDeck.clear();
		this.edittedDeck.addAll(newDeck);
	}
	
	public Vector<Card> getDeck()
	{
		return this.edittedDeck;
	}
	
	public void printStatus()
	{
		System.out.println("Status: " + this.playerTurns + this.edittedDeck + this.alivePlayers);
	}
}

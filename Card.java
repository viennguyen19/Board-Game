
public class Card {
    private String cardName;
    //private final String _cardRole;
    public Card(String cardName)
    {
        this.cardName = cardName; 
    }
    public String getCardName()
    {
        return cardName;
    }
    /*public String getCardRole()
    {
        return _cardRole;
    }*/
    public void setCardName(String cardName)
    {
        this.cardName = cardName;
        return;
    }
    /*public void setCardRole(String cardRole)
    {
        this._cardRole = cardRole;
        return;
    }*/
    public String toString()
    {
    	/*String namecard = "";
		switch(this.cardName)
		{
			case "Attack":
				namecard = "+---+\nSlap\n+---+";
				break;
			case "Skip":
				namecard = "+---+\nSkip\n+---+";
				break;
			case "Super Skips":
				namecard = "+---+\nSuperS\n+---+";
				break;
			case "Swap Top And Bottom":
				namecard = "+---+\nSwapC\n+---+";
				break;
			case "Shuffle":
				namecard = "+---+\nShuff\n+---+";
				break;
			case "See the future":
				namecard = "+---+\nSeeTF\n+---+";
				break;
		}*/
    	return " [ " + this.cardName + " ] " ;
		//return namecard;
    }
}


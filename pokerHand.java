// FILE: pokerHand.java
// NAME: Thomas Loesch
// DATE: 01/05/15


public class pokerHand extends Hand {

	public pokerHand() { super(); }

	private String print() {
		String retval = "";
		for(int i = 0; i < getCardCount(); i++) retval += getCard(i).toString() + "\n";
		return retval;
	}
	
	private boolean hasOnePair() {
		int n = getCardCount();
		
		if(n < 2) return false;
		
		for(int i = 0; i < n - 1; i++)
			for(int j = n - 1; j > i; j--)
				if(getCard(i).getValue() == getCard(j).getValue())
					return true;
		
		return false;
	}
	
	private boolean isFlush() {
		Card temp = getCard(0);
		for(int i = 1; i < 5; i++)
			if(temp.getSuit() != getCard(i).getSuit())
				return false;
		
		return true;
	}
	
	public double bestHand() {
		// TODO - implement
		return 0.0;
	}
	
	public int isMiniFlush() {
		int suit, i;
		
		int temp0 = hand.get(0).getSuit();
		int temp1 = hand.get(1).getSuit();
		for(i = 2; i < 5; i++) {				// check to see if there are more than 2 different suits in the hand
			suit = hand.get(i).getSuit();
			if(suit != temp0) {
				if(temp0 == temp1)	temp1 = suit;
				else if(suit != temp1) return -1;
			}
		}
		
		if(temp0 == temp1) return -1;	// check to see if the hand is a full flush (there is only 1 suit)
		
		// NOTE - at this point there are 2 and only 2 different suits in the hand
		// this nested loop checks to see how many other cards of the same suit there are
		// for every card in hand
		suit = 0;
		for(i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(hand.get(i).getSuit() == hand.get(j).getSuit()) suit++;
			}
			if(suit == 1) return i;		// if a card only finds 1 other card of the same suit (itself), return that position
			suit = 0;
		}
		
		return -1; 						// otherwise, there wasn't a mini flush
	}
	
	public int isMiniStraight() {
		Hand tempHand = new Hand(); // create a temporary hand so we can reorder it without changing the actual hand
		int conflict = -1;			// tracks the index of a conflict
		int i;
		
		for(i = 0; i < this.getCardCount(); i++)	// fill tempHand with the cards in hand
			tempHand.addCard(hand.get(i));
		
		tempHand.sortByValue();
		
		for(i = 1; i < 5; i++){
			if( ( tempHand.getCard(i).getValue() != tempHand.getCard(i - 1).getValue() + 1 ) ){	// check for a conflict
				if ( i == 2 || i == 3 )									// if the conflict occurred in the middle, it isn't a mini flush
					return -1;
				else if( conflict == -1 )								// if there hasn't been a conflict yet, store the appropriate index in conflict
					if(i == 1)
						conflict = 0;
					else
						conflict = 4;
				else return -1;											// otherwise, there has been more than 1 conflict, so it isn't a mini flush
			}
		}
		
		if(conflict > -1) 	
			return hand.indexOf(tempHand.getCard(conflict));
		else
			return -1;	// if conflict didn't change, we know it was a flush, and therefore hand is not a mini flush
	}
}

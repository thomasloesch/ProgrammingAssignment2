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
	
	// returns the value of a four of a kind, if there is none it returns -1
	private int isFourOfKind(int[] valueAry) {
		for(int i = 0; i < 14; i++)
			if(valueAry[i] == 4)
				return i;
		
		return -1;
	}
	
	// Returns the values of a full house, if there is none it returns -1
	// Coded as: xxyy
	private int isFullHouse(int[] valueAry, int size) {
		int retval;
		
		Boolean checkFor2s = false;
		
		// Check to see if there is 3 of a kind and add it to the return value
		retval = isThreeOfKind(valueAry, size);
		if(retval > -1){
			retval *= 100;
			checkFor2s = true;
		}
		
		// If 3 of a kind was found, check for a pair, add it to the return value and then return it.
		if(checkFor2s){
			for(int i = 1; i < size; i++)
				if(valueAry[i] == 2)
					retval += i;
		}
		
		return retval;
	}
	
	// Returns the value of the highest card, if there is no straight it returns -1
	private int isStraight() {
		Hand tempHand = new Hand();
		int i;
		
		for(i = 0; i < this.getCardCount(); i++)	// fill tempHand with the cards in hand
			tempHand.addCard(hand.get(i));
		
		tempHand.sortByValue();
		
		// check for discrepancies
		for(i = 1; i < 5; i++)
			if( ( tempHand.getCard(i).getValue() != tempHand.getCard(i - 1).getValue() + 1 ) ) 
				return -1;
		
		return tempHand.getCard(4).getValue(); // return the highest card value
	}
	
	private int isThreeOfKind(int[] valueAry, int size) {
		for(int i = 1; i < size; i++)
			if(valueAry[i] == 3)
				return i;
		
		return -1;
	}
	
	// Returns the value of the highest value card of the given suit
	private int findHighestOfSuit(int suit) {
		int retval = -1;
		Card temp;
		
		for(int i = 0; i < 5; i++) {
			temp = hand.get(i);
			if(temp.getSuit() == suit){
				if(retval < temp.getValue())
					retval = temp.getValue();
			}
		}
		
		return retval;
	}
	
	public int bestHand() {
		int temp;
		final int VALUE_SIZE = 14;
		final int SUIT_SIZE = 4;
		int[] valueCount = new int[VALUE_SIZE];
		int[] suitCount = new int[SUIT_SIZE];
		
		for(int i = 0; i < 5; i++) {
			suitCount[hand.get(i).getSuit()]++; // populate the suitCount array
			valueCount[hand.get(i).getValue()]++; // populate the valueCount array
		}
		
		// Check for four of a kind. Coded as: 8xx00
		temp = isFourOfKind(valueCount);
		if(temp > -1)
			return 80000 + temp * 100;
		
		// Check for full house. Coded as: 7xxyy
		temp = isFullHouse(valueCount, VALUE_SIZE);
		if(temp > -1)
			return 70000 + temp;
		
		// Check for flush. Coded as: 6xx00
		if(isFlush())
			return 60000 + ( hand.get(0).getSuit() * 100 );
		
		// TODO - Check for straight 5xx00
		temp = isStraight();
		if(temp > -1)
			return 50000 + temp * 100;
		
		// TODO - Check for three of a kind 4xx
		
		// TODO - Check for two pair 3xxyy
		
		// TODO - Check for one pair 2xx
		
		// TODO - Check for high card 1xx
		return 0; // placeholder
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

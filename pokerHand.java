// FILE: pokerHand.java
// NAME: Thomas Loesch
// DATE: 01/05/15


public class pokerHand extends Hand {

	public pokerHand() { super(); }
	
	private boolean isFlush() {
		Card temp = getCard(0);
		
		for(int i = 1; i < 5; i++)
			if(temp.getSuit() != getCard(i).getSuit())
				return false;
		
		return true;
	}
	
	// returns the value of a four of a kind, if there is none it returns -1
	// Coded as: xx
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
		if(retval > -1) {
			retval *= 100;
			checkFor2s = true;
		}
		
		// If 3 of a kind was found, check for a pair, add it to the return value and then return it.
		if(checkFor2s) {
			for(int i = 1; i < size; i++)
				if(valueAry[i] == 2)
					return retval + i;
		}
		
		return -1;
	}
	
	// Returns the value of the highest card, if there is no straight it returns -1
	// Coded as: xx
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
	
	// Returns the value of the three of a kind, returns -1 if there is none
	// Coded as: xx
	private int isThreeOfKind(int[] valueAry, int size) {
		for(int i = 1; i < size; i++)
			if(valueAry[i] == 3)
				return i;
		
		return -1;
	}
	
	// Returns the value of the highest value card, returns -1 if the hand is empty
	// Coded as: xx
	private int findHighCard(int[] valueAry, int size) {
		for(int i = size - 1; i > 0; i--)
				if(valueAry[i] > 0)
					return i;
		
		return -1;
	}
	
	// Returns the values of the two pairs, returns -1 if there aren't 2 pairs
	// Coded as: xxyy
	private int isTwoPair(int[] valueAry, int size) {
		int first = 0, second = 0;
		
		// Check for pairs, then add that value to the appropriate variable
		for(int i = size - 1; i > 0; i--) {
			if(valueAry[i] == 2) {
				if(first == 0)
					first = i;
				else
					second = i;
			}
		}
		
		// If 2 pairs were found return them coded
		if(first > 0 && second > 0)
			return first * 100 + second;

		return -1;
	}
	
	// Returns the value of the highest pair, returns -1 if there are no pairs
	// Coded as: xx
	private int isPair(int[] valueAry, int size) {
		for(int i = size - 1; i > 0; i--)
			if(valueAry[i] == 2)
				return i;
		
		return -1;
	}
	
	// TODO - write comment
	public int bestHand() {
		int temp;
		final int VALUE_SIZE = 14;
		int[] valueCount = new int[VALUE_SIZE];

		for(int i = 0; i < 5; i++)
			valueCount[hand.get(i).getValue()]++; // populate the valueCount array
		
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
		
		// Check for straight. Coded as: 5xx00
		temp = isStraight();
		if(temp > -1)
			return 50000 + temp * 100;
		
		// Check for three of a kind. Coded as: 4xx00
		temp = isThreeOfKind(valueCount, VALUE_SIZE);
		if(temp > -1)
			return 40000 + temp * 100;
		
		// Check for two pair. Coded as: 3xxyy
		temp = isTwoPair(valueCount, VALUE_SIZE);
		if(temp > -1)
			return 30000 + temp;
		
		// Check for one pair. Coded as: 2xx00
		temp = isPair(valueCount, VALUE_SIZE);
		if(temp > -1)
			return 20000 + temp * 100;
		
		// Get the highest card. Coded as: 1xx00
		return 10000 + findHighCard(valueCount, VALUE_SIZE) * 100;
	}

	// TODO - write comment
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
	
	// TODO - write comment
	public int isMiniStraight() {
		Hand tempHand = new Hand(); // create a temporary hand so we can reorder it without changing the actual hand
		int conflict;
		int diff;			// tracks the index of a conflict
		int i;
		
		for(i = 0; i < this.getCardCount(); i++)	// fill tempHand with the cards in hand
			tempHand.addCard(hand.get(i));
		
		tempHand.sortByValue();
		
		for(int n = 0; i < 2; i++){
			diff = 1;
			conflict = 0;
			for(i = n + 1; i < 5; i++){
				if(tempHand.getCard(n).getValue() == tempHand.getCard(i).getValue() - diff)
					diff++;
				else if(conflict == -1)
					conflict = i;
				else
					return -1;
			}
		}
		
		/*if(conflict == 0 || conflict > 1) 	
			return 1;*/	// if conflict didn't change, we know it was a flush, and therefore hand is not a mini flush
		
		// IDEA - Push cards that don't belong in the hand to the front or back to isolate them
	}
}

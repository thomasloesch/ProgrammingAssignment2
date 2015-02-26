// FILE: pokerHand.java
// NAME: Thomas Loesch
// DATE: 01/05/15


public class pokerHand extends Hand {

	public pokerHand() { super(); }
	
	private void fillTempHand(Hand aHand){
		for(int i = 0; i < getCardCount(); i++)	// fill tempHand with the cards in hand
			aHand.addCard(hand.get(i));
	}
	
	private int isFlush() {
		Card temp = getCard(0);
		
		for(int i = 1; i < 5; i++)
			if(temp.getSuit() != getCard(i).getSuit())
				return -1;
		
		Hand tempHand = new Hand();
		
		fillTempHand(tempHand);
		tempHand.sortByValue();
		
		return tempHand.getCard(4).getValue();
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
		
		fillTempHand(tempHand);
		
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
	
	// Returns a code based on the current hand
	// The ten thousandth place shows what kind of hand it is
	// the other four places hold additional useful information based on the type of hand
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
		temp = isFlush();
		if(temp > -1)
			return 60000 + temp * 100;
		
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

	// Returns an integer signifying the location in hand where a card could be replaced to make a flush
	// Returns -1 if there is already a flush or there is no one card that could make it a flush
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
	
	// Returns an integer signifying the location in hand where a card could be replaced to make a straight
	// Returns -1 if there is already a straight or there is no one card that could make it a straight
	public int isMiniStraight() {
		Hand tempHand = new Hand(); 	// create a temporary hand so we can reorder it without changing the actual hand
		int[] values;					
		int diff, cardValue, count;
		int conflict = 0;
		
		fillTempHand(tempHand);
		
		tempHand.sortByValue();
		
		// main loop, first looks at the last 4 cards, then all 5
		for(int n = 1; n >= 0; n--) {						// BEGIN LOOP
			values = new int[5];							// reset the array
			values[n] = tempHand.getCard(n).getValue();		// seed the initial position
			count = 1;
			
			// nested loop, looks at the rest of the hand
			for(int i = n + 1; i < 5; i++) {				// BEGIN NESTED LOOP
				cardValue = tempHand.getCard(i).getValue();
				diff = cardValue - values[0];				// calculate the difference between the initial position and the current card
				
				if(diff < 5) {
					if(values[diff] == 0) {
						values[diff] = cardValue;			// as long as there is a space in the array, put it in the correct location
						count++;							// and increment count
					}
					else conflict = cardValue;				// otherwise save the value for later
				}
				else conflict = cardValue;
			}												// END NESTED LOOP
			
			if(count == 4)									// if there are 4 cards in the array
				for(int i = 0; i < 5; i++)					// check for the card in the actual hand that is causing the problem
					if(hand.get(i).getValue() == conflict)
						return i;
			else if(count == 5)								// if it is a straight, return -1
				return -1;
		}													// END LOOP
		
		return -1;											// must not have been a mini straight, return -1
	}
}

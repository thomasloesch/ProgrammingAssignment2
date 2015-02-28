// NAME: Thomas Loesch
// FILE: taltalPlayer.java
// DATE: 02/27/15

/* RULES
 * 1. If full house or higher, don't discard
 * 2. Check for mini-flush or mini-straight, and replace the problem card if there is one
 * 3. If there is a three of a kind, try for a full house
 * 4. If there is two pairs, try for a full house
 * 5. If there is a pair, try for a full house
 * 6. If there is only a high card, try for three of a kind
 * 7. The only exception to rule 6, is if the faceUpCards are a pair.
 *    In that case the AI will attempt to discard a card of the same value as that pair.
 */
public class taltalPlayer extends pokerPlayer {

	public taltalPlayer(int s, String N) {
		super(s, N);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int discard(int handCode, Hand faceUpCards) {
		if(handCode > 50000)	return 0; 	// Implements rule 1
		
		int temp;
		
		temp = hand.isMiniStraight();		// Implements rule 2
		if(temp > -1) {
			hand.removeCard(temp);
			return 1;
		}
		
		temp = hand.isMiniFlush();			// Implements rule 2
		if(temp > -1) {
			hand.removeCard(temp);
			return 1;
		}
		
		if(handCode > 40000) {				// Implements rule 3
			temp = ( handCode / 100 ) % 100;
			discardNotValue(temp, 2);
			return 2;
		}
		
		if(handCode > 30000) {				// Implements rule 4
			temp = ( handCode / 100 ) % 100;
			int temp2 = handCode % 100;
			discardNotValue(temp, temp2, 1);
			return 1;
		}
		
		if(handCode > 20000) {				// Implements rule 5
			temp = ( handCode / 100 ) % 100;
			hand.removeCard(findLowest(temp));
			hand.removeCard(findLowest(temp));
			return 2;
		}
											// Implements rule 6
		boolean discarded = false;
		if(faceUpCards.getCard(0).getValue() == faceUpCards.getCard(1).getValue())
			discarded = discardValue(faceUpCards.getCard(0).getValue());
		
		if(discarded)						// Implements rule 7
			hand.removeCard(findLowest());
		else {
			hand.removeCard(findLowest());
			hand.removeCard(findLowest());
		}
		
		return 2;
	}
	
	// Finds the lowest card that isn't the same value as not
	// Returns the index in hand of that card
	private int findLowest(int not){
		int temp = -1;
		
		for(int i = 1; i < hand.getCardCount(); i++) 
			if(temp < hand.getCard(i).getValue() && hand.getCard(i).getValue() != not)
				temp = i;
		
		for(int i = 0; i < hand.getCardCount(); i++) {
			if(hand.getCard(i).getValue() == temp) {
				temp = i;
				break;
			}
		}
		
		return temp;
	}
	
	// Finds the index of the lowest value card and returns it
	private int findLowest(){
		int temp = 0;
		
		for(int i = 1; i < hand.getCardCount(); i++) 
			if(hand.getCard(temp).getValue() < hand.getCard(i).getValue())
				temp = i;
		
		return temp;
	}

	// Discards the first count number of cards found with a different value than the passed variable value
	private void discardNotValue(int value, int count) {
		for(int i = 0; i < hand.getCardCount(); i++) {
			if(hand.getCard(i).getValue() != value) {
				hand.removeCard(i);
				count--;
			}
			if(count == 0) break;
		}
	}
	
	// Discards the first count number of cards found with a different value than either passed variable value
	private void discardNotValue(int value1, int value2, int count) {
		for(int i = 0; i < hand.getCardCount(); i++) {
			if(hand.getCard(i).getValue() != value1 && hand.getCard(i).getValue() != value2) {
				hand.removeCard(i);
				count--;
			}
			if(count == 0) break;
		}
	}
	
	// Discards a card with the same value as the passed variable
	private boolean discardValue(int value) {
		
		for(int i = 0; i < hand.getCardCount(); i++) {
			if(hand.getCard(i).getValue() == value) {
				hand.removeCard(i);
				return true;
			}
		}
		
		return false;
	}
}

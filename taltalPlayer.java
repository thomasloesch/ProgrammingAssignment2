/* RULES
 * 1. If full house or higher, don't discard
 * 2. Check for mini-flush or mini-straight, and replace the problem card if there is one
 * 3. If there is a three of a kind, try for a full house
 * 4. If there is two pairs, try for a full house
 * 5. If there is a pair, try for a full house
 * 6. If there is only a high card, try for three of a kind
 * 
 * 
 * 
 * 
 * 
 */


public class taltalPlayer extends pokerPlayer {

	public taltalPlayer(int s, String N) {
		super(s, N);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int discard(int handCode, Hand faceUpCards) {
		if(handCode > 50000)	return 0;
		
		int temp;
		
		temp = hand.isMiniStraight();
		if(temp > -1) {
			hand.removeCard(temp);
			return 1;
		}
		
		temp = hand.isMiniFlush();
		if(temp > -1) {
			hand.removeCard(temp);
			return 1;
		}
		
		if(handCode > 40000) {
			temp = ( handCode / 100 ) % 100;
			discardNotValue(temp, 2);
			return 2;
		}
		
		if(handCode > 30000) {
			temp = ( handCode / 100 ) % 100;
			int temp2 = handCode % 100;
			discardNotValue(temp, temp2, 1);
			return 1;
		}
		
		if(handCode > 20000) {
			temp = ( handCode / 100 ) % 100;
			hand.removeCard(findLowest(temp));
			hand.removeCard(findLowest(temp));
			return 2;
		}
		
		boolean discarded = false;
		if(faceUpCards.getCard(0).getValue() == faceUpCards.getCard(1).getValue())
			discarded = discardValue(faceUpCards.getCard(0).getValue());
		
		if(discarded)
			hand.removeCard(findLowest());
		else {
			hand.removeCard(findLowest());
			hand.removeCard(findLowest());
		}
		
		return 2;
	}
	
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
	
	private int findLowest(){
		int temp = 0;
		
		for(int i = 1; i < hand.getCardCount(); i++) 
			if(hand.getCard(temp).getValue() < hand.getCard(i).getValue())
				temp = i;
		
		return temp;
	}

	private void discardNotValue(int value, int count) {
		for(int i = 0; i < hand.getCardCount(); i++) {
			if(hand.getCard(i).getValue() != value) {
				hand.removeCard(i);
				count--;
			}
			if(count == 0) break;
		}
	}
	
	private void discardNotValue(int value1, int value2, int count) {
		for(int i = 0; i < hand.getCardCount(); i++) {
			if(hand.getCard(i).getValue() != value1 && hand.getCard(i).getValue() != value2) {
				hand.removeCard(i);
				count--;
			}
			if(count == 0) break;
		}
	}
	
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

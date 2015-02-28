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
			discardNotValue(( handCode / 100 ) % 100, 2);
			return 2;
		}
		
		if(handCode > 30000) {
			discardNotValue(( handCode / 100 ) % 100, handCode % 100, 1);
			return 1;
		}
		
		if(handCode > 20000) {
			discardNotValue(( handCode / 100 ) % 100, 2);
			return 2;
		}
		
		discardNotValue(( handCode / 100 ) % 100, 2);
		return 2;
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
	
	private void discardValue(int value, int count) {
		for(int i = 0; i < hand.getCardCount(); i++) {
			if(hand.getCard(i).getValue() == value) {
				hand.removeCard(i);
				count--;
			}
			if(count == 0) break;
		}
	}
}

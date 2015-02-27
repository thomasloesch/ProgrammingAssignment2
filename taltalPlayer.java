
public class taltalPlayer extends pokerPlayer {

	public taltalPlayer(int s, String N) {
		super(s, N);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int discard(double handCode, Hand faceUpCards) {
		int temp;
		
		for(int i = 0; i < 2; i++) {
			temp = 0;
			
			for(int j = 1; j < hand.getCardCount(); j++)
				if(hand.getCard(j).getValue() > hand.getCard(temp).getValue()) temp = j;
			
			hand.removeCard(temp);
		}
			
		
		return 2;
	}

}

public class humPlayer extends pokerPlayer {

	public humPlayer(int s, String N) {
		super(s, N);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int discard(double handCode, Hand faceUpCards) {
		int n = 0;
		int responseAry[];
		
		System.out.print("You may discard up to two cards.\nHow many cards would you like to discard:\t");
		n = TextIO.getInt();
		
		if(n > 0)
			for (int i = 0; i < hand.getCardCount(); i++)
				System.out.println( i + ":\t" + hand.getCard(i).toString());
			
		responseAry = new int[n];
		
		for(int i = 0; i < n; i++) {
			System.out.print("Which of these cards would you like to discard");
			responseAry[i] = TextIO.getInt();
		}
		
		if(responseAry.length == 2 && responseAry[0] > responseAry[1]) {
			int temp = responseAry[0];
			responseAry[0] = responseAry[1];
			responseAry[1] = temp;
		}
		
		for(int i = n - 1; i >= 0; i--)
			hand.removeCard(responseAry[i]);
		
		return n;
	}

}

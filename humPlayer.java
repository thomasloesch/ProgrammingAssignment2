// Name: Thomas Loesch
// File: humPlayer.java
// Date: 02/27/15

public class humPlayer extends pokerPlayer {

	public humPlayer(int s, String N) {
		super(s, N);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int discard(int handCode, Hand faceUpCards) {
		int n = 0;
		int responseAry[];
		
		// Asks the user how many cards to discard (up to 2)
		System.out.print("You may discard up to two cards.\nHow many cards would you like to discard:\t");
		n = TextIO.getInt();
		
		// Print out the hand with the index variables
		if(n > 0)
			for (int i = 0; i < hand.getCardCount(); i++)
				System.out.println( i + ":\t" + hand.getCard(i).toString());
			
		responseAry = new int[n];
		
		// Gets the cards the user would like to discard
		for(int i = 0; i < n; i++) {
			System.out.print("Which of these cards would you like to discard");
			responseAry[i] = TextIO.getInt();
		}
		
		// Make sure the values are sorted
		if(responseAry.length == 2 && responseAry[0] > responseAry[1]) {
			int temp = responseAry[0];
			responseAry[0] = responseAry[1];
			responseAry[1] = temp;
		}
		
		// Removes the cards specified
		for(int i = n - 1; i >= 0; i--)
			hand.removeCard(responseAry[i]);
		
		// return the number of cards removed
		return n;
	}

}

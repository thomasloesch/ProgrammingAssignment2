import java.util.Scanner;


public class humPlayer extends pokerPlayer {

	public humPlayer(int s, String N) {
		super(s, N);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int discard(double handCode, Hand faceUpCards) {
		int n = 0;
		int responseAry[];
		Scanner scan = new Scanner(System.in);
		
		System.out.print("You may discard up to two cards.\nHow many cards would you like to discard?\t");
		n = scan.nextInt();
		
		if(n > 0)
			for (int i = 0; i < hand.getCardCount(); i++)
				System.out.println( i + ":\t" + hand.getCard(i).toString());
			
		responseAry = new int[n];
		
		for(int i = 0; i < n; i++) {
			System.out.print("Which of these cards would you like to discard?\t");
			responseAry[i] = scan.nextInt();	
		}
		
		for(int i = n - 1; i >= 0; i--)
			hand.removeCard(responseAry[i]);
		
		scan.close();
		
		return n;
	}

}

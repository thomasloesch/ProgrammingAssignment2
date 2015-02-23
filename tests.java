
public class tests {
	
	private static void testCase(pokerHand hand, int v1, int s1, int v2, int s2, int v3, int s3, int v4, int s4, int v5,int s5){
		hand.clear();
		hand.addCard(new Card(v1, s1));
		hand.addCard(new Card(v2, s2));
		hand.addCard(new Card(v3, s3));
		hand.addCard(new Card(v4, s4));
		hand.addCard(new Card(v5, s5));	
		
		printHand(hand);
		System.out.println(hand.bestHand() + "\n");
	}
	
	private static void testRand(pokerHand hand, Deck aDeck, int target){
		int temp = 0;
		while(temp < target){
			hand.clear();
			aDeck.shuffle();
			
			for(int i = 0; i < 5; i++) 
				hand.addCard(aDeck.dealCard());
			
			temp = hand.bestHand();
		}
		
		printHand(hand);
		System.out.println(hand.bestHand() + "\n");
	}

	public static void testIsMiniStraight() {
		pokerHand testHand = new pokerHand();
		int retval;
		
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.CLUBS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(6, Card.CLUBS));
		testHand.addCard(new Card(7, Card.CLUBS));
		retval = testHand.isMiniStraight();
		printHand(testHand);
		System.out.println( retval );
		
		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(6, Card.CLUBS));
		testHand.addCard(new Card(7, Card.CLUBS));
		testHand.addCard(new Card(8, Card.CLUBS));
		retval = testHand.isMiniStraight();
		printHand(testHand);
		System.out.println( retval );

		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.CLUBS));
		testHand.addCard(new Card(6, Card.CLUBS));
		testHand.addCard(new Card(7, Card.CLUBS));
		testHand.addCard(new Card(8, Card.CLUBS));
		retval = testHand.isMiniStraight();
		printHand(testHand);
		System.out.println( retval );
		
		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.CLUBS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(7, Card.CLUBS));
		testHand.addCard(new Card(8, Card.CLUBS));
		retval = testHand.isMiniStraight();
		printHand(testHand);
		System.out.println( retval );
		
		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.CLUBS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(6, Card.CLUBS));
		testHand.addCard(new Card(8, Card.CLUBS));
		retval = testHand.isMiniStraight();
		printHand(testHand);
		System.out.println( retval );
	}

	private static void printHand(Hand testHand) {
		for(int i = 0; i < 5; i++)
			System.out.print( testHand.getCard(i) + ", " );
		System.out.println();
	}
	
	public static void testIsMiniFlush() {
		pokerHand testHand = new pokerHand();
		int retval;
		
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.CLUBS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(6, Card.CLUBS));
		testHand.addCard(new Card(7, Card.CLUBS));
		retval = testHand.isMiniFlush();
		printHand(testHand);
		System.out.println( retval );
		
		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.HEARTS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(6, Card.CLUBS));
		testHand.addCard(new Card(7, Card.CLUBS));
		retval = testHand.isMiniFlush();
		printHand(testHand);
		System.out.println( retval );
		
		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.HEARTS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(6, Card.HEARTS));
		testHand.addCard(new Card(7, Card.CLUBS));
		retval = testHand.isMiniFlush();
		printHand(testHand);
		System.out.println( retval );
		
		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.CLUBS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(6, Card.HEARTS));
		testHand.addCard(new Card(7, Card.CLUBS));
		retval = testHand.isMiniFlush();
		printHand(testHand);
		System.out.println( retval );
		
		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.CLUBS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(6, Card.CLUBS));
		testHand.addCard(new Card(7, Card.HEARTS));
		retval = testHand.isMiniFlush();
		printHand(testHand);
		System.out.println( retval );
	}

	public static void testBestHand(){
		pokerHand testHand = new pokerHand();
		Deck aDeck = new Deck(false);
		
		testRand(testHand, aDeck, 70000);
		testRand(testHand, aDeck, 70000);
		testRand(testHand, aDeck, 70000);
		testRand(testHand, aDeck, 70000);
		testRand(testHand, aDeck, 80000);
		testRand(testHand, aDeck, 80000);
		testRand(testHand, aDeck, 80000);
		testRand(testHand, aDeck, 80000);
	}
	
	public static void runTests() {
		//testIsMiniStraight();
		//testIsMiniFlush();
		testBestHand();
	}
}

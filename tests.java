
public class tests {
	
	private static void fillHand(Hand hand, int v1, int s1, int v2, int s2, int v3, int s3, int v4, int s4, int v5,int s5){
		hand.clear();
		hand.addCard(new Card(v1, s1));
		hand.addCard(new Card(v2, s2));
		hand.addCard(new Card(v3, s3));
		hand.addCard(new Card(v4, s4));
		hand.addCard(new Card(v5, s5));	
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
		for(int i = 0; i < 5; i++)
			System.out.println( testHand.getCard(i) );
		System.out.println( retval );
		
		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(6, Card.CLUBS));
		testHand.addCard(new Card(7, Card.CLUBS));
		testHand.addCard(new Card(8, Card.CLUBS));
		retval = testHand.isMiniStraight();
		for(int i = 0; i < 5; i++)
			System.out.println( testHand.getCard(i) );
		System.out.println( retval );

		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.CLUBS));
		testHand.addCard(new Card(6, Card.CLUBS));
		testHand.addCard(new Card(7, Card.CLUBS));
		testHand.addCard(new Card(8, Card.CLUBS));
		retval = testHand.isMiniStraight();
		for(int i = 0; i < 5; i++)
			System.out.println( testHand.getCard(i) );
		System.out.println( retval );
		
		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.CLUBS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(7, Card.CLUBS));
		testHand.addCard(new Card(8, Card.CLUBS));
		retval = testHand.isMiniStraight();
		for(int i = 0; i < 5; i++)
			System.out.println( testHand.getCard(i) );
		System.out.println( retval );
		
		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.CLUBS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(6, Card.CLUBS));
		testHand.addCard(new Card(8, Card.CLUBS));
		retval = testHand.isMiniStraight();
		for(int i = 0; i < 5; i++)
			System.out.println( testHand.getCard(i) );
		System.out.println( retval );
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
		for(int i = 0; i < 5; i++)
			System.out.println( testHand.getCard(i) );
		System.out.println( retval );
		
		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.HEARTS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(6, Card.CLUBS));
		testHand.addCard(new Card(7, Card.CLUBS));
		retval = testHand.isMiniFlush();
		for(int i = 0; i < 5; i++)
			System.out.println( testHand.getCard(i) );
		System.out.println( retval );
		
		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.HEARTS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(6, Card.HEARTS));
		testHand.addCard(new Card(7, Card.CLUBS));
		retval = testHand.isMiniFlush();
		for(int i = 0; i < 5; i++)
			System.out.println( testHand.getCard(i) );
		System.out.println( retval );
		
		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.CLUBS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(6, Card.HEARTS));
		testHand.addCard(new Card(7, Card.CLUBS));
		retval = testHand.isMiniFlush();
		for(int i = 0; i < 5; i++)
			System.out.println( testHand.getCard(i) );
		System.out.println( retval );
		
		testHand.clear();
		testHand.addCard(new Card(3, Card.CLUBS));
		testHand.addCard(new Card(4, Card.CLUBS));
		testHand.addCard(new Card(5, Card.CLUBS));
		testHand.addCard(new Card(6, Card.CLUBS));
		testHand.addCard(new Card(7, Card.HEARTS));
		retval = testHand.isMiniFlush();
		for(int i = 0; i < 5; i++)
			System.out.println( testHand.getCard(i) );
		System.out.println( retval );
	}

	public static void testBestHand(){
		pokerHand testHand = new pokerHand();
		
		fillHand(testHand, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0);
	}
	
	public static void runTests() {
		//testIsMiniStraight();
		//testIsMiniFlush();
		testBestHand();
	}
}

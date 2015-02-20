
public class tests {

	public static void testIsMiniStraight(){
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

	public static void runTests(){
		testIsMiniStraight();
		
	}
}

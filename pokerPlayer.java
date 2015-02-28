/* * parent class for human and AI pokerPlayers *  version 1.0, updated 2015/2/9 *   *  discard must be overridden in child classes */ abstract class pokerPlayer {		private int stake;	private String name;	protected pokerHand hand;		public pokerPlayer( int s, String N){		stake = s;  // for future work		name = N;		reset();	}	/**	 * Method reset	 * create a new empty hand	 */	public void reset() {		hand = new pokerHand();	}	/**	 * Method takeCard	 * Parameter: a Card 	 * Adds the passed Card to the player's hand	 */	public void takeCard(Card aCard) {		hand.addCard(aCard);	}	/**	 * Method getStake	 *	 * @return	 *	 */	public int getStake() {		return stake;	}	/**	 * Method setStake	 */	public void setStake(int val) {		stake = val;	}	/**	 * Method printHand	 * 	 * Print all the cards in the hand to the standard display	 */	public void printAll() {				int cc = hand.getCardCount();		for (int i = 0; i < cc; i++) {			System.out.println( hand.getCard(i).toString());		}	}	/**	 * Method printVisible	 * 	 * Print the initial dealt hand with only Face-Up Cards displayed	 */	public void printVisible() {				int cc = hand.getCardCount();		for (int i = 0; i < cc; i++) {			if ( i <=1)				System.out.println( hand.getCard(i).toString());			else				System.out.println( "***** Hidden *******");		}	}	/**	 * Method discard	 *	 * TODO: override in subclasses	 *       add appropriate logic for sub-class type	 *       may alter orginal ordering of cards	 * return # of cards discarded	 * handCode: the value of the hand 	 * faceUpCards:  the other players visible cards	 * @return	 */	public abstract int discard( int handCode, Hand faceUpCards);		/**	 * This method is Deprecated 
         * Method getFaceUpCard	 * Returns requested Card	 *	 * @return	 *	 */	public Card getFaceUpCard(int i) {	    if ( i <= 1) 		    return hand.getCard(i);		else 		    throw new RuntimeException("no peeking");	}		public String getName() {		return name;	}	/**	 * Method bestHand	 * 	 * Just an accessor function for the bestHand function of the hand data member	 */	public int bestHand() {		return hand.bestHand();	}	/* create and return a copy of the current hand	   allows processing of the hand without altering the original version/ordering	  */	public pokerHand getHand() {		pokerHand  phCopy = new pokerHand();				for (int i=0; i < hand.getCardCount(); i++) {			phCopy.addCard(hand.getCard(i));		}		return phCopy;	}}
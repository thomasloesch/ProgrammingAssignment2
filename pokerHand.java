// FILE: pokerHand.java
// NAME: Thomas Loesch
// DATE: 01/05/15

import java.util.Vector;

public class pokerHand extends Hand {

	public pokerHand() { super(); }

	private String print() {
		String retval = "";
		for(int i = 0; i < getCardCount(); i++) retval += getCard(i).toString() + "\n";
		return retval;
	}
	
	private boolean hasOnePair() {
		int n = getCardCount();
		
		if(n < 2) return false;
		
		for(int i = 0; i < n - 1; i++)
			for(int j = n - 1; j > i; j--)
				if(getCard(i).getValue() == getCard(j).getValue())
					return true;
		
		return false;
	}
	
	private boolean isFlush() {
		Card temp = getCard(0);
		for(int i = 1; i < 5; i++)
			if(temp.getSuit() != getCard(i).getSuit())
				return false;
		
		return true;
	}
	
	public double bestHand() {
		// TODO - implement
		return 0.0;
	}
	
	public int isMiniFlush() {
		// TODO - implement function without moving around the cards (create local copy of hand?)
		sortBySuit();
		
		if(hand.get(0).getSuit() == hand.get(4).getSuit()) return -1; // check to see if the hand is a full flush
		
		int suit;
		int temp0 = hand.get(0).getSuit();
		int temp1 = hand.get(1).getSuit();
		// check to see if there are more than 2 different suits in the hand
		for(int i = 2; i < 4; i++){
			suit = hand.get(i).getSuit();
			if(suit != temp0){
				if(temp0 == temp1)	temp1 = suit;
				else if(suit != temp1) return -1;
			}
		}
		
		// NOTE - at this point there are 2 and only 2 different suits in the hand
		
		return -1;
	}
	
	public int isMiniStraight() {
		// TODO - implement
		return 0;
	}
}

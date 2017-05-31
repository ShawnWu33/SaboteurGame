package module;

import java.util.ArrayList;

public abstract class Player implements Cloneable {
	public ArrayList<Card> hands;
	public int sickTurn;
	String identity;
	public Player(){
		hands = new ArrayList<>();
	}
	
	@Override 
	public Player clone(){
		try{
			final Player result = (Player)super.clone();
			result.hands = (ArrayList<Card>) hands.clone();
			result.identity = identity;
			result.sickTurn = sickTurn;
			return result;
		}
		catch (final CloneNotSupportedException e) {
			// TODO: handle exception
			throw new AssertionError();
		}
	}
	public ArrayList<Card> getHands() {
		return hands;
	}
	public void setHands(ArrayList<Card> hands) {
		this.hands = hands;
	}
	public int getSickTurn() {
		return sickTurn;
	}
	public void setSickTurn(int sickTurn) {
		this.sickTurn = sickTurn;
	}
	
	

}

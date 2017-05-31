package module;

import java.util.ArrayList;

import controller.ControllerInterface;

public class TurnManager {

	int round;
	ArrayList<Turn> turns;
	ControllerInterface controller;
	
	public TurnManager(int n){
		round =0;
		turns = new ArrayList<>();
	}
		
	public void addTurns(Turn t)
	{
		turns.add(t);
	}
	public ArrayList<Turn> getTurns()
	{
		return this.turns;
	}
	
}

package module;

import java.util.ArrayList;

public class Turn {

	ArrayList<Operation> operations;
	ArrayList<Player> playerStartState;
	
	public Turn(ArrayList<Player> players)
	{
		playerStartState = new ArrayList<>();
		operations = new ArrayList<>();
		for(int n = 0; n <players.size(); n++)
		{
			playerStartState.add(n, players.get(n).clone());
		}
	}
	
	public void addUserOperation(int n, Operation operation){
		operations.add(n, operation);
	}
	
	
}

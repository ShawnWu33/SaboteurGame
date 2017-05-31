package module;

import controller.ControllerInterface;
import java.util.Random;

public class FunctionCardBuilder implements CardBuilderInterface{
	@Override
	public Card build(ControllerInterface controller) {
		// TODO Auto-generated method stub
		Random random = new Random();
		int n = random.nextInt(4);
		if(n==0)
		{
			return new Card(controller, "0000", "See", true);
		}
		if(n==1){
			return new Card(controller, "0000", "Bomb", true);
		}
		if(n==2)
		{
			return new Card(controller, "0000", "Toxic_Spill", true);
		}else
		{
			return new Card(controller, "0000", "CleanUp_Toxic_Spill", true);
		}
		
	}
}

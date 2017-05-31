package module;
import java.util.Random;

import controller.ControllerInterface;

public class EndCardBuilder implements CardBuilderInterface {

	int n;
	int gold;
	public EndCardBuilder()
	{
		Random random = new Random();
		gold = random.nextInt(3);
		n = 0;
	}
	
	@Override
	public Card build(ControllerInterface controller) {
		// TODO Auto-generated method stub
		/*	
		for(int i = 0; i <3; i++)
		{
			System.out.println(i);
			if(i == gold)
			{
				Card aCard = new Card(controller, "", "Gold",(i*2),0);
			}
			else
			{
				Card bCard = new Card(controller, "", "Stone",(i*2),0);
			}
			
		}
		return null;
		*/
		Card a;
		if(n == gold)
		{
			a = new Card(controller, "1111", "Gold",false);
		}
		else
		{
			a= new Card(controller,"1111","Stone",false);
		}
		n++;
		return a;
	}
}


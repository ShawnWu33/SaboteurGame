package module;


import controller.ControllerInterface;
import java.util.Random;

public class PathCardBuilder implements CardBuilderInterface{

	
	public PathCardBuilder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Card build(ControllerInterface controller) {
		String path ="0000";
		while(true)
		{
		// TODO Auto-generated method stub
			Random random = new Random();
			int top = random.nextInt(2);
			int right = random.nextInt(2);
			int bottom = random.nextInt(2);
			int left = random.nextInt(2);
			path = ""+top+right+bottom+left;
			if(path.equals("0000")!= true)break;
		}
		return new Card(controller, path, "Path", true);
	}
	

}

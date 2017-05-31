package module;
import java.util.Random;

import controller.ControllerInterface;

public class CardBuildDirector {

	CardBuilderInterface pathCard, functionCard, endCard;
	Random random;
	ControllerInterface controller;
	
	public CardBuildDirector(ControllerInterface controller){
		this.controller =controller; 
		pathCard = new PathCardBuilder();
		functionCard = new FunctionCardBuilder();
		endCard = new EndCardBuilder();
		random = new Random(); 
	}
	
	public Card generateHand(){
		int i = random.nextInt(5);
		if(i <3)
		{
			return pathCard.build(controller);
		}
		return functionCard.build(controller);
	}
	
	public Card generateEnd(){
		return endCard.build(controller);
	}
}

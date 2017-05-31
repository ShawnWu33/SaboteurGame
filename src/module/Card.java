package module;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import javax.imageio.ImageIO;

import controller.ControllerInterface;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;




/**
 * Created by dukunwei on 1/4/17.
 */
public class Card extends StackPane {

	public ControllerInterface controller;
    private double mouseX, mouseY;
    private double oldX, oldY;
    private BufferedImage bufferedimage = null;
    public boolean dragable;
    private ArrayList<Boolean> isPath; //index 0 stands for top, 1 stands for right, 2 stands for bottom, 3 stands for left
    public String function;
	private ImageView imageView;
    private int currentTileX;
    private int currentTileY;

    public Card(){
    	function ="null";
    	isPath = new ArrayList<Boolean>(10);
    	for(int n = 0; n <4; n++)
    	{
    		isPath.add(false);
    	}
    }
    
    public Card(ControllerInterface controller, String path, String function, boolean d)//for build card in player's hand
    {
    	isPath = new ArrayList<Boolean>(10);
    	dragable = d;
    	this.function = function;
    	if(function.equals("Path"))
    	{
    		
    		for(int n = 0; n <=2; n++)
    		{
    			if(path.substring(n, n+1).equals("1"))
    				isPath.add(n, true);
    			else
    				isPath.add(n,false);
    		}
    		if(path.substring(3).equals("1")){
    			isPath.add(3,true);
    		}
    		else
    		{
    			isPath.add(3,false);
    		}
    	}
    	else
    	{
    		if(function.equals("Start") || function.equals("Stone") || function.equals("Gold") || function.equals("Case"));
    		{
    			for(int n =0 ; n < 4; n++)
    			{
    				isPath.add(n,true);
    			}
    		}
    	}
        renewImage();
        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
            System.out.println("mouse:("+mouseX+", "+mouseY+")");
            System.out.println("old:("+oldX+", "+oldY+")");
        });
        //setOnMouseClicked(e -> rotate());

        setOnMouseDragged(e -> {
    		if(dragable)
    		relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
    	});
        
        setOnMouseReleased(e ->
        {
        	if(dragable)
        	{
        		System.out.println("Released!");
            	if(e.getSceneY() - mouseY + oldY >= 560 && e.getSceneY() - mouseX + oldX <= 350)
            	{
            		abortMove();
            		rotate();
            	}
            	else
            	{
            		controller.update(this, e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
            	}
        	}
        	
        });
    }
	public Card(ControllerInterface controller, String path, String function, int x, int y, boolean d){
    	isPath = new ArrayList<Boolean>(10);
    	dragable = d;
    	this.function = function;
    	if(function.equals("Path"))
    	{
    		for(int n = 0; n <=2; n++)
    		{
    			if(path.substring(n, n+1).equals("1"))
    				isPath.add(n, true);
    			else
    				isPath.add(n,false);
    		}
    		if(path.substring(3).equals("1")){
    			isPath.add(3,true);
    		}
    		else
    		{
    			isPath.add(3,false);
    		}
    	}
    	else
    	{
    		if(function.equals("Start") || function.equals("Stone") || function.equals("Gold") || function.equals("Case"))
    		{
    			dragable = false;
    			System.out.println(function);
    			for(int n =0 ; n < 4; n++)
    			{
    				isPath.add(n,true);
    			}
    		}
    	}
        renewImage();
        controller.update(this, x, y);
        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });
        //setOnMouseClicked(e -> rotate());

        {
        	setOnMouseDragged(e -> {
        		if(dragable)
        			relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
        	});
            
            setOnMouseReleased(e ->
            {
            	if(dragable)
            	{
            		System.out.println("Released!");
                	if(e.getSceneY() - mouseY + oldY >= 560)
                	{
                		rotate();
                	}
                	controller.update(this, e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
            	}
            	
            	
            });
        }
    }
    
    

    public String getCardType() {
        return function;
    }
    
    public void rotate(){
        if(function.equals("Path"))
        {
        	boolean temp = isPath.get(3);
            isPath.remove(3);
            isPath.add(0,temp);
            renewImage();
        }
    }

    public BufferedImage getBufferedImage() {
        return bufferedimage;
    }

    public void renewImage(){
        String fileName;
        fileName="src/Image/";
        if(function.equals("Path"))
        {
            for(int n = 0; n <= 3; n ++)
            {
                if(isPath.get(n)==true)
                {
                    fileName+="1";
                }
                else
                {
                    fileName+="0";
                }
            }
        }
        else
        {
            fileName+=function;
        }
        fileName += ".jpg";
        //System.out.println(fileName);
        File file = new File(fileName);
        try {
            bufferedimage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedimage, null);
        imageView = new ImageView();
        imageView.setImage(image);
        getChildren().add(imageView);
    }
    
    public void setBufferedimage(BufferedImage bufferedimage) {
        this.bufferedimage = bufferedimage;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public void move(int x, int y) {
    	
        oldX = x * 70;
        oldY = y * 70;
        relocate(oldX, oldY);
        //System.out.println("relocated!");
        currentTileX = x;
        currentTileY = y;

    }
    
    public boolean isTop(){
    	if(isPath.get(0))
    		return true;
    	return false;
    }
    
    public boolean isRight(){
    	if(isPath.get(1))
    		return true;
    	return false;
    }
    
    public boolean isBottom(){
    	if(isPath.get(2))
    		return true;
    	return false;
    }
    
    public boolean isLeft(){
    	if(isPath.get(3))
    		return true;
    	return false;
    }

    public void abortMove() {
    	System.out.println("Move Aborted");
        relocate(oldX, oldY);
    }
    
    public int getCurrentTileX() {
		return currentTileX;
	}



	public void setCurrentTileX(int currentTileX) {
		this.currentTileX = currentTileX;
	}



	public int getCurrentTileY() {
		return currentTileY;
	}



	public void setCurrentTileY(int currentTileY) {
		this.currentTileY = currentTileY;
	}
	
	public String getFunction() {
		return function;
	}


}
package controller;


import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.Node;
import module.*;
import view.BoardView;

public class Controller  extends Application implements ControllerInterface {
    public static Group tileGroup = new Group();
    public static Group pieceGroup = new Group();
    public static final int TILE_SIZE = 70;
    public static final int WIDTH = 5;
    public static final int HEIGHT = 10;
    public static TextArea text = null;
    public int numberOfPlayer;
    public static String theme = "";
    public Card[][] map;
    
    public ArrayList<Player> players;
    public Turn currentTurn;
    public int currentPlayer;
    public TurnManager turnManager;
    public CardBuildDirector cardBuildDirector;
    public Card[] endCases;
    public static Tile[][] board ;
    public static BoardView boardView;
    private BorderPane rootLayout;
    private Stage primaryStage;
    
	public void closeBoardView(){}

	public void startGame(int numberOfPlayer, String theme) {
		// TODO Auto-generated method stub
		
	}
	public void update(String action) {
		// TODO Auto-generated method stub
		System.out.println(action);
		if(action.equals("Main"))
			System.exit(0);
	}
	
	public void update(Card card, int x, int y){//This API only be used when system generate the Card
		pieceGroup.getChildren().add(card);
		card.move(x, y);
		if(card.getFunction().equals("Case")!=true)
		map[x][y] = card;
		//boardView.show_card(card);
		//System.out.println("Put on board");
	}
	
	public void update(Card card, double x, double y){//This API only be used when player move the Card
		System.out.println("hello");
		if(x>350)//throw this card
		{
			
			pieceGroup.getChildren().remove(card);
			userOperate(players.get(currentPlayer), card, x, y);
		}
		else
		{
			if(card.function.equals("Path"))
			{
				if(checkPathCardSuitability(card, (int)x/70, (int)y/70))
				{
					card.move((int)x/70, (int)y/70);
					map[(int)x/70][(int)y/70] = card;
					userOperate(players.get(currentPlayer), card, x, y);
				}
				else
				{
					System.out.println("Invaild Action");
					card.abortMove();
				}
			}
			else if(card.function.equals("See"))
			{
				if(((int)x/70 == 0 || (int)x/70 == 2 || (int)x/70 == 4) && ((int) y/70 == 0))
				{
					card.move((int)x/70, (int)y/70);
					pieceGroup.getChildren().remove(endCases[(int)x/140]);
					/*
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						long l = 2000;
						wait(l);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
					//pieceGroup.getChildren().add(endCases[(int)x/140]);
					pieceGroup.getChildren().remove(card);
					userOperate(players.get(currentPlayer), card, x, y);
				}
				else
				{
					System.out.println("Invaild Action");
					card.abortMove();
				}
			}
			/*
			else if(card.function.equals("Bomb"))
			{
				if()
				{
					
				}
				else
				{
					System.out.println("Invaild Action");
					card.abortMove();
				}
			}
			*/
		}
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Saboteur");
        showGameStage();	
	}
	
    public void setUp(){
    	numberOfPlayer = 3;
    	cardBuildDirector = new CardBuildDirector(this);
    	turnManager = new TurnManager(numberOfPlayer);
    	//generate all player
    	players = new ArrayList<>();
    	players.add(new Saboteur());
    	for(int n = 0; n < numberOfPlayer-1;n++) 
    	{
    		players.add(new Worker());
    	}
    	//give all player cards
    	for(Player p : players)
    	{
    		for(int n = 0; n < 5; n++)
    		{
    			p.getHands().add(cardBuildDirector.generateHand());
    		}
    	}
    	//Generate Ends
    	endCases = new Card[3];
    	for(int n =0; n<3;n++)
    	{
    		Card a = cardBuildDirector.generateEnd();
    		update(a, n*2, 0);
    		Card endCase = new Card(this, "1111", "Case", (n*2), 0,false);
    		endCases[n] = endCase;
    	}
    	Card start = new Card(this, "1111", "Start", 2, 7, false);
    	
    	
    	
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    public void showGameStage() {
    	boardView=new BoardView(this);
    	Scene scene = new Scene(boardView.createContent());
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    
	public void grid_generate() {
		for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile(x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);
            }
        }
	}
	
	public void generateCardMap(){
		map= new Card[5][10]; 
		for(int i=0; i<5; i++){
			for(int j=0; j<10;j++)
			{
				map[i][j] = new Card();
			}
		}
		
	}
	public void newRound(){
		currentTurn = new Turn(players);
		currentPlayer = 0;	
		for(int n = 0; n < 5; n++)
		{
			update(players.get(currentPlayer).getHands().get(n), n, 8);
		}
	}
	
	public void userOperate(Player player, Card card, double x, double y){
		Operation operation = new Operation(player, card, x, y);
		currentTurn.addUserOperation(currentPlayer, operation);
		player.getHands().remove(card);
		for(Card c : players.get(currentPlayer).getHands())
		{
			pieceGroup.getChildren().remove(c);
		}
		player.getHands().add(cardBuildDirector.generateHand());
		currentPlayer++;
		if(currentPlayer == numberOfPlayer)//all player done
		{
			turnManager.addTurns(currentTurn);
			System.out.println("New Round");
			newRound();
		}
		else
		{
			for(int n = 0; n < 5; n++)
			{
				update(players.get(currentPlayer).getHands().get(n), n, 8);
			}
			System.out.println("Player "+currentPlayer);
		}
	}
	
	
	public boolean checkPathCardSuitability(Card card, int x, int y)//check if card suitable for the tile
	{
		int numberOfEmpytyTile = 0;
		if(y>0) 
		{
			if(map[x][y-1].function.equals("null") == true)
			{
				numberOfEmpytyTile ++;
			}
		}
		else
			numberOfEmpytyTile++;
		
		if(y<7)
		{
			if(map[x][y+1].function.equals("null") == true)
			{
				numberOfEmpytyTile ++;
			}
		}
		else
			numberOfEmpytyTile++;
		
		if(x<4)
		{
			if(map[x+1][y].function.equals("null") == true)
			{
				numberOfEmpytyTile ++;
			}
		
		}
		else
			numberOfEmpytyTile++;
		
		if(x>0)
		{
			if(map[x-1][y].function.equals("null") == true)
			{
				numberOfEmpytyTile++;
			}
		}
		else
			numberOfEmpytyTile++;
		
		if(numberOfEmpytyTile == 4)
		{
			System.out.println("No adjacant tiles");
			return false;
		}
		
		
		if(y>0){
			if(map[x][y-1].function.equals("null") == false && map[x][y-1].isBottom() != card.isTop())
			{
				System.out.println("Up path doesn't match");
				return false;
			}
		}
		if( y<7){
			if(map[x][y+1].function.equals("null") == false && map[x][y+1].isTop() != card.isBottom())
			{
				System.out.println("Bottom path doesn't match");
				return false;
			}
		}
		if( x<4){
			if(map[x+1][y].function.equals("null") == false && map[x+1][y].isLeft() != card.isRight())
			{
				System.out.println("Right path doesn't match");
				return false;
			}
		}
		if( x>0){
			if(map[x-1][y].function.equals("null") == false && map[x-1][y].isRight() != card.isLeft())
			{
				System.out.println("Left path doesn't match");
				return false;
			}
		}
		return true;
	}


    
	@Override
	public void gameBegin() {
		// TODO Auto-generated method stub
		generateCardMap();
		setUp();
		newRound();
		//Card aCard = new Card(this, "0101", "null");
		//update(aCard, 2, 5);
		
		
	}

	

}

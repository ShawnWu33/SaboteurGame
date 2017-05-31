package controller;

import javafx.scene.Group;
import javafx.scene.control.TextArea;
import module.Card;
import module.Tile;

public interface ControllerInterface {
    public  Group tileGroup = new Group();
    public  Group pieceGroup = new Group();
    public  final int TILE_SIZE = 70;
    public  final int WIDTH = 5;
    public  final int HEIGHT = 10;
    public  TextArea text = null;

    public static Tile[][] board = new Tile[WIDTH][HEIGHT];
    public void gameBegin();
    public void closeBoardView();
    public void update(String action);//The string stands for the button been pressed
    public void startGame(int numberOfPlayer, String theme);
	public void update(Card card, int x, int y);
	public void update(Card card, double x, double y);//For drag and release function
	public void grid_generate();





}

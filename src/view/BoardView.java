package view;


import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import module.Card;
import module.Tile;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

import static controller.Controller.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import controller.Controller;
import controller.ControllerInterface;


/**
 * Created by dukunwei on 30/3/17.
 */
public class BoardView {
	
    public static int Surrender=0;
    public static Label timerLabel = new Label();
    public static Timeline timeline = new Timeline();
    private ControllerInterface controller;
    private Tile tile;
    private  Button button_undo;
    private  Button button_surrender;
    private  Button button_main;
    private BorderPane root = new BorderPane();
    private BorderPane message = new BorderPane();
    private BorderPane buttonList=new BorderPane();
    private BorderPane timer=new BorderPane();
    
    public BoardView(ControllerInterface c)
    {
    	controller = c;
    }

    public Parent createContent() {

        message.setPrefSize(WIDTH * TILE_SIZE, 5*HEIGHT);
        Pane mid = new Pane();
        buttonList.setPrefSize(WIDTH * TILE_SIZE, HEIGHT);

        root.setPrefSize((WIDTH+1) * TILE_SIZE, HEIGHT * TILE_SIZE+2.5*TILE_SIZE);

        
        
        timer.setPrefSize(TILE_SIZE,TILE_SIZE);
        root.setRight(timer);
        root.setCenter(mid);
        message.setBottom(buttonList);
        root.setTop(message);

        timer.setTop(timerLabel);
        TextField textField=new TextField();
        textField.setPrefSize(150, 70);

        mid.getChildren().addAll(tileGroup, pieceGroup);

        button_undo=new Button("Undo");
        button_surrender=new Button("Surrender");
        button_main=new Button("Main menu");

        button_undo.setPrefSize(100,20);
        button_surrender.setPrefSize(100,20);
        button_main.setPrefSize(100,20);

        buttonList.setLeft(button_undo);
        buttonList.setCenter(button_surrender);
        buttonList.setRight(button_main);

        board = new Tile[WIDTH][HEIGHT];
        controller.grid_generate();
        button_actions(controller);
        controller.gameBegin();
        
        

        return root;
    }



	public void show_card(Card card) {
        pieceGroup.getChildren().add(card);
		
	}



	private void button_actions(ControllerInterface controller) {
		
		button_main.setOnAction((javafx.event.ActionEvent e) ->{
        	controller.update("Main");
        });
        
		
		button_undo.setOnAction((javafx.event.ActionEvent e) ->{
			controller.update("Undo");
        });
        
        button_surrender.setOnAction((javafx.event.ActionEvent e) ->{
        	controller.update("Surrender");
        });
		
	}



}


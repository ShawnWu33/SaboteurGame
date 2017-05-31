package module;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.Objects;

import controller.Controller;



/**
 * Created by dukunwei on 1/4/17.
 */
public class Tile extends Rectangle implements TileInterface {
    private Card card;

    public boolean hasPiece() {
        return card != null;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Tile( int x, int y) {
        setWidth(Controller.TILE_SIZE);
        setHeight(Controller.TILE_SIZE);

        relocate(x * Controller.TILE_SIZE, y * Controller.TILE_SIZE);

        setStroke(Color.WHITE);
        if(Controller.theme.equals("Emoji theme")) {
            setFill(Color.LIGHTGRAY);
        }else{
            setFill(Color.GHOSTWHITE);
        }
    }
}

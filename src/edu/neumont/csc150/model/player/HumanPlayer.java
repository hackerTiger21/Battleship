package edu.neumont.csc150.model.player;

import edu.neumont.csc150.model.PlayerSurrenderedException;
import edu.neumont.csc150.model.Point;

import java.io.IOException;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        setName(name);
        setHuman(true);
    }

    @Override
    public Point takeTurn(int[][] enemyBoard) throws PlayerSurrenderedException, IOException {
        if (!ui.doesPlayerAttack()) throw new PlayerSurrenderedException("I surrender"); //player has surrendered

        ui.displayBoard(enemyBoard, true);
        return ui.askTarget();
    }
}

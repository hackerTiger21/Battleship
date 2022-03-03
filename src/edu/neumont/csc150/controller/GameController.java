package edu.neumont.csc150.controller;

import edu.neumont.csc150.model.Board;
import edu.neumont.csc150.view.GameUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameController {
    private GameUI ui = new GameUI();
    private Board board = new Board();

    public void run() throws IOException {
        int options = 0;
        while(options != -1){
            ui.displayMenu();
            options = ui.getIntFromUser(1,3);
        }
    }
}

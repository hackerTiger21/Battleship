package edu.neumont.csc150.controller;

import edu.neumont.csc150.model.Board;
import edu.neumont.csc150.view.GameUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameController {
    private GameUI ui = new GameUI();
    private Board board = new Board();

    public void run() {
        boolean keepRunning = true;
        do{
            try {
                ui.displayMenu();
                int selection = ui.getUserInputAsInt(1,3);
                switch (selection){
                    case 1: //human v human
                        break;
                    case 2: //human v bot
                        break;
                    case 3: //exit
                        keepRunning = false;
                        break;
                }
            } catch (IOException io){
                ui.displayIOError();
            }
        }while (keepRunning);
    }
}

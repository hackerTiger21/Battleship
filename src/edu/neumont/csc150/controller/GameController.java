package edu.neumont.csc150.controller;

import edu.neumont.csc150.Human.Bot;
import edu.neumont.csc150.Human.Player;
import edu.neumont.csc150.model.Board;
import edu.neumont.csc150.view.GameUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


public class GameController {
    private GameUI ui = new GameUI();
    private Board board = new Board();

    public void run() throws IOException {
        BufferedReader buffed = new BufferedReader(new InputStreamReader(System.in));
        String name = "";
        Random rand = new Random();
        int options = 0;
        while (options != -1) {
            ui.displayMenu();
            options = ui.getUserInputAsInt(1, 3);
            switch (options) {
                case 1:
                    System.out.println("Enter Player 1's name: ");
                    name = buffed.readLine();
                    Player h1 = new Player(name);
                    System.out.println("Enter bot 1's name: ");
                    name = buffed.readLine();
                    Player h2 = new Player(name);
                    board = new Board(h1, h2);
                    int num = rand.nextInt(1) + 1;

                    break;
                case 2:
                    System.out.println("Enter Player 1's name: ");
                    name = buffed.readLine();
                    Player h3 = new Player(name);
                    System.out.println("Enter Player 2's name: ");
                    name = buffed.readLine();
                    Bot b1 = new Bot(name);
                    board = new Board(h3, b1);
                    int numb = rand.nextInt(1) + 1;
                    break;
                case 3:
                    System.out.println("Enter Bot 1's name: ");
                    name = buffed.readLine();
                    Bot b2 = new Bot(name);
                    System.out.println("Enter Bot 2's name: ");
                    name = buffed.readLine();
                    Bot b3 = new Bot(name);
                    board = new Board(b2, b3);
                    int number = rand.nextInt(1) + 1;
                    break;
            }
        }
    }
}
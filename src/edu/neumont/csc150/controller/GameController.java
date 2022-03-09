package edu.neumont.csc150.controller;

import edu.neumont.csc150.model.Board;
import edu.neumont.csc150.model.Point;
import edu.neumont.csc150.model.ShipType;
import edu.neumont.csc150.model.player.BotPlayer;
import edu.neumont.csc150.model.player.HumanPlayer;
import edu.neumont.csc150.model.player.Player;
import edu.neumont.csc150.model.ship.*;
import edu.neumont.csc150.view.GameUI;

import java.io.IOException;

public class GameController {
    private GameUI ui = new GameUI();
    private Board boardOne = new Board();
    private Board boardTwo = new Board();
    private Player playerOne;
    private Player playerTwo;
    private int gameMode;

    public void run() {
        boolean keepRunning = true;
        do{
            try{
                ui.displayMenu();
                int selection = ui.getUserInputAsInt(1,3);
                switch (selection){
                    case 1: //player vs player
                        initializeGame(true,true);
                        break;
                    case 2: //player vs bot
                        ui.displayUnderConstruction();
                        //initializeGame(true, false);
                        break;
                    case 3: //end game
                        keepRunning = false;
                        break;
                }
            } catch (IOException io){
                ui.displayIOError();
            }
        } while (keepRunning);
    }

    private void initializeGame(boolean playerOneHuman, boolean playerTwoHuman) throws IOException {
        createPlayers(playerOneHuman, playerTwoHuman);
        placeShips();
        setGameMode();
        startGame();
    }

    private void placeShips() throws IOException {
        initializeBoards();
        int[][] workingBoard;

        ShipType[] types = ShipType.values();
        String[] typeStrings = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            typeStrings[i] = types[i].name();
        }

        workingBoard = boardOne.getBoard();
        for (int i = 0; i < 5; i++) {
            Point startingPoint;
            Point[] points;
            String workingType = typeStrings[i];
            switch (workingType){
                case "Carrier":
                    points = new Point[Carrier.getLength()];
                    if (ui.askIfShipVertical(workingType)){

                    } else {

                    }
                    break;
                case "Battleship":
                    points = new Point[Battleship.getLength()];
                    if (ui.askIfShipVertical(workingType)){

                    } else {

                    }
                    break;
                case "Cruiser":
                    points = new Point[Cruiser.getLength()];
                    if (ui.askIfShipVertical(workingType)){

                    } else {

                    }
                    break;
                case "Submarine":
                    points = new Point[Submarine.getLength()];
                    if (ui.askIfShipVertical(workingType)){

                    } else {

                    }
                    break;
                case "Destroyer":
                    points = new Point[Destroyer.getLength()];
                    if (ui.askIfShipVertical(workingType)){

                    } else {

                    }
                    break;
            }
        }
    }

    private void initializeBoards(){ //sets both boards to empty water
        int[][] clearBoard = new int[10][10];
        for (int i = 0; i < boardOne.getBoard().length; i++) {
            for (int j = 0; j < boardOne.getBoard()[0].length; j++) {
                clearBoard[i][j] = 0;
            }
        }
        boardOne.setBoard(clearBoard);
        boardTwo.setBoard(clearBoard);
    }

    private void setGameMode() throws IOException {
        ui.askGameModeMenu();
        gameMode = ui.getUserInputAsInt(1,3);
    }

    private void startGame() {
        switch (gameMode){
            case 1: //one shot
                oneShotGame();
                break;
            case 2: //artillery
                artilleryGame();
                break;
            case 3: //salvo
                salvoGame();
                break;
        }
    }

    private void oneShotGame() {
        boolean playerOneTurn = true;
        boolean gameOver = false;
        do{
            if (playerOneTurn){

            }
        } while (!gameOver);
    }

    private void artilleryGame() {
        boolean playerOneTurn = true;
        boolean gameOver = false;
        do{

        } while (!gameOver);
    }

    private void salvoGame() {
        boolean playerOneTurn = true;
        boolean gameOver = false;
        do{

        } while (!gameOver);
    }

    private void createPlayers(boolean playerOneHuman, boolean playerTwoHuman) throws IOException {
        playerOne = playerOneHuman ? new HumanPlayer(ui.askName(true)) : new BotPlayer(ui.askName(true));
        playerTwo = playerTwoHuman ? new HumanPlayer(ui.askName(false)) : new BotPlayer(ui.askName(false));
    }

    private void displayBoard(boolean playerOne, boolean isOpponent){
        ui.displayBoard(playerOne ? boardOne.getBoard():boardTwo.getBoard(), isOpponent);
    }
}

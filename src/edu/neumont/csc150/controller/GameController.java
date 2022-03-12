package edu.neumont.csc150.controller;

import edu.neumont.csc150.model.Board;
import edu.neumont.csc150.model.PlayerSurrenderedException;
import edu.neumont.csc150.model.Point;
import edu.neumont.csc150.model.ShotType;
import edu.neumont.csc150.model.player.BotPlayer;
import edu.neumont.csc150.model.player.HumanPlayer;
import edu.neumont.csc150.model.player.Player;
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
        do {
            try {
                ui.displayMenu();
                int selection = ui.getUserInputAsInt(1, 3);
                switch (selection) {
                    case 1: //player vs player
                        initializeGame(true, true);
                        break;
                    case 2: //player vs bot
                        ui.displayUnderConstruction();
                        //initializeGame(true, false);
                        break;
                    case 3: //end game
                        keepRunning = false;
                        break;
                }
            } catch (IOException io) {
                ui.displayIOError();
            }
        } while (keepRunning);
    }

    private void initializeGame(boolean playerOneHuman, boolean playerTwoHuman) throws IOException {
        createPlayers(playerOneHuman,playerTwoHuman);
        setGameMode();
        playerOne.placeShips();
        ui.switchPlayers();
        playerTwo.placeShips();
        ui.switchPlayers();
        startGame();
    }

    private void setGameMode() throws IOException {
        ui.askGameModeMenu();
        gameMode = ui.getUserInputAsInt(1, 3);
    }

    private void startGame() throws IOException {
        switch (gameMode) {
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

    private void oneShotGame() throws IOException{
        boolean playerOneTurn = true;
        boolean switchTurn;
        boolean gameOver = false;
        do {
            switchTurn = true;
            if (playerOneTurn) {
                try {
                    Point target = playerOne.takeTurn(playerTwo.getPlayerBoard().getBoard());
                    ShotType shotType = playerTwo.receiveShot(target);
                    playerOne.displayShotResult(shotType);
                    if (shotType.equals(ShotType.Invalid)) switchTurn = false;
                    playerTwo.checkForLoss();
                    if (playerTwo.hasPlayerLost()) throw new PlayerSurrenderedException("Player One wins");
                } catch (PlayerSurrenderedException ex){
                    ui.displayWin(playerOne.getName());
                    gameOver = true;
                }
            } else {
                try {
                    Point target = playerTwo.takeTurn(playerOne.getPlayerBoard().getBoard());
                    ShotType shotType = playerOne.receiveShot(target);
                    playerTwo.displayShotResult(shotType);
                    if (shotType.equals(ShotType.Invalid)) switchTurn = false;
                    playerOne.checkForLoss();
                    if (playerOne.hasPlayerLost()) throw new PlayerSurrenderedException("Player Two Wins");
                } catch (PlayerSurrenderedException ex){
                    ui.displayWin(playerTwo.getName());
                    gameOver = true;
                }
            }
            if (switchTurn) {
                playerOneTurn = !playerOneTurn;
                ui.switchPlayers();
            }
        } while (!gameOver);
    }

    private void artilleryGame() throws IOException {
        boolean playerOneTurn = true;
        boolean switchTurn;
        boolean gameOver = false;
        do {
            switchTurn = true;
            if (playerOneTurn) {
                try {
                    Point target = playerOne.takeTurn(playerTwo.getPlayerBoard().getBoard());
                    ShotType shotType = playerTwo.receiveShot(target);
                    playerOne.displayShotResult(shotType);
                    if (shotType.equals(ShotType.Invalid) || shotType.equals(ShotType.Hit)) switchTurn = false;
                    playerTwo.checkForLoss();
                    if (playerTwo.hasPlayerLost()) throw new PlayerSurrenderedException("Player One wins");
                } catch (PlayerSurrenderedException ex){
                    ui.displayWin(playerOne.getName());
                    gameOver = true;
                }
            } else {
                try {
                    Point target = playerTwo.takeTurn(playerOne.getPlayerBoard().getBoard());
                    ShotType shotType = playerOne.receiveShot(target);
                    playerTwo.displayShotResult(shotType);
                    if (shotType.equals(ShotType.Invalid) || shotType.equals(ShotType.Hit)) switchTurn = false;
                    playerOne.checkForLoss();
                    if (playerOne.hasPlayerLost()) throw new PlayerSurrenderedException("Player Two Wins");
                } catch (PlayerSurrenderedException ex){
                    ui.displayWin(playerTwo.getName());
                    gameOver = true;
                }
            }
            if (switchTurn) {
                playerOneTurn = !playerOneTurn;
                ui.switchPlayers();
            }

        } while (!gameOver);
    }

    private void salvoGame() {
        ui.displayUnderConstruction();
//        boolean playerOneTurn = true;
//        boolean gameOver = false;
//        do {
//
//        } while (!gameOver);
    }

    private void createPlayers(boolean playerOneHuman, boolean playerTwoHuman) throws IOException {
        playerOne = playerOneHuman ? new HumanPlayer(ui.askName(true)) : new BotPlayer(ui.askName(true));
        playerTwo = playerTwoHuman ? new HumanPlayer(ui.askName(false)) : new BotPlayer(ui.askName(false));
    }
}
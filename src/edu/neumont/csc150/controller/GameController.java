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
        createPlayers(playerOneHuman, playerTwoHuman);
        placeShips();
        setGameMode();
        startGame();
    }

    private void placeShips() throws IOException { //* high-pitched demonic screeching *
        initializeBoards();
        int[][] workingBoardArray;

        ShipType[] types = ShipType.values();
        String[] typeStrings = new String[types.length];
        boolean validPlacement;
        for (int i = 0; i < types.length; i++) {
            typeStrings[i] = types[i].name();
        }

        for (int h = 0; h < 2; h++) {
            boolean playerOne = h == 0;
            workingBoardArray = (h == 0 ? boardOne.getBoard() : boardTwo.getBoard());
            for (int i = 0; i < 5; i++) {
                displayBoard(playerOne, false);
                Point startingPoint;
                String workingType = typeStrings[i];
                switch (workingType) {
                    case "Carrier":
                        Carrier carrier = new Carrier();
                        Point[] carrierPoints = new Point[carrier.getLength()];
                        if (ui.askIfShipVertical(workingType)) {
                            validPlacement = false;
                            do {
                                startingPoint = ui.askShipCoords(typeStrings[i], true);
                                validPlacement = placementValid(startingPoint, workingBoardArray, carrier.getLength(), true);
                                carrierPoints[0] = startingPoint;
                                for (int j = 1; j < carrierPoints.length; j++) {
                                    carrierPoints[i] = new Point(startingPoint.getRow() + j, startingPoint.getColumn());
                                }
                            } while (!validPlacement);
                        } else {
                            validPlacement = false;
                            do {
                                startingPoint = ui.askShipCoords(typeStrings[i], false);
                                validPlacement = placementValid(startingPoint, workingBoardArray, carrier.getLength(), false);
                                carrierPoints[0] = startingPoint;
                                for (int j = 1; j < carrierPoints.length; j++) {
                                    carrierPoints[i] = new Point(startingPoint.getRow(), startingPoint.getColumn() + j);
                                }
                            } while (!validPlacement);
                        }
                        carrier.setPoints(carrierPoints);
                        break;
                    case "Battleship":
                        Battleship battleship = new Battleship();
                        Point[] battleshipPoints = new Point[battleship.getLength()];
                        if (ui.askIfShipVertical(workingType)) {
                            validPlacement = false;
                            do {
                                startingPoint = ui.askShipCoords(typeStrings[i], true);
                                validPlacement = placementValid(startingPoint, workingBoardArray, battleship.getLength(), true);
                                battleshipPoints[0] = startingPoint;
                                for (int j = 1; j < battleshipPoints.length; j++) {
                                    battleshipPoints[i] = new Point(startingPoint.getRow() + j, startingPoint.getColumn());
                                }
                            } while (!validPlacement);
                        } else {
                            validPlacement = false;
                            do {
                                startingPoint = ui.askShipCoords(typeStrings[i], false);
                                validPlacement = placementValid(startingPoint, workingBoardArray, battleship.getLength(), false);
                                battleshipPoints[0] = startingPoint;
                                for (int j = 1; j < battleshipPoints.length; j++) {
                                    battleshipPoints[i] = new Point(startingPoint.getRow(), startingPoint.getColumn() + j);
                                }
                            } while (!validPlacement);
                        }
                        battleship.setPoints(battleshipPoints);
                        break;
                    case "Cruiser":
                        Cruiser cruiser = new Cruiser();
                        Point[] cruiserPoints = new Point[cruiser.getLength()];
                        if (ui.askIfShipVertical(workingType)) {
                            validPlacement = false;
                            do {
                                startingPoint = ui.askShipCoords(typeStrings[i], true);
                                validPlacement = placementValid(startingPoint, workingBoardArray, cruiser.getLength(), true);
                                cruiserPoints[0] = startingPoint;
                                for (int j = 1; j < cruiserPoints.length; j++) {
                                    cruiserPoints[i] = new Point(startingPoint.getRow() + j, startingPoint.getColumn());
                                }
                            } while (!validPlacement);
                        } else {
                            validPlacement = false;
                            do {
                                startingPoint = ui.askShipCoords(typeStrings[i], false);
                                validPlacement = placementValid(startingPoint, workingBoardArray, cruiser.getLength(), false);
                                cruiserPoints[0] = startingPoint;
                                for (int j = 1; j < cruiserPoints.length; j++) {
                                    cruiserPoints[i] = new Point(startingPoint.getRow(), startingPoint.getColumn() + j);
                                }
                            } while (!validPlacement);
                        }
                        cruiser.setPoints(cruiserPoints);
                        break;
                    case "Submarine":
                        Submarine submarine = new Submarine();
                        Point[] submarinePoints = new Point[submarine.getLength()];
                        if (ui.askIfShipVertical(workingType)) {
                            validPlacement = false;
                            do {
                                startingPoint = ui.askShipCoords(typeStrings[i], true);
                                validPlacement = placementValid(startingPoint, workingBoardArray, submarine.getLength(), true);
                                submarinePoints[0] = startingPoint;
                                for (int j = 1; j < submarinePoints.length; j++) {
                                    submarinePoints[i] = new Point(startingPoint.getRow() + j, startingPoint.getColumn());
                                }
                            } while (!validPlacement);
                        } else {
                            validPlacement = false;
                            do {
                                startingPoint = ui.askShipCoords(typeStrings[i], false);
                                validPlacement = placementValid(startingPoint, workingBoardArray, submarine.getLength(), false);
                                submarinePoints[0] = startingPoint;
                                for (int j = 1; j < submarinePoints.length; j++) {
                                    submarinePoints[i] = new Point(startingPoint.getRow(), startingPoint.getColumn() + j);
                                }
                            } while (!validPlacement);
                        }
                        submarine.setPoints(submarinePoints);
                        break;
                    case "Destroyer":
                        Destroyer destroyer = new Destroyer();
                        Point[] destroyerPoints = new Point[destroyer.getLength()];
                        if (ui.askIfShipVertical(workingType)) {
                            validPlacement = false;
                            do {
                                startingPoint = ui.askShipCoords(typeStrings[i], true);
                                validPlacement = placementValid(startingPoint, workingBoardArray, destroyer.getLength(), true);
                                destroyerPoints[0] = startingPoint;
                                for (int j = 1; j < destroyerPoints.length; j++) {
                                    destroyerPoints[i] = new Point(startingPoint.getRow() + j, startingPoint.getColumn());
                                }
                            } while (!validPlacement);
                        } else {
                            validPlacement = false;
                            do {
                                startingPoint = ui.askShipCoords(typeStrings[i], false);
                                validPlacement = placementValid(startingPoint, workingBoardArray, destroyer.getLength(), false);
                                destroyerPoints[0] = startingPoint;
                                for (int j = 1; j < destroyerPoints.length; j++) {
                                    destroyerPoints[i] = new Point(startingPoint.getRow(), startingPoint.getColumn() + j);
                                }
                            } while (!validPlacement);
                        }
                        destroyer.setPoints(destroyerPoints);
                        break;
                }
            }
            ui.switchPlayers();
        }
    }

    private void initializeBoards() { //sets both boards to empty water
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
        gameMode = ui.getUserInputAsInt(1, 3);
    }

    private void startGame() {
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

    private void oneShotGame() {
        boolean playerOneTurn = true;
        boolean gameOver = false;
        do {
            if (playerOneTurn) {

            }
        } while (!gameOver);
    }

    private void artilleryGame() {
        boolean playerOneTurn = true;
        boolean gameOver = false;
        do {

        } while (!gameOver);
    }

    private void salvoGame() {
        boolean playerOneTurn = true;
        boolean gameOver = false;
        do {

        } while (!gameOver);
    }

    private void createPlayers(boolean playerOneHuman, boolean playerTwoHuman) throws IOException {
        playerOne = playerOneHuman ? new HumanPlayer(ui.askName(true)) : new BotPlayer(ui.askName(true));
        playerTwo = playerTwoHuman ? new HumanPlayer(ui.askName(false)) : new BotPlayer(ui.askName(false));
    }

    private void displayBoard(boolean playerOne, boolean isOpponent) {
        ui.displayBoard(playerOne ? boardOne.getBoard() : boardTwo.getBoard(), isOpponent);
    }

    private boolean placementValid(Point startingPoint, int[][] workingBoard, int length, boolean vertical) {
        boolean noCollision = true;
        if (vertical) {
            try {
                for (int i = 1; i < length; i++) {
                    if (workingBoard[startingPoint.getRow() + i][startingPoint.getColumn()] == 3) {
                        ui.displayInvalidPlacement();
                        noCollision = false;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                ui.displayInvalidPlacement();
                noCollision = false;
            }
        } else {
            try {
                for (int i = 1; i < length; i++) {
                    if (workingBoard[startingPoint.getRow()][startingPoint.getColumn() + i] == 3) {
                        ui.displayInvalidPlacement();
                        noCollision = false;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                ui.displayInvalidPlacement();
                noCollision = false;
            }
        }
        return noCollision;
    }
}
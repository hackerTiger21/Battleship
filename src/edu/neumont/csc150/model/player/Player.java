package edu.neumont.csc150.model.player;

import edu.neumont.csc150.model.*;
import edu.neumont.csc150.model.ship.*;
import edu.neumont.csc150.view.PlayerUI;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Player {
    protected String name;
    protected boolean isHuman;
    protected ArrayList<Ship> ships;
    protected Board playerBoard = new Board();
    protected boolean playerLost = false;
    PlayerUI ui = new PlayerUI();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHuman() {
        return isHuman;
    }

    protected void setHuman(boolean human) {
        isHuman = human;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(Board playerBoard) {
        this.playerBoard = playerBoard;
    }

    public boolean hasPlayerLost() {
        return playerLost;
    }

    public void setPlayerLost(boolean playerLost) {
        this.playerLost = playerLost;
    }

    public void checkForLoss(){
        boolean allSunk = true;
        for (Ship ship : ships) {
            if (!ship.isSunk()) allSunk = false;
        }
        setPlayerLost(allSunk);
    }

    private void initializeBoards() { //sets both boards to empty water
        int[][] clearBoard = new int[10][10];
        for (int i = 0; i < getPlayerBoard().getBoard().length; i++) {
            for (int j = 0; j < getPlayerBoard().getBoard()[0].length; j++) {
                clearBoard[i][j] = 0;
            }
        }
        getPlayerBoard().setBoard(clearBoard);
    }

    /**
     * Determines if a ship placement is valid, i.e. doesn't go over the edge of the board and doesn't collide into other ships
     * @param startingPoint leftmost or topmost segment of the ship
     * @param workingBoard the board that the ship will be placed on
     * @param length length of the ship
     * @param vertical whether the ship is vertical
     * @return whether the placement is valid
     */
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

    private void addShipToBoard(Point[] points){
        int[][] workingArray = getPlayerBoard().getBoard();
        for (int i = 0; i < points.length; i++) {
            int row = points[i].getRow();
            int column = points[i].getColumn();
            workingArray[row][column] = 3;
        }
        getPlayerBoard().setBoard(workingArray);
    }

    public void placeShips() throws IOException { //* high-pitched demonic screeching *
        ui.showPlaceShips(getName());

        initializeBoards();
        int[][] workingBoardArray = getPlayerBoard().getBoard();
        ShipType[] types = ShipType.values();
        String[] typeStrings = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            typeStrings[i] = types[i].name();
        }
        ArrayList<Ship> playerShips = new ArrayList<>();
        boolean validPlacement;
        displayBoard(false);

        for (int i = 0; i < 5; i++) {
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
                                carrierPoints[j] = new Point(startingPoint.getRow() + j, startingPoint.getColumn());
                            }
                        } while (!validPlacement);
                    } else {
                        validPlacement = false;
                        do {
                            startingPoint = ui.askShipCoords(typeStrings[i], false);
                            validPlacement = placementValid(startingPoint, workingBoardArray, carrier.getLength(), false);
                            carrierPoints[0] = startingPoint;
                            for (int j = 1; j < carrierPoints.length; j++) {
                                carrierPoints[j] = new Point(startingPoint.getRow(), startingPoint.getColumn() + j);
                            }
                        } while (!validPlacement);
                    }
                    carrier.setPoints(carrierPoints);
                    playerShips.add(carrier);
                    addShipToBoard(carrierPoints);
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
                                battleshipPoints[j] = new Point(startingPoint.getRow() + j, startingPoint.getColumn());
                            }
                        } while (!validPlacement);
                    } else {
                        validPlacement = false;
                        do {
                            startingPoint = ui.askShipCoords(typeStrings[i], false);
                            validPlacement = placementValid(startingPoint, workingBoardArray, battleship.getLength(), false);
                            battleshipPoints[0] = startingPoint;
                            for (int j = 1; j < battleshipPoints.length; j++) {
                                battleshipPoints[j] = new Point(startingPoint.getRow(), startingPoint.getColumn() + j);
                            }
                        } while (!validPlacement);
                    }
                    battleship.setPoints(battleshipPoints);
                    playerShips.add(battleship);
                    addShipToBoard(battleshipPoints);
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
                                cruiserPoints[j] = new Point(startingPoint.getRow() + j, startingPoint.getColumn());
                            }
                        } while (!validPlacement);
                    } else {
                        validPlacement = false;
                        do {
                            startingPoint = ui.askShipCoords(typeStrings[i], false);
                            validPlacement = placementValid(startingPoint, workingBoardArray, cruiser.getLength(), false);
                            cruiserPoints[0] = startingPoint;
                            for (int j = 1; j < cruiserPoints.length; j++) {
                                cruiserPoints[j] = new Point(startingPoint.getRow(), startingPoint.getColumn() + j);
                            }
                        } while (!validPlacement);
                    }
                    cruiser.setPoints(cruiserPoints);
                    playerShips.add(cruiser);
                    addShipToBoard(cruiserPoints);
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
                                submarinePoints[j] = new Point(startingPoint.getRow() + j, startingPoint.getColumn());
                            }
                        } while (!validPlacement);
                    } else {
                        validPlacement = false;
                        do {
                            startingPoint = ui.askShipCoords(typeStrings[i], false);
                            validPlacement = placementValid(startingPoint, workingBoardArray, submarine.getLength(), false);
                            submarinePoints[0] = startingPoint;
                            for (int j = 1; j < submarinePoints.length; j++) {
                                submarinePoints[j] = new Point(startingPoint.getRow(), startingPoint.getColumn() + j);
                            }
                        } while (!validPlacement);
                    }
                    submarine.setPoints(submarinePoints);
                    playerShips.add(submarine);
                    addShipToBoard(submarinePoints);
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
                                destroyerPoints[j] = new Point(startingPoint.getRow() + j, startingPoint.getColumn());
                            }
                        } while (!validPlacement);
                    } else {
                        validPlacement = false;
                        do {
                            startingPoint = ui.askShipCoords(typeStrings[i], false);
                            validPlacement = placementValid(startingPoint, workingBoardArray, destroyer.getLength(), false);
                            destroyerPoints[0] = startingPoint;
                            for (int j = 1; j < destroyerPoints.length; j++) {
                                destroyerPoints[j] = new Point(startingPoint.getRow(), startingPoint.getColumn() + j);
                            }
                        } while (!validPlacement);
                    }
                    destroyer.setPoints(destroyerPoints);
                    playerShips.add(destroyer);
                    addShipToBoard(destroyerPoints);
                    break;
            }
            displayBoard(false);
        }
        setShips(playerShips);
    }

    private void displayBoard(boolean isOpponent) {
        ui.displayBoard(getPlayerBoard().getBoard(), isOpponent);
    }

    public ShotType receiveShot(Point target){
        ShotType result = getPlayerBoard().takeShot(target);
        if (result.equals(ShotType.Hit)){
            for (Ship ship: getShips()) {
                for (int i = 0; i < ship.getPoints().length; i++) {
                    Point[] points = ship.getPoints();
                    if (points[i].equals(target)){
                        points[i].setHit(true);
                    }
                }
            }
        }
        return result;
    }

    public abstract Point takeTurn(int[][] enemyBoard) throws PlayerSurrenderedException, IOException;

    public void displayShotResult(ShotType shotType){
        ui.displayResult(shotType);
    }
}

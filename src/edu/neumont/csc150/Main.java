package edu.neumont.csc150;

import edu.neumont.csc150.controller.GameController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new GameController().run();
    }
}

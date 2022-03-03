package edu.neumont.csc150.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameUI {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public void displayMenu(){
        System.out.println("Prepare for battle: Its BattleShip\r\n"+
                "Are you ready:\r\n" +
                "\t1. Player Vs. Player\r\n" +
                "\t2. Player Vs. the Aliens(Bot)\r\n" +
                "\t3. Wave your White FLag (that means exit)\r\n");
    }



    public int getUserInputAsInt(int min, int max) throws IOException {
        int input;
        do{
            String line = br.readLine();
            try{
                input = Integer.parseInt(line);
                if(input > max || input < min){
                    throw new NumberFormatException("out of range");
                }
                break;
            } catch(NumberFormatException ex){
                System.out.println("Input invalid. " +
                        "You must enter a number between " + min + " and " + max);
            }
        }while (true);
        return input;
    }

    public int getIntFromUser(int min, int max) throws IOException{
        float value = getUserInputAsInt(min,max);
        return (int) value;
    }
}

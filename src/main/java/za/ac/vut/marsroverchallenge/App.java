/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import za.ac.vut.marsroverchallenge.model.Request;
import za.ac.vut.marsroverchallenge.service.CommandMediator;

/**
 *
 * @author 20180172
 */
public class App {

    private static final String APPLICATION_NAME = "Rover Nagigation Version 1.2";
    private static String terrainSurfaceArea, roverStartingInstructions, commands;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int menuOption = 0;

        menu();

        while (menuOption != 2) {
            menuOptions();

            try {
                menuOption = Integer.parseInt(reader.readLine());

                if (menuOption == 1) {
                    try {
                        Request request = createUserRequest(reader);

                        CommandMediator mediator = new CommandMediator(request);

                        mediator.executeCommands();

                        roverCommandFeedback(mediator);
                    } catch (Exception e) {
                        roverCommandErrorFeedback(e.getMessage());
                    }
                }
            } catch (NumberFormatException | IOException e) {
                menuWrongOptionChosen();
            }
        }

        exitMessage();
    }

    public static Request createUserRequest(BufferedReader reader) throws IOException {
        String input = "";
        
        menuGiveRoverInstructions();
        
        menuRoverInstructionsTerrainSurfaceArea();
        terrainSurfaceArea = reader.readLine();

        menuRoverInstructionsRoverInstructions();
        roverStartingInstructions = reader.readLine();

        menuRoverInstructionsCommands();
        commands = reader.readLine();

        return new Request(terrainSurfaceArea, roverStartingInstructions, commands);
    }

    public static void exitMessage() {
        System.out.printf("Thank you for using %s, Goodbye.", APPLICATION_NAME);
    }
    
    public static String getTerrainSurfaceArea(BufferedReader reader) throws IOException {
        menuRoverInstructionsTerrainSurfaceArea();
        terrainSurfaceArea = reader.readLine();
        
        return terrainSurfaceArea;
    }
    
    public static String getRoverStartingInstructions(BufferedReader reader) throws IOException {
        menuRoverInstructionsRoverInstructions();
        roverStartingInstructions = reader.readLine();
        
        return roverStartingInstructions;
    }
    
    public static String getRoverCommands(BufferedReader reader) throws IOException {
        menuRoverInstructionsCommands();
        commands = reader.readLine();
        
        return commands;
    }

    public static void menu() {
        System.out.println(APPLICATION_NAME);
        System.out.println("\n");

    }

    public static void menuOptions() {
        System.out.println("Below are the options you have with our application:");
        System.out.println("1. Give Instructions to rover.");
        System.out.println("2. Exit");
    }

    public static void menuGiveRoverInstructions() {
        System.out.println("Dear user, you are about to give the Rover instructions, please follow the guide.");
    }

    public static void menuRoverInstructionsTerrainSurfaceArea() {
        System.out.println("Please enter the terrain's surface area bounds (horizontal and vertical bounds. Please use numbers, separated with a space. Eg. 8 10.");
    }

    public static void menuRoverInstructionsRoverInstructions() {
        System.out.println("Please enter the Rover's positional instructions, their horizontal coordinate, their vertical coordinate and the cardinal point. Please separate them with a space. Eg. 1 2 E.");
    }

    public static void menuRoverInstructionsCommands() {
        System.out.println("Please enter the commands to be given to the Rover. Please use alphabets, can be upper or lower case. Eg. MMLRMM or mmmlrm");
    }

    public static void menuWrongOptionChosen() {
        System.out.println("Dear user, you chose the wrong option");
        System.out.println("\n");
    }

    public static void roverCommandErrorFeedback(String error) {
        System.out.println("\n");
        System.out.println(error);
        System.out.println("\n");
    }

    public static void roverCommandFeedback(CommandMediator mediator) {
        System.out.println("After following your commands, the Rover's new position and cardinal point is: ");
        System.out.println(mediator.getRoverLocationAndCardinalPoint());
        System.out.println("\n");
    }
}

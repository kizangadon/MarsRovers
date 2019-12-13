/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.ac.vut.marsroverchallenge.exception.model.IllegalBoundValueException;
import za.ac.vut.marsroverchallenge.exception.model.IllegalCommandValueException;
import za.ac.vut.marsroverchallenge.exception.model.IllegalCoordinateValueException;
import za.ac.vut.marsroverchallenge.model.Request;
import za.ac.vut.marsroverchallenge.service.CommandMediator;

/**
 *
 * @author 20180172
 */
public class App {

    private static final String APPLICATION_NAME = "Rover Nagigation Version 0.1";
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
                    menuGiveRoverInstructions();

                    try {
                        Request request = createUserRequest(reader);
                        
                        CommandMediator mediator = new CommandMediator(request);
                        
                        mediator.executeCommands();
                        
                        System.out.println("After following your commands, the Rover's new position and cardinal point is: ");
                        System.out.println(mediator.getRoverLocationAndCardinalPoint());
                        System.out.println("\n");
                    } catch (IllegalArgumentException | IllegalCommandValueException | IllegalCoordinateValueException | IllegalBoundValueException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Dear user, you chose the wrong option");
                System.out.println("\n");
            }
        }

    }

    public static Request createUserRequest(BufferedReader reader) throws IOException {
        menuRoverInstructionsTerrainSurfaceArea();
        terrainSurfaceArea = reader.readLine();
        
        menuRoverInstructionsRoverInstructions();
        roverStartingInstructions = reader.readLine();
        
        menuRoverInstructionsCommands();
        commands = reader.readLine();

        return new Request(terrainSurfaceArea, roverStartingInstructions, commands);
    }

    public static void menu() {
        System.out.println(APPLICATION_NAME);
        System.out.println("\n");

    }

    public static void menuOptions() {
        System.out.println("Dear user, welcome to the navigation app. Below are the options you have with our application:");
        System.out.println("1. Give Instructions to rover.");
        System.out.println("2. Exit");
    }

    public static void menuGiveRoverInstructions() {
        System.out.println("Dear user, you are about to give the Rover instructions, please follow the guide.");
        System.out.println("\n");
    }
    
    public static void menuRoverInstructionsTerrainSurfaceArea() {
        System.out.println("Please enter the terrain's surface area bounds (horizontal and vertical bounds. Please use numbers, separated with a space. Eg. 8 10.");
        System.out.println("\n");
    }
    
    public static void menuRoverInstructionsRoverInstructions() {
        System.out.println("Please enter the Rover's positional instructions, their horizontal coordinate, their vertical coordinate and the cardinal point. Please separate them with a space. Eg. 1 2 E.");
        System.out.println("\n");
    }
    
    public static void menuRoverInstructionsCommands() {
        System.out.println("Please enter the commands to be given to the Rover. Please use alphabets, can be upper or lower case. Eg. MMLRMM or mmmlrm");
        System.out.println("\n");
    }
}

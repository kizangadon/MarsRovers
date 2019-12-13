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
                    } catch (IllegalCommandValueException | IllegalCoordinateValueException | IllegalBoundValueException e) {

                    }
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Dear user, you chose the wrong option");
            }
        }

    }

    public static Request createUserRequest(BufferedReader reader) throws IOException {

        terrainSurfaceArea = reader.readLine();
        roverStartingInstructions = reader.readLine();
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
        System.out.println("To exit the application enter: -1");
        System.out.println("Please enter the terrain's surface area bounds (horizontal and vertical bounds. Please use numbers. Eg. 8 10.");

    }
}

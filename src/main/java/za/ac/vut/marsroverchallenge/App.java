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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String terrainSurfaceArea = "8 10";
        String roverStartingInstructions = "1 2 E";
        String commands = "MMLMRMMRRMMLB";
        
        menu();
        
        try {
            terrainSurfaceArea = reader.readLine();
            
            Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        } catch (IOException e) {
            
        }

    }
    
    public static void menu() {
        System.out.println(APPLICATION_NAME);
        System.out.println("\n");
        System.out.println("Dear user, welcome to the navigation app. Below are the options you have with our application:");
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author 20180172
 */
public class Request implements ICommandRequest{
    //The terrain surface area's horizontal bound
    private int terrainHorizontalBound;
    //The terrain surface area's vertical bound
    private int terrainVerticalBound;
    //The rover's starting horizontal coordinate on a terrain
    private int roverHorizontalCoordinate;
    //The rover's starting vertical coordinate on a terrain
    private int roverVerticalCoordinate;
    //The rover's starting cardinal point on a terrain
    private String roverCardinalPoint;
    //List of commands for a rover to follow
    private List<String> commands;
    
    /**
     * 
     * @param terrainSurfaceArea 
     */
    public Request(String terrainSurfaceArea) {
        setTerrainSurfaceArea(terrainSurfaceArea);
    }

    public Request(String terrainSurfaceArea, String roverStartingInstructions) {
        setTerrainSurfaceArea(terrainSurfaceArea);
        setRoverStartingInstructions(roverStartingInstructions);
    }

    public Request(String terrainSurfaceArea, String roverStartingInstructions, String commands) {
        setTerrainSurfaceArea(terrainSurfaceArea);
        setRoverStartingInstructions(roverStartingInstructions);
        setCommands(commands);
    }
    
    private void setTerrainSurfaceArea(String terrainSurfaceArea){
        if(!terrainSurfaceArea.contains(" "))
            throw new IllegalArgumentException("Dear user, the terrain surface area capacity consists of the horizontal and vertical bound which must be entered with a space ( ) separting them.");
        //Split the terrain surface area input by spaces between the values
        String[] terrainSurfaceAreaBounds = terrainSurfaceArea.trim().replaceAll("\\s+", " ").split(" ");
        //Check if the terrain's surface area are two input values
        //Check if the number of supplied input values are two (horizontal and vertical bounds)
        if(terrainSurfaceAreaBounds.length != 2)
            throw new IllegalArgumentException("Dear user, the terrain surface area capacity consists of the horizontal and vertical bound which must be entered with a space ( ) separting them.");
        //Check if the terrain's surface area horizontal and vertical bounds are numbers
        try{
            //Set the terrain surface area's horizontal bound
            terrainHorizontalBound = Integer.parseInt(terrainSurfaceAreaBounds[0]);
            //Set the terrain surface area's vertical bound
            terrainVerticalBound = Integer.parseInt(terrainSurfaceAreaBounds[1]);
        }catch(NumberFormatException e){
            //The two supplied values are not numbers
            throw new IllegalArgumentException("Dear user, the terrain surface area capacity must be a number, consiting of the horizontal and vertical bounds of the surface area.");
        }
    }
    
    private void setRoverStartingInstructions(String roverStartingInstructions){
        //Check if rover's starting instructions doesn't have an spaces separating the values
        if(!roverStartingInstructions.contains(" "))
            throw new IllegalArgumentException("Dear user, the Rover's starting instructions must be their starting horizontal coordinate, separated by a space ( ), their starting vertical coordinate, separated by a space ( _) and then their cardinal point which they are facing (only use the first letter of the direction).");
        //Split the rover's starting instructions by the space between the input values
        String[] roverStartingInstructionsValues = roverStartingInstructions.split(" ");
        //CHeck
        if(roverStartingInstructionsValues.length != 3)
            throw new IllegalArgumentException("Dear user, the Rover's starting instructions must be their starting horizontal coordinate, separated by a space ( ), their starting vertical coordinate, separated by a space ( _) and then their cardinal point which they are facing (only use the first letter of the direction).");
        
        try{
            this.roverHorizontalCoordinate = Integer.parseInt(roverStartingInstructionsValues[0]);
            this.roverVerticalCoordinate = Integer.parseInt(roverStartingInstructionsValues[1]);
            this.roverCardinalPoint = roverStartingInstructionsValues[2];
            
            if(!this.roverCardinalPoint.matches("[a-zA-Z]"))
                throw new IllegalArgumentException("Dear user, the Rover's starting instructions must be their starting horizontal coordinate (number), separated by a space ( ), their starting vertical coordinate (number), separated by a space ( _) and then their cardinal point (alphabet) which they are facing (only use the first letter of the direction).");
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("Dear user, the Rover's starting instructions must be their starting horizontal coordinate (number), separated by a space ( ), their starting vertical coordinate (number), separated by a space ( _) and then their cardinal point (alphabet) which they are facing (only use the first letter of the direction).");
        }
    }
    
    private void setCommands(String commands){
        if(!commands.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException("Dear user, the commands given to the rover must be alphabets and not contain spaces between them.");
        //Convert string of commands into an array of commands
        this.commands = Arrays.asList(commands.split(""));
    }

    /**
     * Gets the terrain surface area's horizontal bound
     * @return terrain horizontal bound 
     */
    @Override
    public int getTerrainHorizontalBound() {
        return this.terrainHorizontalBound;
    }

    /**
     * Gets the terrain surface area's vertical bound
     * @return terrain vertical bound
     */
    @Override
    public int getTerrainVerticalBound() {
        return this.terrainVerticalBound;
    }

    /**
     * Gets the rover's horizontal coordinate on a terrain
     * @return rover horizontal coordinate
     */
    @Override
    public int getRoverHorizontalCoordinate() {
        return this.roverHorizontalCoordinate;
    }

    /**
     * Gets the rover's vertical coordinate on a terrain
     * @return rover vertical coordinate
     */
    @Override
    public int getRoverVerticalCoordinate() {
        return this.roverVerticalCoordinate;
    }

    /**
     * Gets the rover's cardinal point they are facing
     * @return cardinal point
     */
    @Override
    public String getRoverCardinalPoint() {
        return this.roverCardinalPoint;
    }

    /**
     * Gets the list of commands for a rover
     * @return command list
     */
    @Override
    public List<String> getCommands() {
        return this.commands;
    }
}
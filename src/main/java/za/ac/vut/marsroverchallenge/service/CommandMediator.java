/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.service;

import java.util.ArrayList;
import java.util.List;
import za.ac.vut.marsroverchallenge.exception.model.IllegalBoundValueException;
import za.ac.vut.marsroverchallenge.exception.model.IllegalCoordinateValueException;
import za.ac.vut.marsroverchallenge.model.Command;

import za.ac.vut.marsroverchallenge.model.CoordinatePoint;
import za.ac.vut.marsroverchallenge.model.Request;
import za.ac.vut.marsroverchallenge.model.Rover;
import za.ac.vut.marsroverchallenge.model.Terrain;

/**
 *
 * @author 20180172
 */
public class CommandMediator {
    //The user's request
    private final Request request;
    //The terrain that the rover will be operating on
    private Terrain terrain;
    //The rover
    private Rover rover;
    //List of commands for the rover
    private List<Command> commands;

    public CommandMediator(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return this.request;
    }

    public Terrain getTerrain() throws IllegalBoundValueException {
        int horizontalBound = request.getTerrainHorizontalBound();
        int verticalBound = request.getTerrainVerticalBound(); 
        
        terrain = new Terrain(horizontalBound, verticalBound);
        
        return terrain;
    }

    public Rover getRover() throws IllegalCoordinateValueException {
        int horizontalCoordinate = request.getRoverHorizontalCoordinate();
        int verticalCoordinate = request.getRoverVerticalCoordinate();
        
        
        Rover.CardinalPoint cardinalPoint;
        
        switch(request.getRoverCardinalPoint()){
            case "E":
                cardinalPoint = Rover.CardinalPoint.EAST;
                break;
            case "W":
                cardinalPoint = Rover.CardinalPoint.WEST;
                break;
            case "N":
                cardinalPoint = Rover.CardinalPoint.NORTH;
                break;
            case "S":
                cardinalPoint = Rover.CardinalPoint.SOUTH;
                break;
            default:
                cardinalPoint = null;
                break;
        }
        
        CoordinatePoint point = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        
        rover = new Rover(point, cardinalPoint);
        
        return rover;
    }

    public List<Command> getCommands() {
        commands = new ArrayList<>();
        
        for(String command : request.getCommands()){
            switch(command){
            case "M":
                commands.add(Command.MOVE_ONE_SPACE_FORWARD);
                break;
            case "R":
                commands.add(Command.ROTATE_NINETY_DEGREES_RIGHT);
                break;
            case "L":
                commands.add(Command.ROTATE_NINETY_DEGREES_LEFT);
                break;
            default:
                commands.add(null);
                break;
        }
        }
        
        return commands;
    }
    
}

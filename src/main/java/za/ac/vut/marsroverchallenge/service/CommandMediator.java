/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.service;

import za.ac.vut.marsroverchallenge.exception.model.RoverCannotMoveForwardException;
import java.util.List;
import za.ac.vut.marsroverchallenge.exception.model.IllegalBoundValueException;
import za.ac.vut.marsroverchallenge.exception.model.IllegalCommandValueException;
import za.ac.vut.marsroverchallenge.exception.model.IllegalCoordinateValueException;
import za.ac.vut.marsroverchallenge.exception.model.PointOutOfTerrainSurfaceAreaBoundsException;
import za.ac.vut.marsroverchallenge.model.Command;

import za.ac.vut.marsroverchallenge.model.CoordinatePoint;
import za.ac.vut.marsroverchallenge.model.Request;
import za.ac.vut.marsroverchallenge.model.Rover;
import za.ac.vut.marsroverchallenge.model.Terrain;
import za.ac.vut.marsroverchallenge.model.factory.CommandFactory;

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

    public CommandMediator(Request request) throws IllegalCommandValueException, IllegalCoordinateValueException, IllegalBoundValueException {
        this.request = request;
        
        createTerrain();
        createRover();
        createCommands();
    }
    
    private void createCommands() throws IllegalCommandValueException{
        commands = CommandFactory.createCommands(request.getCommands());
    }
    
    private void createRover() throws IllegalCoordinateValueException{
        int horizontalCoordinate = request.getRoverHorizontalCoordinate();
        int verticalCoordinate = request.getRoverVerticalCoordinate();

        Rover.CardinalPoint cardinalPoint;

        switch (request.getRoverCardinalPoint()) {
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
    }
    
    private void createTerrain() throws IllegalBoundValueException{
        int horizontalBound = request.getTerrainHorizontalBound();
        int verticalBound = request.getTerrainVerticalBound();

        terrain = new Terrain(horizontalBound, verticalBound);
    }

    public Request getRequest() {
        return this.request;
    }

    public Terrain getTerrain() {
         return terrain;
    }

    public Rover getRover(){
        return rover;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void executeCommand(Command command) throws RoverCannotMoveForwardException, IllegalCoordinateValueException {
        switch(command){
            case MOVE_ONE_SPACE_FORWARD:
        {
            try {
                rover.moveOneStepForward(terrain);
            } catch (PointOutOfTerrainSurfaceAreaBoundsException e) {
                throw new RoverCannotMoveForwardException();
            }
        }
                break;
        }
    }

    public String getRoverLocationAndCardinalPoint() {
        return String.format("%d %d %s", rover.getCurrentHorizontalCoordinate(), rover.getCurrentVerticalCoordinate(), rover.getCardinalPoint());
    }

}

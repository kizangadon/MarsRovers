/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.service;

import za.ac.vut.marsroverchallenge.exception.model.RoverCannotMoveForwardException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CommandMediator implements ICommandMediator {

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

    private void createCommands() throws IllegalCommandValueException {
        commands = CommandFactory.createCommands(request.getCommands());
    }

    private void createRover() throws IllegalCoordinateValueException {
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

    private void createTerrain() throws IllegalBoundValueException {
        int horizontalBound = request.getTerrainHorizontalBound();
        int verticalBound = request.getTerrainVerticalBound();

        terrain = new Terrain(horizontalBound, verticalBound);
    }

    @Override
    public Request getRequest() {
        return this.request;
    }

    @Override
    public Rover getRover() {
        return rover;
    }

    @Override
    public Terrain getTerrain() {
        return terrain;
    }

    @Override
    public List<Command> getCommands() {
        return commands;
    }

    private void moveOneStepForwardCommand() throws IllegalCoordinateValueException, RoverCannotMoveForwardException {
        try {
            rover.moveOneStepForward(terrain);
        } catch (PointOutOfTerrainSurfaceAreaBoundsException e) {
            throw new RoverCannotMoveForwardException();
        }
    }

    @Override
    public void executeCommand(Command command) throws RoverCannotMoveForwardException, IllegalCoordinateValueException {
        switch (command) {
            case MOVE_ONE_SPACE_FORWARD: {
                moveOneStepForwardCommand();
            }
            break;
            case ROTATE_NINETY_DEGREES_LEFT:
                rover.rotateNinetyDegreesLeft();
                break;
            case ROTATE_NINETY_DEGREES_RIGHT:
                rover.rotateNinetyDegreesRight();
                break;
        }
    }

    @Override
    public void executeCommands() {
        for (Command command : commands) {
            try {
                executeCommand(command);
            } catch (RoverCannotMoveForwardException | IllegalCoordinateValueException e) {

            }
        }
    }

    @Override
    public String getRoverLocationAndCardinalPoint() {
        return String.format("%d %d %s", rover.getCurrentHorizontalCoordinate(), rover.getCurrentVerticalCoordinate(), rover.getCardinalPoint());
    }
}

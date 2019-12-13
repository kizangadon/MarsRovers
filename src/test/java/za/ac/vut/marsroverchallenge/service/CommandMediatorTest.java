/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Ignore;

import za.ac.vut.marsroverchallenge.model.Command;
import za.ac.vut.marsroverchallenge.model.CoordinatePoint;
import za.ac.vut.marsroverchallenge.model.Request;
import za.ac.vut.marsroverchallenge.model.Rover;
import za.ac.vut.marsroverchallenge.model.Terrain;

/**
 *
 * @author 20180172
 */
public class CommandMediatorTest {
    public String terrainSurfaceArea;
    public String roverStartingInstructions;
    public String commands;
        
    @Before
    public void setUp() {
        this.terrainSurfaceArea = "8 10";
        this.roverStartingInstructions = "1 2 E";
        commands = "MMLMR";
    }
    
    @Test
    public void getRequest_returnsTheSameCommandRequestThatWasSetInTheConstructor() throws Exception {
        //Arrange
        //Act
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        CommandMediator mediator = new CommandMediator(request);
        //Assert
        assertEquals(request, mediator.getRequest());
    }
    
    @Test
    public void getTerrain_returnsTheTerrainSpecifiedInTheUsersRequest() throws Exception {
        //Arrange
        int horizontalBound = 8;
        int verticalBound = 10;
        Terrain terrain = new Terrain(horizontalBound, verticalBound);
        //Act
        Request request = new Request(terrainSurfaceArea);
        CommandMediator mediator = new CommandMediator(request);
        //Assert
        assertEquals(terrain, mediator.getTerrain());
    }
    
    @Test
    public void getRover_returnsTheRoverSpecifiedInTheUsersRequest() throws Exception {
        //Arrange
        int horizontalCoordinate = 1;
        int verticalCoordinate = 2;
        Rover.CardinalPoint cardinalPoint = Rover.CardinalPoint.EAST;
        
        CoordinatePoint point = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        
        Rover rover = new Rover(point, cardinalPoint);
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        //Assert
        assertEquals(rover, mediator.getRover());
    }
    
    @Test
    public void getCommands_returnsAListOfTheCorrectCommandsSpecifiedInTheUsersRequest() throws Exception {
        //Arrange
        List<Command> commandList = new ArrayList<>();
        
        commandList.add(Command.MOVE_ONE_SPACE_FORWARD);
        commandList.add(Command.MOVE_ONE_SPACE_FORWARD);
        commandList.add(Command.ROTATE_NINETY_DEGREES_LEFT);
        commandList.add(Command.MOVE_ONE_SPACE_FORWARD);
        commandList.add(Command.ROTATE_NINETY_DEGREES_RIGHT);
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        //Assert
        assertEquals(commandList, mediator.getCommands());
    }
}
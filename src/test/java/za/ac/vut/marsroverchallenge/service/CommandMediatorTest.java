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
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import za.ac.vut.marsroverchallenge.exception.model.RoverCannotMoveForwardException;

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
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
        
    @Before
    public void setUp() {
        terrainSurfaceArea = "8 10";
        roverStartingInstructions = "1 2 E";
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
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
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
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
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
    
    @Test
    public void executeNextCommand_throwsRoverCannotMoveForwardException_whenTheRoverTriesToMoveEastwardsWhilstOnTheTerrainsMaxmimEasternBound() throws Exception {
        //Arrange
        expectedException.expect(RoverCannotMoveForwardException.class);
        
        Command command = Command.MOVE_ONE_SPACE_FORWARD;
        terrainSurfaceArea = "4 4";
        roverStartingInstructions = "4 1 E";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
    }
    
    @Test
    public void executeNextCommand_throwsRoverCannotMoveForwardException_whenTheRoverTriesToMoveWestwardsWhilstOnTheTerrainsMaxmimWesternBound() throws Exception {
        //Arrange
        expectedException.expect(RoverCannotMoveForwardException.class);
        
        Command command = Command.MOVE_ONE_SPACE_FORWARD;
        terrainSurfaceArea = "4 4";
        roverStartingInstructions = "0 2 W";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
    }
    
    @Test
    public void executeNextCommand_throwsRoverCannotMoveForwardException_whenTheRoverTriesToMoveNorthwardsWhilstOnTheTerrainsMaxmimNorthernBound() throws Exception {
        //Arrange
        expectedException.expect(RoverCannotMoveForwardException.class);
        
        Command command = Command.MOVE_ONE_SPACE_FORWARD;
        terrainSurfaceArea = "4 4";
        roverStartingInstructions = "1 0 N";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
    }
    
    @Test
    public void executeNextCommand_throwsRoverCannotMoveForwardException_whenTheRoverTriesToMoveSouthwardsWhilstOnTheTerrainsMaxmimSouthernBound() throws Exception {
        //Arrange
        expectedException.expect(RoverCannotMoveForwardException.class);
        
        Command command = Command.MOVE_ONE_SPACE_FORWARD;
        terrainSurfaceArea = "4 4";
        roverStartingInstructions = "1 4 S";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
    }
    
    @Test
    public void getRoverLocationAndCardinalPoint_returnsTheExpectedRoversPositionAndCardinalPoint_whenTheRoverTriesToMoveEastwardsWhilstWithinTheTerrainsMaxmimEasternBound() throws Exception {
        //Arrange
        Command command = Command.MOVE_ONE_SPACE_FORWARD;
        terrainSurfaceArea = "5 5";
        roverStartingInstructions = "3 1 E";
        String expectedRoverPosition = "4 1 E";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
        //Arrange
        expectedRoverPosition = "5 1 E";
        //Act
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
    }
    
    @Test
    public void getRoverLocationAndCardinalPoint_returnsTheExpectedRoversPositionAndCardinalPoint_whenTheRoverTriesToMoveWastwardsWhilstWithinTheTerrainsMaxmimWesternBound() throws Exception {
        //Arrange
        Command command = Command.MOVE_ONE_SPACE_FORWARD;
        terrainSurfaceArea = "4 4";
        roverStartingInstructions = "2 2 W";
        String expectedRoverPosition = "1 2 W";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
        //Arrange
        expectedRoverPosition = "0 2 W";
        //Act
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
    }
    
    @Test
    public void getRoverLocationAndCardinalPoint_returnsTheExpectedRoversPositionAndCardinalPoint_whenTheRoverTriesToMoveNorthwardsWhilstWithinTheTerrainsMaxmimNorthernBound() throws Exception {
        //Arrange
        Command command = Command.MOVE_ONE_SPACE_FORWARD;
        terrainSurfaceArea = "4 4";
        roverStartingInstructions = "2 4 N";
        String expectedRoverPosition = "2 3 N";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
        //Arrange
        expectedRoverPosition = "2 2 N";
        //Act
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
        //Arrange
        expectedRoverPosition = "2 1 N";
        //Act
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
    }
    
    @Test
    public void getRoverLocationAndCardinalPoint_returnsTheExpectedRoversPositionAndCardinalPoint_whenTheRoverTriesToMoveSouthwardsWhilstWithinTheTerrainsMaxmimSouthernBound() throws Exception {
        //Arrange
        Command command = Command.MOVE_ONE_SPACE_FORWARD;
        terrainSurfaceArea = "4 4";
        roverStartingInstructions = "1 2 S";
        String expectedRoverPosition = "1 3 S";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
        //Arrange
        expectedRoverPosition = "1 4 S";
        //Act
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
    }
    
    @Test
    public void getRoverLocationAndCardinalPoint_returnsTheExpectedRoversPositionAndCardinalPoint_whenTheRoverIsCommandedToRotateNinetyDegreesToTheLeftWhilstFacingEastwards() throws Exception {
        //Arrange
        Command command = Command.ROTATE_NINETY_DEGREES_LEFT;
        terrainSurfaceArea = "4 4";
        roverStartingInstructions = "1 2 E";
        String expectedRoverPosition = "1 2 N";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
    }
    
    @Test
    public void getRoverLocationAndCardinalPoint_returnsTheExpectedRoversPositionAndCardinalPoint_whenTheRoverIsCommandedToRotateNinetyDegreesToTheLeftWhilstFacingWestwards() throws Exception {
        //Arrange
        Command command = Command.ROTATE_NINETY_DEGREES_LEFT;
        terrainSurfaceArea = "4 4";
        roverStartingInstructions = "1 2 W";
        String expectedRoverPosition = "1 2 S";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
    }
    
    @Test
    public void getRoverLocationAndCardinalPoint_returnsTheExpectedRoversPositionAndCardinalPoint_whenTheRoverIsCommandedToRotateNinetyDegreesToTheLeftWhilstFacingNorthwards() throws Exception {
        //Arrange
        Command command = Command.ROTATE_NINETY_DEGREES_LEFT;
        terrainSurfaceArea = "4 4";
        roverStartingInstructions = "1 2 N";
        String expectedRoverPosition = "1 2 W";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
    }
    
    @Test
    public void getRoverLocationAndCardinalPoint_returnsTheExpectedRoversPositionAndCardinalPoint_whenTheRoverIsCommandedToRotateNinetyDegreesToTheLeftWhilstFacingSouthwards() throws Exception {
        //Arrange
        Command command = Command.ROTATE_NINETY_DEGREES_LEFT;
        terrainSurfaceArea = "4 4";
        roverStartingInstructions = "1 2 S";
        String expectedRoverPosition = "1 2 E";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
    }
    
    @Test
    public void getRoverLocationAndCardinalPoint_returnsTheExpectedRoversPositionAndCardinalPoint_whenTheRoverIsCommandedToRotateNinetyDegreesToTheRightWhilstFacingEastwards() throws Exception {
        //Arrange
        Command command = Command.ROTATE_NINETY_DEGREES_RIGHT;
        terrainSurfaceArea = "4 4";
        roverStartingInstructions = "1 2 E";
        String expectedRoverPosition = "1 2 S";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
    }
    
    @Test
    public void getRoverLocationAndCardinalPoint_returnsTheExpectedRoversPositionAndCardinalPoint_whenTheRoverIsCommandedToRotateNinetyDegreesToTheRightWhilstFacingWestwards() throws Exception {
        //Arrange
        Command command = Command.ROTATE_NINETY_DEGREES_RIGHT;
        terrainSurfaceArea = "4 4";
        roverStartingInstructions = "1 2 W";
        String expectedRoverPosition = "1 2 N";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
    }
    
    @Test
    public void getRoverLocationAndCardinalPoint_returnsTheExpectedRoversPositionAndCardinalPoint_whenTheRoverIsCommandedToRotateNinetyDegreesToTheRightWhilstFacingNorthwards() throws Exception {
        //Arrange
        Command command = Command.ROTATE_NINETY_DEGREES_RIGHT;
        terrainSurfaceArea = "4 4";
        roverStartingInstructions = "1 2 N";
        String expectedRoverPosition = "1 2 E";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
    }
    
    @Test
    public void getRoverLocationAndCardinalPoint_returnsTheExpectedRoversPositionAndCardinalPoint_whenTheRoverIsCommandedToRotateNinetyDegreesToTheRightWhilstFacingSouthwards() throws Exception {
        //Arrange
        Command command = Command.ROTATE_NINETY_DEGREES_RIGHT;
        terrainSurfaceArea = "4 4";
        roverStartingInstructions = "1 2 S";
        String expectedRoverPosition = "1 2 W";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Act
        CommandMediator mediator = new CommandMediator(request);
        
        mediator.executeCommand(command);
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
    }
    
    @Test
    public void executeCommands_changesTheRoversPositionAndCardinalPointToTheExpected_whenTheRoverHasBeenGiveCommandsToMoveForwardAndRotate() throws Exception {
        //Arrange
        terrainSurfaceArea = "8 10";
        roverStartingInstructions = "1 2 E";
        commands = "MMLMRMMRRMML";
        String expectedRoverPosition = "3 1 S";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        CommandMediator mediator = new CommandMediator(request);
        //Act
        mediator.executeCommands();
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
        //Arrange
        terrainSurfaceArea = "8 10";
        roverStartingInstructions = "1 2 S";
        commands = "MMMMR";
        expectedRoverPosition = "1 6 W";
        
        request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        mediator = new CommandMediator(request);
        //Act
        mediator.executeCommands();
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
    }
    
    @Test
    public void executeCommands_changesTheRoversPositionAndCardinalPointToTheExpected_whenTheRoverHasBeenGiveCommandsToMoveForwardAndRotatePassedTheTerrainsSurfaceAreaBounds() throws Exception {
        //Arrange
        terrainSurfaceArea = "2 2";
        roverStartingInstructions = "0 0 S";
        commands = "MMMMRMMRMRMM";
        String expectedRoverPosition = "2 1 E";
        
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        CommandMediator mediator = new CommandMediator(request);
        //Act
        mediator.executeCommands();
        //Assert
        assertEquals(expectedRoverPosition, mediator.getRoverLocationAndCardinalPoint());
        
    }
}
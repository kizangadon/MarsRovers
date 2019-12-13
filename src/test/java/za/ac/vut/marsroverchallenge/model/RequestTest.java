/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;

/**
 *
 * @author 20180172
 */
public class RequestTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void constructor_throwsllegalArgumentException_whenThereIsNoSpaceSepartingTheTerrainsSurfaceAreaCapacity() throws Exception {
        //Arrange
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Dear user, the terrain surface area capacity consists of the horizontal and vertical bound which must be entered with a space ( ) separting them.");
        
        String terrainSurfaceArea = "810";
        //Act
        Request request = new Request(terrainSurfaceArea);
    }
    
    @Test
    public void constructor_throwsllegalArgumentException_whenTheTerrainsSurfaceAreaCapacityIsNotANumber() throws Exception {
        //Arrange
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Dear user, the terrain surface area capacity must be a number, consiting of the horizontal and vertical bounds of the surface area.");
        
        String terrainSurfaceArea = "AB C";
  
        Request request = new Request(terrainSurfaceArea);
    }
 
    @Test
    public void getTerrainHorizontalBound_returnsTheSameTerrainHorizontalBoundThatWasSetInTheConstructor() throws Exception {
        //Arrange
        String terrainSurfaceArea = "8 10";
        int terrainHorizontalBound = 8;
        //Act
        Request request = new Request(terrainSurfaceArea);
        //Assert
        assertEquals(terrainHorizontalBound, request.getTerrainHorizontalBound());
        //Arrange
        terrainSurfaceArea = "5 6";
        terrainHorizontalBound = 5;
        //Act
        request = new Request(terrainSurfaceArea);
        //Assert
        assertEquals(terrainHorizontalBound, request.getTerrainHorizontalBound());
    }
    
    @Test
    public void getTerrainHorizontalBound_returnsTheSameTerrainHorizontalBoundThatWasSetInTheConstructor_evenWhenThereAreMoreThanOneSpacesSeparatingTheBounds() throws Exception {
        //Arrange
        String terrainSurfaceArea = "8    10";
        int terrainHorizontalBound = 8;
        //Act
        Request request = new Request(terrainSurfaceArea);
        //Assert
        assertEquals(terrainHorizontalBound, request.getTerrainHorizontalBound());
        //Arrange
        terrainSurfaceArea = "12    5";
        terrainHorizontalBound = 12;
        //Act
        request = new Request(terrainSurfaceArea);
        //Assert
        assertEquals(terrainHorizontalBound, request.getTerrainHorizontalBound());
    }
    
    @Test
    public void getTerrainVerticalBound_returnsTheSameTerrainVerticalBoundThatWasSetInTheConstructor() throws Exception {
        //Arrange
        String terrainSurfaceArea = "5 7";
        int terrainVerticalBound = 7;
        //Act
        Request request = new Request(terrainSurfaceArea);
        //Assert
        assertEquals(terrainVerticalBound, request.getTerrainVerticalBound());
        //Arrange
        terrainSurfaceArea = "10 9";
        terrainVerticalBound = 9;
        //Act
        request = new Request(terrainSurfaceArea);
        //Assert
        assertEquals(terrainVerticalBound, request.getTerrainVerticalBound());
    }
    
    @Test
    public void getTerrainVerticalBound_returnsTheSameTerrainVerticalBoundThatWasSetInTheConstructor_evenWhenThereAreMoreThanOneSpacesSeparatingTheBounds() throws Exception {
        //Arrange
        String terrainSurfaceArea = "5      7";
        int terrainVerticalBound = 7;
        //Act
        Request request = new Request(terrainSurfaceArea);
        //Assert
        assertEquals(terrainVerticalBound, request.getTerrainVerticalBound());
        //Arrange
        terrainSurfaceArea = "10   9";
        terrainVerticalBound = 9;
        //Act
        request = new Request(terrainSurfaceArea);
        //Assert
        assertEquals(terrainVerticalBound, request.getTerrainVerticalBound());
    }
    
    @Test
    public void constructor_throwsIllegalArgumentException_whenTheRoversStartingInstructionValuesAreNotThreeInputValuesSeperatedByASpace() throws Exception {
        //Arrange
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Dear user, the Rover's starting instructions must be their starting horizontal coordinate, separated by a space ( ), their starting vertical coordinate, separated by a space ( _) and then their cardinal point which they are facing (only use the first letter of the direction).");
        String roverStartingInstructions = "12E";
        String terrainSurfaceArea = "8 10";
        //Act
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions);
        //Assert
    }
    
    @Test
    public void constructor_throwsIllegalArgumentException_whenTheRoversStartingInstructionValuesDontConsistOfANumberForStartingCoordinatesAndAnAlphabetForTheirCardinalPoint() throws Exception {
        //Arrange
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Dear user, the Rover's starting instructions must be their starting horizontal coordinate (number), separated by a space ( ), their starting vertical coordinate (number), separated by a space ( _) and then their cardinal point (alphabet) which they are facing (only use the first letter of the direction).");
        String roverStartingInstructions = "A 2 2";
        String terrainSurfaceArea = "8 10";
        //Act
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions);
        //Assert
    }
    
    @Test
    public void constructor_throwsIllegalArgumentException_whenCardinalPointOfTheRoversStartingInstructionValuesHasMoreThanOneAlphabets() throws Exception {
        //Arrange
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Dear user, the Rover's starting instructions must be their starting horizontal coordinate (number), separated by a space ( ), their starting vertical coordinate (number), separated by a space ( _) and then their cardinal point (alphabet) which they are facing (only use the first letter of the direction).");
        String roverStartingInstructions = "1 2 EE";
        String terrainSurfaceArea = "8 10";
        //Act
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions);
        //Assert
    }
    
    @Test
    public void getRoverHorizontalCoordinate_returnsTheRoversHorizontalCoordinateThatWasSetInTheConstructor() throws Exception {
        //Arrange
        String roverStartingInstructions = "1 2 E";
        String terrainSurfaceArea = "8 10";
        int roverHorizontalCoordinate = 1;
        //Act
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions);
        //Assert
        assertEquals(roverHorizontalCoordinate, request.getRoverHorizontalCoordinate());
        //Arrange
        roverStartingInstructions = "5 4 E";
        roverHorizontalCoordinate = 5;
        //Act
        request = new Request(terrainSurfaceArea, roverStartingInstructions);
        //Assert
        assertEquals(roverHorizontalCoordinate, request.getRoverHorizontalCoordinate());
    }
    
    @Test
    public void getRoverVerticalCoordinate_returnsTheRoversVerticalCoordinateThatWasSetInTheConstructor() throws Exception {
        //Arrange
        String roverStartingInstructions = "1 2 E";
        String terrainSurfaceArea = "8 10";
        int roverVerticalCoordinate = 2;
        //Act
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions);
        //Assert
        assertEquals(roverVerticalCoordinate, request.getRoverVerticalCoordinate());
        //Arrange
        roverStartingInstructions = "5 4 E";
        roverVerticalCoordinate = 4;
        //Act
        request = new Request(terrainSurfaceArea, roverStartingInstructions);
        //Assert
        assertEquals(roverVerticalCoordinate, request.getRoverVerticalCoordinate());
    }
    
    @Test
    public void getRoverCardinalPoint_returnsTheRoversCardinalPointThatWasSetInTheConstructor() throws Exception {
        //Arrange
        String roverStartingInstructions = "1 2 E";
        String terrainSurfaceArea = "8 10";
        String cardinalPoint = "E";
        //Act
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions);
        //Assert
        assertEquals(cardinalPoint, request.getRoverCardinalPoint());
        //Arrange
        roverStartingInstructions = "5 4 W";
        cardinalPoint = "W";
        //Act
        request = new Request(terrainSurfaceArea, roverStartingInstructions);
        //Assert
        assertEquals(cardinalPoint, request.getRoverCardinalPoint());
    }

    @Test
    public void constructor_throwsIllegalArgumentException_whenTheCommandsGivenToTheRoverAreNotAlphabets() throws Exception {
        //Arrange
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Dear user, the commands given to the rover must be alphabets and not contain spaces between them.");
        String roverStartingInstructions = "1 2 E";
        String terrainSurfaceArea = "8 10";
        String commands = "123";
        //Act
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
    }
    
    @Test
    public void constructor_throwsIllegalArgumentException_whenTheCommandsGivenToTheRoverAreHaveSpacesBetweenThem() throws Exception {
        //Arrange
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Dear user, the commands given to the rover must be alphabets and not contain spaces between them.");
        String roverStartingInstructions = "1 2 E";
        String terrainSurfaceArea = "8 10";
        String commands = "A A A";
        //Act
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
    }
    
    @Test
    public void getCommands_returnsAListContainingTheCommandsThatWereGivenInTheConstructor() throws Exception {
        //Arrange
        String roverStartingInstructions = "1 2 E";
        String terrainSurfaceArea = "8 10";
        String commands = "AABBCC";
        List<String> commandList = Arrays.asList(new String[]{"A", "A", "B", "B", "C", "C"});
        //Act
        Request request = new Request(terrainSurfaceArea, roverStartingInstructions, commands);
        //Assert
        assertEquals(commandList, request.getCommands());
    }
}
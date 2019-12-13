/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model;

import za.ac.vut.marsroverchallenge.exception.model.PointOutOfTerrainSurfaceAreaBoundsException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import za.ac.vut.marsroverchallenge.model.Rover.CardinalPoint;

/**
 *
 * @author 20180172
 */
public class RoverTest {
    @Before
    public void setUp() {
    }
    
    @Test
    public void test_getCoordinatePoint_returnsTheRoversCurrentCoordinatePointSetInTheConstructor() throws Exception {
        //Arrange
        CoordinatePoint currentPoint = new CoordinatePoint(0, 0);
        //Act
        Rover rover = new Rover(currentPoint);
        //Assert
        assertEquals(currentPoint, rover.getCoordinatePoint());
        //Arrange
        currentPoint = new CoordinatePoint(1, 3);
        //Act
        rover = new Rover(currentPoint);
        //Assert
        assertEquals(currentPoint, rover.getCoordinatePoint());
    }
    
    @Test
    public void test_getCardinalPoint_returnsTheRoversCurrentCardinalPointTheyAreFacingSetInTheConstructor() throws Exception {
        //Arrange
        CardinalPoint currentCardinalPoint = Rover.CardinalPoint.EAST;
        //Act
        Rover rover = new Rover(currentCardinalPoint);
        //Assert
        assertEquals(currentCardinalPoint, rover.getCardinalPoint());
        //Arrange
        currentCardinalPoint = Rover.CardinalPoint.WEST;
        //Act
        rover = new Rover(currentCardinalPoint);
        //Assert
        assertEquals(currentCardinalPoint, rover.getCardinalPoint());
    }
    
    @Test
    public void test_isPotentialMoveAllowed_returnsFalse_whenTheRequestedCoordinatePointOutsideTheTerrainsSurfaceArea() throws Exception {
        //Arrange
        int horizontalPoint = 2;
        int verticalPoint = 1;
        int horizontalBound = 1;
        int verticalBound = 1;
        //Act
        CoordinatePoint targetPoint = new CoordinatePoint(horizontalPoint, verticalPoint);
        Terrain currentTerrain = new Terrain(horizontalBound, verticalBound);
        //Assert
        assertFalse(Rover.isPotentialMoveAllowed(currentTerrain, targetPoint));
        //Arrange
        verticalPoint = 3;
        horizontalBound = 2;
        //Act
        targetPoint = new CoordinatePoint(horizontalPoint, verticalPoint);
        currentTerrain = new Terrain(horizontalBound, verticalBound);
        //Assert
        assertFalse(Rover.isPotentialMoveAllowed(currentTerrain, targetPoint));
    }
    
    @Test
    public void test_isPotentialMoveAllowed_returnsTrue_whenTheRequestedCoordinatePointOutsideTheTerrainsSurfaceArea() throws Exception {
        //Arrange
        int horizontalPoint = 2;
        int verticalPoint = 1;
        int horizontalBound = 4;
        int verticalBound = 4;
        //Act
        CoordinatePoint targetPoint = new CoordinatePoint(horizontalPoint, verticalPoint);
        Terrain currentTerrain = new Terrain(horizontalBound, verticalBound);
        //Assert
        assertTrue(Rover.isPotentialMoveAllowed(currentTerrain, targetPoint));
        //Arrange
        horizontalPoint = 5;
        horizontalBound = 6;
        //Act
        targetPoint = new CoordinatePoint(horizontalPoint, verticalPoint);
        currentTerrain = new Terrain(horizontalBound, verticalBound);
        //Assert
        assertTrue(Rover.isPotentialMoveAllowed(currentTerrain, targetPoint));
    }
    
    @Test(expected = PointOutOfTerrainSurfaceAreaBoundsException.class)
    public void test_moveOneStepForward_throwsPointOutOfTerrainSurfaceAreaBoundsException_whenTheRoverIsFacingEastwardsAndTheRequestedPointIsOutsideTheTerrainsSurfaceArea() throws Exception {
        //Arrange
        int horizontalCoordinate = 1;
        int verticalCoordinate = 1;
        int horizontalBound = 1;
        int verticalBound = 1;
        
        CoordinatePoint currentPoint = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        CardinalPoint currentCardinalPoint = Rover.CardinalPoint.EAST;
        Terrain terrain = new Terrain(horizontalBound, verticalBound);
        
        Rover rover = new Rover(currentPoint, currentCardinalPoint);
        
        rover.moveOneStepForward(terrain);
    }
    
    @Test(expected = PointOutOfTerrainSurfaceAreaBoundsException.class)
    public void test_moveOneStepForward_throwsPointOutOfTerrainSurfaceAreaBoundsException_whenTheRoverIsFacingWestwardsAndTheRequestedPointIsOutsideTheTerrainsSurfaceArea() throws Exception {
        //Arrange
        int horizontalCoordinate = 0;
        int verticalCoordinate = 2;
        int horizontalBound = 3;
        int verticalBound = 3;
        
        CoordinatePoint currentPoint = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        CardinalPoint currentCardinalPoint = Rover.CardinalPoint.WEST;
        Terrain terrain = new Terrain(horizontalBound, verticalBound);
        
        Rover rover = new Rover(currentPoint, currentCardinalPoint);
        
        rover.moveOneStepForward(terrain);
    }
    
    @Test(expected = PointOutOfTerrainSurfaceAreaBoundsException.class)
    public void test_moveOneStepForward_throwsPointOutOfTerrainSurfaceAreaBoundsException_whenTheRoverIsFacingNorthwardsAndTheRequestedPointIsOutsideTheTerrainsSurfaceArea() throws Exception {
        //Arrange
        int horizontalCoordinate = 2;
        int verticalCoordinate = 0;
        int horizontalBound = 2;
        int verticalBound = 2;
        
        CoordinatePoint currentPoint = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        CardinalPoint currentCardinalPoint = Rover.CardinalPoint.NORTH;
        Terrain terrain = new Terrain(horizontalBound, verticalBound);
        
        Rover rover = new Rover(currentPoint, currentCardinalPoint);
        
        rover.moveOneStepForward(terrain);
    }
    
    @Test(expected = PointOutOfTerrainSurfaceAreaBoundsException.class)
    public void test_moveOneStepForward_throwsPointOutOfTerrainSurfaceAreaBoundsException_whenTheRoverIsFacingSouthwardsAndTheRequestedPointIsOutsideTheTerrainsSurfaceArea() throws Exception {
        //Arrange
        int horizontalCoordinate = 1;
        int verticalCoordinate = 5;
        int horizontalBound = 5;
        int verticalBound = 5;
        
        CoordinatePoint currentPoint = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        CardinalPoint currentCardinalPoint = Rover.CardinalPoint.SOUTH;
        Terrain terrain = new Terrain(horizontalBound, verticalBound);
        
        Rover rover = new Rover(currentPoint, currentCardinalPoint);
        
        rover.moveOneStepForward(terrain);
    }
    
    @Test
    public void test_moveOneStepForward_increasesHorizontalCoordinateByOne_whenTheRoverIsFacingEastwardsAndTheRequestedPointIsWithinTheTerrainsSurfaceArea() throws Exception {
        //Arrange
        int horizontalCoordinate = 1;
        int expectedHorizontalCoordinate = 2;
        int verticalCoordinate = 1;
        int horizontalBound = 2;
        int verticalBound = 1;
        
        CoordinatePoint currentPoint = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        CardinalPoint currentCardinalPoint = Rover.CardinalPoint.EAST;
        Terrain terrain = new Terrain(horizontalBound, verticalBound);
        
        Rover rover = new Rover(currentPoint, currentCardinalPoint);
        //Act
        rover.moveOneStepForward(terrain);
        //Assert
        assertEquals(expectedHorizontalCoordinate, rover.getCurrentHorizontalCoordinate());
        //Arrange
        horizontalCoordinate = 3;
        expectedHorizontalCoordinate = 4;
        verticalCoordinate = 1;
        horizontalBound = 5;
        verticalBound = 1;
        
        currentPoint = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        terrain = new Terrain(horizontalBound, verticalBound);
        
        rover = new Rover(currentPoint, currentCardinalPoint);
        //Act
        rover.moveOneStepForward(terrain);
        //Assert
        assertEquals(expectedHorizontalCoordinate, rover.getCurrentHorizontalCoordinate());
    }
    
    @Test
    public void test_moveOneStepForward_decreasesHorizontalCoordinateByOne_whenTheRoverIsFacingWestwardsAndTheRequestedPointIsWithinTheTerrainsSurfaceArea() throws Exception {
        //Arrange
        int horizontalCoordinate = 2;
        int expectedHorizontalCoordinate = 1;
        int verticalCoordinate = 1;
        int horizontalBound = 3;
        int verticalBound = 3;
        
        CoordinatePoint currentPoint = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        CardinalPoint currentCardinalPoint = Rover.CardinalPoint.WEST;
        Terrain terrain = new Terrain(horizontalBound, verticalBound);
        
        Rover rover = new Rover(currentPoint, currentCardinalPoint);
        //Act
        rover.moveOneStepForward(terrain);
        //Assert
        assertEquals(expectedHorizontalCoordinate, rover.getCurrentHorizontalCoordinate());
        //Arrange
        horizontalCoordinate = 5;
        expectedHorizontalCoordinate = 4;
        verticalCoordinate = 1;
        horizontalBound = 5;
        verticalBound = 5;
        
        currentPoint = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        terrain = new Terrain(horizontalBound, verticalBound);
        
        rover = new Rover(currentPoint, currentCardinalPoint);
        //Act
        rover.moveOneStepForward(terrain);
        //Assert
        assertEquals(expectedHorizontalCoordinate, rover.getCurrentHorizontalCoordinate());
    }
    
    @Test
    public void test_moveOneStepForward_decreasesVerticalCoordinateByOne_whenTheRoverIsFacingNorthwardsAndTheRequestedPointIsWithinTheTerrainsSurfaceArea() throws Exception {
        //Arrange
        int horizontalCoordinate = 1;
        int verticalCoordinate = 2;
        int expectedVerticalCoordinate = 1;
        int horizontalBound = 2;
        int verticalBound = 2;
        
        CoordinatePoint currentPoint = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        CardinalPoint currentCardinalPoint = Rover.CardinalPoint.NORTH;
        Terrain terrain = new Terrain(horizontalBound, verticalBound);
        
        Rover rover = new Rover(currentPoint, currentCardinalPoint);
        //Act
        rover.moveOneStepForward(terrain);
        //Assert
        assertEquals(expectedVerticalCoordinate, rover.getCurrentVerticalCoordinate());
        //Arrange
        horizontalCoordinate = 3;
        verticalCoordinate = 4;
        expectedVerticalCoordinate = 3;
        horizontalBound = 6;
        verticalBound = 5;
        
        currentPoint = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        terrain = new Terrain(horizontalBound, verticalBound);
        
        rover = new Rover(currentPoint, currentCardinalPoint);
        //Act
        rover.moveOneStepForward(terrain);
        //Assert
        assertEquals(expectedVerticalCoordinate, rover.getCurrentVerticalCoordinate());
    }
    
    @Test
    public void test_moveOneStepForward_increasesVerticalCoordinateByOne_whenTheRoverIsFacingSouthwardsAndTheRequestedPointIsWithinTheTerrainsSurfaceArea() throws Exception {
        //Arrange
        int horizontalCoordinate = 2;
        int verticalCoordinate = 2;
        int expectedVerticalCoordinate = 3;
        int horizontalBound = 3;
        int verticalBound = 3;
        
        CoordinatePoint currentPoint = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        CardinalPoint currentCardinalPoint = Rover.CardinalPoint.SOUTH;
        Terrain terrain = new Terrain(horizontalBound, verticalBound);
        
        Rover rover = new Rover(currentPoint, currentCardinalPoint);
        //Act
        rover.moveOneStepForward(terrain);
        //Assert
        assertEquals(expectedVerticalCoordinate, rover.getCurrentVerticalCoordinate());
        //Arrange
        horizontalCoordinate = 5;
        verticalCoordinate = 5;
        expectedVerticalCoordinate = 6;
        horizontalBound = 5;
        verticalBound = 6;
        
        currentPoint = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        terrain = new Terrain(horizontalBound, verticalBound);
        
        rover = new Rover(currentPoint, currentCardinalPoint);
        //Act
        rover.moveOneStepForward(terrain);
        //Assert
        assertEquals(expectedVerticalCoordinate, rover.getCurrentVerticalCoordinate());
    }
}
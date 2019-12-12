/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 20180172
 */
public class TerrainTest {
    @Before
    public void setUp() {
    }

    @Test(expected = IllegalHorizontalBoundValueException.class)
    public void test_constructor_ThrowsAnIllegalHorizontalBoundValueException_WhenTheTerrainSurfaceHorizontalBoundIsLessThanOne() throws Exception{
        //Terrain's surface horizontal bound
        int horizontalBound = 0;
        //Terrain's surface vertical bound
        int verticalBound = 0;
        
        Terrain terrain = new Terrain(horizontalBound, verticalBound);
    }
    
    @Test(expected = IllegalVerticalBoundValueException.class)
    public void test_constructor_ThrowsAnIllegalVerticalBoundValueException_WhenTheTerrainSurfaceVerticalBoundIsLessThanOne() throws Exception {
        //Terrain's surface horizontal bound
        int horizontalBound = 1;
        //Terrain's surface vertical bound
        int verticalBound = 0;
        
        Terrain terrain = new Terrain(horizontalBound, verticalBound);
    }
    
    @Test
    public void test_checkIfPointIsWithinSurfaceArea_returnsFalse_whenRequestedHorizontalCoordinateIsOutsideTheSurfaceArea() throws Exception {
        //Terrain's surface horizontal bound
        int horizontalBound = 1;
        //Terrain's surface vertical bound
        int verticalBound = 1;
        //Horizontal coordinate
        int horizontalPosition = 2;
        //Vertical coordinate
        int verticalPosition = 1;
        
        Terrain terrain = new Terrain(horizontalBound, verticalBound);
        
        assertFalse(terrain.checkIfPointIsWithinSurfaceArea(horizontalPosition, verticalPosition));
    }
    
    @Test
    public void test_checkIfPointIsWithinSurfaceArea_returnsFalse_whenRequestedVerticalCoordinateIsOutsideTheSurfaceArea() throws Exception {
        //Terrain's surface horizontal bound
        int horizontalBound = 1;
        //Terrain's surface vertical bound
        int verticalBound = 1;
        //Horizontal coordinate
        int horizontalPosition = 1;
        //Vertical coordinate
        int verticalPosition = 2;
        
        Terrain terrain = new Terrain(horizontalBound, verticalBound);
        
        assertFalse(terrain.checkIfPointIsWithinSurfaceArea(horizontalPosition, verticalPosition));
    }
    
    @Test
    public void test_checkIfPointIsWithinSurfaceArea_returnsTrue_whenRequestedCoordinatePointIsWithinTheSurfaceArea() throws Exception {
        //Arrange
        //Terrain's surface horizontal bound
        int horizontalBound = 1;
        //Terrain's surface vertical bound
        int verticalBound = 1;
        //Horizontal coordinate
        int horizontalPosition = 1;
        //Vertical coordinate
        int verticalPosition = 1;
        
        Terrain terrain = new Terrain(horizontalBound, verticalBound);
        //Assert
        assertTrue(terrain.checkIfPointIsWithinSurfaceArea(horizontalPosition, verticalPosition));
        //Arrange
        //Horizontal coordinate
        horizontalPosition = 0;
        //Vertical coordinate
        verticalPosition = 0;
        //Assert
        assertTrue(terrain.checkIfPointIsWithinSurfaceArea(horizontalPosition, verticalPosition));
    }
}
package za.ac.vut.marsroverchallenge.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import za.ac.vut.marsroverchallenge.model.Request;

/**
 *
 * @author 20180172
 */
public class RequestValidatorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void checkIfTerrainSurfaceAreaCapacityIsValid_returnsFalse_whenThereIsNoSpaceSepartingTheTerrainsSurfaceAreaCapacity() throws Exception {
        //Arrange
        String terrainSurfaceArea = "810";
        //Assert
        assertFalse(RequestValidator.checkIfTerrainSurfaceAreaCapacityIsValid(terrainSurfaceArea));
    }
    
    @Test
    public void checkIfTerrainSurfaceAreaCapacityIsValid_returnsFalse_whenTheTerrainsSurfaceAreaCapacityIsNotANumber() throws Exception {
        //Arrange
        String terrainSurfaceArea = "AB C";
        //Assert
        assertFalse(RequestValidator.checkIfTerrainSurfaceAreaCapacityIsValid(terrainSurfaceArea));
    }
    
    @Ignore
    @Test
    public void checkIfTerrainSurfaceAreaCapacityIsValid_returnsTrue_whenTheTerrainsSurfaceAreaCapacityIsValid() throws Exception {
        //Arrange
        String terrainSurfaceArea = "8 10";
        //Assert
        assertTrue(RequestValidator.checkIfTerrainSurfaceAreaCapacityIsValid(terrainSurfaceArea));
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import za.ac.vut.marsroverchallenge.exception.model.IllegalCoordinateValueException;

/**
 *
 * @author 20180172
 */
public class CoordinatePointTest {
    @Before
    public void setUp() {
    }
    
    @Test(expected = IllegalCoordinateValueException.class)
    public void constructor_throwsAnIllegalCoordinateValueException_whenTheHorizontalCoordinateValueIsLessThanZero() throws Exception {
        //Arrange
        int horizontalCoordinate = -1;
        int verticalCoordinate = 0;
        
        CoordinatePoint point = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
    }
    
    @Test(expected = IllegalCoordinateValueException.class)
    public void constructor_throwsAnIllegalCoordinateValueException_whenTheVerticalCoordinateValueIsLessThanZero() throws Exception {
        //Arrange
        int horizontalCoordinate = 0;
        int verticalCoordinate = -1;
        //Act
        CoordinatePoint point = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
    }
    
    @Test
    public void getHorizontalCooridnate_returnsTheHorizontalCoordinateValueSetInTheConstructor() throws Exception {
        //Arrange
        int horizontalCoordinate = 0;
        int verticalCoordinate = 0;
        //Act
        CoordinatePoint point = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        //Assert
        assertEquals(horizontalCoordinate, point.getHorizontalCoordinate());
        //Arrange
        horizontalCoordinate = 1;
        //Act
        point = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        //Assert
        assertEquals(horizontalCoordinate, point.getHorizontalCoordinate());
    }
    
    @Test
    public void getVerticalCooridnate_returnsTheVerticalCoordinateValueSetInTheConstructor() throws Exception {
        //Arrange
        int horizontalCoordinate = 0;
        int verticalCoordinate = 0;
        //Act
        CoordinatePoint point = new CoordinatePoint(horizontalCoordinate, verticalCoordinate);
        //Assert
        assertEquals(horizontalCoordinate, point.getVerticalCoordinate());
    }
}
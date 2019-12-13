/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model;

import za.ac.vut.marsroverchallenge.exception.model.IllegalCoordinateValueException;

/**
 *
 * @author 20180172
 */
public class CoordinatePoint implements ICoordinatePoint{
    //The point's horizontal coordinate
    private int horizontalCoordinate;
    //The point's vertical coordinate
    private int verticalCoordinate;
    
    /**
     * 
     * @param horizontalCoordinate
     * @param verticalCoordinate
     * @throws IllegalCoordinateValueException 
     */
    public CoordinatePoint(int horizontalCoordinate, int verticalCoordinate) throws IllegalCoordinateValueException {
        //Check whether the horizontal coordinate is less than 0
        if(horizontalCoordinate < 0)
            throw new IllegalCoordinateValueException();
        //Check whether the vertical coordinate is less than 0
        if(verticalCoordinate < 0)
            throw new IllegalCoordinateValueException();
        
        this.horizontalCoordinate = horizontalCoordinate;
        this.verticalCoordinate = verticalCoordinate;
    }
    
    /**
     * Gets the point's horizontal coordinate
     * @return integer horizontal coordinate
     */
    @Override
    public int getHorizontalCoordinate() {
        return this.horizontalCoordinate;
    }

    /**
     * Gets the point's vertical coordinate
     * @return integer vertical coordinate 
     */
    @Override
    public int getVerticalCoordinate() {
        return this.verticalCoordinate;
    }
    
}
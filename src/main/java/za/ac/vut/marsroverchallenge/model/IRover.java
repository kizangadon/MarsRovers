/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model;

import za.ac.vut.marsroverchallenge.exception.model.IllegalCoordinateValueException;
import za.ac.vut.marsroverchallenge.exception.model.PointOutOfTerrainSurfaceAreaBoundsException;

/**
 *
 * @author 20180172
 */
public interface IRover {
    Rover.CardinalPoint getCardinalPoint();
    CoordinatePoint getCoordinatePoint();
    int getCurrentHorizontalCoordinate();
    int getCurrentVerticalCoordinate();
    void moveOneStepForward(Terrain terrain) throws PointOutOfTerrainSurfaceAreaBoundsException, IllegalCoordinateValueException;
    void rotateNinetyDegreesLeft();
    void rotateNinetyDegreesRight();
}

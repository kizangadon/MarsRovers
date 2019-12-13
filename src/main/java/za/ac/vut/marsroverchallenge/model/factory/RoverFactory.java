/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model.factory;

import za.ac.vut.marsroverchallenge.exception.model.IllegalCardinalPointValueException;
import za.ac.vut.marsroverchallenge.exception.model.IllegalCoordinateValueException;
import za.ac.vut.marsroverchallenge.model.CoordinatePoint;
import za.ac.vut.marsroverchallenge.model.Rover;

/**
 *
 * @author 20180172
 */
public class RoverFactory {
    public static Rover createRover(int horizontalPosition, int verticalPosition, String cardinalPoint) throws IllegalCoordinateValueException, IllegalCardinalPointValueException {
        return new Rover(new CoordinatePoint(horizontalPosition, verticalPosition), CardinalPointFactory.createCardinalPoint(cardinalPoint));
    }
}
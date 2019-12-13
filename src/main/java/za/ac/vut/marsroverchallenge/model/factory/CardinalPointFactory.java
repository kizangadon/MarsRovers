/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model.factory;

import za.ac.vut.marsroverchallenge.exception.model.IllegalCardinalPointValueException;
import za.ac.vut.marsroverchallenge.model.Rover;

/**
 *
 * @author 20180172
 */
public class CardinalPointFactory {
    public static Rover.CardinalPoint createCardinalPoint(String cardinalDirection) throws IllegalCardinalPointValueException {
        Rover.CardinalPoint cardinalPoint;
        
        switch(cardinalDirection){
            case "E":
                cardinalPoint = Rover.CardinalPoint.EAST;
                break;
            case "W":
                cardinalPoint = Rover.CardinalPoint.WEST;
                break;
            case "N":
                cardinalPoint = Rover.CardinalPoint.NORTH;
                break;
            case "S":
                cardinalPoint = Rover.CardinalPoint.SOUTH;
                break;
            default:
                throw new IllegalCardinalPointValueException();
        }
        
        return cardinalPoint;
    }
}
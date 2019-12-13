/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model.factory;

import za.ac.vut.marsroverchallenge.model.Rover;

/**
 *
 * @author 20180172
 */
public class CardinalPointFactory {
    public static Rover.CardinalPoint createFacingDirection(String facingDirection) {
        Rover.CardinalPoint cardinalPoint;
        
        switch(facingDirection){
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
                cardinalPoint = null;
                break;
        }
        
        return cardinalPoint;
    }
}
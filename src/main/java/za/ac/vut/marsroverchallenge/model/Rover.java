/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model;

import java.util.Objects;
import za.ac.vut.marsroverchallenge.exception.model.IllegalCoordinateValueException;
import za.ac.vut.marsroverchallenge.exception.model.PointOutOfTerrainSurfaceAreaBoundsException;

/**
 *
 * @author 20180172
 */
public class Rover implements IRover{
    //The Rover's current coordinate point on a terrain.
    private CoordinatePoint point;
    //The Rover's current cardinal point that they are facing on a terrain
    private CardinalPoint cardinalPoint;

    /**
     * The Cardinal Point a Rover could be facing
     */
    public enum CardinalPoint{
        EAST("E"),
        WEST("W"), 
        NORTH("N"), 
        SOUTH("S");
        
        /**
         * The cardinal point a Rover could be facing
         */
        private final String cardinalPoint;
        
        private CardinalPoint(String cardinalPoint){
            this.cardinalPoint = cardinalPoint;
        }

        /**
         * 
         * @return cardinal point 
         */
        @Override
        public String toString() {
            return cardinalPoint;
        } 
    }
    
    /**
     * 
     * @param coordinatePoint 
     */
    public Rover(CoordinatePoint coordinatePoint) {
        this.point = coordinatePoint;
    }

    /**
     * 
     * @param cardinalPoint 
     */
    public Rover(CardinalPoint cardinalPoint) {
        this.cardinalPoint = cardinalPoint;
    }
    
    public Rover(CoordinatePoint coordinatePoint, CardinalPoint cardinalPoint) {
        this.point = coordinatePoint;
        this.cardinalPoint = cardinalPoint;
    }
    
    /**
     * Moves the rover's one step forward whilst facing east if there is 
     * still space for one more step on the terrain's surface area
     * @param terrain
     * @throws PointOutOfTerrainSurfaceAreaBoundsException
     * @throws IllegalCoordinateValueException 
     */
    private void moveOneStepForwardEastwards(Terrain terrain) throws PointOutOfTerrainSurfaceAreaBoundsException, IllegalCoordinateValueException {
        int targetHorizontalCoordinate = this.getCurrentHorizontalCoordinate() + 1;
        int targetVerticalCoordinate = this.point.getVerticalCoordinate();
        
        CoordinatePoint targetPoint = new CoordinatePoint(targetHorizontalCoordinate, targetVerticalCoordinate);
        
        if(!Rover.isPotentialMoveAllowed(terrain, targetPoint))
            throw new PointOutOfTerrainSurfaceAreaBoundsException();
        
        this.point = targetPoint;
    }
    
    private void moveOneStepForwardWestwards(Terrain terrain) throws PointOutOfTerrainSurfaceAreaBoundsException, IllegalCoordinateValueException {
        int targetHorizontalCoordinate = this.getCurrentHorizontalCoordinate() - 1;
        int targetVerticalCoordinate = this.point.getVerticalCoordinate();
        
        if(targetHorizontalCoordinate < 0)
            throw new PointOutOfTerrainSurfaceAreaBoundsException();
        
        CoordinatePoint targetPoint = new CoordinatePoint(targetHorizontalCoordinate, targetVerticalCoordinate);
        
        if(!Rover.isPotentialMoveAllowed(terrain, targetPoint))
            throw new PointOutOfTerrainSurfaceAreaBoundsException();
        
        this.point = targetPoint;
    }
    
    private void moveOneStepForwardNorthwards(Terrain terrain) throws PointOutOfTerrainSurfaceAreaBoundsException, IllegalCoordinateValueException {
        int targetHorizontalCoordinate = this.getCurrentHorizontalCoordinate();
        int targetVerticalCoordinate = this.point.getVerticalCoordinate() - 1;
        
        if(targetVerticalCoordinate < 0)
            throw new PointOutOfTerrainSurfaceAreaBoundsException();
        
        CoordinatePoint targetPoint = new CoordinatePoint(targetHorizontalCoordinate, targetVerticalCoordinate);
        
        if(!Rover.isPotentialMoveAllowed(terrain, targetPoint))
            throw new PointOutOfTerrainSurfaceAreaBoundsException();
        
        this.point = targetPoint;
    }
    
    private void moveOneStepForwardSouthwards(Terrain terrain) throws PointOutOfTerrainSurfaceAreaBoundsException, IllegalCoordinateValueException {
        int targetHorizontalCoordinate = this.getCurrentHorizontalCoordinate();
        int targetVerticalCoordinate = this.point.getVerticalCoordinate() + 1;
        
        if(targetVerticalCoordinate < 0)
            throw new PointOutOfTerrainSurfaceAreaBoundsException();
        
        CoordinatePoint targetPoint = new CoordinatePoint(targetHorizontalCoordinate, targetVerticalCoordinate);
        
        if(!Rover.isPotentialMoveAllowed(terrain, targetPoint))
            throw new PointOutOfTerrainSurfaceAreaBoundsException();
        
        this.point = targetPoint;
    }

    /**
     * Gets the Rover's current cardinal point they are facing 
     * @return cardinal point 
     */
    @Override
    public CardinalPoint getCardinalPoint() {
        return cardinalPoint;
    }
    
    /**
     * Gets the Rover's current coordinate point on a terrain
     * @return coordinate point 
     */
    @Override
    public CoordinatePoint getCoordinatePoint() {
        return point;
    }
    
    /**
     * Gets the rover's current horizontal coordinate on a terrain
     * @return current horizontal coordinate
     */
    @Override
    public int getCurrentHorizontalCoordinate() {
        return point.getHorizontalCoordinate();
    }
    
    /**
     * Gets the rover's current vertical coordinate on a terrain
     * @return current vertical coordinate
     */
    @Override
    public int getCurrentVerticalCoordinate() {
        return point.getVerticalCoordinate();
    }
    
    /**
     * Checks whether a Rover can move to a specific coordinate point on a terrain or not
     * @param terrain
     * @param point
     * @return boolean
     */
    public static boolean isPotentialMoveAllowed(ITerrain terrain, ICoordinatePoint point) {
        return terrain.checkIfPointIsWithinSurfaceArea(point.getHorizontalCoordinate(), point.getVerticalCoordinate());
    }
    
    /**
     * Moves the rover's one step forward in the direction they are facing if there is 
     * still space for one more step on the terrain's surface area
     * @param terrain
     * @throws PointOutOfTerrainSurfaceAreaBoundsException
     * @throws IllegalCoordinateValueException 
     */
    @Override
    public void moveOneStepForward(Terrain terrain) throws PointOutOfTerrainSurfaceAreaBoundsException, IllegalCoordinateValueException {
        switch(cardinalPoint){
            case EAST:
                moveOneStepForwardEastwards(terrain);
                break;
            case WEST:
                moveOneStepForwardWestwards(terrain);
                break;
            case NORTH:
                moveOneStepForwardNorthwards(terrain);
                break;
            case SOUTH:
                moveOneStepForwardSouthwards(terrain);
        }
    }
    
    @Override
    public void rotateNinetyDegreesLeft() {
        switch(cardinalPoint){
            case EAST:
                cardinalPoint = CardinalPoint.NORTH;
                break;
            case WEST:
                cardinalPoint = CardinalPoint.SOUTH;
                break;
            case NORTH:
                cardinalPoint = CardinalPoint.WEST;
                break;
            case SOUTH:
                cardinalPoint = CardinalPoint.EAST;
                break;
        }
    }
    
    @Override
    public void rotateNinetyDegreesRight() {
        switch(cardinalPoint){
            case EAST:
                cardinalPoint = CardinalPoint.SOUTH;
                break;
            case WEST:
                cardinalPoint = CardinalPoint.NORTH;
                break;
            case NORTH:
                cardinalPoint = CardinalPoint.EAST;
                break;
            case SOUTH:
                cardinalPoint = CardinalPoint.WEST;
                break;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.point);
        hash = 79 * hash + Objects.hashCode(this.cardinalPoint);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rover other = (Rover) obj;
        if (!Objects.equals(this.point, other.point)) {
            return false;
        }
        if (this.cardinalPoint != other.cardinalPoint) {
            return false;
        }
        return true;
    }
    
}
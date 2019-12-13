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
    private CoordinatePoint currentPoint;
    //The Rover's current cardinal point that they are facing on a terrain
    private CardinalPoint currentCardinalPoint;

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
        this.currentPoint = coordinatePoint;
    }

    /**
     * 
     * @param cardinalPoint 
     */
    public Rover(CardinalPoint cardinalPoint) {
        this.currentCardinalPoint = cardinalPoint;
    }
    
    public Rover(CoordinatePoint coordinatePoint, CardinalPoint cardinalPoint) {
        this.currentPoint = coordinatePoint;
        this.currentCardinalPoint = cardinalPoint;
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
        int targetVerticalCoordinate = this.currentPoint.getVerticalCoordinate();
        
        CoordinatePoint targetPoint = new CoordinatePoint(targetHorizontalCoordinate, targetVerticalCoordinate);
        
        if(!Rover.isPotentialMoveAllowed(terrain, targetPoint))
            throw new PointOutOfTerrainSurfaceAreaBoundsException();
        
        this.currentPoint = targetPoint;
    }
    
    private void moveOneStepForwardWestwards(Terrain terrain) throws PointOutOfTerrainSurfaceAreaBoundsException, IllegalCoordinateValueException {
        int targetHorizontalCoordinate = this.getCurrentHorizontalCoordinate() - 1;
        int targetVerticalCoordinate = this.currentPoint.getVerticalCoordinate();
        
        if(targetHorizontalCoordinate < 0)
            throw new PointOutOfTerrainSurfaceAreaBoundsException();
        
        CoordinatePoint targetPoint = new CoordinatePoint(targetHorizontalCoordinate, targetVerticalCoordinate);
        
        if(!Rover.isPotentialMoveAllowed(terrain, targetPoint))
            throw new PointOutOfTerrainSurfaceAreaBoundsException();
        
        this.currentPoint = targetPoint;
    }
    
    private void moveOneStepForwardNorthwards(Terrain terrain) throws PointOutOfTerrainSurfaceAreaBoundsException, IllegalCoordinateValueException {
        int targetHorizontalCoordinate = this.getCurrentHorizontalCoordinate();
        int targetVerticalCoordinate = this.currentPoint.getVerticalCoordinate() - 1;
        
        if(targetVerticalCoordinate < 0)
            throw new PointOutOfTerrainSurfaceAreaBoundsException();
        
        CoordinatePoint targetPoint = new CoordinatePoint(targetHorizontalCoordinate, targetVerticalCoordinate);
        
        if(!Rover.isPotentialMoveAllowed(terrain, targetPoint))
            throw new PointOutOfTerrainSurfaceAreaBoundsException();
        
        this.currentPoint = targetPoint;
    }
    
    private void moveOneStepForwardSouthwards(Terrain terrain) throws PointOutOfTerrainSurfaceAreaBoundsException, IllegalCoordinateValueException {
        int targetHorizontalCoordinate = this.getCurrentHorizontalCoordinate();
        int targetVerticalCoordinate = this.currentPoint.getVerticalCoordinate() + 1;
        
        if(targetVerticalCoordinate < 0)
            throw new PointOutOfTerrainSurfaceAreaBoundsException();
        
        CoordinatePoint targetPoint = new CoordinatePoint(targetHorizontalCoordinate, targetVerticalCoordinate);
        
        if(!Rover.isPotentialMoveAllowed(terrain, targetPoint))
            throw new PointOutOfTerrainSurfaceAreaBoundsException();
        
        this.currentPoint = targetPoint;
    }

    /**
     * Gets the Rover's current cardinal point they are facing 
     * @return cardinal point 
     */
    @Override
    public CardinalPoint getCardinalPoint() {
        return currentCardinalPoint;
    }
    
    /**
     * Gets the Rover's current coordinate point on a terrain
     * @return coordinate point 
     */
    @Override
    public CoordinatePoint getCoordinatePoint() {
        return currentPoint;
    }
    
    /**
     * Gets the rover's current horizontal coordinate on a terrain
     * @return current horizontal coordinate
     */
    @Override
    public int getCurrentHorizontalCoordinate() {
        return currentPoint.getHorizontalCoordinate();
    }
    
    /**
     * Gets the rover's current vertical coordinate on a terrain
     * @return current vertical coordinate
     */
    @Override
    public int getCurrentVerticalCoordinate() {
        return currentPoint.getVerticalCoordinate();
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
        switch(currentCardinalPoint){
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
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.currentPoint);
        hash = 79 * hash + Objects.hashCode(this.currentCardinalPoint);
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
        if (!Objects.equals(this.currentPoint, other.currentPoint)) {
            return false;
        }
        if (this.currentCardinalPoint != other.currentCardinalPoint) {
            return false;
        }
        return true;
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model;

import za.ac.vut.marsroverchallenge.exception.model.IllegalBoundValueException;

/**
 *
 * @author 20180172
 */
public class Terrain implements ITerrain{
    //Terrain's surface area's/zone horizontal bounds
    private int horizontalBound;
    //Terrain's surface area/zone vertical bounds
    private int verticalBound;
    
    /**
     * 
     * @param horizontalBound
     * @param verticalBound
     * @throws IllegalBoundValueException 
     */
    public Terrain(int horizontalBound, int verticalBound) throws IllegalBoundValueException {
        //Check whether the horizontal bound is less than one
        if(horizontalBound < 1)
            throw new IllegalBoundValueException();
        //Check whether the vertical bound is less than one
        if(verticalBound < 1)
            throw new IllegalBoundValueException();
        
        this.horizontalBound = horizontalBound;
        this.verticalBound = verticalBound;
    }

    /**
     * Checks whether a giver coordinate point is within the terrain's surface area
     * @param horizontalPosition
     * @param verticalPosition
     * @return boolean
     */
    @Override
    public boolean checkIfPointIsWithinSurfaceArea(int horizontalPosition, int verticalPosition) {
        return (this.horizontalBound >= horizontalPosition && this.verticalBound >= verticalPosition);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.horizontalBound;
        hash = 89 * hash + this.verticalBound;
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
        final Terrain other = (Terrain) obj;
        if (this.horizontalBound != other.horizontalBound) {
            return false;
        }
        if (this.verticalBound != other.verticalBound) {
            return false;
        }
        return true;
    }    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model;

/**
 *
 * @author 20180172
 */
class Terrain {

    Terrain(int horizontalBound, int verticalBound) throws IllegalHorizontalBoundValueException, IllegalVerticalBoundValueException {
        //Check whether the horizontal bound is less than one
        if(horizontalBound < 1)
            throw new IllegalHorizontalBoundValueException();
        //Check whether the vertical bound is less than one
        if(verticalBound < 1)
            throw new IllegalVerticalBoundValueException();
    }

    boolean checkIfPointIsWithinSurfaceArea(int horizontalPosition, int verticalPosition) {
        return false;
    }
    
}
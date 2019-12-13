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
public interface ITerrain {
    /**
     * Checks whether a giver coordinate point is within the terrain's surface area
     * @param horizontalPosition
     * @param verticalPosition
     * @return boolean
     */
    boolean checkIfPointIsWithinSurfaceArea(int horizontalPosition, int verticalPosition);
}
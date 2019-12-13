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
public interface ICoordinatePoint {
    /**
     * Gets the point's horizontal coordinate
     * @return integer horizontal coordinate
     */
    int getHorizontalCoordinate();
    /**
     * Gets the point's vertical coordinate
     * @return integer vertical coordinate 
     */
    int getVerticalCoordinate();
}
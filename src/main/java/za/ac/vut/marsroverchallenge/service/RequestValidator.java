/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.service;

/**
 *
 * @author 20180172
 */
public class RequestValidator {
    public static boolean checkIfTerrainSurfaceAreaCapacityIsValid(String terrainSurfaceArea) {
        if(!terrainSurfaceArea.contains(" "))
            return false;
        
        return false;
    }
    
}
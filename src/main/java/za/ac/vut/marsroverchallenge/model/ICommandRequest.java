/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model;

import java.util.List;

/**
 *
 * @author 20180172
 */
public interface ICommandRequest {
    int getTerrainHorizontalBound();
    int getTerrainVerticalBound();
    int getRoverHorizontalCoordinate();
    int getRoverVerticalCoordinate();
    String getRoverCardinalPoint();
    List<String> getCommands();
}
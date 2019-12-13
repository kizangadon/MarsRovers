/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.service;

import java.util.List;
import za.ac.vut.marsroverchallenge.exception.model.IllegalCoordinateValueException;
import za.ac.vut.marsroverchallenge.exception.model.RoverCannotMoveForwardException;
import za.ac.vut.marsroverchallenge.model.Command;
import za.ac.vut.marsroverchallenge.model.Request;
import za.ac.vut.marsroverchallenge.model.Rover;
import za.ac.vut.marsroverchallenge.model.Terrain;

/**
 *
 * @author 20180172
 */
public interface ICommandMediator {
    void executeCommand(Command command) throws RoverCannotMoveForwardException, IllegalCoordinateValueException;
    void executeCommands();
    List<Command> getCommands();
    Request getRequest();
    Rover getRover();
    String getRoverLocationAndCardinalPoint();
    Terrain getTerrain();
}
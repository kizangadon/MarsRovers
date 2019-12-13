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
public enum Command {
    MOVE_ONE_SPACE_FORWARD("M"),
    ROTATE_NINETY_DEGREES_RIGHT("R"),
    ROTATE_NINETY_DEGREES_LEFT("L");
    
    private final String command;

    private Command(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }
}
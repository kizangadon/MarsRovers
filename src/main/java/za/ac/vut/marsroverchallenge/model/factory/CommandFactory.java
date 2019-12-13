/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model.factory;

import java.util.ArrayList;
import java.util.List;
import za.ac.vut.marsroverchallenge.exception.model.IllegalCommandValueException;
import za.ac.vut.marsroverchallenge.model.Command;

/**
 *
 * @author 20180172
 */
public class CommandFactory {

    private static Command createCommand(char commandRequest) throws IllegalCommandValueException {
        Command command;

        switch (commandRequest) {
            case 'M':
                command = Command.MOVE_ONE_SPACE_FORWARD;
                break;
            case 'R':
                command = Command.ROTATE_NINETY_DEGREES_RIGHT;
                break;
            case 'L':
                command = Command.ROTATE_NINETY_DEGREES_LEFT;
                break;
            default:
                throw new IllegalCommandValueException("Dear user, the command you gave an invalid command. Supported commands are M, L and R.");
        }

        return command;
    }

    public static List<Command> createCommands(String commands) throws IllegalCommandValueException {

        List<Command> commandList = new ArrayList<>();

        for (char command : commands.toCharArray()) {
            commandList.add(createCommand(command));
        }

        return commandList;
    }
}

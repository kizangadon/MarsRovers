/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model.factory;

import java.util.ArrayList;
import java.util.List;
import za.ac.vut.marsroverchallenge.exception.model.IllegalCommandValueException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Rule;

import org.junit.rules.ExpectedException;
import za.ac.vut.marsroverchallenge.model.Command;

/**
 *
 * @author 20180172
 */
public class CommandFactoryTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void createCommands_throwsIllegalCommandValueException_whenTheRequestedCommandDoesNotExist() throws Exception {
        //Arrange
        expectedException.expect(IllegalCommandValueException.class);
        expectedException.expectMessage("Dear user, the command you gave an invalid command. Supported commands are M, L and R.");
        String commands = "E";
        //Act
        CommandFactory.createCommands(commands);
        //Assert
    }
    
    @Test
    public void createCommands_returnsTheCorrectCommandThatWasRequested() throws Exception {
        //Arrange
        String commands = "M";
        List<Command> commandList = new ArrayList<>();
        
        commandList.add(Command.MOVE_ONE_SPACE_FORWARD);
        //Assert
        assertEquals(commandList, CommandFactory.createCommands(commands));
        //Arrange
        commands = "MLM";
        commandList = new ArrayList<>();
        
        commandList.add(Command.MOVE_ONE_SPACE_FORWARD);
        commandList.add(Command.ROTATE_NINETY_DEGREES_LEFT);
        commandList.add(Command.MOVE_ONE_SPACE_FORWARD);
        //Assert
        assertEquals(commandList, CommandFactory.createCommands(commands));
        //Arrange
        commands = "RLM";
        commandList = new ArrayList<>();
        
        commandList.add(Command.ROTATE_NINETY_DEGREES_RIGHT);
        commandList.add(Command.ROTATE_NINETY_DEGREES_LEFT);
        commandList.add(Command.MOVE_ONE_SPACE_FORWARD);
        //Assert
        assertEquals(commandList, CommandFactory.createCommands(commands));
    }
}
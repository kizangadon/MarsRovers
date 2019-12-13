/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.vut.marsroverchallenge.model;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;

/**
 *
 * @author 20180172
 */
public class CommandRequestTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void test_constructor_throwsllegalArgumentException_whenTheTerrainsSurfaceAreaCapacityIsNotANumber() throws Exception {
        //Arrange
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Dear user, the terrain surface area capacity must be a number, consiting of the horizontal and vertical bounds of the surface area");
        
        String terrainSurfaceArea = "ABC";
  
        CommandRequest request = new CommandRequest(terrainSurfaceArea);
    }
    
    @Test
    public void test_constructor_throwsllegalArgumentException_whenThereIsNoSpaceSepartingTheTerrainsSurfaceAreaCapacity() throws Exception {
        //Arrange
        String terrainSurfaceArea = "810";
        
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Dear user, the terrain surface area capacity consists of the horizontal and vertical bound which must be entered with a space ( ) separting them. ");
    }
}

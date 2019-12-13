# MarsRoverChallenge
Code challenge from Next45

Deliverables:
Clear instructions on how to use your program.
Brief decussion of the design decisions made and how you ensured code correctness.
List of inputs to program.

How To Use The Program
1. This program is executed through the console, once executed, the application's menu is shown.
2. Select An option out of two options, which are: Give our selected rover new instructions or exit the application.
3. When you select the first option (Giving our selected rover new instructions), you are prompted with giving the three instructions to our rover. Which are: 
    1. The Selected terrains surface area (zone) bounds, which are the horizontal and vertical bounds, instructions of how to enter this input is given. 
    2. The selected rover's starting coordinates and cardinal point/direction, instructions of the format of the expected input is given (1 2 E -> horizontal point: 1, vertical point: 2, cardinal point/direction: Eastwards). 
    3. The list of commands given to our rover, instructions of the format of the expected input is given (MMLMR -> move forward, move forward, rotate ninety degrees left, move forward, rotate ninety degrees right), the case of the alphabets don't matter.
    When you select the second/last option which is exit, the application closes.
    
Design Decision
1. Initially I wanted to get right to the solution of the problem without wasting a lot of time, so I first started with defining the problem scope.
    In this case was, we have Rovers who have been sent to Mars to survey the terrain and they need a system that will help them navigate this terrain. Mars' surface or the terrain which will be surveyed os a two-dimensional grid. The terrain has been surveyed ahead of time and has been marked safe within certain bounds, therefore we can identify this area using horizontal and vertical bounds which the Rover can explore safely. The Rover understands cardinal points/directions and we've been given the directions which they can face at any time: East (E), West (W), North (N) or South (S). The Rover understands three commands which are M, R, L and with these commands is how the rover will navigate the identified terrain. One of the constraints is that you can only send a Rover a single list of commands at any given time. Due to time constraints, I used the CRC Cards technique to identify what objects are going to be needed in my solution. I identified that we will need a terrain, the area which are Rover will explore, the rover object was the give away here, as this is the sole owner of this system. I identified that we will need some way of representing a command so I made it an object as it holds information that the Rover will use to act. I identifed a CoordinatePoint as a specific point on the terrain's surface area and I would like it represented as we will use this to communicate with the Rover of where they are right now and where to move. I identified a CardinalPoint as an object that the Rover is aware off, initial I wanted to make it a property but then realised that there are more than one and are fixed, so we can make it an enumeration of some sort. I then modified the command object into an enumeration as there are only a given number of commands that a Rover can take. In order to abstract our domain model from the outside, I decided to create a request object which will contain the user's instructions to the Rover. I then thought of the mediator design pattern for the use of communication between the rover the user and the terrain without them all being dependant on each other. Initially I could just of called the CommandMediator a controller, the mediator is to help us have a generally solution for the different architectures, let's say we want to make this a rest service, we can just create the CommandMediator in the controller. I then wrote use cases for the identified classes and test specs were derived from the use cases.
2. How to ensure code correctness?
    I plan to ensure code correctness by build a test-driven solution, using good object-oriented programming principles. The solution will use function testing on two levels, unit-testing and integeration testing. Individual classes will be unit tested whilst components will be integerated using the controller/mediator. SOLID principles will be used in this project. Every class that communicates with another class, will communicate with it's interface instead of that concrete class (Dependency Inversion). Factories will help the instantiation of certain complex classes.

Extras
    Tests
	Unit Tests
		Terain
			class throws an exception when the surface area's horizontal bounds is less than 1.
			class throws an exception when the surface area's vertical bounds is less than 1.
			checkIfPointIsWithinSurfaceArea returns false when a cardinal point is not within its surface area/zone.
			checlIfPointIsWithinSurfaceArea returns true when a cardinal point is within its surface area/zone.
		CoordinatePoint
			class throws an exception when the horizontal position is less than zero.
			class throws an exception when the vertical position is less than zero.
			getHorizontal position returns the same value that was set in the constructor.
			getVertical position returns the same value that was set in the constructor.
		CardinalPoint
		Rover
			getCurrentPosition returns the same CardinalPoint that was set in the constructor.
			getCurrentFacingDirection returns the same RoverFacingDirection that was set in the constructor.
			isPotentialMoveValid returns false if the CoordinatePoint is not within the Terain's surface area.
			isPotentialMoveValid returns true if the CoordinatePoint is within the Terain's surface area.
			moveOneStepForwardEast throws an exception if by increasing the Rover's horizontal coordinate by one puts the rover outside the terain's surface area.
			moveOneStepForwardEast increases the Rover's horizontal coordinate by 1 if that horizontal coordinate is within the Terain's surface area.
			moveOneStepForwardWest throws an exception if by decreasing the Rover's horizontal coordinate by one puts the rover outside the terain's surface area.
			moveOneStepForwardWest increases the Rover's vertical coordinate by 1 if that horizontal coordinate is within the Terain's surface area.
			moveOneStepForwardNorth throws an exception if by increasing the Rover's vertical coordinate by one puts the rover outside the terain's surface area.
			moveOneStepForwardNorth increases the Rover's horizontal coordinate by 1 if that vetical coordinate is within the Terain's surface area.
			moveOneStepForwardSouth throws an exception if by increasing the Rover's vertical coordinate by one puts the rover outside the terain's surface area.
			moveOneStepForwardSouth increases the Rover's horizontal coordinate by 1 if that vertical coordinate is within the Terain's surface area.
			rotateNinetyDegreesLeft changes the Rover's cardinal point to North if the Rover's current cardinal point is East.
			rotateNinetyDegreesLeft changes the Rover's cardinal point to South if the Rover's current cardinal point is West.
			rotateNinetyDegreesLeft changes the Rover's cardinal point to West if the Rover's current cardinal point is North.
			rotateNinetyDegreesLeft changes the Rover's cardinal point to East if the Rover's current cardinal point is South.
			rotateNinetyDegreesRight changes the Rover's cardinal point to South if the Rover's current cardinal point is East.
			rotateNinetyDegreesRight changes the Rover's cardinal point to North if the Rover's current cardinal point is West.
			rotateNinetyDegreesRight changes the Rover's cardinal point to East if the Rover's current cardinal point is North.
			rotateNinetyDegreesRight changes the Rover's cardinal point to West if the Rover's current cardinal point is South.
		CommandRequest
			setTerrainSurfaceArea throws an illegal argument exception when there is no space between the two supplied bounds.
			setTerrainSurfaceArea throws an illegal argument exception when the surface area capacity is not a number.
			setTerrainSurfaceArea throws an illegal argument exception when the surface area capacity are not numbers.
			setRoverStartingInstructions throws an illegal argument exception when the rover's starting instructions are not numbers or the rover's cardinal point is not an alphabet.
			setRoverStartingInstructions throws an illegal argument exception when the rover's commands are not alphabets.
			setCommands throws an illegal argument exception when the rover's commands contain spaces between them
			getSurfaceareaCapacity returns the same horizontal and vertical bounds that were set in the constructor.
			getRoverStartingPosition returns the same coordinate point and cardinal point set in the constructor.
			getCommands returns a list of commands given in the constructor.
		CommandMediator
			getCommandRequest returns the same command request that was set in the constructor
			getTerrain returns the same terrain that was specified in the user's request
			getRover returns the correct rover that was specified in the user's request
			getCommands returns the same commands that specified in the user's request
			executeCommand throws a rover cannot move forward exception when the rover tries to move forward eastwards whilst on the terain surfaces maximum eastern bound.
			executeCommand throws a rover cannot move forward exception when the rover tries to move forward westwards whilst on the terain surfaces maximum western bound.
			executeCommand throws a rover cannot move forward exception when the rover tries to move forward northwards whilst on the terain surfaces maximum northern bound.
			executeCommand throws a rover cannot move forward exception when the rover tries to move forward southwards whilst on the terain surfaces maximum southern bound.
			getRoverPosition returns the expected rover position and cardinal point when the rover tries to move forward eastwards whilst within he terain surfaces' maximum eastern bound.
			getRoverPosition returns the expected rover position and cardinal point when the rover tries to move forward westwards whilst within he terain surfaces' maximum western bound.
			getRoverPosition returns the expected rover position and cardinal point when the rover tries to move forward northwards whilst within he terain surfaces' maximum northern bound.
			getRoverPosition returns the expected rover position and cardinal point when the rover tries to move forward southwards whilst within he terain surfaces' maximum southern bound.
			getRoverPosition returns the expected rover position and cardinal point when the rover is commanded to rotate ninety degress to the left whislt facing eastwards.
			getRoverPosition returns the expected rover position and cardinal point when the rover is commanded to rotate ninety degress to the left whislt facing westwards.
			getRoverPosition returns the expected rover position and cardinal point when the rover is commanded to rotate ninety degress to the left whislt facing northstwards.
			getRoverPosition returns the expected rover position and cardinal point when the rover is commanded to rotate ninety degress to the left whislt facing southwards.
			executeCommands returns the rover's newly expected position and cardinal point after moving and rotating
			getRoverLocationAndCoordinate returns the rover's newly expected position and cardinal point after moving and rotating after rover has received commands
		RoverFactory
		CardinalPointFactory
		CommandFactory
			createCommands throws a illegal command value exception when the requested command does not exist
			createCommands returns a list of the requested commands
			createCommands returns a list of the requested commands irespective of the case		
Challenges
	What happens when a rover is given a command to go to a block that's not within the landing terain bounds
		eg. The zone (Mar's surface) has dimensions of (2, 2) and when the Rover is on block (2, 2) whilst facing East (E) or North (N), they are given a command to move?
			Are there specific exceptions to be thrown or can I create my own?
	What if the user (giving input to the program), inputs cordinates of (0, 0) or negative numbers, are there predefined behaviors of how this system is to behave or can I create my own exceptions?
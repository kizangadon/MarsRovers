# MarsRoverChallenge
## Code challenge from Next45
## Deliverables
1. Clear instructions on how to use your program.
2. Brief decussion of the design decisions made and how you ensured code correctness.
3. List of inputs to program.
### Requirements
1. The project was created using the Netbeans 8 IDE.
2. The project was created using :
   1. Java 8 API, so your system will require the Java 8 JDK.
   2. Maven version: 3.0.5.
### Installation
You will need Git installed on your system, then:
1. Open the command prompt, then locate a working directory where you want to download the project.
2. Then type 
```bash
  git clone https://github.com/kizangadon/MarsRovers.git
  cd MarsRovers
```
### Usage
**How to test the application**
```bash
  mvn test
```
**How to build the application**
```bash
  mvn package
```
**How to execute the application**
```bash
  java -jar target/MarsRoverChallenge-1.0-SNAPSHOT.jar
```
#### Instructions
1. Once executed, the application's menu is shown.
2. Select An option out of two options, which are: Give our selected rover new instructions or exit the application.
3. When you select the first option (Giving our selected rover new instructions), you are prompted with giving the three instructions to our rover. Which are: 
   1. The Selected terrains surface area (zone) bounds, which are the horizontal and vertical bounds, instructions of how to enter this input is given. 
   2. The selected rover's starting coordinates and cardinal point/direction, instructions of the format of the expected input is given (1 2 E -> horizontal point: 1, vertical point: 2, cardinal point/direction: Eastwards). 
   3. The list of commands given to our rover, instructions of the format of the expected input is given (MMLMR -> move forward, move forward, rotate ninety degrees left, move forward, rotate ninety degrees right), the case of the alphabets don't matter.
    When you select the second/last option which is exit, the application closes.
    
## Design Decision
1. Initially I wanted to get right to the solution of the problem without wasting a lot of time, so I first started with defining the problem scope.
   1. In this case was, we have Rovers who have been sent to Mars to survey the terrain and they need a system that will help them navigate this terrain. Mars' surface or the terrain which will be surveyed os a two-dimensional grid. 
   2. The terrain has been surveyed ahead of time and has been marked safe within certain bounds, therefore we can identify this area using horizontal and vertical bounds which the Rover can explore safely. 
   3. The Rover understands cardinal points/directions and we've been given the directions which they can face at any time: East (E), West (W), North (N) or South (S). 
   4. The Rover understands three commands which are M, R, L and with these commands is how the rover will navigate the identified terrain. 
   5. One of the constraints is that you can only send a Rover a single list of commands at any given time. Due to time constraints, I used the CRC Cards technique to identify what objects are going to be needed in my solution. 
   6. I identified that we will need a terrain, the area which are Rover will explore, the rover object was the give away here, as this is the sole owner of this system. 
   7. I identified that we will need some way of representing a command so I made it an object as it holds information that the Rover will use to act. 
   8. I identifed a CoordinatePoint as a specific point on the terrain's surface area and I would like it represented as we will use this to communicate with the Rover of where they are right now and where to move. 
   9. I identified a CardinalPoint as an object that the Rover is aware off, initial I wanted to make it a property but then realised that there are more than one and are fixed, so we can make it an enumeration of some sort. 
   10 I then modified the command object into an enumeration as there are only a given number of commands that a Rover can take. In order to abstract our domain model from the outside, I decided to create a request object which will contain the user's instructions to the Rover. 
   11. I then thought of the mediator design pattern for the use of communication between the rover the user and the terrain without them all being dependant on each other. 
      1. Initially I could just of called the CommandMediator a controller, the mediator is to help us have a generally solution for the different architectures, let's say we want to make this a rest service, we can just create the CommandMediator in the controller. 
   12. I then wrote use cases for the identified classes and test specs were derived from the use cases.
2. How to ensure code correctness?
   1. I plan to ensure code correctness by build a test-driven solution, using good object-oriented programming principles. 
   2. The solution will use function testing on two levels, unit-testing and integeration testing. 
   3. Individual classes will be unit tested whilst components will be integerated using the controller/mediator. 
   4. SOLID principles will be used in this project. Every class that communicates with another class, will communicate with it's interface instead of that concrete class (Dependency Inversion). 
   5. Factories will help the instantiation of certain complex classes.
	
## Challenges
1. What happens when a rover is given a command to go to a block that's not within the landing terain bounds
   1. eg. The zone (Mar's surface) has dimensions of (2, 2) and when the Rover is on block (2, 2) whilst facing East (E) or North (N), they are given a command to move?
2. What if the user (giving input to the program), inputs cordinates of (0, 0) or negative numbers, are there predefined behaviors of how this system is to behave or can I create my own exceptions?
3. Are there specific exceptions to be thrown or can I create my own?

/**********************************************************************
 *  Fianl Project: Brickbreaker
 **********************************************************************/

Name: Kathy Jia
PennKey: kathyjia
Recitation: 209

/**********************************************************************
 *  How to run Brickbreaker
 **********************************************************************/

To run Brickbreaker, run the Brickbreaker.java file and pass in a text
file containing the level description (size of screen, number of lives, 
number of bricks, and position/size of bricks) as the command line 
argument. The execution statement should be: java Brickbreaker filename

/**********************************************************************
 *  Additional features (beyond assignment specifications)                    
 **********************************************************************/

I added a feature that keeps track of and displays the number of points
the player has. I implemented this by adding an instance variable called
points in the Ball class and incrementing it each time it collides with
a brick, then adding it to the ball's draw function.

/**********************************************************************
 *  Description of each file and its purpose
 *********************************************************************/

The Ball.java file contains a class that represents the ball in 
Brickbreaker. It can update the ball's position based on acceleration, 
velocity, and time, and it can test whether it overlaps with a given 
brick or the platform. If it overlaps with a brick, the ball's 
acceleration increases and its velocity is adjusted accordingly so that 
it bounces off. If it overlaps with the platform, the ball's 
acceleration decreases and its velocity is adjusted accordingly so that 
it bounces off.

The Platform.java file contains a class that represents the platform in 
Brickbreaker. It can update the platform's x-position based on mouse 
input from the user; each time the update method is called, the 
platform's x-position is set to the mouse's x-position.

The Brick.java file contains an interface that enforces certain 
properties on all three types of bricks.

The easyBrick.java file contains a class implementing the Brick 
interface that represents the easiest brick to break, requiring two hits 
by the ball. It can decrease the opacity of the brick after each hit.

The mediumBrick.java file contains a class implementing the Brick 
interface that represents the second easiest brick to break, requiring 
three hits by the ball. It can decrease the opacity of the brick after 
each hit.

The hardBrick.java file contains a class implementing the Brick 
interface that represents the hardest brick to break, requiring four 
hits by the ball. It can decrease the opacity of the brick after each 
hit.

The Board.java file contains a class that represents the game board on 
which the Brickbreaker game takes place. It keeps track of the game's 
ball, platform, and bricks and receives player input to start the game 
and to launch the ball into motion after each reset. It can also be 
reset if the player chooses to play again (by clicking anywhere).

The Brickbreaker.java file contains a class that represents the 
Brickbreaker game. It takes in a level description text file and 
initializes a game board with that data, then runs the game. It also
allows the player to play again after the game is over.
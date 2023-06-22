/* Execution: java Brickbreaker bricks.txt
*
*  Description: A class that represents the Brickbreaker game. Takes a level
*               description text file and initializes a game board with that data,  
*               then runs the game. Allows the player to play again after the game is 
*               over. 
*/

public class Brickbreaker {
    public static void main(String[] args) {
        // set width and height of screen
        PennDraw.setCanvasSize(500, 500);
        
        // game runs at 50 frames/sec
        PennDraw.enableAnimation(50);
        
        // instantiate a game state manager object
        Board game = new Board(args[0]);
        
        // change in time between frames will be set to 0.2 seconds.
        final double TIME_STEP = 0.2;
        
        // continues re-running game until player doesn't want to play again
        while (true) {
            // while player has neither won/lost, keep running game
            while (!game.gameOver()) {
                // update game state
                game.update(TIME_STEP);

                // draw updated game board and its components
                game.draw();
            }

            // draw victory or defeat screen if game over
            game.drawGameCompleteScreen();

            // reset game board if player wants to play again
            if (PennDraw.mousePressed()) {
                game.reset();
            }
        }
    }
}

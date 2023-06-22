/* Name: Kathy Jia
*  PennKey: kathyjia
*  Recitation: 209
*
*  Execution: n/a
*
*  Description: A class representing the game board on which the Brickbreaker game 
*               takes place. Keeps track of the game's ball, platform, and bricks
*               and receives player input to start the game and to launch the ball
*               into motion after each reset. Can reset the board if the player wants
*               to play again.
*/

public class Board {
    private double width, height;
    private Brick[] bricks;
    private Ball ball;
    private Platform platform;
    private boolean mouseListeningMode; // whether game is waiting for mouse input

    // constructor
    public Board(String filename) {
        // create variable in to read from file
        In inStream = new In(filename);

        // set member variables
        int numBricks = inStream.readInt();
        width = inStream.readDouble();
        height = inStream.readDouble();
        mouseListeningMode = true;

        // set x and y scales of canvas
        PennDraw.setXscale(0, width);
        PennDraw.setYscale(0, height);

        // initialize ball
        ball = new Ball(250, 24, 6, width, height, inStream.readInt());

        // initialize platform
        platform = new Platform(250, 14, 75, 6);

        // instantiate values of Brick array
        bricks = new Brick[numBricks];
        for (int i = 0; i < 8; i++) {
            bricks[i] = new easyBrick(inStream.readDouble(), inStream.readDouble(), 
            inStream.readDouble(), inStream.readDouble(), inStream.readInt());
        }
        for (int i = 8; i < 14; i++) {
            bricks[i] = new mediumBrick(inStream.readDouble(), inStream.readDouble(), 
            inStream.readDouble(), inStream.readDouble(), inStream.readInt());
        }
        for (int i = 14; i < 20; i++) {
            bricks[i] = new hardBrick(inStream.readDouble(), inStream.readDouble(), 
            inStream.readDouble(), inStream.readDouble(), inStream.readInt());
        }

        // closes input stream for text file
        inStream.close();
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: draws the game board (draws bricks, ball, & platform)
    */
    public void draw() {
        PennDraw.clear();
        for (int i = 0; i < bricks.length; i++) {
            bricks[i].draw();
        }
        ball.draw();
        platform.draw();
        PennDraw.advance();
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: updates each of the entities on the game board; checks the game 
     *              state (mouse listening or game playing) and invokes the
     *              appropriate functions for the ball, platform, and bricks
    */
    public void update(double timeStep) {
        if (mouseListeningMode) {
            // wait for mouse input to start the game
            if (PennDraw.mousePressed()) {
                // launch ball if mouse is clicked
                ball.setVelocity(15.0, 15.0);
                mouseListeningMode = false;
            }
        } else { // game is being played
            // update platform position
            platform.update();

            // test and handle brick + platform collisions
            for (int i = 0; i < bricks.length; i++) {
                if (bricks[i].getHitPoints() > 0) {
                    ball.brickCollision(bricks[i], timeStep);
                }
            }
            ball.platformCollision(platform, timeStep);

            // update ball position
            ball.update(timeStep);

            // reset ball + platform and decrement lives if ball falls offscreen
            if (ballIsOffScreen()) {
                ball.reset();
                platform.reset();
                ball.decrementLives();
                mouseListeningMode = true;
            }
        }
    }

    /**
     * Inputs: none
     * Outputs: whether or not the ball is offscreen (boolean)
     * Description: returns a boolean value that represents whether or not the ball
     *              has fallen off the screen
    */
    public boolean ballIsOffScreen() {
        return ball.getYpos() + ball.getRadius() < 0;
    }

    /**
     * Inputs: none
     * Outputs: whether or not the player has won (boolean)
     * Description: returns a boolean value that represents whether or not the player
     *              has won the game
    */
    public boolean didPlayerWin() {
        int remainingBricks = 0;
        for (int i = 0; i < bricks.length; i++) {
            if (bricks[i].getHitPoints() > 0) {
                remainingBricks++;
            }
        }
        return remainingBricks == 0;
    }

    /**
     * Inputs: none
     * Outputs: whether or not the player has lost (boolean)
     * Description: returns a boolean value that represents whether or not the player
     *              has lost the game
    */
    public boolean didPlayerLose() {
        return ball.getLives() == 0;
    }

    /**
     * Inputs: none
     * Outputs: whether or not the game is over (boolean)
     * Description: returns a boolean value that represents whether or not the game
     *              has finished (player has won or lost)
    */
    public boolean gameOver() {
        return didPlayerWin() || didPlayerLose();
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: draws a winning screen if the player has won or a losing screen 
     *              if the player has lost; both screens give the player the option
     *              to play again
    */
    public void drawGameCompleteScreen() {
        PennDraw.clear();
        if (didPlayerWin()) {
            PennDraw.text(width / 2, height / 2, "You win!");
            PennDraw.text(width / 2, height / 2 - 20, "Click to play again!");
            PennDraw.advance();
        } else {
            PennDraw.text(width / 2, height / 2, "You have lost...");
            PennDraw.text(width / 2, height / 2 - 20, "Click to play again!");
            PennDraw.advance();
        }
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: resets the game board (resets ball, platform, & bricks)
    */
    public void reset() {
        mouseListeningMode = true;
        ball.reset();
        ball.setLives(3);
        ball.setPoints(0);
        platform.reset();
        for (int i = 0; i < bricks.length; i++) {
            bricks[i].reset();
        }
    }
}
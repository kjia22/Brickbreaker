/* Name: Kathy Jia
*  PennKey: kathyjia
*  Recitation: 209
*
*  Execution: n/a
*
*  Description: A class that represents the ball in Brickbreaker. Can update its 
*               position based on acceleration, velocity, and time, and can compute
*               whether it overlaps with a given brick or the platform.
*/


public class Ball {
    private double xPos, yPos, xVel, yVel, xAcc, yAcc, radius;
    private double width, height; // size of game board
    private int lives, points;

    // constructor
    public Ball(double xPos, double yPos, double radius, double width, double height,
    int lives){
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = 0.0;
        this.yVel = 0.0;
        this.xAcc = 0.0;
        this.yAcc = 0.0;
        this.radius = radius;
        this.width = width;
        this.height = height;
        this.lives = lives;
        this.points = 0;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: draws the ball, as well as the number of lives and the number of
     *              points the player has
    */
    public void draw() {
        // draw ball
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.filledCircle(xPos, yPos, radius);
        // display number of lives left
        String numLives = "Lives: " + lives;
        PennDraw.text(35, 15, numLives);
        // display points
        String numPoints = "Points: " + points;
        PennDraw.text(460, 15, numPoints);
    }

    /**
     * Inputs: change in time (double timeStep)
     * Outputs: void
     * Description: checks to see if the ball has collided with the left, right, or 
     *              top sides of the screen and updates the velocity and acceleration 
     *              accordingly, then updates the position of the ball based on its 
     *              velocity and the change in time
    */
    public void update(double timeStep) {
        // change x-direction if ball hits left/right side of board
        if (xPos + radius >= width || xPos - radius <= 0.0) {
            xVel = -xVel;
            xAcc = -xAcc;
        }
        // change y-direction if ball hits top of board
        if (yPos + radius >= height) {
            yVel = -yVel;
            yAcc = -yAcc;
        }
        // update position of ball
        xPos += xVel * timeStep;
        yPos += yVel * timeStep;
    }

    /**
     * Inputs: point 1 (double x1, double y1), point 2 (double x2, double y2)
     * Outputs: distance between point 1 and point 2 (double)
     * Description: calculates the distance between two given points
    */
    public double distance(double x1, double y1, double x2, double y2) {
        double xDist = Math.abs(x2 - x1);
        double yDist = Math.abs(y2 - y1);
        double distance = Math.sqrt(xDist * xDist + yDist * yDist);
        return distance;
    }

    /**
     * Inputs: Brick brick, change in time (double timeStep)
     * Outputs: void
     * Description: tests whether the ball collides with a given brick and updates
     *              the acceleration and velocity accordingly, depending on which
     *              side of the brick it collides with; also increments the player's
     *              points and decrements the brick's HP
    */
    public void brickCollision(Brick brick, double timeStep) {
        // horizontal distance between ball center and brick center
        double xDist = Math.abs(xPos - brick.getXpos());
        // vertical distance between ball center and brick center 
        double yDist = Math.abs(yPos - brick.getYpos());

        if (xDist <= brick.getWidth() / 2) {
            // check if ball collides with top or bottom of brick
            if (yDist <= radius + brick.getHeight() / 2) {
                brick.decreaseHP();
                xAcc *= 1.2;
                yAcc *= -1.2;
                xVel = xVel + xAcc * timeStep;
                yVel = -yVel + yAcc * timeStep;
                points++;
            }
        } else if (yDist <= brick.getHeight() / 2) {
            // check if ball collides with left or right side of brick
            if (xDist <= radius + brick.getWidth() / 2) {
                brick.decreaseHP();
                xAcc *= -1.2;
                yAcc *= 1.2;
                xVel = -xVel + xAcc * timeStep;
                yVel = yVel + yAcc * timeStep;
                points++;
            }
        } else {
            // distance between ball center and brick center
            double distance = distance(xPos, yPos, brick.getXpos(), brick.getYpos());
            // distance between brick center and brick corner
            double brickDiagonal = distance (brick.getXpos(), brick.getYpos(), 
            brick.getXpos() - brick.getWidth() / 2, brick.getYpos() - 
            brick.getHeight() / 2);

            // check if ball collides with corner of brick
            if (distance <= radius + brickDiagonal) {
                brick.decreaseHP();
                xAcc *= -1.2;
                yAcc *= -1.2;
                xVel = -xVel + xAcc * timeStep;
                yVel = -yVel + yAcc * timeStep;
                points++;
            }
        }
    }

    /**
     * Inputs: Platform platform, change in time (double timeStep)
     * Outputs: void
     * Description: tests whether the ball collides with the platform and updates the
     *              acceleration and velocity accordingly, depending on which side of
     *              the platform it collides with
    */
    public void platformCollision(Platform platform, double timeStep) {
        // horizontal distance between ball center and platform center
        double xDist = Math.abs(xPos - platform.getXpos());
        // vertical distance between ball center and platform center 
        double yDist = Math.abs(yPos - platform.getYpos());

        if (xDist <= platform.getWidth() / 2) {
            // check if ball collides with top or bottom of platform
            if (yDist <= radius + platform.getHeight() / 2) {
                xAcc = xAcc;
                yAcc = -yAcc;
                xVel = xVel + xAcc * timeStep;
                yVel = -yVel + yAcc * timeStep;
            }
        } else if (yDist <= platform.getHeight() / 2) {
            // check if ball collides with left or right side of platform
            if (xDist <= radius + platform.getWidth() / 2) {
                xAcc = -xAcc;
                yAcc = yAcc;
                xVel = -xVel + xAcc * timeStep;
                yVel = yVel + yAcc * timeStep;
            }
        } else {
            // distance between ball center and platform center
            double distance = distance(xPos, yPos, platform.getXpos(), 
            platform.getYpos());
            // distance between platform center and platform corner
            double platformDiagonal = distance(platform.getXpos(), platform.getYpos()
            , platform.getXpos() - platform.getWidth() / 2, platform.getYpos() - 
            platform.getHeight() / 2);

            // check if ball collides with corner of platform
            if (distance <= radius + platformDiagonal) {
                xAcc = xAcc;
                yAcc = -yAcc;
                xVel = xVel + xAcc * timeStep;
                yVel = -yVel + yAcc * timeStep;
            }
        }
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: decrements the number of lives the player has by 1
    */
    public void decrementLives() {
        lives--;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: resets the ball's position, velocity, and acceleration to its
     *              starting values
    */
    public void reset() {
        xPos = 250;
        yPos = 24;
        xVel = 0.0;
        yVel = 0.0;
        xAcc = 0.0;
        yAcc = 0.0;
    }


    // getter + setter functions

    /**
     * Inputs: none
     * Outputs: void
     * Description: returns the ball's x-position
    */
    public double getXpos() {
        return xPos;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: returns the ball's y-position
    */
    public double getYpos() {
        return yPos;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: returns the ball's radius
    */
    public double getRadius() {
        return radius;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: returns the number of lives the player has left
    */
    public int getLives() {
        return lives;
    }

    /**
     * Inputs: number of lives (int lives)
     * Outputs: void
     * Description: sets the number of lives the player has left to the given input
    */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Inputs: x-velocity of the ball (double xVel), y-velocity of the ball (double 
     *         yVel)
     * Outputs: void
     * Description: sets the x- and y- velocity of the ball to the given inputs
    */
    public void setVelocity(double xVel, double yVel) {
        this.xVel = xVel;
        this.yVel = yVel;
    }

    /**
     * Inputs: number of points (int points)
     * Outputs: void
     * Description: sets the number of points the player has to the given input
    */
    public void setPoints(int points) {
        this.points = points;
    }
}
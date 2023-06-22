/* Name: Kathy Jia
*  PennKey: kathyjia
*  Recitation: 209
*
*  Execution: n/a
*
*  Description: A class that represents the easiest brick to break. Must be hit by
*               the ball twice to "break"; its opacity decreases after being hit.
*/

public class easyBrick implements Brick {
    private double xPos, yPos, brickWidth, brickHeight;
    private int hitPoints;

    // constructor
    public easyBrick (double xPos, double yPos, double brickWidth, 
    double brickHeight, int hitPoints) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.brickWidth = brickWidth;
        this.brickHeight = brickHeight;
        this.hitPoints = hitPoints;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: draws the brick with a certain opacity depending on its HP
    */
    @Override
    public void draw() {
        if (hitPoints > 1) {
            // color: cider
            PennDraw.setPenColor(216, 161, 117);
            PennDraw.filledRectangle(xPos, yPos, brickWidth/2, brickHeight/2);
        } else if (hitPoints > 0) {
            PennDraw.setPenColor(216, 161, 117, 128);
            PennDraw.filledRectangle(xPos, yPos, brickWidth/2, brickHeight/2);
        } else {
            PennDraw.setPenColor(216, 161, 117, 0);
            PennDraw.filledRectangle(xPos, yPos, brickWidth/2, brickHeight/2);
        }
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: resets the brick's HP to its starting value
    */
    @Override
    public void reset() {
        hitPoints = 2;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: decrements the brick's HP
    */
    @Override
    public void decreaseHP() {
        --hitPoints;
    }


    // getter functions

    /**
     * Inputs: none
     * Outputs: void
     * Description: returns the brick's HP
    */
    @Override
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: returns the brick's x-position
    */
    @Override
    public double getXpos() {
        return xPos;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: returns the brick's y-position
    */
    @Override
    public double getYpos() {
        return yPos;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: returns the brick's width
    */
    @Override
    public double getWidth() {
        return brickWidth;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: returns the brick's height
    */
    @Override
    public double getHeight() {
        return brickHeight;
    }
}
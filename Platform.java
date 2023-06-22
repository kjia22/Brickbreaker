/* Description: A class that represents the platform in Brickbreaker. Can update its
*               x-position based on mouse input from the player.
*/

public class Platform {
    private double xPos, yPos;
    private double width, height; // size of platform

    // constructor
    public Platform (double xPos, double yPos, double width, double height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: draws the platform
    */
    public void draw() {
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.filledRectangle(xPos, yPos, width/2, height/2);
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: sets the x-position of the platform to the x-position of the 
     *              mouse
    */
    public void update() {
        xPos = PennDraw.mouseX();
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: resets the position of the platform to its starting values
    */
    public void reset() {
        xPos = 250;
        yPos = 14;
    }


    // getter functions

    /**
     * Inputs: none
     * Outputs: void
     * Description: returns the platform's x-position
    */
    public double getXpos() {
        return xPos;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: returns the platform's y-position
    */
    public double getYpos() {
        return yPos;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: returns the platform's width
    */
    public double getWidth() {
        return width;
    }

    /**
     * Inputs: none
     * Outputs: void
     * Description: returns the platform's height
    */
    public double getHeight() {
        return height;
    }
}

/* Description: An interface that enforces certain properties on all three types of
*               bricks. 
*/

public interface Brick {
    public void draw();
    public void reset();
    public void decreaseHP();
    public int getHitPoints();
    public double getXpos();
    public double getYpos();
    public double getWidth();
    public double getHeight();
}

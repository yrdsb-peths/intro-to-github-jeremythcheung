import greenfoot.*;

public class Brick extends Actor
{
    public Brick()
    {
        setImage("brick.png");
    }

    public void act()
    {
        // Check if Mario hits from below
        Actor mario = getOneIntersectingObject(Mario.class);
        if (mario != null) {
            int marioTop = mario.getY() - mario.getImage().getHeight() / 2;
            int brickBottom = getY() + getImage().getHeight() / 2;
            if (marioTop >= brickBottom - 10) {
                getWorld().removeObject(this);
            }
        }
    }
}

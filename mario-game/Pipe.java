import greenfoot.*;

public class Pipe extends Actor
{
    public Pipe()
    {
        setImage("cactus.png");
    }

    public void act()
    {
        Actor mario = getOneIntersectingObject(Mario.class);
        if (mario != null) {
            int pipeLeft = getX() - getImage().getWidth() / 2;
            int pipeRight = getX() + getImage().getWidth() / 2;
            int pipeTop = getY() - getImage().getHeight() / 2;

            int marioBottom = mario.getY() + mario.getImage().getHeight() / 2;

            // Mario lands on top
            if (marioBottom <= pipeTop + 10) {
                mario.setLocation(mario.getX(), pipeTop - mario.getImage().getHeight() / 2);
            }
            // Block from left
            else if (mario.getX() < getX()) {
                mario.setLocation(pipeLeft - mario.getImage().getWidth() / 2 - 1, mario.getY());
            }
            // Block from right
            else {
                mario.setLocation(pipeRight + mario.getImage().getWidth() / 2 + 1, mario.getY());
            }
        }
    }
}

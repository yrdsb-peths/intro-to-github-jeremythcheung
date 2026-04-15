import greenfoot.*;

public class Goomba extends Actor
{
    private int speed = 2;
    private int vSpeed = 0;
    private final int GRAVITY = 1;

    public Goomba()
    {
        setImage("hedgehog.png");
    }

    public void act()
    {
        applyGravity();
        move(speed);
        checkEdge();
    }

    private void applyGravity()
    {
        vSpeed += GRAVITY;
        if (vSpeed > 15) vSpeed = 15;
        setLocation(getX(), getY() + vSpeed);

        Actor platform = getOneIntersectingObject(Platform.class);
        if (platform != null && vSpeed >= 0) {
            setLocation(getX(), platform.getY() - platform.getImage().getHeight() / 2 - getImage().getHeight() / 2);
            vSpeed = 0;
        }
    }

    private void checkEdge()
    {
        if (getX() <= 10 || getX() >= getWorld().getWidth() - 10) {
            speed = -speed;
        }
    }

}

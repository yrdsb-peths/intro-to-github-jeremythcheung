import greenfoot.*;

public class Mario extends Actor
{
    private int vSpeed = 0;
    private int hSpeed = 0;
    private final int GRAVITY = 1;
    private final int JUMP_STRENGTH = -15;
    private final int MOVE_SPEED = 5;
    private boolean onGround = false;
    private int jumpsLeft = 0;
    private boolean jumpKeyHeld = false;

    private ScoreCounter scoreCounter;
    private LivesCounter livesCounter;

    public Mario(ScoreCounter sc, LivesCounter lc)
    {
        scoreCounter = sc;
        livesCounter = lc;
        setImage("man01.png");
    }

    public void act()
    {
        handleInput();
        applyGravity();
        moveHorizontal();
        moveVertical();
        checkCoins();
        checkGoombas();
        checkFlag();
        checkFallOff();
    }

    private void handleInput()
    {
        hSpeed = 0;
        if (Greenfoot.isKeyDown("left")) {
            hSpeed = -MOVE_SPEED;
        }
        if (Greenfoot.isKeyDown("right")) {
            hSpeed = MOVE_SPEED;
        }
        boolean jumpKeyDown = Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("up");
        if (jumpKeyDown && !jumpKeyHeld && jumpsLeft > 0) {
            vSpeed = JUMP_STRENGTH;
            onGround = false;
            jumpsLeft--;
        }
        jumpKeyHeld = jumpKeyDown;
    }

    private void applyGravity()
    {
        vSpeed += GRAVITY;
        if (vSpeed > 15) vSpeed = 15;
    }

    private void moveHorizontal()
    {
        int newX = getX() + hSpeed;
        if (newX < 10) newX = 10;
        if (newX > getWorld().getWidth() - 10) newX = getWorld().getWidth() - 10;
        setLocation(newX, getY());
    }

    private void moveVertical()
    {
        setLocation(getX(), getY() + vSpeed);
        onGround = false;

        Actor platform = getOneIntersectingObject(Platform.class);
        if (platform != null && vSpeed >= 0) {
            setLocation(getX(), platform.getY() - platform.getImage().getHeight() / 2 - getImage().getHeight() / 2);
            vSpeed = 0;
            onGround = true;
            jumpsLeft = 2;
        }

        Actor brick = getOneIntersectingObject(Brick.class);
        if (brick != null && vSpeed >= 0) {
            setLocation(getX(), brick.getY() - brick.getImage().getHeight() / 2 - getImage().getHeight() / 2);
            vSpeed = 0;
            onGround = true;
            jumpsLeft = 2;
        }
    }

    private void checkCoins()
    {
        Actor coin = getOneIntersectingObject(Coin.class);
        if (coin != null) {
            getWorld().removeObject(coin);
            scoreCounter.addScore(100);
        }
    }

    private void checkGoombas()
    {
        Actor goomba = getOneIntersectingObject(Goomba.class);
        if (goomba != null) {
            int marioBottom = getY() + getImage().getHeight() / 2;
            int goombaTop = goomba.getY() - goomba.getImage().getHeight() / 2;

            if (marioBottom <= goombaTop + 10 && vSpeed > 0) {
                // Stomp goomba
                getWorld().removeObject(goomba);
                scoreCounter.addScore(200);
                vSpeed = -8;
            } else {
                // Mario gets hit
                ((MyWorld) getWorld()).loseLife(this);
            }
        }
    }

    private void checkFlag()
    {
        if (isTouching(Flag.class)) {
            if (getWorld().getObjects(Coin.class).isEmpty()) {
                scoreCounter.addScore(1000);
                ((MyWorld) getWorld()).levelComplete();
            }
        }
    }

    private void checkFallOff()
    {
        if (getY() > getWorld().getHeight() + 50) {
            ((MyWorld) getWorld()).loseLife(this);
        }
    }

    public void resetSpeed()
    {
        vSpeed = 0;
        hSpeed = 0;
    }
}

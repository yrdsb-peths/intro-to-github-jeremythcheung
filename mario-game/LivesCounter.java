import greenfoot.*;

public class LivesCounter extends Actor
{
    private int lives;

    public LivesCounter(int startingLives)
    {
        lives = startingLives;
        updateImage();
    }

    public void loseLife()
    {
        lives--;
        updateImage();
    }

    public int getLives()
    {
        return lives;
    }

    private void updateImage()
    {
        GreenfootImage img = new GreenfootImage("Lives: " + lives, 20, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(img);
    }

    public void act()
    {
        // Display only
    }
}

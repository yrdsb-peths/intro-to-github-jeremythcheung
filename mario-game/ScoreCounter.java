import greenfoot.*;

public class ScoreCounter extends Actor
{
    private int score = 0;

    public ScoreCounter()
    {
        updateImage();
    }

    public void addScore(int points)
    {
        score += points;
        updateImage();
    }

    public int getScore()
    {
        return score;
    }

    private void updateImage()
    {
        GreenfootImage img = new GreenfootImage("Score: " + score, 20, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(img);
    }

    public void act()
    {
        // Display only
    }
}

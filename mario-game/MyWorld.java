import greenfoot.*;

public class MyWorld extends World
{
    private ScoreCounter scoreCounter;
    private LivesCounter livesCounter;

    public MyWorld()
    {
        super(800, 500, 1);
        GreenfootImage bg = new GreenfootImage(800, 500);
        bg.setColor(new Color(135, 206, 235));
        bg.fill();
        setBackground(bg);

        // Score and lives display
        scoreCounter = new ScoreCounter();
        livesCounter = new LivesCounter(3);
        addObject(scoreCounter, 80, 20);
        addObject(livesCounter, 700, 20);

        // Add Mario
        Mario mario = new Mario(scoreCounter, livesCounter);
        addObject(mario, 100, 350);

        // Ground platforms
        for (int x = 0; x <= 800; x += 50) {
            Platform ground = new Platform();
            addObject(ground, x, 480);
        }

        // Floating platforms
        addObject(new Platform(), 200, 350);
        addObject(new Platform(), 250, 350);
        addObject(new Platform(), 300, 350);

        addObject(new Platform(), 450, 280);
        addObject(new Platform(), 500, 280);
        addObject(new Platform(), 550, 280);

        addObject(new Platform(), 650, 380);
        addObject(new Platform(), 700, 380);

        // Bricks
        addObject(new Brick(), 180, 250);
        addObject(new Brick(), 230, 250);
        addObject(new Brick(), 480, 200);
        addObject(new Brick(), 530, 200);
        addObject(new Brick(), 580, 200);

        // Coins
        addObject(new Coin(), 200, 310);
        addObject(new Coin(), 250, 310);
        addObject(new Coin(), 300, 310);
        addObject(new Coin(), 450, 240);
        addObject(new Coin(), 500, 240);
        addObject(new Coin(), 550, 240);
        addObject(new Coin(), 650, 340);
        addObject(new Coin(), 700, 340);
        addObject(new Coin(), 400, 440);

        // Pipes
        addObject(new Pipe(), 360, 450);
        addObject(new Pipe(), 620, 450);

        // Goombas
        addObject(new Goomba(), 350, 440);
        addObject(new Goomba(), 500, 440);
        addObject(new Goomba(), 680, 440);
        addObject(new Goomba(), 230, 310);

        // Flag at the end
        addObject(new Flag(), 760, 400);
    }

    public void loseLife(Mario mario)
    {
        livesCounter.loseLife();
        if (livesCounter.getLives() <= 0) {
            gameOver();
        } else {
            mario.setLocation(100, 350);
            mario.resetSpeed();
        }
    }

    public void gameOver()
    {
        addObject(new GameOver(), 400, 250);
        Greenfoot.stop();
    }

    public void levelComplete()
    {
        addObject(new LevelComplete(), 400, 250);
        Greenfoot.stop();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class SnakePanel extends JPanel {

    int[] axisX;
    int[] axisY;
    Random rand = new Random();
    Apple apple;
    Snake snake;
    int score;

    public SnakePanel() {
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                snake.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };

        addKeyListener(listener);
        setFocusable(true);

        //create 30 x 30 grid
       axisX = new int[30];
       for(int i = 0; i < 30; i++) {
           axisX[i] = i*20;
       }

       axisY = new int[30];
       for(int j = 0; j < 30; j++) {
           axisY[j] = j*20;
       }

        apple = new Apple(axisX[20], axisY[15]);
        snake = new Snake(axisX[3], axisY[15]);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        apple.paint(g2);
        snake.paint(g2);
        g2.setColor(Color.GRAY);
        g2.setFont(new Font("Verdana", Font.BOLD, 30));
        g2.drawString(String.valueOf(getScore()), 10, 30);
    }

    public void gameOverCheck() {
        if(snake.collidesWithSelf() || snake.collidesWithWall(600, 600)) {
            gameOver();
        }
    }

    public void move() {
        int x;
        int y;
        snake.move();

        if(snake.collision(apple)) {
            score++;
            snake.grow();
            x = axisX[rand.nextInt(30)];
            y = axisY[rand.nextInt(30)];
            apple.move(x, y);
        }

    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    public int getScore() {
        return score;
    }
}

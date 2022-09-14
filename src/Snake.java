import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Integer> xVal;
    private ArrayList<Integer> yVal;
    private int dx;
    private int dy;

    public Snake(int x, int y) {
        xVal = new ArrayList<Integer>();
        yVal = new ArrayList<Integer>();

        for(int i = 0; i < 3; i++) {
            xVal.add(x - i*20);
            yVal.add(y);
        }
    }

    public void keyPressed(KeyEvent e) {
        int hx = xVal.get(0);
        int hy = yVal.get(0);
        int fx = xVal.get(1);
        int fy = yVal.get(1);

        if(e.getKeyCode() == KeyEvent.VK_W && hy == fy) {
            dx = 0;
            dy = -20;
        }

        if(e.getKeyCode() == KeyEvent.VK_A && hx == fx)  {
            dx = -20;
            dy = 0;
        }

        if(e.getKeyCode() == KeyEvent.VK_S && hy == fy) {
            dx = 0;
            dy = 20;
        }

        if(e.getKeyCode() == KeyEvent.VK_D && hx == fx) {
            dx = 20;
            dy = 0;
        }
    }

    public void move() {
        for(int i = xVal.size() - 1; i > 0; i--) {
            xVal.set(i, xVal.get(i - 1));
            yVal.set(i, yVal.get(i - 1));
        }

        xVal.set(0, xVal.get(0) + dx);
        yVal.set(0, yVal.get(0) + dy);
    }

    public void paint(Graphics2D g2) {
        g2.setColor(Color.BLACK);

        for(int i = 0; i < xVal.size(); i++) {
            g2.fillOval(xVal.get(i), yVal.get(i), 20, 20);
        }
    }

    public void grow() {
        int xh = xVal.get(xVal.size() - 2);
        int xf = xVal.get(xVal.size() - 1);
        int yh = yVal.get(yVal.size() - 2);
        int yf = yVal.get(yVal.size() - 1);

        if(xh == xf) {
            xVal.add(xf);

            if(yh < yf) {
                yVal.add(yf - 20);
            }
            else {
                yVal.add(yf + 20);
            }
        }

        if(yh == yf) {
            yVal.add(yf);

            if(xh < xf) {
                xVal.add(xf + 20);
            }
            else {
                xVal.add(xf - 20);
            }
        }
    }

    public boolean collidesWithSelf() {
        for(int i = 4; i < xVal.size(); i++) {
            if(xVal.get(i).equals(xVal.get(0)) && yVal.get(i).equals(yVal.get(0))) {
                return true;
            }
        }

        return false;
    }

    public boolean collision(Apple apple) {
        int appleX = apple.getX();
        int appleY = apple.getY();

        for(int i = 0; i < xVal.size(); i++) {
           if(xVal.get(i) == appleX && yVal.get(i) == appleY) {
               return true;
           }
        }

        return false;
    }

    public boolean collidesWithWall(int width, int height) {
        return xVal.get(0) < 0 || xVal.get(0) ==  width || yVal.get(0) < 0 || yVal.get(0) == height;
    }
}

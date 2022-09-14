import java.awt.*;

public class Apple {

    private int x;
    private int y;

    public Apple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics2D g2) {
        g2.setColor(Color.RED);

        g2.fillOval(x, y, 20, 20);
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

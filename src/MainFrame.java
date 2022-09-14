import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() throws InterruptedException {
        super();  //Should I do this?
        init();
    }

    private void init() throws InterruptedException {
        setTitle("Snake");
        SnakePanel sp = new SnakePanel();
        sp.setPreferredSize(new Dimension(600, 600));
        getContentPane().add(sp);
        pack();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        while(true) {
            sp.move();
            sp.gameOverCheck();
            sp.repaint();
            Thread.sleep(100);
        }
    }
}

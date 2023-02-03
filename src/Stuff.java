import javax.swing.*;
import java.awt.*;

public abstract class Stuff extends JPanel {
    int stuffType;

    public static final int GIRL = 0;
    public static final int ROAD = 1;
    public static final int WALL = 2;
    public static final int BOX = 3;

    private Image girl = MazePanel.girl;
    private Image wall = MazePanel.wall;
    private Image road = MazePanel.road;
    private Image box = MazePanel.box;

    public Stuff(int width, int height, int type) {
        this.stuffType = type;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (stuffType == 0) {
            g.drawImage(girl, 0, 0, getWidth(), getHeight(), this);
        } else if (stuffType == 1) {
            g.drawImage(road, 0, 0, getWidth(), getHeight(), this);
        } else if (stuffType == 2) {
            g.drawImage(wall, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.drawImage(box, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void change(int k) {
        this.stuffType = k;
        this.repaint();
    }
}

class Thing extends Stuff {
    Thing(int width, int height, int modelType) {
        super(width, height, modelType);
        this.setBounds(0, 0, width, height);
    }
}


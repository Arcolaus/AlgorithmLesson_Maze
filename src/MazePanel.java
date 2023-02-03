import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {
    int rowSize;
    int colSize;

    Thing[][] mazeStuff;
    boolean[][] maze;
    int gridWidth;
    int gridHeight;

    static Image road = new ImageIcon("src/image/Road.png").getImage();
    static Image wall = new ImageIcon("src/image/Wall.png").getImage();
    static Image girl = new ImageIcon("src/image/Box.png").getImage();
    static Image box = new ImageIcon("src/image/Box.png").getImage();

    public MazePanel(int rowSize, int colSize) {
        this.colSize = colSize;
        this.rowSize = rowSize;

        gridWidth = 800 / colSize;
        gridHeight = 700 / rowSize;

        this.setLayout(new GridLayout(rowSize, colSize));

        RandomPrimMaze r = new RandomPrimMaze(rowSize - 2, colSize - 2);
        maze = r.createMaze();

        mazeStuff = new Thing[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (maze[i][j]) {
                    if (i == 0 && j == 1)
                        mazeStuff[i][j] = new Thing(this.gridWidth, this.gridHeight, Stuff.GIRL);
                    else if (i == this.rowSize - 2 && j == this.colSize - 1)
                        mazeStuff[i][j] = new Thing(this.gridWidth, this.gridHeight, Stuff.BOX);
                    else
                        mazeStuff[i][j] = new Thing(this.gridWidth, this.gridHeight, Stuff.ROAD);
                    this.add(mazeStuff[i][j]);
                } else {
                    mazeStuff[i][j] = new Thing(this.gridWidth, this.gridHeight, Stuff.WALL);
                    this.add(mazeStuff[i][j]);
                }
            }
        }
    }

    public int getRowSize() {
        return this.rowSize;
    }

    public int getColSize() {
        return this.colSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public void setColSize(int colSize) {
        this.colSize = colSize;
    }
}

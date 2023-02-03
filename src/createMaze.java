import java.awt.Point;
import java.util.*;

abstract class AbstractMaze {
    protected Wall[][] walls;
    protected int rowSize;
    protected int colSize;
    protected final Point start = new Point(1, 1);
    protected final Point end = new Point(rowSize, colSize);
    protected boolean findWay;

    AbstractMaze(int rowSize, int colSize) {
        walls = new Wall[rowSize + 2][colSize + 2];
        this.rowSize = rowSize;
        this.colSize = colSize;
        for (int i = 0; i < rowSize + 2; i++)
            for (int j = 0; j < colSize + 2; j++)
                walls[i][j] = new Wall();
        walls[0][1].setPassable(true);
        walls[rowSize][colSize + 1].setPassable(true);

        findWay = false;
    }

    protected boolean legalPoint(int x, int y) {
        if (x < 1 || x >= this.rowSize || y < 1 || y > this.colSize)
            return false;
        return true;
    }

    protected boolean legalPoint(Point p) {
        if (p.x < 1 || p.x >= this.rowSize || p.y < 1 || p.y > this.colSize)
            return false;
        return true;
    }

    abstract boolean[][] createMaze();
}

class RandomPrimMaze extends AbstractMaze {
    final int[] direction = {-1, 0, 1, 0, -1};
    private boolean maze[][];
    private ArrayList<Point> solve;


    RandomPrimMaze(int rowSize, int colSize) {
        super(rowSize, colSize);
        solve = new ArrayList<>();
    }

    protected void pushWall(Point p, List<Point> queue) {
        for (int i = 0; i < 4; i++) {
            int x = p.x + direction[i];
            int y = p.y + direction[i + 1];

            if (legalPoint(x, y) && !walls[x][y].isPassable())
                queue.add(new Point(x, y));
        }
    }

    Point findPoint(Point singleWall) {
        Point p = null;
        for (int i = (singleWall.y + 1) % 2; i < 2; i += 2) {
            boolean add = walls[singleWall.x + direction[i]][singleWall.y + direction[i + 1]].isPassable();
            boolean sub = walls[singleWall.x - direction[i]][singleWall.y - direction[i + 1]].isPassable();
            if (add && !sub) {
                p = new Point(singleWall.x - direction[i], singleWall.y - direction[i + 1]);
                break;
            }
            if (!add && sub) {
                p = new Point(singleWall.x + direction[i], singleWall.y + direction[i + 1]);
                break;
            }
        }
        return p;
    }

    @Override
    public boolean[][] createMaze() {
        Random rand = new Random();
        Point currentPoint = new Point(2 * rand.nextInt(rowSize / 2) + 1, 2 * rand.nextInt(colSize / 2) + 1);
        walls[currentPoint.x][currentPoint.y].setPassable(true);

        List<Point> listWall = new LinkedList<Point>();
        pushWall(currentPoint, listWall);

        while (!listWall.isEmpty()) {
            int k = rand.nextInt(listWall.size());
            Point wall = listWall.remove(k);

            currentPoint = findPoint(wall);
            if (currentPoint != null) {
                walls[wall.x][wall.y].setPassable(true);
                walls[currentPoint.x][currentPoint.y].setPassable(true);
                pushWall(currentPoint, listWall);
            }
        }
        this.maze = new boolean[rowSize + 2][colSize + 2];
        for (int i = 0; i < rowSize + 2; i++)
            for (int j = 0; j < colSize + 2; j++)
                if (walls[i][j].isPassable())
                    this.maze[i][j] = true;
                else
                    this.maze[i][j] = false;
        solve.add(start);
        depthSearch(start);
        return this.maze;
    }

    private void depthSearch(Point u) {
        if (findWay)
            return;
        final int dir[][] = {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        boolean vis[][] = this.maze;
        if (u.x == this.end.x && u.y == this.end.y) {
            findWay = true;
            return;
        }
        for (int i = 0; i < 4; i++) {
            Point np = new Point(u.x + dir[i][0], u.y + dir[i][1]);
            if (np.x >= 0 && np.y >= 0 && np.x < rowSize + 2 && np.y < colSize + 2 && this.maze[np.x][np.y] && vis[np.x][np.y]) {
                solve.add(np);
                vis[np.x][np.y] = false;
                depthSearch(np);
                vis[np.x][np.y] = true;
                solve.remove(np);
            }
        }
    }

    public ArrayList<Point> getSolve() {
        solve.add(end);
        return this.solve;
    }
}

class Wall {
    private boolean passable;
    private Point parent;

    public Wall() {
        this.passable = false;
        this.parent = null;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean isPassable) {
        this.passable = isPassable;
    }

    public Point getParent() {
        return parent;
    }

    public void setParent(Point parent) {
        this.parent = parent;
    }
}
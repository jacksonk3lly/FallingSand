import java.util.Stack;

public class World {
    private int[][] grid;
    private int[][] newGrid;
    public int width;
    public int height;
    public Stack<Coordinate> addStack = new Stack<Coordinate>();

    public World(int width, int height) {
        this.height = height;
        this.width = width;
        grid = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = 0;
            }
        }
        step();
    }

    public void step() {
        newGrid = new int[grid.length][grid[0].length];
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (i == grid.length - 1) {
                        newGrid[i][j] = 1;
                    } else {
                        int bellow = grid[i + 1][j];
                        if (bellow == 0) {
                            newGrid[i + 1][j] = 1;
                        } else if (j != width - 1 && grid[i + 1][j + 1] == 0) {
                            newGrid[i + 1][j + 1] = 1;
                        } else if (j != 0 && grid[i + 1][j - 1] == 0) {
                            newGrid[i + 1][j - 1] = 1;
                        } else {
                            newGrid[i][j] = 1;
                        }
                    }
                }
            }
        }
        while (!addStack.isEmpty()) {
            Coordinate c = addStack.pop();
            newGrid[c.x][c.y] = 1;
        }
        grid = newGrid;
    }

    public int get(int x, int y) {
        return grid[x][y];
    }

    public void addSand(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height)
            return;
        addStack.push(new Coordinate(x, y));
    }
}
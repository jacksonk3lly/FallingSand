import java.util.Stack;

public class World {
    private Particle[][] grid;
    private Particle[][] newGrid;
    public int width;
    public int height;

    public World(int width, int height) {
        this.height = height;
        this.width = width;
        grid = new Particle[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Particle(i, j);
            }
        }
        step();
    }

    public void step() {
        System.out.println("Step");
        newGrid = new Particle[grid.length][grid[0].length];

        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j].behave(grid, newGrid, i, j);
            }
        }
        System.out.println("Full RUN");
        grid = newGrid;
    }

    public Particle get(int x, int y) {
        return grid[x][y];
    }

    public void addSand(int x, int y, float hue) {
        if (x < 0 || x >= width || y < 0 || y >= height)
            return;
        grid[x][y].makeSand(hue);
    }

    public void addConcrete(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height)
            return;
        grid[x][y].makeConcrete();
    }
}
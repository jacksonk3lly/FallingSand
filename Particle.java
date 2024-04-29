import java.awt.Color;

public class Particle {

    private String type = "empty";
    private Color color = Color.WHITE;
    private static final double gravity = 9.81;
    private int x;
    private int y;

    public Particle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public void makeConcrete() {
        type = "concrete";
        color = Color.GRAY;
    }

    public void makeWater() {
        type = "water";
        color = Color.blue;
    }

    public void makeSand(float hue) {
        type = "sand";
        color = Color.getHSBColor(hue, 1, 1);
    }

    public void makeEmpty() {
        type = "empty";
        color = Color.BLACK;
    }

    public void behave(Particle[][] grid, Particle[][] newGrid, int i, int j) {
        if (type.equals("empty")) {
            if (newGrid[i][j] == null) {
                newGrid[i][j] = this;
            }
        } else if (type.equals("sand")) {
            sandBehave(grid, newGrid, i, j);
        } else if (type.equals("water")) {
            waterBehave(grid, newGrid, i, j);
        } else if (type.equals("concrete")) {
            newGrid[i][j] = this;
        } else {
            System.out.println("Unknown type: " + type);

        }
    }

    private void waterBehave(Particle[][] grid, Particle[][] newGrid, int i, int j) {
        if (i + 1 < grid.length && grid[i + 1][j].type.equals("empty")) {
            newGrid[i + 1][j] = this;
            newGrid[i][j] = new Particle(i, j);
        } else if (j - 1 >= 0 && j + 1 < grid[0].length && isEmpty(grid[i][j - 1]) && isEmpty(grid[i][j + 1])
                && isEmpty(newGrid[i][j - 1]) && isEmpty(newGrid[i][j + 1])) {
            if (coinFlip()) {
                newGrid[i][j - 1] = this;
            } else {
                newGrid[i][j + 1] = this;
            }
            if (newGrid[i][j] == null) {
                newGrid[i][j] = new Particle(i, j);
            }
            // && isEmpty(grid[i][j - 1]) && isEmpty(grid[i][j + 1]
        } else if (j - 1 > 0 && isEmpty(grid[i][j - 1]) && isEmpty(newGrid[i][j - 1])) {
            newGrid[i][j - 1] = this;
            if (newGrid[i][j] == null) {
                newGrid[i][j] = new Particle(i, j);
            }

        } else if (j + 1 < grid[0].length && isEmpty(grid[i][j + 1]) && isEmpty(newGrid[i][j + 1])) {
            newGrid[i][j + 1] = this;
            if (newGrid[i][j] == null) {
                newGrid[i][j] = new Particle(i, j);
            }

        } else {
            newGrid[i][j] = this;
        }
    }

    private boolean coinFlip() {
        return Math.random() < 0.5;
    }

    private boolean isEmpty(Particle p) {
        if (p == null) {
            return true;
        }
        return p.type.equals("empty");
    }

    private void sandBehave(Particle[][] grid, Particle[][] newGrid, int i, int j) {
        if (i + 1 < grid.length && grid[i + 1][j].type.equals("empty")) {
            newGrid[i + 1][j] = this;
            newGrid[i][j] = new Particle(i, j);
        } else if (i + 1 < grid.length && j + 1 < grid[0].length && grid[i + 1][j + 1].type.equals("empty")) {
            newGrid[i + 1][j + 1] = this;
            newGrid[i][j] = new Particle(i, j);
        } else if (i + 1 < grid.length && j - 1 >= 0 && grid[i + 1][j - 1].type.equals("empty")) {
            newGrid[i + 1][j - 1] = this;
            newGrid[i][j] = new Particle(i, j);
        } else {
            newGrid[i][j] = this;
        }
    }
}

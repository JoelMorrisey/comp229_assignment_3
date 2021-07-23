import java.awt.Color;

class Water extends Cell {
    public Water(char col, int row, int x, int y) {
        super(col, row, x, y);
        this.description = "waterway";
        colour = Color.BLUE;
    }

    @Override
    public int movementCost() {
        return 1000;
    }
}
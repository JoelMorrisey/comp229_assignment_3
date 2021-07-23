import java.awt.Color;

class Road extends Cell {
    public Road(char col, int row, int x, int y) {
        super(col, row, x, y);
        this.description = "road";
        colour = Color.DARK_GRAY;
    }

    @Override
    public int movementCost() {
        return 1;
    }
}
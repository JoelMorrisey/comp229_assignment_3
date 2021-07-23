import java.awt.Color;

class Mountain extends Cell {
    public Mountain(char col, int row, int x, int y) {
        super(col, row, x, y);
        this.description = "mountains";
        colour = Color.LIGHT_GRAY;
    }

    @Override
    public int movementCost() {
        return 10;
    }
}
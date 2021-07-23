import java.awt.Color;
import java.util.*;

class Grass extends Cell {

    private static Random rand = new Random();
    public Grass(char col, int row, int x, int y) {
        super(col, row, x, y);
        this.description = "grasslands";
        colour =  new Color(rand.nextInt(30), rand.nextInt(155) + 100, rand.nextInt(30));;
    }

    @Override
    public int movementCost() {
        return (colour.getGreen() - 100) / 50 + 1;
    }
}
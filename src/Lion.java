import java.awt.*;
import java.util.ArrayList;

public class Lion extends Actor {

    public Lion(Cell loc, float redness) {
        this.setLocation(loc);
        this.redness = redness;
        this.colour = Color.RED;
        this.turns = 1;
        this.moves = 10;
        this.range = 1;
    }

    public void setPoly(){
        this.display = new ArrayList<Polygon>();
        Polygon mane = new Polygon();
        mane.addPoint(loc.x + 6, loc.y + 6);
        mane.addPoint(loc.x + 29, loc.y + 6);
        mane.addPoint(loc.x + 29, loc.y + 29);
        mane.addPoint(loc.x + 6, loc.y + 29);
        Polygon face = new Polygon();
        face.addPoint(loc.x + 11, loc.y + 11);
        face.addPoint(loc.x + 24, loc.y + 11);
        face.addPoint(loc.x + 24, loc.y + 24);
        face.addPoint(loc.x + 11, loc.y + 24);
        this.display.add(mane);
        this.display.add(face);
    }
}
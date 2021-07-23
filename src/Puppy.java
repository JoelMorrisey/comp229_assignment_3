import java.awt.*;
import java.util.ArrayList;

public class Puppy extends Actor {

    public Puppy(Cell loc, float redness){
        this.setLocation(loc);
        this.redness = redness;
        this.colour = Color.GREEN;
        this.turns = 1;
        this.moves = 3;
        this.range = 1;
    }

    public void setPoly(){
        this.display = new ArrayList<Polygon>();
        Polygon ear1 = new Polygon();
        ear1.addPoint(loc.x + 5, loc.y + 5);
        ear1.addPoint(loc.x + 15, loc.y + 5);
        ear1.addPoint(loc.x + 5, loc.y + 15);
        Polygon ear2 = new Polygon();
        ear2.addPoint(loc.x + 20, loc.y + 5);
        ear2.addPoint(loc.x + 30, loc.y + 5);
        ear2.addPoint(loc.x + 30, loc.y + 15);
        Polygon face = new Polygon();
        face.addPoint(loc.x + 8, loc.y + 7);
        face.addPoint(loc.x + 27, loc.y + 7);
        face.addPoint(loc.x + 27, loc.y + 25);
        face.addPoint(loc.x + 8, loc.y + 25);
        this.display.add(face);
        this.display.add(ear1);
        this.display.add(ear2);
    }
}
import java.awt.*;
import java.util.ArrayList;

public abstract class Actor {
    Color colour;
    Cell loc;
    ArrayList<Polygon> display;
    MoveStrategy strat;
    float redness;
    int turns;
    int moves;
    int range;
    boolean moving = false;

    public void paint(Graphics g){
        for(Polygon p: display){
            g.setColor(new Color(redness, 0f, 1f-redness, AnimationBeat.getInstance().phaseCompletion()/100f));
            g.fillPolygon(p);
            g.setColor(Color.GRAY);
            g.drawPolygon(p);
        }
    }

    public abstract void setPoly();

    public boolean isTeamRed(){
        return redness >= 0.5;
    }

    public boolean isTeamBlue(){
        return redness < 0.5;
    }

    public void setLocation(Cell loc){
        this.loc = loc;
        this.strat = new GetToA0();
        setPoly();
    }

    public void makeRedder(float amt){
        redness = redness + amt;
        if(redness > 1.0f){
            redness = 1.0f;
        }
    }
    public Cell getLocation(){
        return loc;
    }

    public float getRedness(){
        return redness;
    }

    public String getStrat(){
        return strat.toString();
    }
}
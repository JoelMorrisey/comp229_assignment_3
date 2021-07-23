import java.awt.Graphics;

public class ImpAdapter extends Actor {
    Impediment<Cell> delegate;
    Thread myThread = null;

    public ImpAdapter(Impediment<Cell> delegate) {
        this.delegate = delegate;
        this.turns = 0;
        this.moves = 0;
        this.strat = new LeftMostMove();
    }

    @Override
    public void setPoly() {
        delegate.setPoly();
    }

    @Override
    public boolean isTeamRed() {
        return false;
    }

    @Override
    public boolean isTeamBlue() {
        return false;
    }

    @Override
    public synchronized void setLocation(Cell location) {
        if (myThread == null) {
            myThread = new Thread(() -> {
                delegate.moveAtOwnSpeed(location);
                myThread = null;
            });
            myThread.start();
        }
    }

    @Override
    public void paint(Graphics g){
        delegate.paint(g);
    }

    @Override
    public Cell getLocation(){
        return delegate.getLocation();
    }

    @Override
    public float getRedness(){
        return 0.0f;
    }

    @Override
    public String getStrat(){
        return "do what I want";
    }
}
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.util.*;
import java.util.function.Consumer;

class Grid implements Iterable<Cell> {
    //fields
    Cell[][] cells = new Cell[20][20];
    //Cell[][] cells = new Cell[50][50];

    private static Random rand = new Random();

    // constructor
    public Grid(){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                switch (rand.nextInt(6)){
                    case 0:
                        cells[i][j] = new Road(colToLabel(i), j, 10+35*i,10+35*j);
                        break;
                    case 1:
                        cells[i][j] = new Water(colToLabel(i), j, 10+35*i,10+35*j);
                        break;
                    case 2:
                        cells[i][j] = new Mountain(colToLabel(i), j, 10+35*i,10+35*j);
                        break;
                    default:
                        cells[i][j] = new Grass(colToLabel(i), j, 10+35*i,10+35*j);
                        break;
                }
            }
        }
    }

    private char colToLabel(int col) {
        return (char) (col + 65);
    }

    private int labelToCol(char col) {
        return (int) col - 65;
    }

    // methods
    public void paint(Graphics g, Point mousePos){
        doToEachCell(   (Cell c) -> c.paint(g, mousePos)  );
    }

    public Optional<Cell> cellAtColRow(char c, int r){
        int cc = labelToCol(c);
        if (cc >= 0 && cc < cells.length && r >= 0 && r < cells[cc].length){
            return Optional.of(cells[cc][r]);
        } else {
            return Optional.empty();
        }
    }

    public List<Cell> cellsInRange(char c1, int r1, char c2, int r2){
        int c1i = labelToCol(c1);
        int c2i = labelToCol(c2);
        java.util.List<Cell> output = new ArrayList<Cell>();
        for(int i = c1i; i <= c2i; i++){
            for(int j = r1; j<= r2; j++){
                cellAtColRow(colToLabel(i),j).ifPresent(output::add);
            }
        }
        return output;
    }

    public void replaceCell(Cell old, Cell knew){
        cells[labelToCol(old.col)][old.row] = knew;
    }

    public Optional<Cell> cellAtPoint(Point p){
        for(Cell c: this){
            if (c.contains(p)){
                return Optional.of(c);
            }
        }

        return Optional.empty();
    }

    public Cell randomCell(){
        Random rand = new Random();
        int row = rand.nextInt(20);
        int col = rand.nextInt(20);
        return cells[row][col];
    }

    /**
     * Takes a cell consumer (i.e. a function that has a single `Cell` argument and
     * returns `void`) and applies that consumer to each cell in the grid.
     * @param func The `Cell` to `void` function to apply at each spot.
     */
    public void doToEachCell(Consumer<Cell> func){
        for(Cell c : this){
            func.accept(c);
        }
    }

    public void paintOverlay(Graphics g, List<Cell> cells, Color colour){
        g.setColor(colour);
        for(Cell c: cells){
            g.fillRect(c.x+2, c.y+2, c.width-4, c.height-4);
        }
    }

    public List<Cell> getRadius(Cell from, int size, boolean considerCost) {
        int i = labelToCol(from.col);
        int j = from.row;
        Set<Cell> inRadius = new HashSet<Cell>();
        java.util.function.Predicate<Cell> checker;
        if (considerCost){
            checker = (Cell c) -> c.movementCost() < size;
        } else {
            checker = (Cell c) -> size > 0 ;
        }
        cellAtColRow(colToLabel(i), j - 1).filter(checker).ifPresent(inRadius::add);
        cellAtColRow(colToLabel(i), j + 1).filter(checker).ifPresent(inRadius::add);
        cellAtColRow(colToLabel(i - 1), j).filter(checker).ifPresent(inRadius::add);
        cellAtColRow(colToLabel(i + 1), j).filter(checker).ifPresent(inRadius::add);

        for(Cell c: inRadius.toArray(new Cell[0])){
            if (considerCost){
                inRadius.addAll(getRadius(c, size - c.movementCost(), true));
            } else {
              inRadius.addAll(getRadius(c, size - 1, false));
            }
        }
        return new ArrayList<Cell>(inRadius);
    }

    @Override
    public CellIterator iterator(){
        return new CellIterator(cells);
    }

}
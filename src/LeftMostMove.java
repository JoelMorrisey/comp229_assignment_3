import java.util.*;

public class LeftMostMove implements MoveStrategy {

    @Override
    public Optional<Cell> chooseNextLoc(Actor a, Stage s, List<Cell> possibleLocs) {
        if (possibleLocs.size() == 0)
            return Optional.empty();

        Cell currLM = possibleLocs.get(0);
        for(Cell c: possibleLocs){
            if (c.leftOfComparison(currLM) < 0){
                currLM = c;
            }
        }
        return Optional.of(currLM);
    }

    @Override
    public String toString(){
        return "left-most movement strategy";
    }

}
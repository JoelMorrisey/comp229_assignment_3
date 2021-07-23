import java.util.*;

public class RandomMove implements MoveStrategy {

    @Override
    public Optional<Cell> chooseNextLoc(Actor a, Stage s, List<Cell> possibleLocs) {
        if (possibleLocs.size() == 0)
            return Optional.empty();
        int i = (new Random()).nextInt(possibleLocs.size());
        return Optional.of(possibleLocs.get(i));
    }

    @Override
    public String toString(){
        return "random movement strategy";
    }

}
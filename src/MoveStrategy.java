import java.util.*;

public interface MoveStrategy{
    public Optional<Cell> chooseNextLoc(Actor a, Stage s, List<Cell> possibleLocs);
}
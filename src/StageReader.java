import java.io.*;
import java.util.*;
import java.util.regex.*;

class StageReader {
    public static Stage readStage(String path){
        class OrderedProperties extends Properties {
            public Vector orderedNames;
            public OrderedProperties() {
                super ();
                orderedNames = new Vector<String>();
            }
    
            public Object put(Object key, Object value) {
                if (orderedNames.contains(key)) {
                    orderedNames.remove(key);
                }
    
                orderedNames.add(key);
                return super.put(key, value);
            }
    
            public Object remove(Object key) {
                orderedNames.remove(key);
                return super.remove(key);
            }
        }

        Stage stage = new Stage();
        try{
            Properties props = (new OrderedProperties());
            props.load(new FileInputStream(path));
            for (String key : props.stringPropertyNames()) {
                String value = props.getProperty(key);
                Pattern range = Pattern.compile("(.)(\\d+)->(.)(\\d+)");
                Pattern cell = Pattern.compile("(.)(\\d+)");
                List<Cell> cellsInQuestion = new ArrayList<Cell>();
                Matcher rangeMatcher = range.matcher(key);
                Matcher cellMatcher = cell.matcher(key);
                if(rangeMatcher.matches()){
                    cellsInQuestion = stage.grid.cellsInRange(rangeMatcher.group(1).charAt(0),
                                                              Integer.parseInt(rangeMatcher.group(2)),
                                                              rangeMatcher.group(3).charAt(0),
                                                              Integer.parseInt(rangeMatcher.group(4))
                                                             );
                } else if (cellMatcher.matches()) {
                    stage.grid.cellAtColRow(cellMatcher.group(1).charAt(0), Integer.parseInt(cellMatcher.group(2)))
                            .ifPresent(cellsInQuestion::add);
                } else {System.out.println("no match");}
                for (Cell c : cellsInQuestion) {
                    if (value.equals("road")){
                        stage.grid.replaceCell(c, new Road(c.col, c.row, c.x, c.y));
                    } else if (value.equals("grass")){
                        stage.grid.replaceCell(c, new Grass(c.col, c.row, c.x, c.y));
                    } else if (value.equals("water")){
                        stage.grid.replaceCell(c, new Water(c.col, c.row, c.x, c.y));
                    } else if (value.equals("mountain")){
                        stage.grid.replaceCell(c, new Mountain(c.col, c.row, c.x, c.y));
                    } else if (value.equals("mountain")){
                        stage.grid.replaceCell(c, new Mountain(c.col, c.row, c.x, c.y));
                    }else if (value.equals("puppy red")){
                        Cell newCell = stage.grid.cellAtColRow(c.col, c.row).get();
                        stage.actors.add(new Puppy(newCell, 1.0f));
                    }else if (value.equals("puppy blue")){
                        Cell newCell = stage.grid.cellAtColRow(c.col, c.row).get();
                        stage.actors.add(new Puppy(newCell, 0.0f));
                    }else if (value.equals("lion red")){
                        Cell newCell = stage.grid.cellAtColRow(c.col, c.row).get();
                        stage.actors.add(new Lion(newCell, 1.0f));
                    }else if (value.equals("lion blue")){
                        Cell newCell = stage.grid.cellAtColRow(c.col, c.row).get();
                        stage.actors.add(new Lion(newCell, 0.0f));
                    }else if (value.equals("bunny red")){
                        Cell newCell = stage.grid.cellAtColRow(c.col, c.row).get();
                        stage.actors.add(new Rabbit(newCell, 1.0f));
                    }else if (value.equals("bunny blue")){
                        Cell newCell = stage.grid.cellAtColRow(c.col, c.row).get();
                        stage.actors.add(new Rabbit(newCell, 0.0f));
                    }else if (value.equals("mole")){
                        Cell newCell = stage.grid.cellAtColRow(c.col, c.row).get();
                        stage.actors.add(new ImpAdapter(new Mole<Cell>(newCell)));
                    }else if (value.equals("wall")){
                        Cell newCell = stage.grid.cellAtColRow(c.col, c.row).get();
                        stage.actors.add(new ImpAdapter(new Wall<Cell>(newCell)));
                    }else if (value.equals("butterfly")){
                        Cell newCell = stage.grid.cellAtColRow(c.col, c.row).get();
                        stage.actors.add(new ImpAdapter(new Butterfly<Cell>(newCell)));
                    }
                }
            }
        } catch (IOException e){

        }
        return stage;
    }
}
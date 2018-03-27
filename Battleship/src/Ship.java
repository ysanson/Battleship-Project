import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Ship {
    private int startLine, endLine;
    private char startColumn, endColumn;
    private int nbTimesTouched;
    private ShipType type;

    public ShipType getType() {
        return type;
    }

    public void setType(ShipType type) {
        this.type = type;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    public char getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(char startColumn) {
        this.startColumn = startColumn;
    }

    public char getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(char endColumn) {
        this.endColumn = endColumn;
    }

    public int getNbTimesTouched() {
        return nbTimesTouched;
    }

    public void setNbTimesTouched(int nbTimesTouched) {
        this.nbTimesTouched = nbTimesTouched;
    }



    public Ship(String startCoord, String endCoord, ShipType type){ //On attend des coordonnées telles que A1, B5...
        nbTimesTouched = 0;
        String first = startCoord.substring(0, 1);
        startColumn = first.toUpperCase().charAt(0);
        startLine = Integer.parseInt(startCoord.substring(1));
        first = endCoord.substring(0,1);
        endColumn = first.toUpperCase().charAt(0);
        endLine = Integer.parseInt(endCoord.substring(1));
        this.type = type;
    }

    public boolean isHit(String missileCoord){ //Coordonnées de type A1, B5...
        char columnHit;
        int lineHit;
        columnHit = missileCoord.substring(0, 1).toUpperCase().charAt(0);
        lineHit = Integer.parseInt(missileCoord.substring(1));
        if(startLine == endLine && startLine == lineHit) //Navire en ligne et tir sur celle-ci
        {
            if((columnHit >= startColumn && columnHit <= endColumn)) //Tir entre la colonne de début et la colonne de fin
            {
                nbTimesTouched ++;
                return true;
            }
        }
        else if(startColumn == endColumn && columnHit == startColumn) //Navire en colonne et tir sur celle-ci
        {
            if(lineHit >= startLine && lineHit <= endLine)
            {
                nbTimesTouched ++;
                return true;
            }
        }
        return false;
    }

    public boolean isDestroyed()
    {
        return nbTimesTouched == type.getLength();

    }

    public static boolean isCorrect(String startCoord, String endCoord, ShipType type)
    {
       int startLine, endLine, size = 0;
       char startColumn, endColumn;
       startColumn = startCoord.toUpperCase().charAt(0);
       endColumn = endCoord.toUpperCase().charAt(0);
       startLine = Integer.parseInt(startCoord.substring(1));
       endLine = Integer.parseInt(endCoord.substring(1));
       if(Character.compare(startColumn, endColumn) == 0){
           size = Math.abs(startLine - endLine)+1;
       }
       else if(startLine == endLine){
           size = Math.abs(Character.getNumericValue(startColumn) - Character.getNumericValue(endColumn))+1;
       }
       return size == type.getLength();
    }

    public List<String>shipGrid()
    {
        List<String>shipRepresentation = new ArrayList<>();
        String coordinate;
        coordinate= getStartColumn() + Integer.toString(getStartLine());
        int line = getStartLine();
        char column = getStartColumn();
        shipRepresentation.add(coordinate);
        if(getStartColumn() == getEndColumn())
        {
            while(line < getEndLine())
            {
                line ++;
                coordinate = getStartColumn() + Integer.toString(line);
                shipRepresentation.add(coordinate);
            }
        }
        else
        {
            while(column < getEndColumn())
            {
                column++;
                coordinate = column + Integer.toString(line);
                shipRepresentation.add(coordinate);
            }
        }
        return shipRepresentation;
    }

    public boolean isOverlapping(Joueur player){
        if (Collections.disjoint(this.shipGrid(), player.getAircraftCarrier().shipGrid())) return true;
        if (Collections.disjoint(this.shipGrid(), player.getBattleship().shipGrid())) return true;
        if (Collections.disjoint(this.shipGrid(), player.getCruiser().shipGrid())) return true;
        if (Collections.disjoint(this.shipGrid(), player.getSubmarine().shipGrid())) return true;
        if (Collections.disjoint(this.shipGrid(), player.getDestroyer().shipGrid())) return true;
        return false;
    }

    @Override
    public String toString() {
        StringBuffer output = new StringBuffer("Ship type : ");
        output.append(type.getName());
        output.append(" Length : ");
        output.append(type.getLength());
        output.append(" [start : ");
        output.append(startColumn);
        output.append(startLine);
        output.append(" Finish : ");
        output.append(endColumn);
        output.append(endLine);
        output.append("]\n");
        output.append("Number of times touched : ");
        output.append(nbTimesTouched);
        return output.toString();

    }
}

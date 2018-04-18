import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Ship {
    private char startLine, endLine;
    private int startColumn, endColumn;
    private int nbTimesTouched;
    private ShipType type;

    public ShipType getType() {
        return type;
    }

    public void setType(ShipType type) {
        this.type = type;
    }

    public char getStartLine() {
        return startLine;
    }

    public void setStartLine(char startLine) {
        this.startLine = startLine;
    }

    public char getEndLine() {
        return endLine;
    }

    public void setEndLine(char endLine) {
        this.endLine = endLine;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(int endColumn) {
        this.endColumn = endColumn;
    }

    public int getNbTimesTouched() {
        return nbTimesTouched;
    }

    public void setNbTimesTouched(int nbTimesTouched) {
        this.nbTimesTouched = nbTimesTouched;
    }

    public boolean isHit(String missileCoord){ //Coordonnées de type A1, B5...
        int columnHit;
        char lineHit;
        try {
            lineHit = missileCoord.toUpperCase().charAt(0);
            columnHit = Integer.parseInt(missileCoord.substring(1));
            if (startLine == endLine && startLine == lineHit) //Navire en ligne et tir sur celle-ci
            {
                if ((columnHit >= startColumn && columnHit <= endColumn)) //Tir entre la colonne de début et la colonne de fin
                {
                    nbTimesTouched++;
                    return true;
                }
            } else if (startColumn == endColumn && columnHit == startColumn) //Navire en colonne et tir sur celle-ci
            {
                if (lineHit >= startLine && lineHit <= endLine) {
                    nbTimesTouched++;
                    return true;
                }
            }
        }catch(Exception e){System.out.println("Bad coordinates");}
        return false;
    }

    public boolean isDestroyed()
    {
        return nbTimesTouched == type.getLength();
    }

    public List<String>shipGrid()
    {
        List<String>shipRepresentation = new ArrayList<>();
        String coordinate;
        coordinate= getStartLine() + Integer.toString(getStartColumn()) ;
        char line = getStartLine();
        int column = getStartColumn();
        shipRepresentation.add(coordinate);
        if(getStartColumn() == getEndColumn())
        {
            while(line < getEndLine())
            {
                line ++;
                coordinate = line + Integer.toString(getStartColumn());
                shipRepresentation.add(coordinate);
            }
        }
        else
        {
            while(column < getEndColumn())
            {
                column++;
                coordinate = line + Integer.toString(column);
                shipRepresentation.add(coordinate);
            }
        }
        return shipRepresentation;
    }


    public Ship(String startCoord, String endCoord, ShipType type){ //Waiting for coordinates like A1, B5...
        nbTimesTouched = 0;
        try {
            startLine = startCoord.toUpperCase().charAt(0);
            startColumn = Integer.parseInt(startCoord.substring(1));
            endLine = endCoord.toUpperCase().charAt(0);
            endColumn = Integer.parseInt(endCoord.substring(1));
        }catch (Exception e){System.out.println("Bad coordinates");}
        this.type = type;
    }

    public static boolean isCorrect(String startCoord, String endCoord, ShipType type)
    {
       char startLine, endLine;
       int startColumn, endColumn, size = 0;
       try {
           startLine = startCoord.toUpperCase().charAt(0);
           endLine = endCoord.toUpperCase().charAt(0);
           startColumn = Integer.parseInt(startCoord.substring(1));
           endColumn = Integer.parseInt(endCoord.substring(1));
           if (startLine <= 'J' && endLine <= 'J' && startColumn <= 10 && endColumn <= 10) {
               if (startColumn == endColumn) {
                   size = Math.abs(startLine - endLine) + 1;
               } else if (Character.compare(startLine, endLine) == 0) {
                   size = Math.abs(startColumn - endColumn) + 1;
               }
           }
           return size == type.getLength();
       }catch (Exception e){return false;}
    }


    @Override
    public String toString() {
        StringBuffer output = new StringBuffer("Ship type : ");
        output.append(type.getName());
        output.append(" Length : ");
        output.append(type.getLength());
        output.append(" [start : ");
        output.append(startLine);
        output.append(startColumn);
        output.append(" Finish : ");
        output.append(endLine);
        output.append(endColumn);
        output.append("] ");
        output.append("Number of times touched : ");
        output.append(nbTimesTouched);
        return output.toString();

    }
}

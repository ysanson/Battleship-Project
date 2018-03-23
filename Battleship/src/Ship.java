import java.util.ArrayList;
import java.util.List;

public class Ship {
    private int startLine, endLine;
    private char startColumn, endColumn;
    private int size, nbTimesTouched;

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNbTimesTouched() {
        return nbTimesTouched;
    }

    public void setNbTimesTouched(int nbTimesTouched) {
        this.nbTimesTouched = nbTimesTouched;
    }


    public Ship(String startCoord, String endCoord){ //On attend des coordonnées telles que A1, B5...
        nbTimesTouched = 0;
        String first = startCoord.substring(0, 1);
        startColumn = first.toUpperCase().charAt(0);
        startLine = Integer.parseInt(startCoord.substring(1));
        first = endCoord.substring(0,1);
        endColumn = first.toUpperCase().charAt(0);
        endLine = Integer.parseInt(endCoord.substring(1));
        if(startLine == endLine)
        {
            size = Math.abs(startColumn - endColumn) + 1;
        }
        else if(startColumn == endColumn)
        {
            size = Math.abs(startLine - endLine) + 1;
        }

    }

    public boolean isHit(String missileCoord){ //Coordonnées de type A1, B5...
        char columnHit;
        int lineHit;
        columnHit = missileCoord.substring(0, 1).toUpperCase().charAt(0);
        lineHit = Integer.parseInt(missileCoord.substring(1));
        if(startLine == endLine && startLine == lineHit) //Navire en ligne et tir sur celle-ci
        {
            if(columnHit >= startColumn && columnHit <= endColumn) //Tir entre la colonne de début et la colonne de fin
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
        return nbTimesTouched == size;

    }

    public static boolean isCorrect(String startCoord, String endCoord)
    {
        char startLine, endLine;
        int startColumn, endColumn, size = 0;
        startLine = startCoord.substring(0,1).toUpperCase().charAt(0);
        endLine = endCoord.substring(0,1).toUpperCase().charAt(0);
        startColumn = Integer.parseInt(startCoord.substring(1));
        endColumn = Integer.parseInt(endCoord.substring(1));
        if(startColumn == endColumn)
        {
            size = Math.abs(startLine - endLine) +1;
        }
        else if(startLine == endLine)
        {
            size = Math.abs(startColumn - endColumn) +1;
        }
        return (size == 5) || (size == 4) || (size == 3) || (size == 2);
    }

    public List<String>shipGrid()
    {
        List<String>shipRepresentation = new ArrayList<String>();
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

    @Override
    public String toString() {
        StringBuffer output = new StringBuffer("Ship size : ");
        output.append(size);
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

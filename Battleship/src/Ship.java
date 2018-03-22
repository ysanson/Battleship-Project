public class Ship {
    char startLine, endLine;
    int startColumn, endColumn;
    private int size, nbTimesTouched;


    public Ship(String startCoord, String endCoord){ //On attend des coordonnées telles que A1, B5...
        nbTimesTouched = 0;
        String first = startCoord.substring(0, 1);
        startLine = first.toUpperCase().charAt(0);
        startColumn = Integer.parseInt(startCoord.substring(1));
        first = endCoord.substring(0,1);
        endLine = first.toUpperCase().charAt(0);
        endColumn = Integer.parseInt(endCoord.substring(1));
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
        int columnHit;
        char lineHit;
        lineHit = missileCoord.substring(0, 1).charAt(0);
        lineHit = Character.toUpperCase(lineHit);
        columnHit = Integer.parseInt(missileCoord.substring(1));
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
    public boolean isDestroyed(){
        return nbTimesTouched == size;

    }

    public String toString() {
        StringBuffer output = new StringBuffer("Ship size : ");
        output.append(size);
        output.append(" [start : ");
        output.append(startLine);
        output.append(startColumn);
        output.append(" Finish : ");
        output.append(endLine);
        output.append(endColumn);
        output.append("]\n");
        output.append("Number of times touched : ");
        output.append(nbTimesTouched);
        return output.toString();

    }
}

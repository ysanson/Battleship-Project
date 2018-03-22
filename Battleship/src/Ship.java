
public class Ship {
    String startLine, endLine;
    int startColumn, endColumn;


    public Ship(String startCoord, String endCoord){ //On attend des coordonnées telles que A1, B5...
        String first = startCoord.substring(0, 1);
        String second = startCoord.substring(1);
        startLine = first;
        startColumn = Integer.parseInt(second);
        first = endCoord.substring(0,1);
        second = endCoord.substring(1);
        endLine = first;
        startColumn = Integer.parseInt(second);
    }

    public boolean isHit(String missileCoord){
        int column;
        String line;

    }
    public boolean isDestroyed(){

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}

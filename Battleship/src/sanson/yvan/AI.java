package sanson.yvan;
import java.util.*;

public class AI extends Joueur {
    /*The IA has three levels :
    1- Easy : It will choose random coordinates, without checking if it has already chose it before.
    2- Medium : It will chose random coordinates, minus the ones that have already been shot at
    3- Hard : When the IA touches a ship, it will try to look around the coordinate, and will try to look in the orientation of the last two hits.
    If it doesn't touch anything, it will fall back to level 2 like search.

    Ship placement is independant of the level.
     */
    private int level;
    private ArrayList<Coordinates> shotsTouched;
    private boolean isSunk, isHit;

    public AI(int level) {
        super("AI");
        this.level=level;
        shotsTouched = new ArrayList<Coordinates>();
        isSunk=false;
        isHit=false;
    }

    public AI(String name, int level){
        super(name);
        this.level=level;
        shotsTouched = new ArrayList<Coordinates>();
        isSunk=false;
        isHit=false;
    }

    @Override
    public void initialize(Scanner sc){
    	Coordinates startCoord=null, endCoord=null;
    	Ship ship;
    	//Aircraft Carrier first
    	do{
	    	startCoord=randomCoord();
	    	endCoord=findEndCoord(startCoord, ShipType.aircraftCarrier);
    	}while(endCoord==null);
	    setAircraftCarrier(new Ship(startCoord, endCoord, ShipType.aircraftCarrier));
    	
    	do{
    		do{
		    	startCoord=randomCoord();
		    	endCoord=findEndCoord(startCoord, ShipType.battleship);
    		}while(endCoord==null);
    		ship = new Ship(startCoord, endCoord, ShipType.battleship);
    	}while(isOverlapping(ship, this));
    	setBattleship(ship);
    	
    	do{
    		do{
	    		startCoord=randomCoord();
	    		endCoord=findEndCoord(startCoord, ShipType.cruiser);
    		}while(endCoord==null);
    		ship = new Ship(startCoord, endCoord, ShipType.cruiser);
    	}while(isOverlapping(ship, this));
    	setCruiser(ship);
    	
    	do{
    		do{
	    		startCoord=randomCoord();
	    		endCoord=findEndCoord(startCoord, ShipType.submarine);
    		}while(endCoord==null);
    		ship = new Ship(startCoord, endCoord, ShipType.submarine);
    	}while(isOverlapping(ship, this));
    	setSubmarine(ship);
    	
    	do{
    		do{
	    		startCoord=randomCoord();
	    		endCoord=findEndCoord(startCoord, ShipType.destroyer);
    		}while(endCoord==null);
    		ship=new Ship(startCoord, endCoord, ShipType.destroyer);
    	}while(isOverlapping(ship, this));
    	setDestroyer(ship);
    }

    @Override
    public int sendMissile(Coordinates missile, Joueur playerReceiving){
        int hasHit =  super.sendMissile(missile, playerReceiving);
        if(hasHit==2){
            shotsTouched.add(missile);
            isSunk=true;
            isHit=true;
        }
        else if(hasHit==1) {
            shotsTouched.add(missile);
            isSunk=false;
            isHit=true;
        }
        else{
            isSunk=false;
            isHit=false;
        }

        return hasHit;
    }

    @Override
    public int receiveMissile(Coordinates missile)
    {//We first need to know if the missile has already been shot at this position. If not, we receive it
        if(!getShotsReceived().contains(missile)) {
            addShotsReceived(missile);
            if (getAircraftCarrier().isHit(missile)) {
                getAircraftCarrier().damageShip(missile);
                if(getAircraftCarrier().isDestroyed()){
                    return 2;
                }
                return 1;
            } else if (getBattleship().isHit(missile)) {
                getBattleship().damageShip(missile);
                if(getBattleship().isDestroyed()){
                    return 2;
                }
                return 1;
            } else if (getCruiser().isHit(missile)) {
                getCruiser().damageShip(missile);
                if(getCruiser().isDestroyed()){
                    return 2;
                }
                return 1;
            } else if (getDestroyer().isHit(missile)) {
                getDestroyer().damageShip(missile);
                if(getDestroyer().isDestroyed()){
                    return 2;
                }
                return 1;
            } else if (getSubmarine().isHit(missile)) {
                getSubmarine().damageShip(missile);
                if(getSubmarine().isDestroyed()){
                    return 2;
                }
                return 1;
            } else {
                return 0;
            }
        }
        else
        {
            return -1;
        }
    }

    public Coordinates randomCoord(){
        //Calculates a random coordinate
        Random r = new Random();
        String line="ABCDEFGHIJ";
        char randLine=line.charAt(r.nextInt(line.length()));
        int randColumn=r.nextInt(10)+1;
        Coordinates randCoord=new Coordinates(randLine, randColumn);
        return randCoord;
    }

    public Coordinates findEndCoord(Coordinates startCoord, ShipType type){
        //Find the end coordinate of a ship, and places it in line or in column
        Random r = new Random();
        boolean orientation = r.nextBoolean();
        Coordinates endCoord = null;
        if(orientation){
            //In line, we must move the char.
            char endLine = (char) (startCoord.getLine() + type.getLength()-1);
            if(endLine<='J'){
                endCoord = new Coordinates(endLine, startCoord.getColumn());
            }
            else{//if impossible, we must try to place it in column
            	int endColumn=startCoord.getColumn()+type.getLength()-1;
                if(endColumn<=10){
                    endCoord = new Coordinates(startCoord.getLine(), endColumn);
                }
            }
        }else{
            //In column, we must move the integer
            int endColumn=startCoord.getColumn()+type.getLength()-1;
            if(endColumn<=10){
                endCoord = new Coordinates(startCoord.getLine(), endColumn);
            }
            else{//if impossible, we must try to place it in line
            	char endLine = (char) (startCoord.getLine() + type.getLength()-1);
                if(endLine<='J'){
                    endCoord = new Coordinates(endLine, startCoord.getColumn());
                }
            }
        }
        return endCoord;
    }

    public Coordinates calculateMissile(){
        Coordinates missileCoord = randomCoord();
        if(level==2) {
            while (getShotsFired().contains(missileCoord)) {
                missileCoord = randomCoord();
            }
        }
        else if(level==3) {
            Random r = new Random();
            int randPos=0;
            if(!shotsTouched.isEmpty()) { //Must have at least one shot touched and one shot fired
                if(!isSunk && isHit && lastTwoHitsAreNeighbors()){
                    //The ship is not sunk, we hit something and the last 2 missiles are either on the same line or same column
                    if(shotsTouched.get(shotsTouched.size()-1).getLine()==shotsTouched.get(shotsTouched.size()-2).getLine()){
                        //Last 2 missiles touched the same line, so we search on it
                        ArrayList<Coordinates> lineNeighbors=shotsTouched.get(shotsTouched.size()-1).findNeighborsInline();
                        lineNeighbors.removeIf(c->getShotsFired().contains(c)); //It will remove the second last shot touched.
                        if(!lineNeighbors.isEmpty()){ //neighbors will contains either one or zero coordinates
                            if(lineNeighbors.size()==1) //Just a precaution, most likely always true
                                return lineNeighbors.get(0);
                        }
                    }
                    else{
                        ArrayList<Coordinates>columnNeighbors=shotsTouched.get(shotsTouched.size()-1).findNeighborsInColumn();
                        columnNeighbors.removeIf(c->getShotsFired().contains(c));
                        if(!columnNeighbors.isEmpty()){
                            if(columnNeighbors.size()==1) //Just a precaution, most likely always true
                                return columnNeighbors.get(0);
                        }
                    }
                }
                if(!isSunk) {
                    //The ship is not sunk
                    ArrayList<Coordinates> neighbors = shotsTouched.get(shotsTouched.size() - 1).findNeighbors();
                    //If we already shot in some of the neighbors, we remove them
                    neighbors.removeIf(c -> getShotsFired().contains(c));
                    if (!neighbors.isEmpty()) {
                        do {
                            randPos = r.nextInt(neighbors.size());
                            missileCoord = neighbors.get(randPos);
                        }
                        while (getShotsFired().contains(missileCoord)); //One of the coordinates is not yet touched : we loop until we find it
                        return missileCoord;
                    }
                }
            }
            //If didn't shot, if neighbors are already shot, we fall back to default search
            while (getShotsFired().contains(missileCoord)) {
                missileCoord = randomCoord();
            }

        }
        return missileCoord;
    }

    public boolean lastTwoHitsAreNeighbors(){
        //Used to determine if we touched a ship
        if(shotsTouched.size()>1) {
            ArrayList<Coordinates> neighbors = shotsTouched.get(shotsTouched.size() - 1).findNeighbors();
            return neighbors.contains(shotsTouched.get(shotsTouched.size() - 2));
        }
        return false;
    }

}

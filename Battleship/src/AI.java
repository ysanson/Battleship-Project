import java.util.Random;

public class AI extends Joueur {

    public AI() {
        super("I-401");
    }

    @Override
    public void initialize(){
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
    	
    	System.out.println("Init complete.");
    }

    public int sendMissile(Coordinates missile, Joueur playerReceiving){
        Coordinates missileCoord=randomCoord();
        return super.sendMissile(missileCoord, playerReceiving);
    }

    public Coordinates randomCoord(){
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
    

}

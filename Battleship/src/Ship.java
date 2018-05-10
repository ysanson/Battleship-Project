import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Ship {
    private Coordinates startCoord, endCoord;
    private int nbTimesTouched;
    private ArrayList<Coordinates>positionsTouched;
    private ShipType type;

    public ShipType getType() {
        return type;
    }

    public void setType(ShipType type) {
        this.type = type;
    }

    public int getNbTimesTouched() {
        return nbTimesTouched;
    }

    public void setNbTimesTouched(int nbTimesTouched) {
        this.nbTimesTouched = nbTimesTouched;
    }

    public Coordinates getStartCoord() {
		return startCoord;
	}

	public void setStartCoord(Coordinates startCoord) {
		this.startCoord = startCoord;
	}

	public Coordinates getEndCoord() {
		return endCoord;
	}

	public void setEndCoord(Coordinates endCoord) {
		this.endCoord = endCoord;
	}

	public ArrayList<Coordinates> getPositionsTouched() {
		return positionsTouched;
	}

	public void setPositionsTouched(ArrayList<Coordinates> positionsTouched) {
		this.positionsTouched = positionsTouched;
	}

	public boolean isHit(Coordinates missileCoord){ //Coordinates type A1, B5...
		if(!positionsTouched.contains(missileCoord)){
			if (startCoord.getLine() == endCoord.getLine() && startCoord.getLine() == missileCoord.getLine()) //Ship in line and missile on it
			{
			    if ((missileCoord.getColumn() >= startCoord.getColumn() && missileCoord.getColumn() <= endCoord.getColumn())) //Missile btw start column and end column
			    {
			        return true;
			    }
			}else if (startCoord.getColumn() == endCoord.getColumn() && missileCoord.getColumn() == startCoord.getColumn()) //Ship in column and missile on it
			{
		        if (missileCoord.getLine() >= startCoord.getLine() && missileCoord.getLine() <= endCoord.getLine()) {
		            return true;
		        }
			}
		}
		else{
			System.out.println("Missile already shot on this part of the ship!");
		}
		return false;
    }
	
	public void damageShip(Coordinates missileCoord){
		nbTimesTouched++;
		positionsTouched.add(missileCoord);
	}

    public boolean isDestroyed()
    {//The ship is destroyed if it is touched in all its length.
        return nbTimesTouched == type.getLength();
    }

    public List<String>shipGrid()
    {//Build a list containing all cases of the ship
        List<String>shipRepresentation = new ArrayList<>();
        String coordinate;
        coordinate= startCoord.toString();
        char line = startCoord.getLine();
        int column = startCoord.getColumn();
        shipRepresentation.add(coordinate);
        if(startCoord.getColumn() == endCoord.getColumn())
        {
            while(line < endCoord.getLine())
            {
                line ++;
                coordinate = line + Integer.toString(column);
                shipRepresentation.add(coordinate);
            }
        }
        else
        {
            while(column < endCoord.getColumn())
            {
                column++;
                coordinate = line + Integer.toString(column);
                shipRepresentation.add(coordinate);
            }
        }
        return shipRepresentation;
    }


    public Ship(Coordinates startCoord, Coordinates endCoord, ShipType type){ //Waiting for coordinates like A1, B5...
        nbTimesTouched = 0;
        positionsTouched = new ArrayList<>();
        this.startCoord = startCoord;
        this.endCoord = endCoord;
        this.type = type;
        
    }

    public static boolean isCorrect(Coordinates startCoord, Coordinates endCoord, ShipType type)
    {//Used to test if the coordinates given can be applied to this type of ship
       int size = 0;
       //testing if it's in the game grid
       if (startCoord.getLine() <= 'J' && endCoord.getLine() <= 'J' && startCoord.getColumn() <= 10 && endCoord.getColumn() <= 10) {
           //Testing if it's in line or in column
           if (startCoord.getColumn() == endCoord.getColumn()) {
               size = Math.abs(startCoord.getLine() - endCoord.getLine()) + 1;
           } else if (startCoord.getLine() == endCoord.getLine()) {
               size = Math.abs(startCoord.getColumn() - endCoord.getColumn()) + 1;
           }
       }
       return size == type.getLength();
    }


    @Override
    public String toString() {
        StringBuffer output = new StringBuffer("Ship type : ");
        output.append(type.getName());
        output.append(" Length : ");
        output.append(type.getLength());
        output.append(" [start : ");
        output.append(startCoord.toString());
        output.append(" Finish : ");
        output.append(endCoord.toString());
        output.append("] ");
        output.append("Number of times touched : ");
        output.append(nbTimesTouched);
        return output.toString();

    }
}

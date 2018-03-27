import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Joueur {
    private Ship aircraftCarrier;
    private Ship battleship;
    private Ship cruiser;
    private Ship submarine;
    private Ship destroyer;
    private int nbShipsLeft;
    private List<String>shotsFired;
    private List<String>shotsReceived;

    public int getNbShipsLeft() {
        return nbShipsLeft;
    }

    public void setNbShipsLeft(int nbShipsLeft) {
        this.nbShipsLeft = nbShipsLeft;
    }

    public Ship getAircraftCarrier() {
        return aircraftCarrier;
    }

    public void setAircraftCarrier(Ship aircraftCarrier) {
        if(aircraftCarrier.getType().equals(ShipType.aircraftCarrier))
        {
            this.aircraftCarrier = aircraftCarrier;
        }
        else
        {
            System.out.println("Aircraft Carrier must be a size of 5");
        }

    }

    public Ship getBattleship() {
        return battleship;
    }

    public void setBattleship(Ship battleship) {
        if(battleship.getType().equals(ShipType.battleship))
        {
            this.battleship = battleship;
        }
        else
        {
            System.out.println("Battleship must be a size of 4");
        }

    }

    public Ship getCruiser() {
        return cruiser;
    }

    public void setCruiser(Ship cruiser) {
        if(cruiser.getType().equals(ShipType.cruiser))
        {
            this.cruiser = cruiser;
        }
        else
        {
            System.out.println("Cruiser must be a size of 3");
        }

    }

    public Ship getSubmarine() {
        return submarine;
    }

    public void setSubmarine(Ship submarine) {
        if(submarine.getType().equals(ShipType.submarine))
        {
            this.submarine = submarine;
        }
        else
        {
            System.out.println("Submarine must be a size of 3");
        }

    }

    public Ship getDestroyer() {
        return destroyer;
    }

    public void setDestroyer(Ship destroyer) {
        if(destroyer.getType().equals(ShipType.destroyer))
        {
            this.destroyer = destroyer;
        }
        else
        {
            System.out.println("Destroyer must be a size of 2");
        }
    }

    public List<String> getShotsFired() {
        return shotsFired;
    }

    public List<String> getShotsReceived() {
        return shotsReceived;
    }

    public void addShotFired(String coordinates)
    {
        shotsFired.add(coordinates);
    }

    public void addShotsReceived(String coordinates)
    {
        shotsReceived.add(coordinates);
    }

    public void receiveMissile(String missile)
    {
        if(!shotsReceived.contains(missile)) {
            addShotsReceived(missile);
            boolean hasHit;
            if (aircraftCarrier.isHit(missile) || battleship.isHit(missile) || cruiser.isHit(missile) || destroyer.isHit(missile) || submarine.isHit(missile)) {
                hasHit = true;
            }
        }
        else
        {
            System.out.println("Missile already shot on this position");
        }
    }

    public void sendMissile(String missile)
    {
        if(!shotsFired.contains(missile))
        {
            addShotFired(missile);
        }
        else{
            System.out.println("Missile already fired on this position");
        }
    }


    public Joueur() {
        nbShipsLeft = 5;
        shotsFired = new ArrayList<>();
        shotsReceived = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "Joueur{" +
                "aircraftCarrier=" + aircraftCarrier.toString() +
                ", battleship=" + battleship.toString() +
                ", cruiser=" + cruiser.toString() +
                ", submarine=" + submarine.toString() +
                ", destroyer=" + destroyer.toString() +
                ", nbShipsLeft=" + nbShipsLeft +
                '}';
    }
}

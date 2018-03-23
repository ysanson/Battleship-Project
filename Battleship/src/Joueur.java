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
        if(aircraftCarrier.getSize() == 5)
        {
            this.aircraftCarrier = aircraftCarrier;
        }
        else
        {
            throw new RuntimeException("Aircraft Carrier must be a size of 5");
        }

    }

    public Ship getBattleship() {
        return battleship;
    }

    public void setBattleship(Ship battleship) {
        if(battleship.getSize() == 4)
        {
            this.battleship = battleship;
        }
        else
        {
            throw new RuntimeException("Battleship must be a size of 4");
        }

    }

    public Ship getCruiser() {
        return cruiser;
    }

    public void setCruiser(Ship cruiser) {
        if(cruiser.getSize() == 3)
        {
            this.cruiser = cruiser;
        }
        else
        {
            throw new RuntimeException("Cruiser must be a size of 3");
        }

    }

    public Ship getSubmarine() {
        return submarine;
    }

    public void setSubmarine(Ship submarine) {
        if(submarine.getSize() == 3)
        {
            this.submarine = submarine;
        }
        else
        {
            throw new RuntimeException("Submarine must be a size of 3");
        }

    }

    public Ship getDestroyer() {
        return destroyer;
    }

    public void setDestroyer(Ship destroyer) {
        if(destroyer.getSize() == 2)
        {
            this.destroyer = destroyer;
        }
        else
        {
            throw new RuntimeException("Destroyer must be a size of 2");
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
            throw new RuntimeException("Missile already shot on this position");
        }
    }

    public void sendMissile(String missile)
    {
        if(!shotsFired.contains(missile))
        {
            addShotFired(missile);
        }
        else{
            throw new RuntimeException("Missile already fired at this position");
        }
    }

    public boolean noOverlap(Ship aircraftCarrier, Ship battleship, Ship cruiser, Ship submarine, Ship destroyer)
    {
        return (Collections.disjoint(aircraftCarrier.shipGrid(), battleship.shipGrid())
                || Collections.disjoint(aircraftCarrier.shipGrid(), cruiser.shipGrid())
                || Collections.disjoint(aircraftCarrier.shipGrid(), submarine.shipGrid())
                || Collections.disjoint(aircraftCarrier.shipGrid(), destroyer.shipGrid())
                || Collections.disjoint(battleship.shipGrid(), cruiser.shipGrid())
                || Collections.disjoint((battleship.shipGrid()), submarine.shipGrid())
                || Collections.disjoint(battleship.shipGrid(), destroyer.shipGrid())
                || Collections.disjoint(cruiser.shipGrid(), submarine.shipGrid())
                || Collections.disjoint(cruiser.shipGrid(), destroyer.shipGrid())
                || Collections.disjoint(submarine.shipGrid(), destroyer.shipGrid()));
    }


    public Joueur(Ship aircraftCarrier, Ship battleship, Ship cruiser, Ship submarine, Ship destroyer) {
        if(noOverlap(aircraftCarrier, battleship, cruiser, submarine, destroyer))
        {
            setAircraftCarrier(aircraftCarrier);
            setBattleship(battleship);
            setCruiser(cruiser);
            setSubmarine(submarine);
            setDestroyer(destroyer);
            nbShipsLeft = 5;
            shotsFired = new ArrayList<>();
            shotsReceived = new ArrayList<>();
        }
        else
        {
            throw new RuntimeException("Ships are overlapping !");
        }
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Joueur {
    private String name;
    private Ship aircraftCarrier;
    private Ship battleship;
    private Ship cruiser;
    private Ship submarine;
    private Ship destroyer;
    private int nbShipsLeft;
    private List<String>shotsFired;
    private List<String>shotsReceived;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
            System.out.println("This is not an aicraft carrier");
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
            System.out.println("This is not a batteship");
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
            System.out.println("This is not a cruiser");
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
            System.out.println("This is not a submarine");
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
            System.out.println("This is not a destroyer");
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

    public boolean receiveMissile(String missile)
    {
        if(!shotsReceived.contains(missile)) {
            addShotsReceived(missile);
            if (aircraftCarrier.isHit(missile) || battleship.isHit(missile) || cruiser.isHit(missile) || destroyer.isHit(missile) || submarine.isHit(missile)) {
                return true;
            }
            else{
                return false;
            }
        }
        else
        {
            System.out.println("Missile already shot on this position");
            return false;
        }
    }

    public void sendMissile(String missile, Joueur playerReceiving)
    {
        if(!shotsFired.contains(missile))
        {
            addShotFired(missile);
            playerReceiving.receiveMissile(missile);
        }
        else{
            System.out.println("Missile already fired on this position");
        }
    }


    public Joueur(String name) {
        this.name = name;
        nbShipsLeft = 5;
        shotsFired = new ArrayList<>();
        shotsReceived = new ArrayList<>();
    }

    public static boolean isOverlapping(Ship ship, Joueur player){
        if (Collections.disjoint(ship.shipGrid(), player.getAircraftCarrier().shipGrid())) return true;
        if (Collections.disjoint(ship.shipGrid(), player.getBattleship().shipGrid())) return true;
        if (Collections.disjoint(ship.shipGrid(), player.getCruiser().shipGrid())) return true;
        if (Collections.disjoint(ship.shipGrid(), player.getSubmarine().shipGrid())) return true;
        if (Collections.disjoint(ship.shipGrid(), player.getDestroyer().shipGrid())) return true;
        return false;
    }


    @Override
    public String toString() {
        return "Joueur{" +
                 aircraftCarrier.toString() + "\n" +
                battleship.toString() + "\n" +
                cruiser.toString() + "\n" +
                submarine.toString() + "\n" +
                destroyer.toString() + "\n" +
                "Nb ships left : " + nbShipsLeft +
                '}';
    }
}

package sanson.yvan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Joueur {
    private String name;
    private Ship aircraftCarrier;
    private Ship battleship;
    private Ship cruiser;
    private Ship submarine;
    private Ship destroyer;
    private int nbShipsLeft;
    private ArrayList<Coordinates>shotsFired;
    private ArrayList<Coordinates>shotsReceived;

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
        if(aircraftCarrier.getType() == ShipType.aircraftCarrier)
            this.aircraftCarrier = aircraftCarrier;
        else
            System.out.println("This is not an aircraft carrier");
    }

    public Ship getBattleship() {
        return battleship;
    }

    public void setBattleship(Ship battleship) {
        if(battleship.getType() == ShipType.battleship)
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

    public ArrayList<Coordinates> getShotsFired() {
        return shotsFired;
    }

    public ArrayList<Coordinates> getShotsReceived() {
        return shotsReceived;
    }

    public void addShotFired(Coordinates coordinates)
    {
        shotsFired.add(coordinates);
    }

    public void addShotsReceived(Coordinates coordinates)
    {
        shotsReceived.add(coordinates);
    }

    public boolean isDead(){
        return nbShipsLeft==0;
    }


    public Joueur(String name) {
        this.name = name;
        nbShipsLeft = 5;
        shotsFired = new ArrayList<>();
        shotsReceived = new ArrayList<>();
    }

    public void calculateShipsLeft(){
        //Updates the number of ships left for the player
        nbShipsLeft=0;
        if(!aircraftCarrier.isDestroyed()) nbShipsLeft++;
        if(!battleship.isDestroyed()) nbShipsLeft++;
        if(!cruiser.isDestroyed()) nbShipsLeft++;
        if(!submarine.isDestroyed()) nbShipsLeft++;
        if(!destroyer.isDestroyed()) nbShipsLeft++;
    }

    public int receiveMissile(Coordinates missile)
    {//We first need to know if the missile has already been shot at this position. If not, we receive it
        if(!shotsReceived.contains(missile)) {
            addShotsReceived(missile);
            if (aircraftCarrier.isHit(missile)) {
            	aircraftCarrier.damageShip(missile);
                if(aircraftCarrier.isDestroyed()){
                    System.out.println("Aircraft Carrier sunk!");
                    return 2;
                }
                System.out.println("Aircraft Carrier damaged");
                return 1;
            } else if (battleship.isHit(missile)) {
            	battleship.damageShip(missile);
                if(battleship.isDestroyed()){
                    System.out.println("Battleship sunk!");
                    return 2;
                }
                System.out.println("Battleship damaged");
                return 1;
            } else if (cruiser.isHit(missile)) {
            	cruiser.damageShip(missile);
                if(cruiser.isDestroyed()){
                    System.out.println("Cruiser sunk!");
                    return 2;
                }
                System.out.println("Cruiser damaged");
                return 1;
            } else if (destroyer.isHit(missile)) {
            	destroyer.damageShip(missile);
                if(destroyer.isDestroyed()){
                    System.out.println("Destroyer sunk!");
                    return 2;
                }
                System.out.println("Destroyer damaged");
                return 1;
            } else if (submarine.isHit(missile)) {
            	submarine.damageShip(missile);
                if(submarine.isDestroyed()){
                    System.out.println("Submarine sunk!");
                    return 2;
                }
                System.out.println("Submarine damaged");
                return 1;
            } else {
                return 0;
            }
        }
        else
        {
            System.out.println("Missile already shot on this position");
            return -1;
        }
    }

    public int sendMissile(Coordinates missile, Joueur playerReceiving)
    {
        if (shotsFired.contains(missile)) {
            if(!(this instanceof AI))
                System.out.println("Missile already fired on this position");
            return -1;
        } else {
            addShotFired(missile);
            return playerReceiving.receiveMissile(missile);
        }
    }

    public void initialize(Scanner sc){
        System.out.println("Initializing " + name + ".");
        //Initializes the player. Perhaps more of a static method ?
        String sCoord, eCoord;
        Coordinates startCoord = null, endCoord = null;
        Boolean overlap;
        Ship aircraftCarrier, battleship, cruiser, submarine, destroyer;
        //Aircraft Carrier
        System.out.println("Now creating an Aircraft Carrier, size of 5.");
        do {//While the ship is overlapping with another one
            do {//While the coordinates are incorrect
                System.out.println("Enter the start coordinate of your ship (eg. A1, B5, etc.): ");
                sCoord = sc.nextLine();
                System.out.println("Enter the end coordinates of your ship: ");
                eCoord = sc.nextLine();
                if(Coordinates.isCorrect(sCoord)&&Coordinates.isCorrect(eCoord)){
                	startCoord = new Coordinates(sCoord);
                	endCoord = new Coordinates(eCoord);
                }
                else{
                	System.out.println("These are bad coordinates.");
                }
                if(!Ship.isCorrect(startCoord, endCoord, ShipType.aircraftCarrier)){
                    System.out.println("This are not correct values for the specified ship. Please try again.");
                }
            } while (!Ship.isCorrect(startCoord, endCoord, ShipType.aircraftCarrier));
            aircraftCarrier = new Ship(startCoord, endCoord, ShipType.aircraftCarrier);
            overlap = isOverlapping(aircraftCarrier, this);

            if(overlap){
                System.out.println("The ship is overlapping with another. Please change the coordinates");
            }
        }while(overlap);
        this.setAircraftCarrier(aircraftCarrier);
        //Battleship
        System.out.println("Now creating a battleship, size of 4.");
        do{
            do{
                System.out.println("Enter the start coordinate of your ship (eg. A1, B5, etc.): ");
                sCoord = sc.nextLine();
                System.out.println("Enter the end coordinates of your ship: ");
                eCoord = sc.nextLine();
                if(Coordinates.isCorrect(sCoord)&&Coordinates.isCorrect(eCoord)){
                	startCoord = new Coordinates(sCoord);
                	endCoord = new Coordinates(eCoord);
                }
                else{
                	System.out.println("These are bad coordinates.");
                }
                if(!Ship.isCorrect(startCoord, endCoord, ShipType.battleship)){
                    System.out.println("This are not correct values for the specified ship. Please try again.");
                }
                if(!Ship.isCorrect(startCoord, endCoord, ShipType.battleship)){
                    System.out.println("These are not correct values for the specified ship. Please try again.");
                }
            }while(!Ship.isCorrect(startCoord, endCoord, ShipType.battleship));
            battleship = new Ship(startCoord, endCoord, ShipType.battleship);
            overlap = isOverlapping(battleship, this);
            if(overlap){
                System.out.println("The ship is overlapping with another. Please change the coordinates");
            }
        }while(overlap);
        setBattleship(battleship);
        //Cruiser
        System.out.println("Now creating a cruiser, size 3.");
        do{
            do{
                System.out.println("Enter the start coordinate of your ship (eg. A1, B5, etc.): ");
                sCoord = sc.nextLine();
                System.out.println("Enter the end coordinates of your ship: ");
                eCoord = sc.nextLine();
                if(Coordinates.isCorrect(sCoord)&&Coordinates.isCorrect(eCoord)){
                	startCoord = new Coordinates(sCoord);
                	endCoord = new Coordinates(eCoord);
                }
                else{
                	System.out.println("These are bad coordinates.");
                }
                if(!Ship.isCorrect(startCoord, endCoord, ShipType.cruiser)){
                    System.out.println("This are not correct values for the specified ship. Please try again.");
                }
            }while(!Ship.isCorrect(startCoord, endCoord, ShipType.cruiser));
            cruiser = new Ship(startCoord, endCoord, ShipType.cruiser);
            overlap = isOverlapping(cruiser, this);
            if(overlap) {
                System.out.println("The ship is overlapping with another. Please change the coordinates");
            }
        }while(overlap);
        setCruiser(cruiser);
        //Submarine
        System.out.println("Now creating a submarine, size 3.");
        do{
            do{
                System.out.println("Enter the start coordinate of your ship (eg. A1, B5, etc.): ");
                sCoord = sc.nextLine();
                System.out.println("Enter the end coordinates of your ship: ");
                eCoord = sc.nextLine();
                if(Coordinates.isCorrect(sCoord)&&Coordinates.isCorrect(eCoord)){
                	startCoord = new Coordinates(sCoord);
                	endCoord = new Coordinates(eCoord);
                }
                else{
                	System.out.println("These are bad coordinates.");
                }
                if(!Ship.isCorrect(startCoord, endCoord, ShipType.submarine)){
                    System.out.println("This are not correct values for the specified ship. Please try again.");
                }
            }while (!Ship.isCorrect(startCoord, endCoord, ShipType.submarine));
            submarine = new Ship(startCoord, endCoord, ShipType.submarine);
            overlap = isOverlapping(submarine, this);
            if(overlap){
                System.out.println("The ship is overlapping with another. Please change the coordinates");
            }
        }while(overlap);
        setSubmarine(submarine);
        //Destroyer
        System.out.println("Now creating a destroyer, size 2");
        do{
            do{
                System.out.println("Enter the start coordinate of your ship (eg. A1, B5, etc.): ");
                sCoord = sc.nextLine();
                System.out.println("Enter the end coordinates of your ship: ");
                eCoord = sc.nextLine();
                if(Coordinates.isCorrect(sCoord)&&Coordinates.isCorrect(eCoord)){
                	startCoord = new Coordinates(sCoord);
                	endCoord = new Coordinates(eCoord);
                }
                else{
                	System.out.println("These are bad coordinates.");
                }
                if(!Ship.isCorrect(startCoord, endCoord, ShipType.destroyer)){
                    System.out.println("This are not correct values for the specified ship. Please try again.");
                }
            }while(!Ship.isCorrect(startCoord, endCoord, ShipType.destroyer));
            destroyer = new Ship(startCoord, endCoord, ShipType.destroyer);
            overlap = isOverlapping(destroyer, this);
            if(overlap){
                System.out.println("The ship is overlapping with another. Please change the coordinates");
            }
        }while (overlap);
        setDestroyer(destroyer);
        System.out.println("Completed !");
    }

    public static boolean isOverlapping(Ship ship, Joueur player){
        boolean overlap = false;
        ArrayList<String> shipRepresentation = new ArrayList<>(ship.shipGrid()); //So we don't have to recalculate it 5 times
        if(player.getAircraftCarrier()!=null) {
            if (!player.getAircraftCarrier().shipGrid().isEmpty()) { //Not really useful but still
                overlap = !Collections.disjoint(shipRepresentation, player.getAircraftCarrier().shipGrid()); //testing if the ship is overlapping with the carrier
                if (overlap) return true;
            }
        }
        if(player.getBattleship()!=null) {
            if (!player.getBattleship().shipGrid().isEmpty()) {
                overlap = !Collections.disjoint(shipRepresentation, player.getBattleship().shipGrid());
                if (overlap) return true;
            }
        }
        if(player.getCruiser()!=null) {
            if (!player.getCruiser().shipGrid().isEmpty()) {
                overlap = !Collections.disjoint(shipRepresentation, player.getCruiser().shipGrid());
                if (overlap) return true;
            }
        }
        if(player.getSubmarine()!=null) {
            if (!player.getSubmarine().shipGrid().isEmpty()) {
                overlap = !Collections.disjoint(shipRepresentation, player.getSubmarine().shipGrid());
                if (overlap) return true;
            }
        }
        if(player.getDestroyer()!=null) {
            if (!player.getDestroyer().shipGrid().isEmpty()) {
                overlap = !Collections.disjoint(shipRepresentation, player.getDestroyer().shipGrid());
                if (overlap) return true;
            }
        }

        return overlap;
    }

    @Override
    public String toString() {
        return "{" +
                "Name='" + name  +
                "\n" + aircraftCarrier +
                "\n " + battleship +
                "\n" + cruiser +
                "\n" + submarine +
                "\n" + destroyer +
                "\n Number of ships still afloat: " + nbShipsLeft +
                "\n Shots fired: " + shotsFired +
                "\n Shots received: " + shotsReceived +
                '}';
    }
}
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String startCoord, endCoord;
        ShipType ac = ShipType.aircraftCarrier;
        ShipType bs = ShipType.battleship;
        ShipType cr = ShipType.cruiser;
        ShipType sb = ShipType.submarine;
        ShipType dt = ShipType.destroyer;
        System.out.println("Création d'un porte avions:");
        do {
            System.out.println("Entrez les coordonnées du début du navire : ");
            startCoord = sc.nextLine();
            System.out.println("Entrez les coordonénes de fin du navire : ");
            endCoord = sc.nextLine();
        }while(!Ship.isCorrect(startCoord, endCoord, ac));
        Ship navire = new Ship(startCoord, endCoord, ac);
        System.out.println(navire.toString());


    }
}

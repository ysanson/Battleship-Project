import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String startCoord, endCoord;
        do {
            System.out.println("Entrez les coordonnées du début du navire : ");
            startCoord = sc.nextLine();
            System.out.println("Entrez les coordonénes de fin du navire : ");
            endCoord = sc.nextLine();
        }while(!Ship.isCorrect(startCoord, endCoord));
        Ship navire = new Ship(startCoord, endCoord);
        System.out.println(navire.toString());
        System.out.println("Entrez un missile : ");
        String missile = sc.nextLine();
        if(navire.isHit(missile))
        {
            System.out.println("Touché ! ");
        }
        else
        {
            System.out.println("Raté ! ");
        }
        System.out.println(navire.toString());
        
    }
}

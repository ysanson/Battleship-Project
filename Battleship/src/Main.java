import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String startCoord, endCoord;
        String name;
        System.out.println("Entrez votre nom de joueur : ");
        name = sc.nextLine();
        Joueur j1 = new Joueur(name);
        j1.initialize();
        System.out.println(j1.toString());

    }
}

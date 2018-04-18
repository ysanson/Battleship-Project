import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String name;
        System.out.println("Player 1, enter your name : ");
        name = sc.nextLine();
        Joueur j1 = new Joueur(name);
        System.out.println("Player 2, enter your name : ");
        name = sc.nextLine();
        Joueur j2 = new Joueur(name);
        Game game = new Game(j1, j2);
        game.initialize();

        while(!game.isFinished()){

        }
    }
}

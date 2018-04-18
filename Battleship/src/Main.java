import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String name, missileCoordinate;
        System.out.println("Player 1, enter your name : ");
        name = sc.nextLine();
        Joueur j1 = new Joueur(name);
        System.out.println("Player 2, enter your name : ");
        name = sc.nextLine();
        Joueur j2 = new Joueur(name);
        Game game = new Game(j1, j2);
        game.initialize();
        boolean hasHit;

        while(!game.isFinished()){
            System.out.println("Enter a missile coordinate : ");
            missileCoordinate = sc.nextLine();
            hasHit = game.getCurrentPlayer().sendMissile(missileCoordinate, game.getPassivePlayer());
            if(hasHit){
                System.out.println("Your missile touched the opponent ! Continue like this !");
            }
            else{
                System.out.println("Your missile plunged in water. Better luck next time !");
            }
            
        }
    }
}

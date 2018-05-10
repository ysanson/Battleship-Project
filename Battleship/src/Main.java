
import java.util.Scanner;

public class Main {
    static boolean iaBattle;

    public static void main(String[] args)
    {
        iaBattle=true;
        Game game;
        Scanner sc = new Scanner(System.in);
        String name;
        String coord;
        int hasHit=-1;
        Coordinates missileCoordinate;
        System.out.println("Player 1, enter your name: ");
        name = sc.nextLine();
        Joueur j1 = new Joueur(name);

        if(iaBattle){
            AI ai = new AI(2);
            game = new Game(j1, ai);
        }
        else {
            System.out.println("Player 2, enter your name: ");
            name = sc.nextLine();
            Joueur j2 = new Joueur(name);
            game = new Game(j1, j2);
        }
        game.initialize(sc);

        while(!game.isFinished()){
            if(!(game.getCurrentPlayer() instanceof AI)) {
                System.out.println("Get ready " + game.getCurrentPlayer().getName() + "!");
                do {
                    System.out.println("Enter a missile coordinate: ");
                    coord = sc.nextLine();
                    if(Coordinates.isCorrect(coord)) {
                        missileCoordinate = new Coordinates(coord);
                        hasHit = game.getCurrentPlayer().sendMissile(missileCoordinate, game.getPassivePlayer());
                        if(hasHit == 2)
                            System.out.println("You destroyed a ship!");
                        else if (hasHit == 1)
                            System.out.println("Your missile touched the opponent ! Continue like this !");
                        else if (hasHit == 0)
                            System.out.println("Your missile plunged in water. Better luck next time !");
                    }
                } while (hasHit == -1);
                //We enter coordinates again if the player enters coordinates already tested(sendMissile returns-1)
            }
            else{
                System.out.println("AI's turn!");
                do{
                    missileCoordinate = ((AI) game.getCurrentPlayer()).calculateMissile();
                    System.out.println("The AI shoots at the coordinate " + missileCoordinate);
                    hasHit=((AI)game.getCurrentPlayer()).sendMissile(missileCoordinate, game.getPassivePlayer());
                    if(hasHit==2)
                        System.out.println("The AI destroyed a ship!");
                    if(hasHit==1)
                        System.out.println("The AI touched a ship!");
                    else if(hasHit==0)
                        System.out.println("The AI touched nothing.");
                }while(hasHit==-1);
            }

            if(game.newTurn()) {
                try { //To improve console readability
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(game.toString());
            }

        }
        System.out.println("Game is over!");
        System.out.println("Congratulations " + game.whoWon().getName() + ", you win with " + game.whoWon().getNbShipsLeft() + " ships left!");
        sc.close();
    }
}

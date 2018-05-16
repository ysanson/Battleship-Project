package sanson.yvan;
import java.util.Scanner;
public class Game {
    private Joueur player1, player2;
    private int nbTurns;

    public Joueur getPlayer1() {
        return player1;
    }

    public void setPlayer1(Joueur player1) {
        this.player1 = player1;
    }

    public Joueur getPlayer2() {
        return player2;
    }

    public void setPlayer2(Joueur player2) {
        this.player2 = player2;
    }

    public int getNbTurns() {
        return nbTurns;
    }

    public void setNbTurns(int nbTurns) {
        this.nbTurns = nbTurns;
    }

    public Game(Joueur player1, Joueur player2) {
        this.player1 = player1;
        this.player2 = player2;
        nbTurns = 0;
    }

    public Game(Joueur player, AI ai){
        this.player1=player;
        this.player2=ai;
        nbTurns=0;
    }

    public void initialize(Scanner sc){
        System.out.println("Now initializing player 1 : " + player1.getName());
        player1.initialize(sc);
        if(!(player2 instanceof AI)){
            System.out.println("Now initializing player 2 : " + player2.getName());
        }
        else{
            System.out.println("Now initializing AI...");
        }
        player2.initialize(sc);

        System.out.println("Starting with player 1.");
        player1.setCurrentPlayer(true);
        player2.setCurrentPlayer(false);
    }

    public boolean isFinished(){
        return player1.isDead() || player2.isDead();
    }

    public Joueur whoWon(){
        if (player1.isDead()) return player2;
        else return player1;
    }

    public void changeCurrentPlayer(){
        if(player1.isCurrentPlayer()){
            player2.setCurrentPlayer(true);
            player1.setCurrentPlayer(false);
        }
        else{
            player1.setCurrentPlayer(true);
            player2.setCurrentPlayer(false);
        }
    }

    public Joueur getCurrentPlayer(){
        if(player1.isCurrentPlayer()) return player1;
        else return player2;
    }

    public Joueur getPassivePlayer(){
        if(player1.isCurrentPlayer()) return player2;
        else return player1;
    }

    public boolean newTurn(){
        boolean isNew=false;
        if(player2.isCurrentPlayer()) {
            nbTurns++;
            isNew = true;
        }
        player1.calculateShipsLeft();
        player2.calculateShipsLeft();
        changeCurrentPlayer();
        return isNew;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Game status :\nPlayer 1:\n");
        sb.append(player1.toString());
        sb.append("\nPlayer 2:\n");
        sb.append(player2.toString());
        sb.append("\nNumber of turns :");
        sb.append(nbTurns);
        return sb.toString();
    }

}

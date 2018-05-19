package sanson.yvan;
import java.util.Scanner;
public class Game {
    private Joueur player1, player2;
    private int nbTurns;
    private boolean current;//True if player 1, False if player 2

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

    public void initialize(Scanner sc, int firstPlayer) {
        //First player must be either 1 or 2. If not, player 1 will start.
        player1.initialize(sc);
        player2.initialize(sc);
        if (firstPlayer == 2) {
            current=false;
        } else {
            current=true;
        }
    }

    public boolean isFinished(){
        return player1.isDead() || player2.isDead();
    }

    public Joueur whoWon(){
        if (player1.isDead()) return player2;
        else return player1;
    }

    public void changeCurrentPlayer(){
        current=!current;
    }

    public Joueur getCurrentPlayer(){
        if(current)
            return player1;
        else
            return player2;
    }

    public Joueur getPassivePlayer(){
        if(current)
            return player2;
        else
            return player1;
    }

    public boolean newTurn(){
        boolean isNew=false;
        if(!current) {
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

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

    public void initialize(){
        System.out.println("Now initializing player 1 : " + player1.getName());
        player1.initialize();
        System.out.println("Now initializing player 2 : " + player2.getName());
        player2.initialize();
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

    public void newTurn(){
        nbTurns++;
    }

}

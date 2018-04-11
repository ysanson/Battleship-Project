public class Game {
    private Joueur player1, player2;

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

    public Game(Joueur player1, Joueur player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
}

package fr.battleship;
import sanson.yvan.*;
import java.util.Scanner;

public class TestIA {
    public static void main(String args[]){
        int hasHit, turn=1;
        AI ia1, ia2, ia3;
        Scanner sc= new Scanner(System.in);
        Coordinates missileCoordinate;
        int level1Wins=0, level2Wins=0, level3Wins=0;
        //Part One: Beginner vs Medium
        Game game;
        for(int i=0;i<100; i++){
            ia1 = new AI("Noob", 1);
            ia2 = new AI("T-800", 2);
            game = new Game(ia1, ia2);
            game.initialize(sc, turn);
            while(!game.isFinished()){
                missileCoordinate = ((AI) game.getCurrentPlayer()).calculateMissile();
                ((AI) game.getCurrentPlayer()).sendMissile(missileCoordinate, game.getPassivePlayer());
                game.newTurn();
            }
            System.out.println("Game nb." + (i+1) + " is finished! First player : " + turn);
            System.out.println("Winner : " + game.whoWon().getName());
            if(game.whoWon()==ia1)
                level1Wins++;
            else
                level2Wins++;
            if(i%2==0)
                turn=2;
            else
                turn=1;
        }
        System.out.println("End of the games. Results : AI level 1: "+ level1Wins + ", AI level 2: "+ level2Wins);
        String wait = sc.nextLine();
        //Part 2: Beginner vs Hard
        level1Wins=0;
        level2Wins=0;
        level3Wins=0;
        turn=1;
        for(int i=0;i<100; i++){
            ia1 = new AI("Noob", 1);
            ia3 = new AI("Amadeus", 3);
            game = new Game(ia1, ia3);
            game.initialize( sc, turn);
            while(!game.isFinished()){
                missileCoordinate = ((AI) game.getCurrentPlayer()).calculateMissile();
                ((AI) game.getCurrentPlayer()).sendMissile(missileCoordinate, game.getPassivePlayer());
                game.newTurn();
            }
            System.out.println("Game nb." + (i+1) + " is finished!");
            if(game.whoWon()==ia1)
                level1Wins++;
            else
                level3Wins++;
            if(i%2==0)
                turn=2;
            else
                turn=1;
        }
        System.out.println("End of the games. Results : AI level 1 : " + level1Wins + ", AI level 3 : " + level3Wins);
        //Part 3 : Medium vs Expert
        wait = sc.nextLine();
        level1Wins=0;
        level2Wins=0;
        level3Wins=0;
        turn=1;

        for(int i=0;i<100; i++){
            ia2 = new AI("T-800", 2);
            ia3 = new AI("Amadeus", 3);
            game = new Game(ia2, ia3);
            game.initialize( sc, turn);
            while(!game.isFinished()){
                missileCoordinate = ((AI) game.getCurrentPlayer()).calculateMissile();
                ((AI) game.getCurrentPlayer()).sendMissile(missileCoordinate, game.getPassivePlayer());
                game.newTurn();
            }
            System.out.println("Game nb." + (i+1) + " is finished!");
            if(game.whoWon()==ia2)
                level2Wins++;
            else
                level3Wins++;
            if(i%2==0)
                turn=2;
            else
                turn=1;
        }
        System.out.println("End of the games. Results : AI level 2:" + level2Wins + ", AI level 3:" + level3Wins);

    }
}

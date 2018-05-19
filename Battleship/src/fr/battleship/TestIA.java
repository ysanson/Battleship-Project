package fr.battleship;
import sanson.yvan.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TestIA {

    public static void main(String args[]) throws FileNotFoundException{
        /*We need to reset the AIs at every game.
         *Otherwise, it will be the same ones, and will produce bad results.
         */
        int hasHit, turn=1;
        AI ia1, ia2, ia3;
        Scanner sc= new Scanner(System.in);
        Coordinates missileCoordinate;
        int level1Wins=0, level2Wins=0, level3Wins=0;
        PrintWriter pw = new PrintWriter(new File("ai_proof.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("AI name; Score; AI name 2; Score 2\n");


        //Part One: Beginner vs Medium
        Game game;
        for(int i=0;i<100; i++){
            ia1 = new AI("Noob", 1);
            ia2 = new AI("T-800", 2);
            game = new Game(ia1, ia2);
            game.initialize(sc, turn);
            while(!game.isFinished()){
                missileCoordinate = ((AI) game.getCurrentPlayer()).calculateMissile();
                game.getCurrentPlayer().sendMissile(missileCoordinate, game.getPassivePlayer());
                game.newTurn();
            }
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
        sb.append("Level Beginner; ");
        sb.append(level1Wins);
        sb.append(';');
        sb.append(" Level Medium; ");
        sb.append(level2Wins);
        sb.append("\n");
        //Part 2: Beginner vs Hard
        level1Wins=0;
        level2Wins=0;
        level3Wins=0;
        turn=1;
        for(int i=0;i<100; i++){
            ia1 = new AI("Noob", 1);
            ia3 = new AI("Amadeus", 3);
            game = new Game(ia1, ia3);
            game.initialize(sc, turn);
            while(!game.isFinished()){
                missileCoordinate = ((AI) game.getCurrentPlayer()).calculateMissile();
                game.getCurrentPlayer().sendMissile(missileCoordinate, game.getPassivePlayer());
                game.newTurn();
            }
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
        sb.append("Level Beginner; ");
        sb.append(level1Wins);
        sb.append(';');
        sb.append(" Level Hard; ");
        sb.append(level3Wins);
        sb.append("\n");

        //Part 3 : Medium vs Expert
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
                game.getCurrentPlayer().sendMissile(missileCoordinate, game.getPassivePlayer());
                game.newTurn();
            }
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
        sb.append("Level Medium; ");
        sb.append(level2Wins);
        sb.append(';');
        sb.append(" Level Hard; ");
        sb.append(level3Wins);
        sb.append("\n");

        pw.write(sb.toString());
        pw.close();
        System.out.println("Done.");

    }
}

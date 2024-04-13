package com.indra.games;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;



public class SnakeAndLadder {

    public static void main(String[] args) {
        SnakesLadders s = new SnakesLadders();
        s.startGame();

    }

}

class SnakesLadders
{

    final static int WIN_POINT = 100;


    static Map<Integer,Integer> snakes = new HashMap<Integer,Integer>();
    static Map<Integer,Integer> ladders = new HashMap<Integer,Integer>();

    {
        snakes.put(99, 54);
        snakes.put(70, 55);
        snakes.put(52, 42);
        snakes.put(25, 2);
        snakes.put(95, 72);

        ladders.put(6, 25);
        ladders.put(11, 40);
        ladders.put(60, 85);
        ladders.put(46, 90);
        ladders.put(17, 69);
    }



    public int rollDice()
    {
        int n = 0;
        Random r = new Random();
        n=r.nextInt(7);
        return (n==0?1:n);
    }

    public void startGame()
    {
        int player1 =0, player2=0;
        int currentPlayer=-1;
        Scanner s = new Scanner(System.in);
        System.out.println("Player one please enter your name : ");
        String playerOne = s.next();
        System.out.println("Player two please enter your name : ");
        String playerTwo = s.next();
        String roll;
        int diceValue =0;
        do
        {
            System.out.printf("\n\n%s PLEASE ROLL THE DICE\n\n", currentPlayer == -1 ? playerOne : playerTwo);
            System.out.println("Press r to roll Dice");
            roll = s.next();
            diceValue = rollDice();


            if(currentPlayer == -1)
            {
                player1 = calculatePlayerValue(player1,diceValue);
                System.out.println(playerOne+" you are currently on :: " + player1);
                System.out.println(playerTwo+" you are currently on :: " + player2);
                System.out.println("------------------");
                if(isWin(player1))
                {
                    System.out.printf("Congratulations !!! %s wins",playerOne);
                    return;
                }
            }
            else
            {
                player2 = calculatePlayerValue(player2,diceValue);
                System.out.println(playerOne+" you are currently on :: " + player1);
                System.out.println(playerTwo+" you are currently on :: " + player2);
                System.out.println("------------------");
                if(isWin(player2))
                {
                    System.out.printf("Congratulations !!! %s wins",playerTwo);
                    return;
                }
            }

            currentPlayer= -currentPlayer;



        }while("r".equals(roll));
    }


    public int calculatePlayerValue(int player, int diceValue)
    {
        player = player + diceValue;

        if(player > WIN_POINT)
        {
            player = player - diceValue;
            return player;
        }

        if(null!=snakes.get(player))
        {
            System.out.println("swallowed and eaten by snake");
            player= snakes.get(player);
        }

        if(null!=ladders.get(player))
        {
            System.out.println("climb up the ladder");
            player= ladders.get(player);
        }
        return player;
    }

    public boolean isWin(int player)
    {
        return WIN_POINT == player;
    }

}
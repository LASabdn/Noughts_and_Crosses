/*
 * Game.java
 *
 * Represents a game of noughts and crosses
 */

package noughts;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author ereiter
 */
public class Game {
    
    BoxStatus[] board = new BoxStatus[9];  // board contains 9 boxes
    
    /** Creates a new instance of game */
    public Game() {
        for(int i = 0; i<9; i++)
            board[i] = BoxStatus.Empty;  // initially each box is empty (not taken)
        
    }
    
    public boolean isEmpty(int n) {
        // is a box empty?
        return (board[n-1] == BoxStatus.Empty);
    }
    
    public boolean isComputer(int n) {
        // is a box taken by the Computer?
        return (board[n-1] == BoxStatus.Computer);
    }
    
    public boolean isHuman(int n) {
        // is a box taken by the Human?
        return (board[n-1] == BoxStatus.Human);
    }
    
    public void setHuman(int n) {
        // human claims square N
        board[n-1] = BoxStatus.Human;
    }
    
    public void setComputer(int n) {
        // computer claims square N
        board[n-1] = BoxStatus.Computer;
    }
    
    public BoxStatus getBox(int n) {
        // return square N
        return board[n-1];
    }

    public char boxChar(int n) {
        // return a character which shows whether a square is empty, taken by the computer, or taken by the human
        switch (board[n-1]) {
            case Human: return 'H';
            case Computer: return 'C';
            case Empty: return '.';
        }
        return ' ';
    }

    public void printBoard() {
        // print the noard on System.out
        System.out.println("Board");
        System.out.printf("| %c %c %c |\n", boxChar(1), boxChar(2), boxChar(3));
        System.out.printf("| %c %c %c |\n", boxChar(4), boxChar(5), boxChar(6));
        System.out.printf("| %c %c %c |\n", boxChar(7), boxChar(8), boxChar(9));
    }

    public List<List> generateWincons() {
        ArrayList<Integer> win1 = new ArrayList<Integer>();
        win1.add(1);
        win1.add(2);
        win1.add(3);
        ArrayList<Integer> win2 = new ArrayList<Integer>();
        win2.add(1);
        win2.add(4);
        win2.add(7);
        ArrayList<Integer> win3 = new ArrayList<Integer>();
        win3.add(1);
        win3.add(5);
        win3.add(9);
        ArrayList<Integer> win4 = new ArrayList<Integer>();
        win4.add(4);
        win4.add(5);
        win4.add(6);
        ArrayList<Integer> win5 = new ArrayList<Integer>();
        win5.add(2);
        win5.add(5);
        win5.add(8);
        ArrayList<Integer> win6 = new ArrayList<Integer>();
        win6.add(7);
        win6.add(8);
        win6.add(9);
        ArrayList<Integer> win7 = new ArrayList<Integer>();
        win7.add(3);
        win7.add(6);
        win7.add(9);
        ArrayList<Integer> win8 = new ArrayList<Integer>();
        win8.add(3);
        win8.add(5);
        win8.add(7);

        return(wincons);
    }

    public char checkWinner(char player, ArrayList<Integer> moves, List<List> wincons){
        for (List con : wincons){
            if (moves.containsAll(con)){
                return (player);
            }
        }
        return ('n');
    }

    public boolean initWin(char winner, Game game){
        if (winner == 'h'){
            game.printBoard();
            System.out.println("Congratulations!  You won!");
            return (true);
        }
        else if (winner == 'c'){
            game.printBoard();
            System.out.println("The computer won!  Bad luck!");
            return (true);
        }
        return (false);
    }

    public Integer computeMove(int humanin, ArrayList<Integer> HumanMove, ArrayList<Integer> ComputerMove, List<List> compwincons){
        //block human
        Random rand = new Random();
        int human_count=0;
        int move = 0;
        for (List con : compwincons){
            human_count = 0;
            List<Integer> focus = con;
            System.out.println(focus);
            
            for (Integer pos : focus){
                if (HumanMove.contains(pos)){
                    focus.remove(Integer.valueOf(pos));
                    human_count ++;
                }
            }
            
            if (human_count > 1){
                move = focus.get(0);
                return (move);
            }
        }
        move = rand.nextInt(9) + 1;
        return (move);
    }
}

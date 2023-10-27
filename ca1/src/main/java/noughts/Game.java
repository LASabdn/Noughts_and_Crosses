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
        List win1 = Arrays.asList(1,2,3);
        List win2 = Arrays.asList(1,4,7);
        List win3 = Arrays.asList(1,5,9);
        List win4 = Arrays.asList(4,5,6);
        List win5 = Arrays.asList(2,5,8);
        List win6 = Arrays.asList(7,8,9);
        List win7 = Arrays.asList(3,6,9);
        List win8 = Arrays.asList(3,5,7);
        List<List> wincons = new ArrayList<List>();
        wincons.add(win1);
        wincons.add(win2);
        wincons.add(win3);
        wincons.add(win4);
        wincons.add(win5);
        wincons.add(win6);
        wincons.add(win7);
        wincons.add(win8);

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
}

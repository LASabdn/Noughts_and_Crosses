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
 * @author ereiter and lshaw
 * Below stuff is provided code
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

    //This where my own methods begin

    public List<List> generateWincons() { //this method generates a list of lists where each list is the squares needed to win a game.
        ArrayList<Integer> win1 = new ArrayList<Integer>(); //win condition (1,2,3)
        win1.add(1);
        win1.add(2);
        win1.add(3);
        ArrayList<Integer> win2 = new ArrayList<Integer>(); //wincon (1,4,7)
        win2.add(1);
        win2.add(4);
        win2.add(7);
        ArrayList<Integer> win3 = new ArrayList<Integer>(); //wincon (1,5,9)
        win3.add(1);
        win3.add(5);
        win3.add(9);
        ArrayList<Integer> win4 = new ArrayList<Integer>(); //wincon (4,5,6)
        win4.add(4);
        win4.add(5);
        win4.add(6);
        ArrayList<Integer> win5 = new ArrayList<Integer>(); //wincon (2,5,8)
        win5.add(2);
        win5.add(5);
        win5.add(8);
        ArrayList<Integer> win6 = new ArrayList<Integer>(); //wincon (7,8,9)
        win6.add(7);
        win6.add(8);
        win6.add(9);
        ArrayList<Integer> win7 = new ArrayList<Integer>(); //wincon (3,6,9)
        win7.add(3);
        win7.add(6);
        win7.add(9);
        ArrayList<Integer> win8 = new ArrayList<Integer>(); //wincon (3,5,7)
        win8.add(3);
        win8.add(5);
        win8.add(7);

        List<List> wincons = new ArrayList<List>(); //adds all win conditions to a list
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
        //every turn, this method is called to check if someone won.  Returns 'h' (from parameters) for human win, returns 'c' for computer
        //'n' signifies no winner was detected and the game continues
        for (List con : wincons){
            if (moves.containsAll(con)){
                return (player);
            }
        }
        return ('n');
    }

    public boolean initWin(char winner, Game game){ //initiate win sequence
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

    public Integer computeMove(ArrayList<Integer> HumanMove, ArrayList<Integer> ComputerMove, List<List> compwincons, List<List> humanwincons){
        //returns a move based on the situation the computer is in.  
        Random rand = new Random();
        int human_count=0;
        int move = 0;
        //complete self
        /*The method goes through the list of all wincons.  If it finds a wincon where two of the spots are taken,
        //it will remove these two spots from the list and try to move to the remaining number.  If the spots are taken by the human,
        then the computer undoes the process and looks for another wincon (if there is one)*/
        for (List con : compwincons){
            human_count = 0;
            List<Integer> focus = con;
            ArrayList<Integer> remove_list = new ArrayList<Integer>();
            for (Integer pos : focus){
                if (ComputerMove.contains(pos)){
                    remove_list.add(Integer.valueOf(pos));
                    human_count ++;
                }
            }

            if (human_count > 1){
                int rem1 = remove_list.get(0);
                int rem2 = remove_list.get(1);
                focus.remove(Integer.valueOf(rem1));
                focus.remove(Integer.valueOf(rem2));
                move = focus.get(0);
                if (!HumanMove.contains(move)){
                    return(move);
                }
                focus.add(rem1);
                focus.add(rem2);
                move = 0;
            }
        }
        
        //block human
        //works like last loop except it tries to complete a set of two human moves
        for (List con : humanwincons){
            human_count = 0;
            List<Integer> focus = con;
            ArrayList<Integer> remove_list = new ArrayList<Integer>();
            for (Integer pos : focus){
                if (HumanMove.contains(pos)){
                    remove_list.add(Integer.valueOf(pos));
                    human_count ++;
                }
            }

            if (human_count > 1){
                int rem1 = remove_list.get(0);
                int rem2 = remove_list.get(1);
                focus.remove(Integer.valueOf(rem1));
                focus.remove(Integer.valueOf(rem2));
                move = focus.get(0);
                if (!ComputerMove.contains(move)){
                    return(move);
                }
                focus.add(rem1);
                focus.add(rem2);
                move = 0;
            }
        }
        //if there's no completion or blocking opportunities, computer just picks a random number.
        //I coded it like this so you still have a chance to win against the computer as I thought it would be more fun.
        move = rand.nextInt(9) + 1;
        return(move);
    }
}

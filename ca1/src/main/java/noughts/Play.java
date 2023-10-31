/*
 * Play.java
 *
 * Play a game of noughts and crosses
 * includes main method
 */
 
 package noughts;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class Play{
    Game game;   // the noughts and crosses game
    Scanner input;
        public static void main(String[] args) {
            // main method - just create a Play object
            new Play();
        }

        public Play () {
            // constructor
            System.out.println("Welcome to noughts and crosses"); 
            game = new Game();  // create game board
            input = new Scanner(System.in);  // Scanner for user input
            boolean gameover = false;
            char winner;
            ArrayList<Integer> HumanMove = new ArrayList<Integer>();
            ArrayList<Integer> ComputerMove = new ArrayList<Integer>();
            List<List> wincons = game.generateWincons();
            List<List> humanwincons = game.generateWincons();
            List<List> compwincons = game.generateWincons();
            // Was going to make below section a method, but it needed the HumanMove and ComputerMove Lists to be used as parameters and returned which I couldn't get to work.
            boolean start = false;
            String choice = "";
            int gofirst = 0;
            while (!start){
                System.out.print("Do you want to go first? y/n: ");
                choice = input.next();
                System.out.println(choice);
                if (choice.equals("n")){
                    System.out.println("Computer is thinking");
                    ComputerMove = (computerTurn(ComputerMove, HumanMove, compwincons,humanwincons));
                    gofirst = 1;
                    start = true;
                }
                else if (choice.equals("y")){
                    gofirst = 0;
                    start = true;
                }
                else{
                    System.out.println("Did not input y or n.");
                }
            }

            for(int turns = 0; turns<(5-gofirst); turns++) { // not-so-infinite loop
                game.printBoard(); // print board
                HumanMove = (playerTurn(HumanMove)); // human turn
                winner = game.checkWinner('h',HumanMove,wincons);
                if (game.initWin(winner,game)){
                    gameover = true;
                    break;
                }
                System.out.println("Computer is thinking");
                ComputerMove = (computerTurn(ComputerMove, HumanMove, compwincons,humanwincons)); // computer tuen
                winner = game.checkWinner('c',ComputerMove,wincons);
                if (game.initWin(winner,game)){
                    gameover = true;
                    break;
                }
            }
            if (!gameover){
                game.printBoard();
                System.out.println("Looks like it was a tie!");
            }
    }
    public ArrayList<Integer> playerTurn(ArrayList<Integer> hmovelist)  {
        // Player turn: just read in a sqaure and claim it for Human
        while(true){
            try{
                System.out.print("Take a square (1-9): ");
                // Reading data using readLine
                int square = Integer.parseInt(input.next());
                if (game.isComputer(square) || game.isHuman(square)){
                    System.out.println("This square is already taken.  Please choose another.");
                    playerTurn(hmovelist);
                    break;
                }
                else{
                    hmovelist.add(square);
                    game.setHuman(square);
                    return(hmovelist);
                }      
            }
            catch (Exception e){
            System.out.println("Please input a valid integer.");
            }   
        }
        
        return(hmovelist);
    }

    public ArrayList<Integer> computerTurn(ArrayList<Integer> cmovelist, ArrayList<Integer> hmovelist, List<List> compwincons, List<List> humanwincons) {
        if ((cmovelist.size() + hmovelist.size())==9){
            return(cmovelist);
        }
        int square = 0;
        square = game.computeMove(hmovelist, cmovelist, compwincons, humanwincons);
        if(game.isComputer(square) || game.isHuman(square)){
            computerTurn(cmovelist,hmovelist,compwincons,humanwincons);
        }
        else{
            cmovelist.add(square);
            game.setComputer(square);
            return(cmovelist);
        }
        return(cmovelist);
    }
}
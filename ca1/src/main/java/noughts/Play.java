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
            boolean gameover = false; //flag for when the game is over so a tie can be declared
            char winner; //variable for winner
            ArrayList<Integer> HumanMove = new ArrayList<Integer>(); //list of all moves made by human
            ArrayList<Integer> ComputerMove = new ArrayList<Integer>(); //list of all moves made by computer
            List<List> wincons = game.generateWincons(); //static list of wincons to be used to detect winner
            List<List> humanwincons = game.generateWincons(); //list of human wincons close to completion so computer can block
            List<List> compwincons = game.generateWincons(); //list of computer wincons close to completion so it can win
            // Was going to make below section a method, but it needed the HumanMove and ComputerMove Lists to be used as parameters and returned which I couldn't get to work.
            
            //Determines whether Human or computer goes first
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
                    gofirst = 1; //decreases max turns before tie
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

            //game loops until a winner is found or board is full (with no winners) and declares tie
            for(int turns = 0; turns<(5-gofirst); turns++) { // not-so-infinite loop
                game.printBoard(); // print board
                HumanMove = (playerTurn(HumanMove)); // human turn
                winner = game.checkWinner('h',HumanMove,wincons);
                if (game.initWin(winner,game)){ //need to check for a winner after each move
                    gameover = true;
                    break;
                }
                System.out.println("Computer is thinking");
                ComputerMove = (computerTurn(ComputerMove, HumanMove, compwincons,humanwincons)); // computer turn
                winner = game.checkWinner('c',ComputerMove,wincons);
                if (game.initWin(winner,game)){
                    gameover = true;
                    break;
                }
            }
            if (!gameover){ //if end of loop reached and no winner, declare tie
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
                    break; //stops player taking own square again
                }
                else{
                    hmovelist.add(square);
                    game.setHuman(square);
                    return(hmovelist); //takes square and adds new move to move list
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
            return(cmovelist);  //skip turn if board full
        }
        int square = 0;
        square = game.computeMove(hmovelist, cmovelist, compwincons, humanwincons);
        if(game.isComputer(square) || game.isHuman(square)){
            computerTurn(cmovelist,hmovelist,compwincons,humanwincons); //run method again if square is selected that is already taken
        }
        else{
            cmovelist.add(square);
            game.setComputer(square);
            return(cmovelist); //adds move to move list and takes square
        }
        return(cmovelist);
    }
}
package noughts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class PlayTest 
{
    Game game = new Game();
    //Let's test win/loss/tie detection first.
    //First test that generateWincons outputs correct list
    @Test
    //list is right length
    public void list_from_generateWincons_has_length_8(){
        List<List> testlist = game.generateWincons();
        assertEquals(8,testlist.size());
    }
    
    @Test
    //list has elements of length 3.
    public void list_from_generateWincons_has_elements_length_3(){
        List<List> testlist = game.generateWincons();
        for (List item : testlist){
            assertEquals(3,item.size());
        }
    }

    @Test
    //every list has the expected elements.
    public void sublists_in_generateWincons_have_correct_elements(){
        List<List> testlist = game.generateWincons();
        List sub1 = testlist.get(0);
        assertEquals(1,sub1.get(0));
        assertEquals(2,sub1.get(1));
        String teststring = testlist.toString();
        String compare_string = "[[1, 2, 3], [1, 4, 7], [1, 5, 9], [4, 5, 6], [2, 5, 8], [7, 8, 9], [3, 6, 9], [3, 5, 7]]";
        assertEquals(compare_string, teststring);
    }

    //Now, let's check that the game can check for a win after every turn is made.
    //Let's check a board state where there is a tie and human goes first.
    @Test
    public void check_for_tie_1(){
        //let's check the human first
        ArrayList test_moves = new ArrayList();
        test_moves.add(1);
        test_moves.add(2);
        test_moves.add(6);
        test_moves.add(7);
        test_moves.add(8);
        List<List> wincons = game.generateWincons();
        char test_winner = game.checkWinner('h', test_moves,wincons); //we're using generateWincons because we just tested it
        assertEquals('n',test_winner);

        //Now let's check computer
        test_moves.clear(); //we just use our old list to simulate human moves because we don't need both lists to exist at the same time.
        test_moves.add(3);
        test_moves.add(4);
        test_moves.add(5);
        test_moves.add(9);
        test_winner = game.checkWinner('c', test_moves,wincons);
        assertEquals('n',test_winner);
    }

    //Let's check for another tie board state but the computer goes first.
    @Test
    public void check_for_tie_2(){
        //let's check the human first
        ArrayList test_moves = new ArrayList();
        test_moves.add(1);
        test_moves.add(3);
        test_moves.add(4);
        test_moves.add(8);
        List<List> wincons = game.generateWincons();
        char test_winner = game.checkWinner('h', test_moves,wincons);
        assertEquals('n',test_winner);

        //Now let's check computer
        test_moves.clear();
        test_moves.add(2);
        test_moves.add(5);
        test_moves.add(6);
        test_moves.add(7);
        test_moves.add(9);
        test_winner = game.checkWinner('c', test_moves,wincons);
        assertEquals('n',test_winner);
    }

    //Let's check for a human win when the human goes first
    @Test
    public void check_for_human_win_1(){
        //let's check the human first
        ArrayList test_moves = new ArrayList();
        test_moves.add(3);
        test_moves.add(5);
        test_moves.add(7);
        test_moves.add(8);
        List<List> wincons = game.generateWincons();
        char test_winner = game.checkWinner('h', test_moves,wincons);
        assertEquals('h',test_winner);

        //Now let's check the computer loses
        test_moves.clear();
        test_moves.add(1);
        test_moves.add(2);
        test_moves.add(9);
        test_winner = game.checkWinner('c', test_moves,wincons);
        assertEquals('n',test_winner);
    }

    //Let's check for a human win when the computer goes first
    @Test
    public void check_for_human_win_2(){
        //let's check the human first
        ArrayList test_moves = new ArrayList();
        test_moves.add(2);
        test_moves.add(3);
        test_moves.add(6);
        test_moves.add(9);
        List<List> wincons = game.generateWincons();
        char test_winner = game.checkWinner('h', test_moves,wincons);
        assertEquals('h',test_winner);

        //Now let's check the computer loses
        test_moves.clear();
        test_moves.add(1);
        test_moves.add(5);
        test_moves.add(7);
        test_moves.add(8);
        test_winner = game.checkWinner('c', test_moves,wincons);
        assertEquals('n',test_winner);
    }

    //Now let's check for a computer win when the human goes first
    @Test
    public void check_for_computer_win_1(){
        //let's check the human loses first
        ArrayList test_moves = new ArrayList();
        test_moves.add(2);
        test_moves.add(3);
        test_moves.add(8);
        test_moves.add(9);
        List<List> wincons = game.generateWincons();
        char test_winner = game.checkWinner('h', test_moves,wincons);
        assertEquals('n',test_winner);

        //Now let's check the computer wins
        test_moves.clear();
        test_moves.add(1);
        test_moves.add(4);
        test_moves.add(5);
        test_moves.add(6);
        test_winner = game.checkWinner('c', test_moves,wincons);
        assertEquals('c',test_winner);
    }

    //Check for computer win when computer goes first
    @Test
    public void check_for_computer_win_2(){
        //let's check the human loses first
        ArrayList test_moves = new ArrayList();
        test_moves.add(2);
        test_moves.add(6);
        test_moves.add(9);
        List<List> wincons = game.generateWincons();
        char test_winner = game.checkWinner('h', test_moves,wincons);
        assertEquals('n',test_winner);

        //Now let's check the computer wins
        test_moves.clear();
        test_moves.add(1);
        test_moves.add(4);
        test_moves.add(5);
        test_moves.add(7);
        test_winner = game.checkWinner('c', test_moves,wincons);
        assertEquals('c',test_winner);
    }

    //Let's now check that initWin() actually works

    //first we test for a situation where no win should be initiated
    @Test
    public void check_no_win_initiated(){
        boolean win = game.initWin('n', game);
        assertFalse(win);
    }

    //next we test for a situation where a human win is initiated
    @Test
    public void check_human_win_initiated(){
        boolean win = game.initWin('h', game);
        assertTrue(win);
    }

    //next we test for a situation where a loss occurs (computer wins)
    @Test
    public void check_computer_win_initiated(){
        boolean win = game.initWin('c', game);
        assertTrue(win);
    }


    //Next we test if the computer player behaves in an intended way
    
    //first we test computeMove()
    //Does the method output a valid move for an empty board? (i.e is it between 1 and 9?)
    @Test
    public void computer_moves_on_empty_board(){
        ArrayList human_test_moves = new ArrayList();
        ArrayList computer_test_moves = new ArrayList();
        List<List> comp_test_cons = game.generateWincons();
        List<List> human_test_cons = game.generateWincons();
        Integer test_move = game.computeMove(human_test_moves,computer_test_moves,comp_test_cons,human_test_cons);
        assertTrue(0<=test_move && test_move<=9);
    }

    //Does the method output a move to block a player?
    @Test
    public void computer_blocks_player_1(){
        ArrayList human_test_moves = new ArrayList(); //simulate human moves
        human_test_moves.add(1);
        human_test_moves.add(3);
        human_test_moves.add(4);
        human_test_moves.add(8);
        ArrayList computer_test_moves = new ArrayList(); //simulate computer moves
        computer_test_moves.add(9);
        computer_test_moves.add(2);
        computer_test_moves.add(5);
        List<List> comp_test_cons = game.generateWincons();
        List<List> human_test_cons = game.generateWincons();
        Integer test_move = game.computeMove(human_test_moves,computer_test_moves,comp_test_cons,human_test_cons);
        assertEquals(7,test_move); //the computer needs to play 7 to block the human
    }

    //Does blocking work for another scenario?
    @Test
    public void computer_blocks_player_2(){
        ArrayList human_test_moves = new ArrayList(); //simulate human moves
        human_test_moves.add(1);
        human_test_moves.add(5);
        ArrayList computer_test_moves = new ArrayList(); //simulate computer moves
        computer_test_moves.add(2);
        List<List> comp_test_cons = game.generateWincons();
        List<List> human_test_cons = game.generateWincons();
        Integer test_move = game.computeMove(human_test_moves,computer_test_moves,comp_test_cons,human_test_cons);
        assertEquals(9,test_move); //the computer needs to play 9 to block the human
    }

    //If the player has two wins available, will the computer block at least one?
    @Test
    public void computer_blocks_player_mult_1(){
        ArrayList human_test_moves = new ArrayList(); //simulate human moves
        human_test_moves.add(4);
        human_test_moves.add(5);
        human_test_moves.add(7);
        ArrayList computer_test_moves = new ArrayList(); //simulate computer moves
        computer_test_moves.add(1);
        computer_test_moves.add(2);
        List<List> comp_test_cons = game.generateWincons();
        List<List> human_test_cons = game.generateWincons();
        Integer test_move = game.computeMove(human_test_moves,computer_test_moves,comp_test_cons,human_test_cons);
        assertTrue(test_move == 6 || test_move == 3); //the computer needs to play 3 or 6 to block
    }

    //Will this work in another scenario?
    @Test
    public void computer_blocks_player_mult_2(){
        ArrayList human_test_moves = new ArrayList(); //simulate human moves
        human_test_moves.add(5);
        human_test_moves.add(6);
        human_test_moves.add(9);
        ArrayList computer_test_moves = new ArrayList(); //simulate computer moves
        computer_test_moves.add(3);
        computer_test_moves.add(7);
        List<List> comp_test_cons = game.generateWincons();
        List<List> human_test_cons = game.generateWincons();
        Integer test_move = game.computeMove(human_test_moves,computer_test_moves,comp_test_cons,human_test_cons);
        assertTrue(test_move == 1 || test_move == 4); //the computer needs to play 1 or 4 to block
    }

    //Will the computer complete if the opportunity arises?
    @Test
    public void computer_completes_1(){
        ArrayList human_test_moves = new ArrayList(); //simulate human moves
        human_test_moves.add(2);
        human_test_moves.add(5);
        human_test_moves.add(6);
        ArrayList computer_test_moves = new ArrayList(); //simulate computer moves
        computer_test_moves.add(1);
        computer_test_moves.add(4);
        computer_test_moves.add(8);
        List<List> comp_test_cons = game.generateWincons();
        List<List> human_test_cons = game.generateWincons();
        Integer test_move = game.computeMove(human_test_moves,computer_test_moves,comp_test_cons,human_test_cons);
        assertEquals(7,test_move); //the computer needs to play 7 to complete
    }

    //Will this work in another scenario?
    @Test
    public void computer_completes_2(){
        ArrayList human_test_moves = new ArrayList(); //simulate human moves
        human_test_moves.add(1);
        human_test_moves.add(2);
        human_test_moves.add(5);
        human_test_moves.add(6);
        ArrayList computer_test_moves = new ArrayList(); //simulate computer moves
        computer_test_moves.add(3);
        computer_test_moves.add(8);
        computer_test_moves.add(9);
        List<List> comp_test_cons = game.generateWincons();
        List<List> human_test_cons = game.generateWincons();
        Integer test_move = game.computeMove(human_test_moves,computer_test_moves,comp_test_cons,human_test_cons);
        assertEquals(7,test_move); //the computer needs to play 7 to complete
    }

    //If the computer has two wins available, will it complete at least one?
    @Test
    public void computer_completes_with_two_wins_1(){   
        ArrayList human_test_moves = new ArrayList(); //simulate human moves
        human_test_moves.add(2);
        human_test_moves.add(9);
        ArrayList computer_test_moves = new ArrayList(); //simulate computer moves
        computer_test_moves.add(1);
        computer_test_moves.add(4);
        computer_test_moves.add(5);
        List<List> comp_test_cons = game.generateWincons();
        List<List> human_test_cons = game.generateWincons();
        Integer test_move = game.computeMove(human_test_moves,computer_test_moves,comp_test_cons,human_test_cons);
        assertTrue(test_move == 6 || test_move == 7); //the computer needs to play 6 or 7 to complete
    }

    //What about another scenario?
    @Test
    public void computer_completes_with_two_wins_2(){   
        ArrayList human_test_moves = new ArrayList(); //simulate human moves
        human_test_moves.add(1);
        human_test_moves.add(9);
        ArrayList computer_test_moves = new ArrayList(); //simulate computer moves
        computer_test_moves.add(2);
        computer_test_moves.add(4);
        computer_test_moves.add(5);
        List<List> comp_test_cons = game.generateWincons();
        List<List> human_test_cons = game.generateWincons();
        Integer test_move = game.computeMove(human_test_moves,computer_test_moves,comp_test_cons,human_test_cons);
        assertTrue(test_move == 6 || test_move == 8); //the computer needs to play 6 or 8 to complete
    }

    //If the computer can both block and complete on the same square, will it pick it?
    @Test
    public void computer_completes_and_blocks_1(){   
        ArrayList human_test_moves = new ArrayList(); //simulate human moves
        human_test_moves.add(1);
        human_test_moves.add(6);
        human_test_moves.add(8);
        human_test_moves.add(9);
        ArrayList computer_test_moves = new ArrayList(); //simulate computer moves
        computer_test_moves.add(2);
        computer_test_moves.add(3);
        computer_test_moves.add(5);
        List<List> comp_test_cons = game.generateWincons();
        List<List> human_test_cons = game.generateWincons();
        Integer test_move = game.computeMove(human_test_moves,computer_test_moves,comp_test_cons,human_test_cons);
        assertEquals(7,test_move); //the computer needs to play 7 to complete and block
    }

    //Another scenario?
    @Test
    public void computer_completes_and_blocks_2(){   
        ArrayList human_test_moves = new ArrayList(); //simulate human moves
        human_test_moves.add(2);
        human_test_moves.add(3);
        human_test_moves.add(6);
        ArrayList computer_test_moves = new ArrayList(); //simulate computer moves
        computer_test_moves.add(1);
        computer_test_moves.add(5);
        List<List> comp_test_cons = game.generateWincons();
        List<List> human_test_cons = game.generateWincons();
        Integer test_move = game.computeMove(human_test_moves,computer_test_moves,comp_test_cons,human_test_cons);
        assertEquals(9,test_move); //the computer needs to play  to complete and block
    }

    //If the computer has the opportunity to complete or block, it should complete
    @Test
    public void computer_completes_instead_of_blocking_1(){
        ArrayList human_test_moves = new ArrayList(); //simulate human moves
        human_test_moves.add(1);
        human_test_moves.add(2);
        human_test_moves.add(7);
        human_test_moves.add(8);
        ArrayList computer_test_moves = new ArrayList(); //simulate computer moves
        computer_test_moves.add(4);
        computer_test_moves.add(5);
        computer_test_moves.add(3);
        List<List> comp_test_cons = game.generateWincons();
        List<List> human_test_cons = game.generateWincons();
        Integer test_move = game.computeMove(human_test_moves,computer_test_moves,comp_test_cons,human_test_cons);
        assertEquals(6,test_move); //the computer needs to play 6 to complete instead of blocking on 9
    }

    //Will this happen in another scenario?
    @Test
    public void computer_completes_instead_of_blocking_2(){
        ArrayList human_test_moves = new ArrayList(); //simulate human moves
        human_test_moves.add(2);
        human_test_moves.add(5);
        human_test_moves.add(6);
        human_test_moves.add(9);
        ArrayList computer_test_moves = new ArrayList(); //simulate computer moves
        computer_test_moves.add(1);
        computer_test_moves.add(3);
        computer_test_moves.add(7);
        List<List> comp_test_cons = game.generateWincons();
        List<List> human_test_cons = game.generateWincons();
        Integer test_move = game.computeMove(human_test_moves,computer_test_moves,comp_test_cons,human_test_cons);
        assertEquals(4,test_move); //the computer needs to play 6 to complete instead of blocking on 9
    }

    //This concludes testing of the computer AI
}

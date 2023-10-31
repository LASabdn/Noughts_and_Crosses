package noughts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        test_moves.clear();
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
        char test_winner = game.checkWinner('h', test_moves,wincons); //we're using generateWincons because we just tested it
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
        char test_winner = game.checkWinner('h', test_moves,wincons); //we're using generateWincons because we just tested it
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
        char test_winner = game.checkWinner('h', test_moves,wincons); //we're using generateWincons because we just tested it
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
        char test_winner = game.checkWinner('h', test_moves,wincons); //we're using generateWincons because we just tested it
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
        char test_winner = game.checkWinner('h', test_moves,wincons); //we're using generateWincons because we just tested it
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

}

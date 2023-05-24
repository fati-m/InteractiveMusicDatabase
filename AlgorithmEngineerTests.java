// --== CS400 Spring 2023 File Header Information ==--
// Name: Matthew Wang
// Email: mewang@wisc.edu
// Team: AN Blue
// TA: Gary Dahl
// Lecturer: Gary Dahl
// Notes to Grader: uwu :3

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AlgorithmEngineerTests {

    /**
     * TESTING REMOVING A NODE WITHIN THE RED BLACK TREE WITH ONLY ONE RED CHILD AND
     * A BLACK SIBLING
     */
    @Test
    public void test1() {

        RedBlackTreeTraversalAE<Integer> test = new RedBlackTreeTraversalAE<>();

        test.insert(20);
        test.insert(10);
        test.insert(30);
        test.insert(15);
        test.insert(35);

        test.remove(15);

        assertEquals("[ 20, 10, 30, 35 ]", test.toLevelOrderString());
        assertEquals("[ 1, 1, 1, 0 ]", test.toLevelOrderColor());

    }

    /**
     * TESTING REMOVING A NODE WITH NO CHILDREN, AND A BLACK SIBLING WITH 2 RED
     * CHILDREN
     */
    @Test
    public void test2() {

        RedBlackTreeTraversalAE<Integer> test = new RedBlackTreeTraversalAE<>();
        test.insert(20);
        test.insert(10);
        test.insert(30);
        test.insert(35);
        test.insert(25);

        test.remove(30);

        assertEquals("[ 20, 10, 35, 25 ]", test.toLevelOrderString());
        assertEquals("[ 1, 1, 1, 0 ]", test.toLevelOrderColor());

    }

    /**
     * TESTING REMOVE ON A BLACK NODE WITH NO CHILDREN
     */
    @Test
    public void test3() {

        RedBlackTreeTraversalAE<Integer> test = new RedBlackTreeTraversalAE<>();
        test.insert(20);
        test.insert(10);
        test.insert(30);
        test.insert(35);
        test.insert(25);
        test.insert(5);
        test.insert(15);
        test.insert(12);
        test.remove(10);

        test.remove(15);

        assertEquals("[ 20, 12, 30, 5, 25, 35 ]", test.toLevelOrderString());
        assertEquals("[ 1, 1, 1, 0, 0, 0 ]", test.toLevelOrderColor());

    }

    /**
     * TESTING REMOVE ON THE ROOT NODE (WITH 2 CHILDREN)
     */
    @Test
    public void test4() {

        RedBlackTreeTraversalAE<Integer> test = new RedBlackTreeTraversalAE<>();
        test.insert(20);
        test.insert(10);
        test.insert(30);
        test.insert(35);
        test.insert(25);
        test.insert(5);
        test.insert(15);
        test.insert(12);
        test.remove(10);

        test.remove(20);

        assertEquals("[ 25, 12, 30, 5, 15, 35 ]", test.toLevelOrderString());
        assertEquals("[ 1, 0, 1, 1, 1, 0 ]", test.toLevelOrderColor());

    }

    /**
     * TESTING REMOVE ON A BLACK NODE WITH A RED SIBLING
     */
    @Test
    public void test5() {

        RedBlackTreeTraversalAE<Integer> test = new RedBlackTreeTraversalAE<>();
        test.insert(20);
        test.insert(10);
        test.insert(30);
        test.insert(15);
        test.insert(35);
        test.insert(40);
        test.insert(25);

        test.remove(30);

        assertEquals("[ 20, 10, 35, 15, 25, 40 ]", test.toLevelOrderString());
        assertEquals("[ 1, 1, 0, 0, 1, 1 ]", test.toLevelOrderColor());

    }

    /**
     * Testing the basic getter methods of the Song object and the compareTo()
     * method
     */
    @Test
    public void CodeReviewOfDataWrangler() {

        // tests the constructor of the Song object
        SongDW test = new SongDW("Name", "Artist", "Genre");

        // tests the getter methods of the Song object's data fields
        assertEquals("Name", test.getSongname());
        assertEquals("Artist", test.getArtist());
        assertEquals("Genre", test.getGenre());

        // tests the compareTo() method of Song
        SongDW lessTest = new SongDW("Name", "Brtist", "Genre");
        assertTrue(test.compareTo(lessTest) < 0);
    }

    /**
     * Testing the readSongsFromFile() method of SongDW and the splitting of the csv
     * file
     */
    @Test
    public void CodeReviewOfDataWrangler2() {
        // Basic CSV reader setup
        List<SongDW> test = new ArrayList<SongDW>();

        // testing the readSongsFromFile() method
        try {
            SongReaderDW reader = new SongReaderDW();
            test = reader.readSongsFromFile("top10s.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // testing the splitting of the csv file
        SongDW babyJB = new SongDW("Baby", "Justin Bieber", "canadian pop");
        assertEquals(0, babyJB.compareTo(test.get(6)));
    }

    /**
     * Testing the relationship between AE and DW by adding the DW object into the
     * AE tree and calling a DW method
     */
    @Test
    public void IntegrationAE_DW() {

        RedBlackTreeTraversalAE<SongDW> tree = new RedBlackTreeTraversalAE<>();

        tree.insert(new SongDW("Alejandro", "Lady Gaga", "dance pop"));
        tree.insert(new SongDW("Take It Off", "Kesha", "dance pop"));
        tree.insert(new SongDW("Misery", "Maroon 5", "pop"));
        tree.insert(new SongDW("Teenage Dream", "Katy Perry", "dance pop"));

        assertEquals(tree.getRoot().data.getSongname(), "Misery");

    }

    /**
     * Testing the relationship between AE and BD by making a BD object using an AE
     * tree which can remove
     */
    @Test
    public void IntegrationAE_BD() {

        RedBlackTreeTraversalAE<SongDW> tree = new RedBlackTreeTraversalAE<>();
        RBTreeBackendBD test = new RBTreeBackendBD(tree);

        test.addSong("Justin Bieber", "Baby", "canadian pop");
        test.addSong("Katy Perry", "California Gurls", "dance pop");
        test.addSong("Britney Spears", "3", "dance pop");
        test.addSong("Rihanna", "Hard", "barbadian pop");

        assertTrue(test.searchSong("Rihanna", "Hard", "barbadian pop"));
        test.deleteSong("Rihanna", "Hard", "barbadian pop");
        assertFalse(test.searchSong("Rihanna", "Hard", "barbadian pop"));

    }
}

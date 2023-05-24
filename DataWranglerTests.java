
// --== CS400 Project Two File Header ==--
// Name: Bailey Kau
// CSL Username: kau
// Email: bkau@wisc.edu
// Lecture #: 001 (Tuesday and Thursday at 9 am)
// Notes to Grader: None
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DataWranglerTests {
    /**
     * JUnit Tester for Song Class
     * Basic Test for whether Song class works (emphasis on if compare method works
     * correctly)
     */
    @Test
    public void testCase1() {
        SongDW song1 = new SongDW("Killshot", "Eminem", "Rap");
        // Test if getSongname works as expected
        assertEquals("Killshot", song1.getSongname());
        // Test if getArtist works as expected
        assertEquals("Eminem", song1.getArtist());
        // Test if getGenre works as expected
        assertEquals("Rap", song1.getGenre());

        SongDW song2 = new SongDW("Levitating", "Dua Lipa", "Pop");
        SongDW song3 = new SongDW("Killshot", "Magdalena Bay", "Indie");
        SongDW song4 = new SongDW("Killshot", "Eminem", "Hip Hop");
        // CompareTo Test 1 (Different Songname)
        assertTrue(song1.compareTo(song2) < 0);

        // CompareTo Test 2 (Same Songname, Different Artist)
        assertTrue(song1.compareTo(song3) < 0);

        // CompareTo Test 3 (Same Songname, Same Artist)
        assertEquals(0, song1.compareTo(song4));
    }

    /**
     * JUnit Tester for Song Reader class
     * Basic Test for whether the reader splits the csv file correctly
     */
    @Test
    public void testCase2() {
        // Basic CSV reader setup
        List<SongDW> songList = new ArrayList<SongDW>();
        try {
            SongReaderDW reader = new SongReaderDW();
            songList = reader.readSongsFromFile("top10s.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Found that you can't use assertEquals on two objects directly since it
        // compares them using address
        // Instead I used compareTo in my Song class with an expected value of 0
        assertEquals(0, new SongDW("Love The Way You Lie", "Eminem", "detroit hip hop").compareTo(songList.get(2)));
    }

    /**
     * JUnit Tester for Song Reader Class
     * Test for whether the reader ignores commas within quotes
     */
    @Test
    public void testCase3() {
        // Basic CSV reader setup
        List<SongDW> songList = new ArrayList<SongDW>();
        try {
            SongReaderDW reader = new SongReaderDW();
            songList = reader.readSongsFromFile("top10s.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Found that you can't use assertEquals on two objects directly since it
        // compares them using address
        // Instead I used compareTo in my Song class with an expected value of 0
        assertEquals(0, new SongDW("Hey, Soul Sister", "Train", "neo mellow").compareTo(songList.get(1)));
    }

    /**
     * JUnit Tester for Song Reader class
     * Test how many times a Bruno Mars song appears in our dataset
     */
    @Test
    public void testCase4() {
        // Basic CSV Reader Setup
        List<SongDW> songList = new ArrayList<SongDW>();
        int count = 0;
        try {
            SongReaderDW reader = new SongReaderDW();
            songList = reader.readSongsFromFile("top10s.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Loop through the list of songs
        for (SongDW song : songList) {
            if (song.getArtist().contains("Bruno Mars")) {
                count++;
            }
        }
        assertEquals(2, count);
    }

    /**
     * JUnit Tester for Song Reader class
     * Test how many times a song with genre 'dance pop' appears in our dataset
     */
    @Test
    public void testCase5() {
        // Basic CSV Reader Setup
        List<SongDW> songList = new ArrayList<SongDW>();
        int count = 0;
        try {
            SongReaderDW reader = new SongReaderDW();
            songList = reader.readSongsFromFile("top10s.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (SongDW song : songList) {
            if (song.getGenre().contains("dance pop")) {
                count++;
            }
        }
        assertEquals(33, count);
    }

    /**
     * Integration Tester:
     * JUnit Tester for Integration of all Branches
     */
    @Test
    public void testIntegration1() {
        RedBlackTreeTraversalAE<SongDW> tree = new RedBlackTreeTraversalAE<>();

        tree.insert(new SongDW("Keshi", "Wisconsin", "dance pop"));
        tree.insert(new SongDW("Eminem", "Rap God", "dance pop"));
        tree.insert(new SongDW("Childish Gambino", "Sad", "pop"));
        tree.insert(new SongDW("Master Oogway", "Ninjago Theme Song", "dance pop"));

        assertEquals(tree.getRoot().data.getSongname(), "Eminem");
    }

    /**
     * Integration Tester:
     * JUnit Tester for Integration of all Branches
     */
    @Test
    public void testIntegration2() {
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

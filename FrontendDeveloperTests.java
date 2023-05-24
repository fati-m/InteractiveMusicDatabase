import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrontendDeveloperTests {


    /**
     * checks that the corect user greetings appear when selecting the lookup songs option 1
     * by artist
     */
    @Test
    public void frontendTest1() {
        TextUITester tester = new TextUITester("1\nA\n5\n");
        try (Scanner scnr = new Scanner(System.in)) {
            RedBlackTreeTraversalAE<SongDW> redBlackTree = new RedBlackTreeTraversalAE<SongDW>();
            RBTreeBackendBD backend = new RBTreeBackendBD(redBlackTree);
            UserPlaylistFrontendFD frontend = new UserPlaylistFrontendFD(scnr, backend);
            frontend.userOptionsLoop();
            String returnedOutput = tester.checkOutput();
            scnr.close();

            assertEquals(true, returnedOutput.startsWith("**** Welcome to y"));
            assertEquals(true, returnedOutput.contains("Lookup up a song by [A]rtist or [G]enre"));
            assertEquals(true, returnedOutput.contains("Enter Artist name:"));
            assertEquals(true, returnedOutput.trim().endsWith("your playlist! ****"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *checks that the corect user greetings appear when selecting the lookup songs option 1
     * by genre
     */
    @Test
    public void frontendTest2() {
        TextUITester tester = new TextUITester("1\nG\n5\n");
        try (Scanner scnr = new Scanner(System.in)) {
            RedBlackTreeTraversalAE<SongDW> redBlackTree = new RedBlackTreeTraversalAE<SongDW>();
            RBTreeBackendBD backend = new RBTreeBackendBD(redBlackTree);
            UserPlaylistFrontendFD frontend = new UserPlaylistFrontendFD(scnr, backend);
            frontend.userOptionsLoop();
            String returnedOutput = tester.checkOutput();
            scnr.close();

            assertEquals(true, returnedOutput.startsWith("**** Welcome t"));
            assertEquals(true, returnedOutput.contains("Lookup up a song by [A]rtist or [G]enre"));
            assertEquals(true, returnedOutput.contains("Enter Genre name:"));
            
            assertEquals(true, returnedOutput.trim().endsWith("your playlist! ****"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *checks that the corect user greetings appear when selecting the to modify the playlist option 2
     * by adding a song
     */
    @Test
    public void frontendTest3() {
        TextUITester tester = new TextUITester("2\nA\nOut 2\n5\n");
        try (Scanner scnr = new Scanner(System.in)) {
            RedBlackTreeTraversalAE<SongDW> redBlackTree = new RedBlackTreeTraversalAE<SongDW>();
            RBTreeBackendBD backend = new RBTreeBackendBD(redBlackTree);
            UserPlaylistFrontendFD frontend = new UserPlaylistFrontendFD(scnr, backend);
            frontend.userOptionsLoop();
            String returnedOutput = tester.checkOutput();
            scnr.close();

            assertEquals(true, returnedOutput.startsWith("**** Welcome t"));
            assertEquals(true, returnedOutput.contains("Enter letters A or D to select an option:"));
            assertEquals(true, returnedOutput.contains("Enter song to add in this format separated by commas or spaces -> song name, artist, genre:"));
            assertEquals(true, returnedOutput.contains("Song added to playlist: Out of Time by The Weeknd"));
            assertEquals(true, returnedOutput.trim().endsWith("your playlist! ****"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
       

    /**
     *checks that the corect user greetings appear when selecting the to modify the playlist option 2
     * by deleting a song
     */
    @Test
    public void frontendTest4() {
        TextUITester tester = new TextUITester("2\nD\nOut of Time, The Weeknd, POP\n5\n");
        try (Scanner scnr = new Scanner(System.in)) {
            RedBlackTreeTraversalAE<SongDW> redBlackTree = new RedBlackTreeTraversalAE<SongDW>();
            RBTreeBackendBD backend = new RBTreeBackendBD(redBlackTree);
            UserPlaylistFrontendFD frontend = new UserPlaylistFrontendFD(scnr, backend);
            frontend.userOptionsLoop();
            String returnedOutput = tester.checkOutput();
            scnr.close();

            assertEquals(true, returnedOutput.startsWith("**** Welcome t"));
            assertEquals(true, returnedOutput.contains("Enter letters A or D to select an option:"));
            assertEquals(true, returnedOutput.contains("Enter song to delete in this format separated by commas or spaces -> song name, artist, genre:"));
            assertEquals(true, returnedOutput.contains("Song removed from playlist: Out of Time by The Weeknd"));
            assertEquals(true, returnedOutput.trim().endsWith("your playlist! ****"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *checks that the corect user greetings appear when selecting the to view playlist stats option 4
     */
    @Test
    public void frontendTest5() {
        TextUITester tester = new TextUITester("4\n5\n");
        try (Scanner scnr = new Scanner(System.in)) {
            RedBlackTreeTraversalAE<SongDW> redBlackTree = new RedBlackTreeTraversalAE<SongDW>();
            RBTreeBackendBD backend = new RBTreeBackendBD(redBlackTree);
            UserPlaylistFrontendFD frontend = new UserPlaylistFrontendFD(scnr, backend);
            frontend.userOptionsLoop();
            String returnedOutput = tester.checkOutput();
            scnr.close();

            assertEquals(true, returnedOutput.startsWith("**** Welcome t"));
            assertEquals(true, returnedOutput.contains("In your playlist..."));
            assertEquals(true, returnedOutput.contains("50 songs"));
            assertEquals(true, returnedOutput.contains("13 artists"));
            assertEquals(true, returnedOutput.contains("31 genres"));    
            assertEquals(true, returnedOutput.trim().endsWith("your playlist! ****"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // integration week tests

    /**
     *checks that the interface displays the added song but also that 
     * backend correctly adds it
     */
    @Test
    public void integrationTest1() {
        TextUITester tester = new TextUITester("2\nA\nOut 2\n5\n");
        try (Scanner scnr = new Scanner(System.in)) {
            RedBlackTreeTraversalAE<SongDW> redBlackTree = new RedBlackTreeTraversalAE<SongDW>();
            RBTreeBackendBD backend = new RBTreeBackendBD(redBlackTree);
            UserPlaylistFrontendFD frontend = new UserPlaylistFrontendFD(scnr, backend);
            frontend.userOptionsLoop();
            String returnedOutput = tester.checkOutput();
            System.out.println(returnedOutput);
            scnr.close();

            assertEquals(true, returnedOutput.startsWith("**** Welcome t"));
            assertEquals(true, returnedOutput.contains("Enter letters A or D to select an option:"));
            assertEquals(true, returnedOutput.contains("Enter song to add in this format separated by commas or spaces -> song name, artist, genre:"));
            assertEquals(true, returnedOutput.contains("Song added to playlist: Out of Time by The Weeknd"));
            assertEquals(true, returnedOutput.trim().endsWith("your playlist! ****"));
            assertEquals(true, returnedOutput.contains("Out of Time, The Weeknd, pop"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *checks that adding a song will increment that total number of songs
     * as done by backend and checks that frontend displays correct code
     */
    @Test
    public void integrationTest2() {
        TextUITester tester = new TextUITester("2\nA\nOut 2\n5\n4\n5\n");
        try (Scanner scnr = new Scanner(System.in)) {
            RedBlackTreeTraversalAE<SongDW> redBlackTree = new RedBlackTreeTraversalAE<SongDW>();
            RBTreeBackendBD backend = new RBTreeBackendBD(redBlackTree);
            UserPlaylistFrontendFD frontend = new UserPlaylistFrontendFD(scnr, backend);
            frontend.userOptionsLoop();
            String returnedOutput = tester.checkOutput();
            System.out.println(returnedOutput);
            scnr.close();

            assertEquals(true, returnedOutput.startsWith("**** Welcome t"));
            assertEquals(true, returnedOutput.contains("In your playlist..."));
            assertEquals(true, returnedOutput.contains("51 songs"));
            assertEquals(true, returnedOutput.contains("13 artists"));
            assertEquals(true, returnedOutput.contains("31 genres"));    
            assertEquals(true, returnedOutput.trim().endsWith("your playlist! ****"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *checks addSong() from backend
     */
    @Test
    public void codeReviewOfBackend1() {
        RedBlackTreeTraversalAE<SongDW> testTree = new RedBlackTreeTraversalAE<SongDW>();
        RBTreeBackendBD testBD = new RBTreeBackendBD(testTree);
        testBD.addSong("Billie Eilish", "Bad guy", "Pop");
        testBD.addSong("Ed Sheeran", "Shape of You", "Pop");
        testBD.addSong("Hozier", "Take Me to Church", "Alternative");
        testBD.addSong("The Beatles", "Let It Be", "Rock");
        testBD.addSong("Whitney Houston", "I Will Always Love You", "R&B");
        assertEquals(5, testBD.getPlaylistAlphabetically().size());
        assertEquals("Bad guy, Billie Eilish, Pop", testBD.getPlaylistAlphabetically().get(0));
        assertEquals("I Will Always Love You, Whitney Houston, R&B", testBD.getPlaylistAlphabetically().get(1));
        assertEquals("Let It Be, The Beatles, Rock", testBD.getPlaylistAlphabetically().get(2));
        assertEquals("Shape of You, Ed Sheeran, Pop", testBD.getPlaylistAlphabetically().get(3));
        assertEquals("Take Me to Church, Hozier, Alternative", testBD.getPlaylistAlphabetically().get(4));
    }
    

    /**
     *checks that geting songs by genre works in backend
     */
    @Test
    public void codeReviewOfBackend2() {
        RedBlackTreeTraversalAE<SongDW> testTree = new RedBlackTreeTraversalAE<SongDW>();
        RBTreeBackendBD testBD = new RBTreeBackendBD(testTree);
        testBD.addSong("Billie Eilish", "bad guy", "Pop");
        testBD.addSong("Ed Sheeran", "Shape of You", "test");
        testBD.addSong("Hozier", "Take Me to Church", "Alternative");
        testBD.addSong("The Beatles", "Let It Be", "Rock");
        testBD.addSong("Whitney Houston", "I Will Always Love You", "R&B");
        assertEquals(1, testBD.getSongsByGenre("test").size());
        assertEquals("Shape of You, Ed Sheeran, test", testBD.getSongsByGenre("test").get(0));
    }
}

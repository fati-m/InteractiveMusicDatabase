// --== CS400 Spring 2023 File Header Information ==--
// Name: Ryan Kassem
// Email: rmkassem@wisc.edu
// Team: AN Blue
// TA: Gary Dahl
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import static org.junit.Assert.assertEquals;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class BackendDeveloperTests {
    /**
     * This tester method tests addSong
     * 
     * @return Returns true if test passes, false otherwise
     */
    @Test
    public void test1() {
        RedBlackTreeTraversalAE<SongDW> testTree = new RedBlackTreeTraversalAE<SongDW>();
        RBTreeBackendBD testBD = new RBTreeBackendBD(testTree);
        testBD.addSong("ArtistA", "SongA", "GenreA");
        testBD.addSong("ArtistC", "SongC", "GenreC");
        testBD.addSong("ArtistD", "SongD", "GenreD");
        testBD.addSong("ArtistB", "SongB", "GenreB");
        testBD.addSong("ArtistE", "SongE", "GenreE");
        assertEquals(5, testBD.getPlaylistAlphabetically().size());
        assertEquals("SongA, ArtistA, GenreA", testBD.getPlaylistAlphabetically().get(0));
        assertEquals("SongB, ArtistB, GenreB", testBD.getPlaylistAlphabetically().get(1));
        assertEquals("SongC, ArtistC, GenreC", testBD.getPlaylistAlphabetically().get(2));
        assertEquals("SongD, ArtistD, GenreD", testBD.getPlaylistAlphabetically().get(3));
        assertEquals("SongE, ArtistE, GenreE", testBD.getPlaylistAlphabetically().get(4));
    }

    /**
     * This tester method tests deleteSong
     * 
     * @return Returns true if test passes, false otherwise
     */
    @Test
    public void test2() {
        RedBlackTreeTraversalAE<SongDW> testTree = new RedBlackTreeTraversalAE<SongDW>();
        RBTreeBackendBD testBD = new RBTreeBackendBD(testTree);
        testBD.addSong("Artist1", "Song1", "Genre1");
        testBD.addSong("Artist2", "Song2", "Genre2");
        testBD.addSong("Artist3", "Song3", "Genre3");
        testBD.addSong("Artist4", "Song4", "Genre4");
        testBD.addSong("Artist5", "Song5", "Genre5");
        testBD.deleteSong("Artist1", "Song1", "Genre1");
        assertEquals(4, testBD.getPlaylistAlphabetically().size());
        assertEquals("Song2, Artist2, Genre2", testBD.getPlaylistAlphabetically().get(0));
        assertEquals("Song3, Artist3, Genre3", testBD.getPlaylistAlphabetically().get(1));
        assertEquals("Song4, Artist4, Genre4", testBD.getPlaylistAlphabetically().get(2));
        assertEquals("Song5, Artist5, Genre5", testBD.getPlaylistAlphabetically().get(3));
    }

    /**
     * This tester method tests getPlaylistAlphabetically
     * 
     * @return Returns true if test passes, false otherwise
     */
    @Test
    public void test3() {
        RedBlackTreeTraversalAE<SongDW> testTree = new RedBlackTreeTraversalAE<SongDW>();
        RBTreeBackendBD testBD = new RBTreeBackendBD(testTree);
        testBD.addSong("Artist2", "Song2", "Genre2");
        testBD.addSong("Artist3", "Song3", "Genre3");
        testBD.addSong("Artist4", "Song4", "Genre4");
        testBD.addSong("Artist5", "Song5", "Genre5");
        assertEquals(4, testBD.getPlaylistAlphabetically().size());
        assertEquals("Song2, Artist2, Genre2", testBD.getPlaylistAlphabetically().get(0));
        assertEquals("Song3, Artist3, Genre3", testBD.getPlaylistAlphabetically().get(1));
        assertEquals("Song4, Artist4, Genre4", testBD.getPlaylistAlphabetically().get(2));
        assertEquals("Song5, Artist5, Genre5", testBD.getPlaylistAlphabetically().get(3));
    }

    /**
     * This tester method tests getPlaylistByArtist by a playlist that doesn't have the artist
     * 
     * @return Returns true if test passes, false otherwise
     */
    @Test
    public void test4() {
        RedBlackTreeTraversalAE<SongDW> testTree = new RedBlackTreeTraversalAE<SongDW>();
        RBTreeBackendBD testBD = new RBTreeBackendBD(testTree);
        assertEquals(0, testBD.getSongsByArtist("Artist1").size());
    }

    /**
     * This tester method tests getPlaylistByGenre
     * 
     * @return Returns true if test passes, false otherwise
     */
    @Test
    public void test5() {
        RedBlackTreeTraversalAE<SongDW> testTree = new RedBlackTreeTraversalAE<SongDW>();
        RBTreeBackendBD testBD = new RBTreeBackendBD(testTree);
        testBD.addSong("Artist2", "Song2", "Genre2");
        testBD.addSong("Artist3", "Song3", "Genre3");
        testBD.addSong("Artist4", "Song4", "Genre4");
        testBD.addSong("Artist5", "Song5", "Genre5");
        assertEquals(1, testBD.getSongsByGenre("Genre4").size());
        assertEquals("Song4, Artist4, Genre4", testBD.getSongsByGenre("Genre4").get(0));
    }

    /**
     * Tests the program (FD) after having integrated all branches
     * 
     * @returns Returns true if test passes, false otherwise
     */
    @Test
    public void integrationFDTest1() {
        TextUITester textUITester = new TextUITester("2\nA\n2\n5\n");
        try (Scanner scnr = new Scanner(System.in)) {
            RedBlackTreeTraversalAE<SongDW> testTree = new RedBlackTreeTraversalAE<SongDW>();
            RBTreeBackendBD backend = new RBTreeBackendBD(testTree);
            UserPlaylistFrontendFD frontend = new UserPlaylistFrontendFD(scnr, backend);
            frontend.userOptionsLoop();
            String output = textUITester.checkOutput();
            scnr.close();
            
            assertEquals(true, output.startsWith("**** We"));
            assertEquals(true, output.contains("letters A or D"));
            assertEquals(true, output.contains("-> song name, artist, genre:"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Tests the program (FD) after having integrated all branches
     * 
     * @returns Returns true if test passes, false otherwise
     */
    @Test
    public void integrationFDTest2() {
        TextUITester tester = new TextUITester("2\nD\nSong1, Artist1, Genre1\n5\n");
        try (Scanner scnr = new Scanner(System.in)) {
            RedBlackTreeTraversalAE<SongDW> testTree = new RedBlackTreeTraversalAE<SongDW>();
            RBTreeBackendBD backend = new RBTreeBackendBD(testTree);
            UserPlaylistFrontendFD frontend = new UserPlaylistFrontendFD(scnr, backend);
            frontend.userOptionsLoop();
            String output = tester.checkOutput();
            scnr.close();
            assertEquals(true, output.startsWith("**** We"));
            assertEquals(true, output.contains("letters A or D"));
            assertEquals(true, output.contains("-> song name, artist, genre:"));
            assertEquals(true, output.contains("removed from playlist: Song1 by Artist1"));
            assertEquals(true, output.trim().endsWith("playlist! ****"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



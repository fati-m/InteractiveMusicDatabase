// --== CS400 Spring 2023 File Header Information ==--
// Name: Ryan Kassem
// Email: rmkassem@wisc.edu
// Team: AN Blue
// TA: Gary Dahl
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import java.util.List;

/**
 * This is the interface used for the backend. Read comments below to understand the functionality
 * of each method
 * 
 * @author Ryan Kassem
 *
 */
public interface RBTreeBackendInterfaceBD {
    // Creates a Song object based on user input and passes it into AE's contains method
    public boolean searchSong(String artist, String songName, String genre);

    // Creates a Song object based on user input and passes it into AE's insert method
    public void addSong(String artist, String songName, String genre);

    // Creates a Song object based on user input and passes it into AE's remove method
    public void deleteSong(String artist, String songName, String genre);

    // Returns all songs in a playlist by alphabetical order
    public List<String> getPlaylistAlphabetically();

    // Returns a list of all songs in a playlist belonging to a specific artist based on user input
    public List<String> getSongsByArtist(String artist);

    // Returns a list of all songs in a playlist belonging to a specific genre based on user input
    public List<String> getSongsByGenre(String genre);
}



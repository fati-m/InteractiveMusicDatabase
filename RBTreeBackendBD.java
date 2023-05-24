// --== CS400 Spring 2023 File Header Information ==--
// Name: Ryan Kassem
// Email: rmkassem@wisc.edu
// Team: AN Blue
// TA: Gary Dahl
// Lecturer: Gary Dahl
// Notes to Grader: N/A


import java.util.ArrayList;
import java.util.List;

/**
 * This red-black tree backend class can add or delete songs from a playlist. It can also: (1) Check
 * if a playlist contains a specific song (2) Return a list containing the songs in a playlist by
 * alphabetical order (3) Return a list of all songs in a playlist belonging to a specific artist
 * (4) Return a list of all songs in a playlist belonging to a specific genre
 *
 * The use of private helper methods are used for (2), (3), and (4)
 * 
 * @author Ryan Kassem
 *
 */
public class RBTreeBackendBD implements RBTreeBackendInterfaceBD {

    protected RedBlackTreeTraversalAE<SongDW> redBlackTree; // The RBT used


    /**
     * Constructor for the RBTreeBackendBD class
     *
     * @param redBlackTree The red-black tree that will be used
     */
    public RBTreeBackendBD(RedBlackTreeTraversalAE<SongDW> redBlackTree) {
        this.redBlackTree = redBlackTree;
    }


    /**
     * This method determines if a song exists within a playlist or not
     *
     * @param artist   The artist provided by user input
     * @param songName The songName provided by user input
     * @param genre    The genre provided by user input
     * @return Returns true if the song was found within the playlist, false otherwise
     */

    @Override
    public boolean searchSong(String artist, String songName, String genre) {
        SongDW songToSearch = new SongDW(songName, artist, genre);
        return redBlackTree.contains(songToSearch);
    }


    /**
     * This method adds a song to a playlist
     *
     * @param artist   The artist provided by user input
     * @param songName The songName provided by user input
     * @param genre    The genre provided by user input
     */
    @Override
    public void addSong(String artist, String songName, String genre) {
        SongDW songToAdd = new SongDW(songName, artist, genre);
        redBlackTree.insert(songToAdd);
    }

    /**
     * This method deletes a song from a playlist
     *
     * @param artist   The artist provided by user input
     * @param songName The songName provided by user input
     * @param genre    The genre provided by user input
     */
    @Override
    public void deleteSong(String artist, String songName, String genre) {
        SongDW songToDelete = new SongDW(songName, artist, genre);
        redBlackTree.remove(songToDelete);
    }

    /**
     * This method gets a list containing the songs in a playlist by alphabetical order
     *
     * @return Returns a list containing the songs in a playlist by alphabetical order
     */
    @Override
    public List<String> getPlaylistAlphabetically() {
        List<String> playlist = new ArrayList<String>();
        // Helper method (in-order traversal) creates list of all songs alphabetically
        inorderAlphabetically(redBlackTree.getRoot(), playlist);
        return playlist;
    }

    /**
     * This method gets a list of all songs in a playlist belonging to a specific artist based on
     * user input
     *
     * @param artist The artist who's songs will need to be returned
     * @return Returns a list of all songs in a playlist belonging to a specific artist
     */
    @Override
    public List<String> getSongsByArtist(String artist) {
        List<String> playlist = new ArrayList<String>();
        // Helper method (in-order traversal) creates list based on provided artist alphabetically
        inorderByArtist(redBlackTree.getRoot(), playlist, artist);
        return playlist;
    }

    /**
     * This method gets a list of all songs in a playlist belonging to a specific genre based on
     * user input
     *
     * @param genre The genre who's songs will need to be returned
     * @return Returns a list of all songs in a playlist belonging to a specific genre
     */
    @Override
    public List<String> getSongsByGenre(String genre) {
        List<String> playlist = new ArrayList<String>();
        // Helper method (in-order traversal) creates list based provided genre alphabetically
        inorderByGenre(redBlackTree.getRoot(), playlist, genre);
        return playlist;
    }


    /**
     * Helper method for getPlaylistAlphabetically(). Achieved using an in-order search
     *
     * @param song     The root of the RBT being searched
     * @param playlist The playlist where the song(s) will be stored
     */
    private void inorderAlphabetically(RedBlackTreeAE.Node<SongDW> song, List<String> playlist) {
        if (song == null) {
            return;
        }
        inorderAlphabetically(song.context[1], playlist);
        playlist.add(song.data.toString());
        inorderAlphabetically(song.context[2], playlist);
    }


    /**
     * Helper method for getSongsByArtist(). Achieved using an in-order search
     *
     * @param song     The root of the RBT being searched
     * @param playlist The playlist where the song(s) will be stored
     * @param artist   The artist who's songs will need to be returned
     */
    private void inorderByArtist(RedBlackTreeAE.Node<SongDW> song, List<String> playlist,
            String artist) { // Change song to songTree
        if (song == null) {
            return;
        }
        inorderByArtist(song.context[1], playlist, artist);
        if (song.data.getArtist().equals(artist)) {
            playlist.add(song.data.toString());
        }
        inorderByArtist(song.context[2], playlist, artist);
    }

    /**
     * Helper method for getSongsByGenre(). Achieved using an in-order search
     *
     * @param song     The root of the RBT being searched
     * @param playlist The playlist where the song(s) will be stored
     * @param genre    The genre who's songs will need to be returned
     */
    private void inorderByGenre(RedBlackTreeAE.Node<SongDW> song, List<String> playlist,
            String genre) {
        if (song == null) {
            return;
        }
        inorderByGenre(song.context[1], playlist, genre);
        if (song.data.getGenre().equals(genre)) {
            playlist.add(song.data.toString());
        }
        inorderByGenre(song.context[2], playlist, genre);
    }
}


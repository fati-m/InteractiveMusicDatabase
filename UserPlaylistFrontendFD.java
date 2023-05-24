// --== CS400 Spring 2023 File Header Information ==--
// Name: Fatimah Mohammed
// Email: famohammed2@wisc.edu
// Team: AN Blue
// TA: Gary Dahl
// Lecturer: Gary Dahl
// Notes to Grader: none

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This class defines all user interface options
 */
public class UserPlaylistFrontendFD implements UserPlaylistFrontendInterfaceFD {
    Scanner userChoice; // takes user input
    RBTreeBackendBD backend; // backend fucntionality
    RedBlackTreeTraversalAE<SongDW> redBlackTree; // rbt where data is stored

    /**
     * constructer that defines the class and sets up needed objects
     * @param userChoice
     * @param backend
     */
    public UserPlaylistFrontendFD(Scanner userChoice, RBTreeBackendBD backend) {
        this.userChoice = new Scanner(System.in);
        redBlackTree = new RedBlackTreeTraversalAE<SongDW>();
        this.backend = new RBTreeBackendBD(redBlackTree);
    }

    /**
     * runs user interface loop and all options 
     */
    @Override
    public void userOptionsLoop() { 
        try{
        // load default songs from csv file
        
        SongReaderDW reader = new SongReaderDW();
        List<SongDW> songList = reader.readSongsFromFile("./top10s.csv");
        for (int i =0; i< 50; i++) {
            SongDW song = songList.get(i);
            redBlackTree.insert(song);
        }
        } catch (FileNotFoundException e) { // if file cannot be found
            System.out.println("Playlist failed. Check that file path is correct in code and run again.");
            return;
        }

        // print welcome message
        System.out.println("**** Welcome to your interactive playlist! ****");
        String command = "\0";
        while (!command.equals("5")) { // main loop continues until user chooses to quit
            // display menu of choices
            System.out.println("Select an option by entering one of the following numbers:");
            System.out.println("    1. Lookup a Song");
            System.out.println("    2. Modify Playlist");
            System.out.println("    3. View Playlist Alphabetically");
            System.out.println("    4. View Playlist Statistics");
            System.out.println("    5. Exit Application");

            // read in user's choice, and trim away any leading or trailing whitespace
            System.out.print("Enter selection:");
            String input = userChoice.nextLine().trim().toUpperCase();
            if (input.length() == 0) { // if user's choice is blank, return null character
                command = "\0";
            }

            command = input;
            switch (command) {
                case "1": // System.out.println("    1. Lookup a Song");
                    System.out.println("");
                    System.out.println("**** You Selected to Lookup a Song ****");
                    System.out.println("Enter letters A or G to select an option:");
                    System.out.println("    Lookup up a song by [A]rtist or [G]enre");

                    // read users choice
                    System.out.print("Enter choice:");
                    String choice = userChoice.nextLine().trim().toUpperCase();

                    // by artist
                    if(choice.equals("A")) {
                        lookupSongsByArtist();
                    }

                    // by genre
                    if(choice.equals("G")) {
                        lookupSongsByGenre();
                    }
                    break;

                case "2": // System.out.println("    2. Modify Playlist");
                    System.out.println("");
                    System.out.println("**** You Selected to Modify the Playlist ****");
                    System.out.println("Enter letters A or D to select an option:");
                    System.out.println("    [A]dd a song to the playlist");
                    System.out.println("    [D]elete a song from the playlist");
                    System.out.print("Enter choice:");

                    // read users choice
                    choice = userChoice.nextLine().trim().toUpperCase();

                    // add
                    if(choice.equals("A")) {
                        addToPlaylist();
                    }

                    // delete
                    if(choice.equals("D")) {
                        deleteFromPlaylist();
                    }
                    break;

                case "3": // System.out.println("    3. View Playlist Alphabetically");
                    lookupPlaylistAlphabetically();
                    break;

                case "4": // System.out.println("    4. View Playlist Statistics");
                    playlistDetails();
                    break;

                case "5": // System.out.println("    5. Exit Application");
                    System.out.println("**** Goodbye, enjoy your playlist! ****");
                    break;

                default:
                    System.out.println("Didn't recognize that. Please Try Again.");
                    break;
            }
        }

    }

    /**
     * returns all songs in playlist from user inputed genre
     */
    @Override
    public void lookupSongsByArtist(){ // uses getSongsByArtist() from backend
        // choice selection message
        System.out.println("**** You Selected Artist ****");
        System.out.print("Enter Artist name:");
        String choice = userChoice.nextLine().trim();

        // gets all songs from artist
        List<String> results = backend.getSongsByArtist(choice);
        int resultIndex = 1;

        // returns results
        if (results.size() > 0) {
            System.out.println("Found Results:");
            for (String result : results)
                System.out.println("[" + (resultIndex++) + "] " + result);
        } else {
            System.out.println("No song from that artist was found.");
        }
    }

    /**
     * returns all songs in playlist from user inputed genre
     */
    @Override
    public void lookupSongsByGenre() { // uses getSongsByGenre() from backend
        // choice selection message
        System.out.println("**** You Selected Genre ****");
        System.out.print("Enter Genre name:");
        String choice = userChoice.nextLine().trim();
        // gets all songs from genre
        List<String> results = backend.getSongsByGenre(choice);
        int resultIndex = 1;

        // returns results
        if (results.size() > 0) {
            System.out.println("Found Results:");
            for (String result : results)
                System.out.println("[" + (resultIndex++) + "] " + result);
        } else {
            System.out.println("No song from that genre was found.");
        }
    }

    /**
     * returns all songs in playlist alphabetically
     */
    @Override
    public void lookupPlaylistAlphabetically() {// uses getPlaylistAlphabetically() from backend
        // choice selection message
        System.out.println("**** You selected to View Playlist Alphabetically ****");
        System.out.println("In your playlist...");

        // gets all songs alphabetically
        List<String> results = backend.getPlaylistAlphabetically();
        int resultIndex = 1;

        // returns results
        if (results.size() > 0) {
            for (String result : results)
                System.out.println("[" + (resultIndex++) + "] " + result);
        } else {
            System.out.println("No songs are the playlist.");
        }
    }

    /**
     * adds the user inputed song from playlist
     */
    @Override
    public void addToPlaylist() { // uses addSong() from backend
        // choice selection message
        System.out.println("**** You Selected to Add a Song ****");
        System.out.print("Enter song to add in this format separated by commas or spaces -> song name, artist, genre:");
        String choice = userChoice.nextLine();

        // gathers songs into to add it
        String[] songParts = choice.replaceAll("\"", "").split(",\\s*");
        String songName = songParts[0];
        String artist = songParts[1];
        String genre = songParts[2];

        // adds song
        backend.addSong(artist, songName, genre);
        System.out.print("");
        System.out.println("Song added to playlist: " + songName + " by " + artist);
        System.out.print("");
    }

    /**
     * deletes the user inputed song from playlist
     */
    @Override
    public void deleteFromPlaylist() { // uses deleteSong() from backend
        // choice selection message
        System.out.println("**** You Selected to Delete a Song ****");
        System.out.print("Enter song to delete in this format separated by commas or spaces -> song name, artist, genre:");
        String choice = userChoice.nextLine();

        // gathers songs into for deletion
        String[] songParts = choice.replaceAll("\"", "").split(",\\s*");
        String songName = songParts[0];
        String artist = songParts[1];
        String genre = songParts[2];

        // deletes song
        backend.deleteSong(artist, songName, genre);
        System.out.print("");
        System.out.println("Song removed from playlist: " + songName + " by " + artist);
        System.out.print("");
    }

    /**
     * displays playlist details with info on number of songs, genres, and artists
     */
    @Override
    public void playlistDetails(){ // will return general stats about the playlist
        // choice selection message
        System.out.println("**** You Selected to View Playlist Statistics ****");
        System.out.println("In your playlist...");
        
        // stores all songs, artists, and genres in playlist
        List<String> songs = backend.getPlaylistAlphabetically();
        int numSongs = songs.size();
        List<String> genres = new ArrayList<>();
        List<String> artists = new ArrayList<>();

        // interates through and finds number of artists and genres
        for (String song: backend.getPlaylistAlphabetically()) {
            String[] songInfo = song.split(", ");
            String artist = songInfo[1];
            String genre = songInfo[2];

            if(!genres.contains(genre.toLowerCase())) {
                genres.add(genre.toLowerCase());
            }

            if(!artists.contains(artist.toLowerCase())) {
                artists.add(artist.toLowerCase());
            }
        }

        // prints stats
        System.out.print("");
        System.out.println(songs.size() + " songs");
	System.out.println(genres.size() + " artists");
        System.out.println(artists.size() + " genres");
    }


    /**
     * main method to run whole application 
     * @param args
     */
    public static void main(String args[]) {
        // Use data wrangler's code to load data
        //SongReaderFD songReader = new SongReaderFD();
        // Use algorithm engineer's code to store and search for data
        RedBlackTreeTraversalAE<SongDW> redBlackTree;
        redBlackTree = new RedBlackTreeTraversalAE<SongDW>();
        // Use the backend developer's code to manage all app specific processing
        RBTreeBackendBD backend = new RBTreeBackendBD(redBlackTree);
        // Use the frontend developer's code to drive the text-base user interface
        Scanner scanner = new Scanner(System.in);
        UserPlaylistFrontendFD frontend = new UserPlaylistFrontendFD (scanner, backend);
        frontend.userOptionsLoop();
    }
}

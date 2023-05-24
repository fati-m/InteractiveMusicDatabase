// --== CS400 Spring 2023 File Header Information ==--
// Name: Fatimah Mohammed
// Email: famohammed2@wisc.edu
// Team: AN Blue
// TA: Gary Dahl
// Lecturer: Gary Dahl
// Notes to Grader: none

/*
 * this interface defines all user interface options
 */
public interface UserPlaylistFrontendInterfaceFD {
    //public UserPlaylistFrontendInterface(Scanner userChoice,
    //RBTreeBackendInterface backend);
    public void userOptionsLoop(); // uses searchSong() from backend
    public void lookupSongsByArtist(); // uses getSongsByArtist() from backend
    public void lookupSongsByGenre(); // uses getSongsByGenre() from backend
    public void addToPlaylist(); // uses addSong() from backend
    public void deleteFromPlaylist(); // uses deleteSong() from backend
    public void lookupPlaylistAlphabetically(); // uses
    //getPlaylistAlphabetically() from backend
    public void playlistDetails(); // will return general stats about the playlist
    // public static void maim(String args[]); 
}
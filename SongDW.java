// --== CS400 Project Two File Header ==--
// Name: Bailey Kau
// CSL Username: kau
// Email: bkau@wisc.edu
// Lecture #: 001 (Tuesday and Thursday at 9 am)
// Notes to Grader: None
public class SongDW implements SongInterfaceDW {
    private String songname;
    private String artist;
    private String genre;

    public SongDW(String songname, String artist, String genre) {
        this.songname = songname;
        this.artist = artist;
        this.genre = genre;
    }

    public String getSongname() {
        return songname;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public int compareTo(SongDW toCompare) {
        int songnameCompare = songname.compareTo(toCompare.getSongname());
        int artistCompare = artist.compareTo(toCompare.getArtist());
        if (songnameCompare != 0) {
            return songnameCompare;
        } else if (artistCompare != 0) {
            return artistCompare;
        }
        return 0;
    }

    public String toString() {
        return songname + ", " + artist + ", " + genre;
    }
}

// --== CS400 Project Two File Header ==--
// Name: Bailey Kau
// CSL Username: kau
// Email: bkau@wisc.edu
// Lecture #: 001 (Tuesday and Thursday at 9 am)
// Notes to Grader: None
public interface SongInterfaceDW extends Comparable<SongDW>{
    //public SongInterface(String songname, String artist, String genre)
    public String getSongname();
    public String getArtist();
    public String getGenre();

    public int compareTo(SongDW toCompare);

    public String toString();
}

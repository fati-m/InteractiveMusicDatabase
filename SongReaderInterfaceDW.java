// --== CS400 Project Two File Header ==--
// Name: Bailey Kau
// CSL Username: kau
// Email: bkau@wisc.edu
// Lecture #: 001 (Tuesday and Thursday at 9 am)
// Notes to Grader: None
import java.util.List;
import java.io.FileNotFoundException;
public interface SongReaderInterfaceDW {
    //public SongReaderInterface()
    public List<SongDW> readSongsFromFile (String filename) throws FileNotFoundException;
}
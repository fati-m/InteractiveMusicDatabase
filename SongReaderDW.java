import java.io.File;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class SongReaderDW implements SongReaderInterfaceDW {
    @Override
    public List<SongDW> readSongsFromFile (String filename) throws FileNotFoundException {
        List<SongDW> songList = new ArrayList<>();
        try (Scanner scan = new Scanner(new File(filename))) {
            while (scan.hasNextLine()) {
                try (Scanner rowScan = new Scanner(scan.nextLine())) {
                    while (rowScan.hasNextLine()) {
                        songList.add(splitStringOutsideQuotes(rowScan.nextLine()));
                    }
                }
            }
        }
        return songList;
    }

    public SongDW splitStringOutsideQuotes (String toAdjust) {
        String toSplit = "";
        boolean inQuotes = false;
        for (int i = 0; i < toAdjust.length(); i++) {
            if (toAdjust.charAt(i) == ',' && inQuotes) {
                toSplit += "£";
            } else if (toAdjust.charAt(i) == '"') {
                inQuotes = !inQuotes;
            } else {
                toSplit += toAdjust.charAt(i);
            }
        }
        String[] splitstr = toSplit.split(",");
        String songname = "";
        String artist = "";
        String genre = "";
        if (splitstr.length > 1) {
            if (splitstr[1].contains("£")) {
                for (int i = 0; i < splitstr[1].length(); i++) {
                    if (splitstr[1].charAt(i) == '£') {
                        songname += ",";
                    } else {
                        songname += splitstr[1].charAt(i);
                    }
                }
            } else {
                songname = splitstr[1];
            }
        }
        if (splitstr.length > 2) {
            if (splitstr[2].contains("£")) {
                for (int i = 0; i < splitstr[2].length(); i++) {
                    if (splitstr[2].charAt(i) == '£') {
                        artist += ",";
                    } else {
                        artist += splitstr[2].charAt(i);
                    }
                }
            } else {
                artist = splitstr[2];
            }
        }
        if (splitstr.length > 3) {
            if (splitstr[3].contains("£")) {
                for (int i = 0; i < splitstr[3].length(); i++) {
                    if (splitstr[3].charAt(i) == '£') {
                        genre += ",";
                    } else {
                        genre += splitstr[3].charAt(i);
                    }
                }
            } else {
                genre = splitstr[3];
            }
        }
        return new SongDW(songname, artist, genre);
    }

    public static void main (String[] args) {
        try {
            SongReaderDW reader = new SongReaderDW();
            List<SongDW> songList = reader.readSongsFromFile("./top10s.csv");
            for (SongDW song: songList) {
                System.out.println(song);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
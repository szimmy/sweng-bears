import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Zim on 2/22/2017.
 */
public class FileScanner {

    private File file;
    private ReportContent scan;
    private HashMap<String, Integer> data;

    public FileScanner(File file, ReportContent scan) {
        this.file = file;
        this.scan = scan;
        this.data = new HashMap<>();
    }

    public HashMap<String, Integer> run() {
        // Data
        int numLines = 0;
        int HComments = 0;

        //Boolean to tell when you are currently in a high-level comment block
        boolean Hcomment = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                //Checks if you are currently in a high-level comment block
                if (Hcomment == true) {
                    //Checks if the comment block is terminated shown by '$'
                    if (line.contains("$")) {
                        Hcomment = false;
                    }
                    HComments++;
                }
                //If not currently in a high-level comment block, checks if one starts
                else if (line.contains("COMMENT")) {
                    //Checks if the block ends on the same line it started
                    if (!line.contains("$")) {
                        Hcomment = true;
                    }
                    HComments++;
                }

                if(line.length() !=  0) {
                    numLines++;
                }
            }
        } catch(IOException ie) {
        }

        data.put("Lines", numLines);
        data.put("High level comments", HComments);

        return data;
    }
}

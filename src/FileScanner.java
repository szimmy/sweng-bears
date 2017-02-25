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
        int numLines = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line
                if(line.length() !=  0) {
                    numLines++;
                }
            }
        } catch(IOException ie) {
        }

        data.put("Lines", numLines);

        return data;
    }
}

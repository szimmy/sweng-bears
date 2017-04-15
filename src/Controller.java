import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import Report.Column;
import Reports.Report;
import Reports.SourceAnalysis;

/**
 * Contains the main method of the program. Takes in file(s) and runs selected reports on them.
 *
 * @author Sean Zimmerman
 */

public class Controller {

    private final static String DEFAULTDIRECTORY = System.getProperty("user.dir"); // TODO check if this works on linux

    private static Report report = Report.sourceReview; // default for now, will be changed to selection eventually

    /**
     * The main method of the program. Outputs the time the program takes to run.
     * @param args Any filenames (entered at the command line) to be scanned
     */
    public static void main(String args[]) {
        long startTime = System.nanoTime();
        chooseFiles(args);
        long endTime = System.nanoTime();
        long output = endTime - startTime;
        System.out.println("Elapsed time in milliseconds: " + output / 1000000);
        System.exit(0);
    }

    /**
     * Choose the files and run the scan on them.
     * @param args Any filenames (entered at the command line) to be scanned
     */
    private static void chooseFiles(String [] args) {
        report.generateReportColumns();

        if(args.length != 0) {
            report = Report.getReport(args[0]); // program will error if input is not valid
        }

        File files[] = getFiles(args);

        for(int i = 1; i < files.length; i++) {
            report.fillColumn(new FileScanner(files[i], new SourceAnalysis()).run());
        }

        // Prints the report
        report.generateReports();

        // An ArrayList of type String to keep track of invalid Files that were selected to scan
        ArrayList<String> invalidFiles = new ArrayList<>();


        for(File f: files) {
            if(!validExtension(getExtension(f))) {
                invalidFiles.add(f.getName());
                System.out.println("Invalid Extension: " + getExtension(f));
            }
        }

        if(invalidFiles.size() != 0) {
            System.out.println("Invalid Files: " + invalidFiles.toString());
        }
    }

    /**
     * Get the extension for a file
     * @param file The file to get the extension of
     * @return The extension of the file
     */
    private static String getExtension(File file) {
        String extension = "";

        int i = file.getAbsolutePath().lastIndexOf('.');
        int p = Math.max(file.getAbsolutePath().lastIndexOf('/'),
                file.getAbsolutePath().lastIndexOf('\\'));

        if (i > 0 && i > p) {
            extension = file.getAbsolutePath().substring(i+1);
        }

        return extension;
    }

    /**
     * Checks if the extension is of a valid type
     * @param ext Extension to check
     * @return If the extension is valid
     */
    private static boolean validExtension(String ext) {
        return ext.equals("txt");
    }

    /**
     * @author Jeffrey Lehman (updated by Alexander Mendelsohn)
     * @Date 02 Feb 2017
     * @return  files; an array of type File which the user has chosen using jfilechooser
     */
    private static File[] getFiles(String [] args){
        if (args.length == 0) {
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(true);
            chooser.showOpenDialog(new JFrame());
            File[] files = chooser.getSelectedFiles();
            return files;
        }
        File[] files = new File[args.length];
        for (int i = 0; i < args.length; i++) {
            files[i] = new File(DEFAULTDIRECTORY + "\\" + args[i]);
        }
        return files;
    }
}
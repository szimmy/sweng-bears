import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import Report.Column;
import Reports.Report;
/**
 * Contains the main method of the program. Takes in file(s) and runs selected reports on them.
 *
 * @author Sean Zimmerman
 */

public class Controller {

    private final static String DEFAULTDIRECTORY = System.getProperty("user.dir");

    public static void main(String args[]) {
        chooseFiles(args);
        System.exit(0);
    }

    /**
     * Choose the files and run the scan on them.
     */
    private static void chooseFiles(String [] args) {
        ArrayList<ArrayList<String>> scans = new ArrayList<>();
        // Uses method getFiles(); to create an array files of type File
        File files[] = getFiles(args);
        // Adds an ArrayList of the scanned data on each File in files to an ArrayList scans by performing
        // FileScanner.run()
        for(int i=0;i<files.length;i++) {
            if(validExtension(getExtension(files[i]))) {
                scans.add(new FileScanner(files[i], Report.sourceAnalysis).run()); // run source analysis as default for now
            }
        }

        //Prints the information scanned to the terminal from ArrayList scans
        Report.reportGeneration(Report.sourceAnalysis.getHeader(), fillColumns(scans));

        //An ArrayList of type String to keep track of invalid Files that were selected to scan
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
     * Fill the Columns of the generated report with the output data
     * @param data The data to fill the report
     * @return The filled columns
     */
    private static ArrayList<Column> fillColumns(ArrayList<ArrayList<String>> data) {
        ArrayList<Column> result = Report.sourceAnalysis.generateReportColumns();

        Column tempColumn;
        ArrayList<String> tempArrayList;

        // Add the data from data to the columns
        for(int i = 0; i < result.size(); i++) { // for every column
            tempColumn = result.get(i);
            for(int j = 0; j < data.size(); j++) { // for every row, all rows are the same fixed size by definition
                tempArrayList = data.get(j);
                tempColumn.addData(tempArrayList.get(i)); // add the corresponding element to the column from that row
            }
        }

        return result;
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
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
/**
 * Contains the main method of the program. Takes in file(s) and runs selected reports on them.
 *
 * @author Sean Zimmerman
 */

public class Controller {

    private final static JFileChooser chooser = new JFileChooser();

    public static void main(String args[]) {
        chooseFiles();
    }

    /**
     * Choose the files and run the scan on them.
     */
    private static void chooseFiles() {
        ArrayList scans=new ArrayList();
        // Uses method getFiles(); to create an array files of type File
        File files[] = getFiles();
        // Adds a HashMap of the scanned data on each File in files to an ArrayList scans by performing
        // FileScanner.run()
        for(int i=0;i<files.length;i++) {
            scans.add(new FileScanner(files[i], null).run());
        }
        //Prints the information scanned to the terminal from ArrayList scans
        System.out.println(scans.toString());

        //An ArrayList of type String to keep track of invalid Files that were selected to scan
        ArrayList<String> invalidFiles = new ArrayList<>();

        
        for(File f: files) {
            if(!validExtension(getExtension(f))) {
                invalidFiles.add(f.getName());
                System.out.println("Invalid Extension: " + getExtension(f));
            }
        }
        
        System.out.println("Invalid Files: " + invalidFiles.toString());
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
     * @author Jeffrey Lehman
     * @Date 02 Feb 2017
     * @return  files; an array of type File which the user has chosen using jfilechooser
     */
    private static File[] getFiles(){
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        chooser.showOpenDialog(new JFrame());
        File[] files = chooser.getSelectedFiles();
        return files;
    }
}
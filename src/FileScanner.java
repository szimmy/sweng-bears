import CMS2Statements.StatementReader;
import CMS2Statements.Statement;
import Reports.Entry;
import Reports.Report;
import Scans.*;

import java.io.File;
import java.util.ArrayList;

/**
 * Class which scans the file for characteristics of a CMS-2Y program and tracks the data it collects.
 */
public class FileScanner {
    private ArrayList<String> data;
    private File file;
    private Report scan;
    private StatementReader stmtReader;
 
    /**
     * Constructor for FileScanner. Takes in file type File, which the the file being scanned and scan type Reports.ReportContent
     * which is used to store the data. Creates a new HashMap which save the data scanned.
     * @param file the File to be scanned
     * @param scan the Report for the FileScanner
     */
    public FileScanner(File file, Report scan) {
        this.stmtReader = new StatementReader(file);
        this.file = file;
        this.scan = scan;
        this.data = new ArrayList<String>();
    }

    /**
     * Runs the FileScanner.
     * @return ArrayList<String> An ArrayList of the data gained in the scan
     */
    public ArrayList<String> run() {
        // Runs all of the scans on all of the statements in the file
        ArrayList<Scan> scans = scan.getScans();
        for(Statement stmt : stmtReader.getStatements()){
           for(Scan s : scans) {
               s.scan(stmt);
           }
        }

        // Add the file name and type to the data
        String fileNameWithOutExt = file.getName().replaceFirst("[.][^.]+$", ""); // remove the first dot followed by any characters
        data.add(fileNameWithOutExt);
        data.add("SYST");

        // Add the output of all of the scans into the ArrayList
        for(Scan s : scans) {
            ArrayList<Entry> temp = s.getData();
            for(Entry e : temp) {
                data.add(String.valueOf(e.getValue()));
            }
        }

        // Add the total number of lines to the specified location in the ArrayList (easier to do it this way than to have a seperate scan)
        if(scan.getTotalLinesArrayPos() != -1) {
            data.add(scan.getTotalLinesArrayPos(), String.valueOf(stmtReader.numLines()));
        }

        return data;
    }
}

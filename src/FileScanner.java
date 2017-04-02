import CMS2Statements.StatementReader;
import CMS2Statements.Statement;
import Reports.Entry;
import Reports.Report;
import Scans.Scan;

import java.io.File;
import java.util.ArrayList;

/**
 * Class which scans the file for characteristics of a CMS-2Y program and tracks the data it collects.
 */
public class FileScanner {

    private StatementReader stmtReader;
    private File file;
    private Report scan;

    private ArrayList<String> data;
 
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
        data.add(scan.getTotalLinesArrayPos(), String.valueOf(stmtReader.numLines()));

        return data;


        //The lines below are temporary. This function should be changed to return a Report (I think), not a HashMap
      
        /*
        * Old code below, used for reference until everything is properly converted into scanners
        */
        /*
        // Data, Integers to keep track of how many times each data type occurs in the File file
        int numLines = 0;
        int numCommentStmts = 0;
        int numCommentLines = 0;
        int numDataStmts = 0;
        int numDataLines = 0;
        int numGotoStmts = 0;
        int numGotoLines = 0;
        int numDirCommentsStmts = 0;
        // This will need to be split into two in a later sprint.
        int numDirOther = 0;
        int numCMSOtherStmts = 0;
        int numCMSOtherLines = 0;

        // Boolean to keep track if the scan is currently in a Direct cms2 code block
        boolean inDirectBlock = false;

        
         // Uses BufferedReader and FileReader on File file to begin the scan.
         // Strings line and statement are used to track and evaluate the current line and/or block of cms2 code.
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String statement = "";

            boolean stmtBegun = false;
            int stmtBeginningLine = 0;

            while ((line = br.readLine()) != null) {
                numLines++;
                //Change tabs into spaces
                line = transformTabs(line);
                //Take the first ten characters off. They are not used.
                if (line.length() > 10) {
                    line = line.substring(10);
                    if (stmtBegun == false) { //May incorrectly handle labels on blank lines proceeding statements.
                        stmtBeginningLine = numLines;
                        stmtBegun = true;
                    }
                } else {
                    line = "";

                }
                //TODO Pad the back with spaces. (?)
                if (!inDirectBlock) {
                    statement = statement.concat(trimDelim(line, '$'));
                    if (statement.contains("$")) {  //TODO Change this to check if it ends with a dollar sign. (?)
                        //Perform analysis of CMS-2Y statements here. (Not Direct Code, that is below.)
                        if (getFirstToken(statement).equals("COMMENT")) {
                            numCommentStmts++;
                            numCommentLines += (numLines - stmtBeginningLine) + 1;
                        } else if (getFirstToken(statement).equals("VRBL")
                                || getFirstToken(statement).equals("TABLE")
                                || getFirstToken(statement).equals("END-TABLE")
                                || getFirstToken(statement).equals("FIELD")
                                || getFirstToken(statement).equals("ITEM-AREA")
                                || getFirstToken(statement).equals("TYPE")
                                || getFirstToken(statement).equals("END-TYPE")
                                || getFirstToken(statement).equals("SUB-TABLE")
                                || getFirstToken(statement).equals("SUBTABLE")) {
                            numDataStmts++;
                            numDataLines += (numLines - stmtBeginningLine) + 1;
                        } else if (getFirstToken(statement).equals("GOTO")) {
                            numGotoStmts++;
                            numGotoLines += (numLines - stmtBeginningLine) + 1;
                        } else if (getFirstToken(statement).equals("DIRECT") ||
                                getFirstToken(statement).equals("DIRECT$")) { //I don't know if this would be legal
                            numCMSOtherStmts++; //Test this line and the next
                            numCMSOtherLines += (numLines - stmtBeginningLine) + 1;
                            inDirectBlock = true;
                        } else {
                            numCMSOtherStmts++; //Test this line and the next
                            numCMSOtherLines += (numLines - stmtBeginningLine) + 1;
                        }
                        //clear appropriate values
                        statement = "";
                        stmtBeginningLine = 0; //This seems like a dangerous default value, but should work.
                        stmtBegun = false;
                    }
                } else { //current statement is in a direct code block
                    statement = line;
                    //Perform analysis of Direct Code statements here.
                    if (getFirstToken(statement).equals(".")) {
                        numDirCommentsStmts++;
                    } else if (getFirstToken(statement).equals("CMS-2") ||
                            getFirstToken(statement).equals("CMS-2$")) {
                        //I'm counting the direct code block ending-delimiter as CMS-2, not direct code.
                        numCMSOtherStmts++;
                        numCMSOtherLines += (numLines - stmtBeginningLine) + 1;
                        inDirectBlock = false;
                    } else {
                        numDirOther++;
                    }
                    statement = "";
                    stmtBegun = false;
                }
            }
        } catch(IOException ie) {
        }

        // Puts the data into a HashMap data with the corresponding String (data type).
        data.put("Lines", numLines);
        data.put("Comment Statements", numCommentStmts);
        data.put("Comment Lines", numCommentLines);
        data.put("Data Statements", numDataStmts);
        data.put("Data Lines", numDataLines);
        data.put("Goto Statements", numGotoStmts);
        data.put("Goto Lines", numGotoLines);
        data.put("CMS-2Y Other Statements", numCMSOtherStmts);
        data.put("CMS-2Y Other Lines", numCMSOtherLines);
        data.put("Direct Code Comments", numDirCommentsStmts);
        data.put("Direct Code Other", numDirOther);
      */

    }
}

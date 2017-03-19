import CMS2Statements.StatementReader;
import CMS2Statements.Statement;
import Reports.Report;
import java.io.File;
import java.util.HashMap;
import Scans.CommentScanner;
import Scans.DirCommentScanner;


/**
 * Class which scans the file for characteristics of a CMS-2Y program and tracks the data it collects.
 */
public class FileScanner {

    private StatementReader stmtReader;
    private File file;
    private Report scan;

    private HashMap<String, Integer> data;
 
    /**
     * Constructor for FileScanner. Takes in file type File, which the the file being scanned and scan type Reports.ReportContent
     * which is used to store the data. Creates a new HashMap which save the data scanned.
     * @param file
     * @param scan
     */
    public FileScanner(File file, Report scan) {
        this.stmtReader = new StatementReader(file);
        this.file = file;
        this.scan = scan;
        this.data = new HashMap<>();
    }

    /**
     * @return HashMap<String, Integer> A HashMap of the data gained in the scan, <String, Integer> being the
     * <Type of data, the number of times that type was found in the file>
     */
    public HashMap<String, Integer> run() {
        for(Statement stmt : stmtReader.getStatements()){
            commentScanner.scan(stmt);
            dirCommentScanner.scan(stmt);
        }

        data.put("Lines", stmtReader.numLines());
        //The lines below are temporary. This function should be changed to return a Report (I think), not a HashMap
        data.put("Comment Statements", commentScanner.getData().get(0).getValue());
        data.put("Comment Lines", commentScanner.getData().get(1).getValue());
        data.put("Direct Comment Statements", dirCommentScanner.getData().get(0).getValue());
      
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

        // Returns HashMap data
        return data;
    }
}

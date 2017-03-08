import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class which scans the file for characteristics of a CMS-2Y program and tracks the data it collects.
 */
public class FileScanner {

    private File file;
    private ReportContent scan;
    private HashMap<String, Integer> data;

    /**
     * Constructor for FileScanner. Takes in file type File, which the the file being scanned and scan type ReportContent
     * which is used to store the data. Creates a new HashMap which save the data scanned.
     * @param file
     * @param scan
     */
    public FileScanner(File file, ReportContent scan) {
        this.file = file;
        this.scan = scan;
        this.data = new HashMap<>();
    }

    /**
     * Performs a scan on the File file,
     *
     * @return HashMap<String, Integer> A HashMap of the data gained in the scan, <String, Integer> being the
     * <Type of data, the number of times that type was found in the file>
     */
    public HashMap<String, Integer> run() {
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

        /**
         * Uses BufferedReader and FileReader on File file to begin the scan.
         * Strings line and statement are used to track and evaluate the current line and/or block of cms2 code.
         */
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

        // Returns HashMap data
        return data;
    }

    /**
     * Returns the given string with everything after the first instance of the delimiter removed.
     */
    private String trimDelim(String line, char delimiter){ //Change the name of this, but not to trim
        int delimiterIndex = line.indexOf(delimiter);
        if(delimiterIndex == -1) {
            return line;
        }
        return line.substring(0, delimiterIndex + 1);
    }
    //If it is possible that the first token isn't followed by a space, needs to be changed.
    private String getFirstToken(String statement){
        if(statement.trim().indexOf(" ") != -1){
            return statement.trim().substring(0, statement.trim().indexOf(" ")).trim();
    }
        return statement.trim();
    }

    /**
     * Returns the original string with tabs turned into spaces
     */
    private String transformTabs(String string){
        String[] segments = string.split("\t");
        String result = "";
        for(String seg : segments){
            result = result.concat(seg + "    ");
        }
        if(result.length() >= 4) {
            result = result.substring(0, result.length() - 4);
            return result;
        }
        return string; //check.
    }
}

import CMS2Statements.Statement;
import CMS2Statements.StatementReader;

import java.io.File;
import java.util.HashMap;

/**
 * Class which scans the file for characteristics of a CMS-2Y program and tracks the data it collects.
 */
public class FileScanner {

    private StatementReader stmtReader;
    private HashMap<String, Integer> data;
    private int numCommentStmts;
    private int numCommentLines;
    private int numDirCommentsStmts;
    // These will need to be split into two in a later sprint.
    private int numDirOther;
    //private int numCMSOtherStmts;
    //private int numCMSOtherLines;

    public FileScanner(File file, ReportContent scan) {
        this.stmtReader = new StatementReader(file);
        this.data = new HashMap<>();
    }

    /**
     * @return HashMap<String, Integer> A HashMap of the data gained in the scan, <String, Integer> being the
     * <Type of data, the number of times that type was found in the file>
     */
    public HashMap<String, Integer> run() {
        // Data, Integers to keep track of how many times each data type occurs in the File file
        this.numCommentStmts = 0;
        this.numCommentLines = 0;
        this.numDirCommentsStmts = 0;
        this.numDirOther = 0;
        //this.numCMSOtherStmts = 0;
        //this.numCMSOtherLines = 0;
        

        // Puts the data into a HashMap data with the corresponding String (data type).
        data.put("Lines", stmtReader.numLines());
        data.put("Comment Statements", numCommentStmts);
        data.put("Comment Lines", numCommentLines);
        //data.put("CMS-2Y Other Statements", numCMSOtherStmts);
        //data.put("CMS-2Y Other Lines", numCMSOtherLines);
        data.put("Direct Code Comments", numDirCommentsStmts);
        data.put("Direct Code Other", numDirOther);

    // Returns HashMap data
        return data;
    }
}

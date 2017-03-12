import CMS2Statements.StatementReader;
import CMS2Statements.Statement;
import java.io.File;
import java.util.HashMap;
import Scans.CommentScanner;
import Scans.DirCommentScanner;


/**
 * Class which scans the file for characteristics of a CMS-2Y program and tracks the data it collects.
 */
public class FileScanner {

    private StatementReader stmtReader;
    private HashMap<String, Integer> data;
    private CommentScanner commentScanner;
    private DirCommentScanner dirCommentScanner;

    public FileScanner(File file, ReportContent scan) {
        this.stmtReader = new StatementReader(file);
        this.commentScanner = new CommentScanner();
        this.dirCommentScanner = new DirCommentScanner();
        this.data = new HashMap<>();
    }

    /**
     * @return HashMap<String, Integer> A HashMap of the data gained in the scan, <String, Integer> being the
     * <Type of data, the number of times that type was found in the file>
     */
    public HashMap<String, Integer> run() {

        for(Statement stmt : stmtReader.getStatements()){
            System.out.println(stmt.getText());
            commentScanner.scan(stmt);
            dirCommentScanner.scan(stmt);
        }

        data.put("Lines", stmtReader.numLines());
        //The lines below are temporary. This function should be changed to return a Report, not a HashMap
        data.put("Comment Statements", commentScanner.getData().get(0).getValue());
        data.put("Comment Lines", commentScanner.getData().get(1).getValue());
        data.put("Direct Comment Statements", dirCommentScanner.getData().get(0).getValue());

    // Returns HashMap data
        return data;
    }
}

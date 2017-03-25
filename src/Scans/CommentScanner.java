package Scans;

import CMS2Statements.Statement;
import Reports.Entry;
import java.util.ArrayList;

public class CommentScanner extends LineScan {
    public CommentScanner(){
        KEYWORD = "Comment";
        count = 0;
        lineCount = 0;
    }

    public void scan(Statement statement){
        if(!statement.isDirectCode() && getFirstToken(statement.getText()).equals("COMMENT")){
            count++;
            tallyLines(statement);
        }
    }
}

package Scans;

import CMS2Statements.Statement;

public class CommentScanner extends LineScan {
    public CommentScanner(){
        KEYWORD = "Comment";
        count = 0;
        lineCount = 0;
    }

    public void scan(Statement statement){
        if(!statement.isDirectCode()){
            if(getFirstToken(statement.getText()).equals("COMMENT")){
                count++;
                tallyLines(statement);
            }
        }
    }
}

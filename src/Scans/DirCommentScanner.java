package Scans;

import CMS2Statements.Statement;

public class DirCommentScanner extends Scan{
    public DirCommentScanner() {
        KEYWORD = "Direct Code Comment";
        count = 0;
    }

    public void scan(Statement statement){
        if(statement.isDirectCode() && statement.getText().contains(".")){
            count++;
        }
    }
}

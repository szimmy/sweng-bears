package Scans;

import CMS2Statements.Statement;

public class DirCommentScanner extends LineScan{
    public DirCommentScanner() {
        KEYWORD = "Direct Code Comment";
        count = 0;
        lineCount = 0;
    }

    public void scan(Statement statement){
        if(!statement.isDirectCode()){
            if(statement.getText().contains(".")){
                count++;
                tallyLines(statement);
            }
        }
    }
}

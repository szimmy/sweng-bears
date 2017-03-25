package Scans;

import CMS2Statements.Statement;

public class GotoScanner extends LineScan {
    public GotoScanner(){
        KEYWORD = "Goto";
        count = 0;
        lineCount = 0;
    }

    public void scan(Statement statement){
        if(!statement.isDirectCode() && getFirstToken(statement.getText()).equals("GOTO")){
            count++;
            tallyLines(statement);
        }
    }
}

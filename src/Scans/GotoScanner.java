package Scans;

import CMS2Statements.Statement;

public class GotoScanner extends Scan {
    public GotoScanner(){
        KEYWORD = "Goto";
        count = 0;
    }

    public void scan(Statement statement){
        if(!statement.isDirectCode() && getFirstToken(statement.getText()).equals("GOTO")){
            count++;
        }
    }
}

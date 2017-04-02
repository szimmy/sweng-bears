package Scans;

import CMS2Statements.Statement;
import Reports.Entry;

import java.util.ArrayList;

public class HighLevelExecScanner extends LineScan {

    boolean inExecBlock;

    public HighLevelExecScanner() {
        KEYWORD = "Exec";
        count = 0;
        lineCount = 0;
        inExecBlock = false;
    }

    public void scan(Statement statement) {
        if(!statement.isDirectCode()){
            if(getFirstToken(statement.getText()).equals("SYS-PROC")) {
                inExecBlock = true;
            }
            else if(getFirstToken(statement.getText()).equals("END-SYS-PROC")) {
                inExecBlock = false;
            }
            else if(inExecBlock && !getFirstToken(statement.getText()).equals("COMMENT")){//Notice that SYS-PROC is not
                count++;                                                                  //counted as a exec statement.
                tallyLines(statement);
            }
        }

    }
}

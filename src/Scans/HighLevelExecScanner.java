package Scans;

import CMS2Statements.Statement;
import Reports.Entry;

import java.util.ArrayList;

public class HighLevelExecScanner extends LineScan {

    boolean inExecBlock;
    private ArrayList<String> procStartWords;
    private ArrayList<String> procEndWords;

    public HighLevelExecScanner() {
        KEYWORD = "Exec";
        count = 0;
        lineCount = 0;
        inExecBlock = false;

        procStartWords.add("PROCEDURE");
        procStartWords.add("EXEC-PROC");

        procEndWords.add("END-PROC");
    }

    public void scan(Statement statement) {
        if(!statement.isDirectCode()){
            String firstToken = getFirstToken(statement.getText());
            if(procStartWords.contains(firstToken)) {
                inExecBlock = true;
            }
            else if(procEndWords.contains(firstToken)) {
                inExecBlock = false;
            }
            else if(inExecBlock && !getFirstToken(statement.getText()).equals("COMMENT")){//Notice that SYS-PROC is not
                count++;                                                                  //counted as a exec statement.
                tallyLines(statement);
                statement.setClassified(true);
            }
        }
    }
}

package Scans;

import CMS2Statements.Statement;
import Reports.Entry;

import java.util.ArrayList;

public class MisplacedEndSysScanner extends Scan {

    private boolean correctENDSYS = false; // assume false for now

    public MisplacedEndSysScanner() {
        KEYWORD = "MisplacedEndSys";
        count = 0; // the number of misplaced END-SYSTEM statements
    }

    public void scan(Statement statement) {
        if (correctENDSYS) { // if this scanner is run even though an END-SYSTEM statement was already found
            correctENDSYS = false; // the END-SYSTEM statement must be in the wrong spot
            count++;
        }
        if (!statement.isDirectCode() && getFirstToken(statement.getText()).equals("END-SYS-PROC")) {
            correctENDSYS = true; // assume this to be true until a statement is found afterward.
        }
    }

    @Override
    public ArrayList<Entry> getData() {
        ArrayList<Entry> data = new ArrayList<Entry>();

        if (!correctENDSYS) { // if the final statement read wasn't END-SYSTEM, something's wrong
            count++;
        }
        data.add(new Entry(KEYWORD + " Stmts", count));
        return data;
    }
}

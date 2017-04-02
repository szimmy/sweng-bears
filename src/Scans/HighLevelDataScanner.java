package Scans;

import CMS2Statements.Statement;
import Reports.Entry;

import java.util.ArrayList;

/**
 * This class scans code for High Level Data Statements
 */
public class HighLevelDataScanner extends LineScan {
    public HighLevelDataScanner() {
        KEYWORD = "Data";
        count = 0;
        lineCount = 0;
    }

    // DID INCLUDE
    // TABLE, END-TABLE, ITEM-AREA, TYPE, END-TYPE, DATA, SUB-TABLE, SUBTABLE
    // UNSURE IF INCLUDED UNDER DATA STMTS
    public void scan(Statement statement) {
        String s = getFirstToken(statement.getText());
        if (!statement.isDirectCode()) {
                if (
                        s.equals("VRBL") || s.equals("FIELD") || s.equals("TABLE") ||
                            s.equals("END-TABLE") || s.equals("ITEM-AREA") || s.equals("TYPE") ||
                            s.equals("END-TYPE") || s.equals("DATA") || s.equals("SUB-TABLE") ||
                            s.equals("SUBTABLE")) {
                count++;
                tallyLines(statement);
            }
        }
    }
}

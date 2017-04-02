package Scans;

import CMS2Statements.Statement;
import Reports.Entry;

import java.util.ArrayList;

/**
 * This class scans code for High Level Data Statements
 */
public class HighLevelDataScanner extends LineScan {

    /**
     * The constructor for HighLevelDataScanner
     */
    public HighLevelDataScanner() {
        KEYWORD = "Data";
        count = 0;
        lineCount = 0;
    }

    /**
     * Scans a statement and counts it and its lines if the statement is a High Level Data Statement
     * @param statement The statement to be scanned
     */
    public void scan(Statement statement) {
        if(!statement.isDirectCode()
                && (getFirstToken(statement.getText()).equals("VRBL")
                || getFirstToken(statement.getText()).equals("TABLE")
                || getFirstToken(statement.getText()).equals("END-TABLE")
                || getFirstToken(statement.getText()).equals("FIELD")
                || getFirstToken(statement.getText()).equals("ITEM-AREA")
                || getFirstToken(statement.getText()).equals("TYPE")
                || getFirstToken(statement.getText()).equals("END-TYPE")
                || getFirstToken(statement.getText()).equals("SUB-TABLE")
                || getFirstToken(statement.getText()).equals("SUBTABLE"))) {
            count++;
            tallyLines(statement);
        }
    }

//    public ArrayList<Entry> getData() {
//        ArrayList<Entry> data = super.getData();
//
//        data.add(new Entry(KEYWORD + " Lines", lineCount));
//
//        return data;
//    }
}

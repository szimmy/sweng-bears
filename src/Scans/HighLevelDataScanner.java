package Scans;

import CMS2Statements.Statement;
import Reports.Entry;

import java.util.ArrayList;

public class HighLevelDataScanner extends LineScan {

    public HighLevelDataScanner() {
        KEYWORD = "Data";
        count = 0;
        lineCount = 0;
    }

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

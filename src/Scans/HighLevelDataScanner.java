package Scans;

import CMS2Statements.Statement;
import Reports.Entry;
import java.util.ArrayList;

public class HighLevelDataScanner extends LineScan {


    public HighLevelDataScanner() {
        KEYWORD = "Data";
        count = 0;
        lineCount=0;
    }

    // DID NOT INCLUDE
    // TABLE, END-TABLE, ITEM-AREA, TYPE, END-TYPE, DATA, SUB-TABLE, SUBTABLE
    // UNSURE IF INCLUDED UNDER DATA STMTS
    public void scan(Statement statement){
        String s=getFirstToken(statement.getText());
        if(!statement.isDirectCode()){
            if(s.equals("VRBL") || s.equals("FIELD")) {
                count++;
                tallyLines(statement);
            }
        }

    }

    public ArrayList<Entry> getData() {
        ArrayList<Entry> data = super.getData();
        data.add(new Entry(KEYWORD + " Lines", lineCount));
        return data;
    }
}
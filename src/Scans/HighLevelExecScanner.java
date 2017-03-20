package Scans;

import CMS2Statements.Statement;
import Reports.Entry;

import java.util.ArrayList;

public class HighLevelExecScanner extends Scan {

    private int lineCount = 0;

    public HighLevelExecScanner() {
        KEYWORD = "Exec";
        count = 0;
    }

    public void scan(Statement statement) {
        // TODO
    }

    public ArrayList<Entry> getData() {
        ArrayList<Entry> data = super.getData();

        data.add(new Entry(KEYWORD + " Lines", lineCount));

        return data;
    }
}

package Scans;

import CMS2Statements.Statement;
import Reports.Entry;

import java.util.ArrayList;

public class HighLevelNonCommentScanner extends Scan {

    public HighLevelNonCommentScanner() {
        KEYWORD = "NonCmt";
        count = 0;
    }

    public void scan(Statement statement) {
        // TODO
    }

    public ArrayList<Entry> getData() {
        ArrayList<Entry> data = new ArrayList<Entry>();

        data.add(new Entry(KEYWORD + " Lines", count));

        return data;
    }
}

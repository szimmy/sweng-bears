package Scans;

import CMS2Statements.Statement;
import Reports.Entry;

import java.util.ArrayList;

public class TotalExecScanner extends Scan {

    private HighLevelExecScanner hles = new HighLevelExecScanner();
    private DirectExecScanner des = new DirectExecScanner();

    public TotalExecScanner() {
        KEYWORD = "Exec";
        count = 0;
    }

    public void scan(Statement statement) {
        hles.scan(statement);
        des.scan(statement);
    }

    public ArrayList<Entry> getData() {
        ArrayList<Entry> data = new ArrayList<Entry>();

        data.add(new Entry(KEYWORD + " Stmts", hles.getData().get(0).getValue() + des.getData().get(0).getValue()));

        return data;
    }
}

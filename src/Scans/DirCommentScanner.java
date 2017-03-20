package Scans;

import CMS2Statements.Statement;
import java.util.ArrayList;
import Reports.Entry;

public class DirCommentScanner extends Scan{
    public DirCommentScanner() {
        KEYWORD = "Direct Code Comment";
        count = 0;
    }

    public void scan(Statement statement){
        System.out.println("Test 1");
        if(statement.isDirectCode() && statement.getText().contains(".")){
            count++;
            System.out.println("Test 2");
        }
    }

    public ArrayList<Entry> getData() {
        ArrayList<Entry> result = new ArrayList<Entry>();

        result.add(new Entry("Direct Code Comment Lines", count));

        return result;
    }
}

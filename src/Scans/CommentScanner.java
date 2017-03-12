package Scans;

import CMS2Statements.Statement;
import Reports.Entry;

import java.util.ArrayList;

/**
 * Created by Jay on 3/11/2017.
 */
public class CommentScanner extends Scan {
    private int lineCount;
    public CommentScanner(){
        KEYWORD = "Comment";
        count = 0;
        lineCount = 0;
    }

    public void scan(Statement statement){
        if(!statement.isDirectCode()){
            if(getFirstToken(statement.getText()).equals("COMMENT")){
                count++;
                lineCount += statement.getNumLines();//TODO fix double counting issue.
            }
        }
    }

    public ArrayList<Entry> getData() { //TODO
        return null;
    }

}

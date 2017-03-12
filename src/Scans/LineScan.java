package Scans;

import CMS2Statements.Statement;
import Reports.Entry;

import java.util.ArrayList;

/**
 * An abstract class that extends scan by adding functionality useful for counting lines.
 */
public abstract class LineScan extends Scan {
    //The number of lines of that contain a statement or statements that has met the critia for this scan.
    protected int lineCount;


    //Used to prevent counting multiple statements of the same type on the same line as multiple lines
    private int lastLineTallied = -1;

    /**
     * Updates lineCount. Will not count two statements on the same line as two lines.
     * @param statement
     */
    protected void tallyLines(Statement statement){
        int result = statement.getNumLines();
        if(statement.getBeginningLine() == lastLineTallied){
            result -= 1;
        }
        lastLineTallied = statement.getEndingLine();
        lineCount += result;
    }

    public ArrayList<Entry> getData() {
        ArrayList<Entry> result = new ArrayList<Entry>();
        result.add(new Entry(KEYWORD + " Statements", count));
        result.add(new Entry(KEYWORD + " Lines", lineCount));
        return result;
    }
}

package Scans;

import CMS2Statements.Statement;
import Reports.Entry;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * This class scans code for procedures with 230-250 statements.
 */
public class Procedure230to250Scanner extends Scan {

    private boolean inProcBlock;
    public static TreeSet<String> procNames = new TreeSet<>();
    private String procName;

    /**
     * Constructor for Procedure230to250Scanner.
     */
    public Procedure230to250Scanner() {
        KEYWORD = "230-250";
        count = 0;
        inProcBlock = false;
        procName = "";
    }

    /**
     * Scans a procedure's statement and counts the procedure if it has 230-250 statements.
     * @param statement The statement to be scanned
     */
    public void scan(Statement statement) {
        String s = getFirstToken(statement.getText());
        if (!statement.isDirectCode() && s.equals("PROCEDURE")) {
            inProcBlock = true;
            procName = getSecondToken(statement.getText());
        } else if (!statement.isDirectCode() && s.equals("END-PROC")) {
            inProcBlock = false;
            count = 0;
        } else if (inProcBlock) {
            count++;
            if (count == 230) {
                procNames.add(procName);
            }
            if (count == 251) {
                procNames.remove(procName);
            }
        }
    }

    /**
     * Overrides Scan's getData() method so that it returns how many procedures match.
     * @return number of matching procedures
     */
    public ArrayList<Entry> getData() {
        ArrayList<Entry> data = new ArrayList<Entry>();

        data.add(new Entry(KEYWORD + " Procs", procNames.size()));
        // the actual names of these procedures can be accessed via procNames, which is public

        return data;
    }
}

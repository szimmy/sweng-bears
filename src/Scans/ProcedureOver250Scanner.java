package Scans;

import CMS2Statements.Statement;
import Reports.Entry;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * This class scans code for procedures with over 250 lines.
 */
public class ProcedureOver250Scanner extends LineScan {

    private boolean inProcBlock;
    public static TreeSet<String> procNames = new TreeSet<>();
//    public static TreeSet<String> fileNames = new TreeSet<>();
    private String procName;
//    private String fileName = "..";

    /**
     * Constructor for ProcedureOver250Scanner.
     */
    public ProcedureOver250Scanner() {
        KEYWORD = "Over250";
        count = 0;
        lineCount = 0; // in this case, count refers to the number of lines in a procedure
        inProcBlock = false;
        procName = "";
        //this.fileName = fileName;
    }

    /**
     * Scans a procedure's statement and counts the procedure if it has over 250 lines.
     * @param statement The statement to be scanned
     */
    public void scan(Statement statement) {
        String s = getFirstToken(statement.getText());
        if (!statement.isDirectCode() && s.equals("PROCEDURE")) {
            inProcBlock = true;
            procName = getSecondToken(statement.getText());
        } else if (!statement.isDirectCode() && s.equals("END-PROC")) {
            inProcBlock = false;
            if (lineCount > 250) {
                procNames.add(procName);
                count++;
            }
            lineCount = 0;
        } else if (inProcBlock) {
            tallyLines(statement);
        }
    }

    /**
     * Overrides Scan's getData() method so that it returns how many procedures match.
     * @return number of matching procedures
     */
    public ArrayList<Entry> getData() {
        ArrayList<Entry> data = new ArrayList<Entry>();

        data.add(new Entry(KEYWORD + " Procs", count));
        // the actual names of these procedures can be accessed via procNames, which is public

        return data;
    }

    /**
     * Provides the second token of a Statement (so we can get procedure names).
     * @param statement The Statement to be read
     * @return the second token statement
     */
    private String getSecondToken(String statement) {
        String s;
        if(statement.trim().contains(" ")){
            s = statement.trim().substring(statement.trim().indexOf(" ")).trim();
            s = s.trim();
            return getFirstToken(s);
        }
        return statement.trim();
    }
}

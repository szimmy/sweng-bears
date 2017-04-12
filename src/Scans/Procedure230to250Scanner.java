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
    public TreeSet<String> fileNames;
    public TreeSet<String> procNames;
    private String procName;

    /**
     * Constructor for Procedure230to250Scanner.
     */
    public Procedure230to250Scanner() {
        KEYWORD = "230-250";
        count = 0;
        inProcBlock = false;
        fileNames = new TreeSet<>();
        procNames = new TreeSet<>();
        procName = "";
    }

    /**
     * Scans a statement and counts it and its lines if it is a procedure with 230-250 statements.
     * @param statement The statement to be scanned
     */
    public void scan(Statement statement) {
        String s = getFirstToken(statement.getText());
        if (!statement.isDirectCode()) {
            if (s.equals("PROCEDURE")) {
                inProcBlock = true;
                procName = getSecondToken(statement.getText());
            } else if (s.equals("END-PROC")) {
                inProcBlock = false;
                count = 0;
            } else if (inProcBlock) {
                count++;
                if (count == 230) {
                    procNames.add(procName);
                    // TODO: need way to add filename to fileNames
                }
                if (count == 251){
                    procNames.remove(procName);
                    // TODO: need way to add filename to fileNames
                }
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
        // TODO: need way to return number of fileNames and actual names of procedures and files

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

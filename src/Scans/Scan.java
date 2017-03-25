package Scans;

import Reports.Entry;
import java.util.ArrayList;
import CMS2Statements.Statement;

/**
 * Abstract class which creates the framework for scans to be built off of.
 * A Scan is used to determine if a statement meets certain criteria of input CMS-2Y code.
 * For example, a Scan could be counting the number of comments.
 *
 * @author Sean Zimmerman
 */
public abstract class Scan {
    // Keyword: used to identify the type of scan being used.
    // This is what will be reported in the column header of the generated report.
    protected String KEYWORD = "";

    // Count: The number of statements that have met the criteria of this Scan.
    protected int count;

    /**
     * Runs the designated scan on the statement.
     * @param statement The statement to be scanned
     */
    public abstract void scan(Statement statement);

    public String getKEYWORD() {
        return this.KEYWORD;
    }

    /**
     * Some scans will count both number of statements and number of lines.
     *
     * For example: High level CMS-2 executables.
     * Keyword: "Exec"
     * Make an Entry for each thing being counted (ex. keys would be "Stmts" and "Lines")
     * Insert the Entry's into the ArrayList in the order desired to show up on the Report.
     * For Source Analysis Summary this would be "Stmts" -> "Lines".
     *
     * This method collects all of the data from the Scan into an ArrayList.
     * @return The data from the Scan.
     */
    public ArrayList<Entry> getData() {
        ArrayList<Entry> data = new ArrayList<Entry>();

        data.add(new Entry(KEYWORD + " Stmts", count));

        return data;
    }


    //If it is possible that the first token isn't followed by a space, needs to be changed.
    protected String getFirstToken(String statement){
        if(statement.trim().indexOf(" ") != -1){
            return statement.trim().substring(0, statement.trim().indexOf(" ")).trim();
        }
        return statement.trim();
    }
}

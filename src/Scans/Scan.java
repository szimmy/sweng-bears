package Scans;

import Reports.Entry;
import java.util.ArrayList;

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
    private final String KEYWORD = "";

    // Count: The number of statements that have met the criteria of this Scan.
    private int count;

    /**
     * Runs the designated scan on the statement.
     * @param statement The statement to be scanned
     */
    public abstract void scan(String statement);

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
    public abstract ArrayList<Entry> getData();
}
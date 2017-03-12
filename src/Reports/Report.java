package Reports;

import Scans.Scan;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A Report consistents of a collection of Scans that the user wants to run on the given input file.
 * All subclasses of report will be stored as static variables in Report.java.
 * To create a new report, make a subclass of whatever title, and in the default constructor
 * instantiate scans with whatever scan objects desired.
 * Then fill in the functionality for generateReport().
 *
 * Basic report template is included in *TO BE DETERMINED*.
 *
 * @author Sean Zimmerman
 */
public abstract class Report {
    // Collection of Scan objects which will be run on the file.
    protected ArrayList<Scan> scans;

    // Title of the report to be used in the report.
    protected final String TITLE = "";

    // Any generated reports will be added below.
    public static Report sourceAnalysis = new SourceAnalysis();

    /**
     * Generate the report for the given Report.
     * @return The correctly formatted report.
     */
    public abstract String generateReport();

    public String getTITLE() {
        return this.TITLE;
    }
}
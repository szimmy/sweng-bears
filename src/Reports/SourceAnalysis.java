package Reports;

import Scans.Scan;
import java.util.ArrayList;

/**
 * SourceAnalysis is a type of report which scans select features of CMS-2Y code. These features are:
 * High Level CMS:
 *   Executable Statements
 *   Executable Lines
 *   Data Statements
 *   Data Lines
 *   Comment Statements
 *   Comment Lines
 *   NonComment Lines
 *   Other Statements
 * CMS-2 Direct:
 *   Executable Statements
 *   Data Statements
 *   Comment Lines
 * Total:
 *   Executable Statements
 *   Source Lines
 *   CSWTC Statements
 *   MX LV
 *   Delimt Statements
 */
public class SourceAnalysis extends Report {
    // Title of the report to be used in the report.
    private final String TITLE = "Source Analysis Summary";

    public SourceAnalysis() {
        this.scans = new ArrayList<Scan>();

        // TODO when scan classes are created add them into here for testing (for source analysis)
    }

    /**
     * Generate the report for the given Report.
     * @return The correctly formatted report.
     */
    public String generateReport(String[] columnNames, Object[][] data) {
        return ""; // TODO
    }
}

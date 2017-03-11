import Report.Column;
import Reports.Report;

import java.util.ArrayList;

/**
 * Test for the report output in Reports.Report.
 */
public class ReportTest {
    public static void main(String[] args) {
        ArrayList<Column> data = new ArrayList<Column>();

        Column nameColumn = new Column(false, 9);

        nameColumn.addData("");
        nameColumn.addData("Name");
        nameColumn.addData("---------");
        nameColumn.addData("MAI4E1");
        nameColumn.addData("MAL4E1");
        nameColumn.addData("MBI4E1");

        data.add(nameColumn);

        Column typeColumn = new Column(true, 4);

        typeColumn.addData("");
        typeColumn.addData("Type");
        typeColumn.addData("----");
        typeColumn.addData("SYST");
        typeColumn.addData("SYST");
        typeColumn.addData("SYST");

        data.add(typeColumn);

        Report.sourceAnalysis.reportGeneration("", data);

        // LOOKS GREAT ADD MORE TESTING
    }
}

package Reports;

import Report.Column;
import Scans.*;

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
        this.totalLinesArrayPos = 14;

        this.scans = new ArrayList<Scan>();

        // The header String for the report
        header = Column.padLeft("Source Analysis Summary", 78);
        header += "\n";
        header += "               +----------------- High Level CMS-2 ------------------+";
        header += " +-- CMS-2 DIRECT --+";
        header += " +--- Total ---+";

        scans.add(new HighLevelExecScanner());
        scans.add(new HighLevelDataScanner());
        scans.add(new CommentScanner());
        scans.add(new HighLevelNonCommentScanner());
        scans.add(new HighLevelOtherScanner());
        scans.add(new DirectExecScanner());
        scans.add(new DirectDataScanner());
        scans.add(new DirCommentScanner());
        scans.add(new TotalExecScanner());
        scans.add(new TotalCSWTCScanner());
        scans.add(new TotalMXLVScanner());
        scans.add(new TotalDelimtStmtsScanner());

        //scans.add(new GotoScanner());
        //scans.add(new MisplacedEndSysScanner());
    }

    /**
     * Generate the columns for the given Report.
     * @return The columns to be used for the report.
     */
    public ArrayList<Column> generateReportColumns() {
        ArrayList<Column> data = new ArrayList<Column>();

        Column nameColumn = new Column(false, false, 10);

        nameColumn.addData("");
        nameColumn.addData("  Name");
        nameColumn.addData("---------");

        data.add(nameColumn);

        Column typeColumn = new Column(true, true, 4);

        typeColumn.addData("");
        typeColumn.addData("Type");
        typeColumn.addData("----");

        data.add(typeColumn);

        Column execstsmt = new Column(true, false, 6);

        execstsmt.addData(" Exec");
        execstsmt.addData(" Stmts");
        execstsmt.addData("------");

        data.add(execstsmt);

        Column execlines = new Column(true, false, 6);

        execlines.addData("Exec");
        execlines.addData("Lines");
        execlines.addData("------");

        data.add(execlines);

        Column datastmts = new Column(true, false, 6);

        datastmts.addData("Data");
        datastmts.addData("Stmts");
        datastmts.addData("------");

        data.add(datastmts);

        Column datalines = new Column(true, false, 6);

        datalines.addData("Data");
        datalines.addData("Lines");
        datalines.addData("------");

        data.add(datalines);

        Column commentstmts = new Column(true, true, 6);

        commentstmts.addData("Cmmnt");
        commentstmts.addData("Stmts");
        commentstmts.addData("------");

        data.add(commentstmts);

        Column commentlines = new Column(true, true, 6);

        commentlines.addData("Cmmnt");
        commentlines.addData("Lines");
        commentlines.addData("------");

        data.add(commentlines);

        Column noncmtlines = new Column(true, true, 6);

        noncmtlines.addData("NonCmt");
        noncmtlines.addData("Lines");
        noncmtlines.addData("------");

        data.add(noncmtlines);

        Column otherstmts = new Column(true, true, 5);

        otherstmts.addData("Other");
        otherstmts.addData("Stmts");
        otherstmts.addData("-----");

        data.add(otherstmts);

        Column execstmtsdir = new Column(true, false, 6);

        execstmtsdir.addData(" Exec");
        execstmtsdir.addData(" Stmts");
        execstmtsdir.addData("------");

        data.add(execstmtsdir);

        Column datastmtsdir = new Column(true, false, 6);

        datastmtsdir.addData(" Data");
        datastmtsdir.addData(" Stmts");
        datastmtsdir.addData("------");

        data.add(datastmtsdir);

        Column commentlinesdir = new Column(true, false, 6);

        commentlinesdir.addData("Cmmnt");
        commentlinesdir.addData("Lines");
        commentlinesdir.addData("------");

        data.add(commentlinesdir);

        Column execstmtstotal = new Column(true, false, 7);

        execstmtstotal.addData(" Exec");
        execstmtstotal.addData(" Stmts");
        execstmtstotal.addData("-------");

        data.add(execstmtstotal);

        Column sourcelines = new Column(true, true, 7);

        sourcelines.addData("Source");
        sourcelines.addData("Lines");
        sourcelines.addData("-------");

        data.add(sourcelines);

        Column cswtcstmts = new Column(true, false, 5);

        cswtcstmts.addData("CSWTC");
        cswtcstmts.addData("Stmts");
        cswtcstmts.addData("----");

        data.add(cswtcstmts);

        Column mxlv = new Column(true, true, 2);

        mxlv.addData("MX");
        mxlv.addData("LV");
        mxlv.addData("--");

        data.add(mxlv);

        Column delimtstmts = new Column(true, true, 6);

        delimtstmts.addData("Delimt");
        delimtstmts.addData("Stmts");
        delimtstmts.addData("------");

        data.add(delimtstmts);

        return data;
    }
}

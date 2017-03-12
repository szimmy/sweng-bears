import Report.Column;
import Reports.Report;

import java.util.ArrayList;

/**
 * Test for the report output in Reports.Report.
 */
public class ReportTest {
    public static void main(String[] args) {
        ArrayList<Column> data = new ArrayList<Column>();

        Column nameColumn = new Column(false, false, 10);

        nameColumn.addData("");
        nameColumn.addData("Name");
        nameColumn.addData("---------");
        nameColumn.addData("MAI4E1");
        nameColumn.addData("MAL4E1");
        nameColumn.addData("MBI4E1");

        data.add(nameColumn);

        Column typeColumn = new Column(true, true, 4);

        typeColumn.addData("");
        typeColumn.addData("Type");
        typeColumn.addData("----");
        typeColumn.addData("SYST");
        typeColumn.addData("SYST");
        typeColumn.addData("SYST");

        data.add(typeColumn);

        Column execstsmt = new Column(true, false, 6);

        execstsmt.addData("Exec");
        execstsmt.addData("Stmts");
        execstsmt.addData("------");
        execstsmt.addData("369");
        execstsmt.addData("415");
        execstsmt.addData("375");

        data.add(execstsmt);

        Column execlines = new Column(true, false, 6);

        execlines.addData("Exec");
        execlines.addData("Lines");
        execlines.addData("------");
        execlines.addData("906");
        execlines.addData("760");
        execlines.addData("632");

        data.add(execlines);

        Column datastmts = new Column(true, false, 6);

        datastmts.addData("Data");
        datastmts.addData("Stmts");
        datastmts.addData("------");
        datastmts.addData("659");
        datastmts.addData("290");
        datastmts.addData("246");

        data.add(datastmts);

        Column datalines = new Column(true, false, 6);

        datalines.addData("Data");
        datalines.addData("Lines");
        datalines.addData("------");
        datalines.addData("815");
        datalines.addData("428");
        datalines.addData("247");

        data.add(datalines);

        Column commentstmts = new Column(true, true, 6);

        commentstmts.addData("Cmmnt");
        commentstmts.addData("Stmts");
        commentstmts.addData("------");
        commentstmts.addData("927");
        commentstmts.addData("436");
        commentstmts.addData("408");

        data.add(commentstmts);

        Column commentlines = new Column(true, true, 6);

        commentlines.addData("Cmmnt");
        commentlines.addData("Lines");
        commentlines.addData("------");
        commentlines.addData("4016");
        commentlines.addData("1984");
        commentlines.addData("1837");

        data.add(commentlines);

        Column noncmtlines = new Column(true, true, 6);

        noncmtlines.addData("NonCmt");
        noncmtlines.addData("Lines");
        noncmtlines.addData("------");
        noncmtlines.addData("2321");
        noncmtlines.addData("1699");
        noncmtlines.addData("1269");

        data.add(noncmtlines);

        Column otherstmts = new Column(true, true, 5);

        otherstmts.addData("Other");
        otherstmts.addData("Stmts");
        otherstmts.addData("-----");
        otherstmts.addData("374");
        otherstmts.addData("431");
        otherstmts.addData("360");

        data.add(otherstmts);

        Column execstmtsdir = new Column(true, false, 6);

        execstmtsdir.addData("Exec");
        execstmtsdir.addData("Stmts");
        execstmtsdir.addData("------");
        execstmtsdir.addData("1");
        execstmtsdir.addData("8");
        execstmtsdir.addData("3");

        data.add(execstmtsdir);

        Column datastmtsdir = new Column(true, false, 6);

        datastmtsdir.addData("Data");
        datastmtsdir.addData("Stmts");
        datastmtsdir.addData("------");
        datastmtsdir.addData("72");
        datastmtsdir.addData("0");
        datastmtsdir.addData("0");

        data.add(datastmtsdir);

        Column commentlinesdir = new Column(true, false, 6);

        commentlinesdir.addData("Cmmnt");
        commentlinesdir.addData("Lines");
        commentlinesdir.addData("------");
        commentlinesdir.addData("139");
        commentlinesdir.addData("0");
        commentlinesdir.addData("0");

        data.add(commentlinesdir);

        Column execstmtstotal = new Column(true, false, 7);

        execstmtstotal.addData("Exec");
        execstmtstotal.addData("Stmts");
        execstmtstotal.addData("-------");
        execstmtstotal.addData("370");
        execstmtstotal.addData("423");
        execstmtstotal.addData("378");

        data.add(execstmtstotal);

        Column sourcelines = new Column(true, true, 7);

        sourcelines.addData("Source");
        sourcelines.addData("Lines");
        sourcelines.addData("-------");
        sourcelines.addData("6549");
        sourcelines.addData("3691");
        sourcelines.addData("3109");

        data.add(sourcelines);

        Column cswtcstmts = new Column(true, false, 5);

        cswtcstmts.addData("CSWTC");
        cswtcstmts.addData("Stmts");
        cswtcstmts.addData("----");
        cswtcstmts.addData("0");
        cswtcstmts.addData("0");
        cswtcstmts.addData("0");

        data.add(cswtcstmts);

        Column mxlv = new Column(true, true, 2);

        mxlv.addData("MX");
        mxlv.addData("LV");
        mxlv.addData("--");
        mxlv.addData("6");
        mxlv.addData("7");
        mxlv.addData("6");

        data.add(mxlv);

        Column delimtstmts = new Column(true, true, 6);

        delimtstmts.addData("Delimt");
        delimtstmts.addData("Stmts");
        delimtstmts.addData("------");
        delimtstmts.addData("1602");
        delimtstmts.addData("1127");
        delimtstmts.addData("979");

        data.add(delimtstmts);

        Report.sourceAnalysis.reportGeneration("", data);

        // LOOKS GREAT ADD MORE TESTING
    }
}

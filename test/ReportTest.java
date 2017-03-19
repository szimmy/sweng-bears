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
        nameColumn.addData("  Name");
        nameColumn.addData("---------");
        nameColumn.addData("MAI4E1");
        nameColumn.addData("MAL4E1");
        nameColumn.addData("MBI4E1");
        nameColumn.addData("MCF4E1");
        nameColumn.addData("MCSA4E1");
        nameColumn.addData("MCSC4E1");
        nameColumn.addData("MCSE4E0");
        nameColumn.addData("MCSE4E1");
        nameColumn.addData("MCW481");
        nameColumn.addData("MDBA4E1");

        data.add(nameColumn);

        Column typeColumn = new Column(true, true, 4);

        typeColumn.addData("");
        typeColumn.addData("Type");
        typeColumn.addData("----");
        typeColumn.addData("SYST");
        typeColumn.addData("SYST");
        typeColumn.addData("SYST");
        typeColumn.addData("SYST");
        typeColumn.addData("SYST");
        typeColumn.addData("SYST");
        typeColumn.addData("SYST");
        typeColumn.addData("SYST");
        typeColumn.addData("SYST");
        typeColumn.addData("SYST");

        data.add(typeColumn);

        Column execstsmt = new Column(true, false, 6);

        execstsmt.addData(" Exec");
        execstsmt.addData(" Stmts");
        execstsmt.addData("------");
        execstsmt.addData("369");
        execstsmt.addData("415");
        execstsmt.addData("375");
        execstsmt.addData("818");
        execstsmt.addData("4");
        execstsmt.addData("890");
        execstsmt.addData("8");
        execstsmt.addData("8");
        execstsmt.addData("969");
        execstsmt.addData("50");

        data.add(execstsmt);

        Column execlines = new Column(true, false, 6);

        execlines.addData("Exec");
        execlines.addData("Lines");
        execlines.addData("------");
        execlines.addData("906");
        execlines.addData("760");
        execlines.addData("632");
        execlines.addData("1261");
        execlines.addData("4");
        execlines.addData("1318");
        execlines.addData("11");
        execlines.addData("11");
        execlines.addData("1680");
        execlines.addData("54");

        data.add(execlines);

        Column datastmts = new Column(true, false, 6);

        datastmts.addData("Data");
        datastmts.addData("Stmts");
        datastmts.addData("------");
        datastmts.addData("659");
        datastmts.addData("290");
        datastmts.addData("246");
        datastmts.addData("300");
        datastmts.addData("3");
        datastmts.addData("229");
        datastmts.addData("16");
        datastmts.addData("16");
        datastmts.addData("240");
        datastmts.addData("6463");

        data.add(datastmts);

        Column datalines = new Column(true, false, 6);

        datalines.addData("Data");
        datalines.addData("Lines");
        datalines.addData("------");
        datalines.addData("815");
        datalines.addData("428");
        datalines.addData("247");
        datalines.addData("335");
        datalines.addData("4");
        datalines.addData("294");
        datalines.addData("16");
        datalines.addData("16");
        datalines.addData("244");
        datalines.addData("6949");

        data.add(datalines);

        Column commentstmts = new Column(true, true, 6);

        commentstmts.addData("Cmmnt");
        commentstmts.addData("Stmts");
        commentstmts.addData("------");
        commentstmts.addData("927");
        commentstmts.addData("436");
        commentstmts.addData("408");
        commentstmts.addData("665");
        commentstmts.addData("37");
        commentstmts.addData("404");
        commentstmts.addData("37");
        commentstmts.addData("37");
        commentstmts.addData("447");
        commentstmts.addData("7520");

        data.add(commentstmts);

        Column commentlines = new Column(true, true, 6);

        commentlines.addData("Cmmnt");
        commentlines.addData("Lines");
        commentlines.addData("------");
        commentlines.addData("4016");
        commentlines.addData("1984");
        commentlines.addData("1837");
        commentlines.addData("2747");
        commentlines.addData("215");
        commentlines.addData("2158");
        commentlines.addData("150");
        commentlines.addData("204");
        commentlines.addData("2000");
        commentlines.addData("37800");

        data.add(commentlines);

        Column noncmtlines = new Column(true, true, 6);

        noncmtlines.addData("NonCmt");
        noncmtlines.addData("Lines");
        noncmtlines.addData("------");
        noncmtlines.addData("2321");
        noncmtlines.addData("1699");
        noncmtlines.addData("1269");
        noncmtlines.addData("2482");
        noncmtlines.addData("72");
        noncmtlines.addData("2224");
        noncmtlines.addData("54");
        noncmtlines.addData("54");
        noncmtlines.addData("2790");
        noncmtlines.addData("7465");

        data.add(noncmtlines);

        Column otherstmts = new Column(true, true, 5);

        otherstmts.addData("Other");
        otherstmts.addData("Stmts");
        otherstmts.addData("-----");
        otherstmts.addData("374");
        otherstmts.addData("431");
        otherstmts.addData("360");
        otherstmts.addData("786");
        otherstmts.addData("62");
        otherstmts.addData("528");
        otherstmts.addData("27");
        otherstmts.addData("27");
        otherstmts.addData("838");
        otherstmts.addData("384");

        data.add(otherstmts);

        Column execstmtsdir = new Column(true, false, 6);

        execstmtsdir.addData(" Exec");
        execstmtsdir.addData(" Stmts");
        execstmtsdir.addData("------");
        execstmtsdir.addData("1");
        execstmtsdir.addData("8");
        execstmtsdir.addData("3");
        execstmtsdir.addData("1");
        execstmtsdir.addData("125");
        execstmtsdir.addData("129");
        execstmtsdir.addData("73");
        execstmtsdir.addData("73");
        execstmtsdir.addData("14");
        execstmtsdir.addData("0");

        data.add(execstmtsdir);

        Column datastmtsdir = new Column(true, false, 6);

        datastmtsdir.addData(" Data");
        datastmtsdir.addData(" Stmts");
        datastmtsdir.addData("------");
        datastmtsdir.addData("72");
        datastmtsdir.addData("0");
        datastmtsdir.addData("0");
        datastmtsdir.addData("0");
        datastmtsdir.addData("2");
        datastmtsdir.addData("19");
        datastmtsdir.addData("5");
        datastmtsdir.addData("5");
        datastmtsdir.addData("0");
        datastmtsdir.addData("721");

        data.add(datastmtsdir);

        Column commentlinesdir = new Column(true, false, 6);

        commentlinesdir.addData("Cmmnt");
        commentlinesdir.addData("Lines");
        commentlinesdir.addData("------");
        commentlinesdir.addData("139");
        commentlinesdir.addData("0");
        commentlinesdir.addData("0");
        commentlinesdir.addData("0");
        commentlinesdir.addData("5");
        commentlinesdir.addData("4");
        commentlinesdir.addData("7");
        commentlinesdir.addData("7");
        commentlinesdir.addData("0");
        commentlinesdir.addData("26");

        data.add(commentlinesdir);

        Column execstmtstotal = new Column(true, false, 7);

        execstmtstotal.addData(" Exec");
        execstmtstotal.addData(" Stmts");
        execstmtstotal.addData("-------");
        execstmtstotal.addData("370");
        execstmtstotal.addData("423");
        execstmtstotal.addData("378");
        execstmtstotal.addData("819");
        execstmtstotal.addData("129");
        execstmtstotal.addData("1019");
        execstmtstotal.addData("81");
        execstmtstotal.addData("81");
        execstmtstotal.addData("983");
        execstmtstotal.addData("50");

        data.add(execstmtstotal);

        Column sourcelines = new Column(true, true, 7);

        sourcelines.addData("Source");
        sourcelines.addData("Lines");
        sourcelines.addData("-------");
        sourcelines.addData("6549");
        sourcelines.addData("3691");
        sourcelines.addData("3109");
        sourcelines.addData("5230");
        sourcelines.addData("419");
        sourcelines.addData("4534");
        sourcelines.addData("289");
        sourcelines.addData("343");
        sourcelines.addData("4804");
        sourcelines.addData("46012");

        data.add(sourcelines);

        Column cswtcstmts = new Column(true, false, 5);

        cswtcstmts.addData("CSWTC");
        cswtcstmts.addData("Stmts");
        cswtcstmts.addData("----");
        cswtcstmts.addData("0");
        cswtcstmts.addData("0");
        cswtcstmts.addData("0");
        cswtcstmts.addData("0");
        cswtcstmts.addData("0");
        cswtcstmts.addData("0");
        cswtcstmts.addData("0");
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
        mxlv.addData("7");
        mxlv.addData("0");
        mxlv.addData("9");
        mxlv.addData("1");
        mxlv.addData("1");
        mxlv.addData("9");
        mxlv.addData("0");

        data.add(mxlv);

        Column delimtstmts = new Column(true, true, 6);

        delimtstmts.addData("Delimt");
        delimtstmts.addData("Stmts");
        delimtstmts.addData("------");
        delimtstmts.addData("1602");
        delimtstmts.addData("1127");
        delimtstmts.addData("979");
        delimtstmts.addData("1878");
        delimtstmts.addData("195");
        delimtstmts.addData("1790");
        delimtstmts.addData("126");
        delimtstmts.addData("126");
        delimtstmts.addData("2051");
        delimtstmts.addData("7589");

        data.add(delimtstmts);

        Report.sourceAnalysis.reportGeneration(Report.sourceAnalysis.getHeader(), data);
    }
}

package Reports;

import Scans.*;
import Report.Column;

import java.util.ArrayList;

/**
 * Created by Zim on 4/10/2017.
 */
public class SourceReview extends Report {
    // Title of the report to be used in the report.
    private final String TITLE = "Source Analysis Summary";

    /**
     * The constructor for the SourceAnalysis
     */
    public SourceReview() {
        this.totalLinesArrayPos = -1;

        this.scans = new ArrayList<Scan>();

        // The header String for the report
        header = Column.padLeft("Source Review Summary", 75);

        // add scans

    }

    /**
     * Generate the columns for the given Report.
     * @return The columns to be used for the report.
     */
    public ArrayList<Column> generateReportColumns() {
        ArrayList<Column> data = new ArrayList<Column>();

        Column nameColumn = new Column(false, false, 10);

        nameColumn.addData("");
        nameColumn.addData("   Name");
        nameColumn.addData("----------");

        data.add(nameColumn);

        Column typeColumn = new Column(false, false, 5);

        typeColumn.addData("");
        typeColumn.addData("Type");
        typeColumn.addData("---- ");

        data.add(typeColumn);

        Column nestdColumn = new Column(true, true, 5);

        nestdColumn.addData("Nestd");
        nestdColumn.addData("Incld");
        nestdColumn.addData("-----");

        data.add(nestdColumn);

        Column ds250 = new Column(true, false, 5);

        ds250.addData(" DS");
        ds250.addData(">250");
        ds250.addData("-----");

        data.add(ds250);

        Column goTo = new Column(true, true, 5);

        goTo.addData("GOTO");
        goTo.addData("Stmt");
        goTo.addData("-----");

        data.add(goTo);

        Column struc = new Column(true, true, 5);

        struc.addData("STRUC");
        struc.addData("Mssng");
        struc.addData("-----");

        data.add(struc);

        Column msplc = new Column(true, true, 5);

        msplc.addData("Msplc");
        msplc.addData("ENDSY");
        msplc.addData("-----");

        data.add(msplc);

        Column fileExt = new Column(true, true, 5);

        fileExt.addData("File");
        fileExt.addData("Extn");
        fileExt.addData("-----");

        data.add(fileExt);

        Column mult = new Column(true, true, 5);

        mult.addData("Mult");
        mult.addData("Comp");
        mult.addData("-----");

        data.add(mult);

        Column fileName = new Column(true, true, 5);

        fileName.addData("File");
        fileName.addData("Name");
        fileName.addData("-----");

        data.add(fileName);

        Column systltr = new Column(true, true, 5);

        systltr.addData("Systm");
        systltr.addData("Lettr");
        systltr.addData("-----");

        data.add(systltr);

        Column modMn = new Column(true, true, 5);

        modMn.addData("Modul");
        modMn.addData("Mnemn");
        modMn.addData("-----");

        data.add(modMn);

        Column nsPrime = new Column(true, true, 5);

        nsPrime.addData("N/S ");
        nsPrime.addData("Prime");
        nsPrime.addData("-----");

        data.add(nsPrime);

        Column cswtc = new Column(true, true, 5);

        cswtc.addData("CSWTC");
        cswtc.addData("Refnc");
        cswtc.addData("-----");

        data.add(cswtc);

        Column direct = new Column(true, true, 5);

        direct.addData("DIRCT");
        direct.addData("Refnc");
        direct.addData("-----");

        data.add(direct);

        Column abstr = new Column(true, true, 5);

        abstr.addData("ABSTR");
        abstr.addData("Mssng");
        abstr.addData("-----");

        data.add(abstr);

        return data;
    }
}

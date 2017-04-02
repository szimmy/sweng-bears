package Scans;

import CMS2Statements.Statement;

public class TotalDelimtStmtsScanner extends Scan {


    public TotalDelimtStmtsScanner() {
        KEYWORD = "Delim";
        count = 0;
    }

    /**
     * Scans for specific keywords which start a delimited statement
     * Uses getFirstToken(statement.getText()); to ensure that it does not count the
     * keywords in COMMENTs.
     *
     *
     * @param statement The statement to be scanned
     */
    public void scan(Statement statement) {
    }
}
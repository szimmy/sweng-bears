package Scans;

import CMS2Statements.Statement;

/**
 * A scanner which extends the LineScan class which uses LineScan's methods to scan and count the number of
 * delimiter statements in a CMKS2-Y code.
 *
 * @author Jeff
 */


public class DelimiterScanner extends Scan {

    // Array containing all Delimiter Statements keywords in the CMS2 language
    // The end of the Delimiter Statements keywords are commented out, assuming the do not need to be counted.
    // If they do, uncomment them back in.
    String[] delimiters = new String[]{
            "SYSTEM",// "END-SYSTEM",
            "SYS-DD",// "END-SYS-DD",
            "SYS-PROC",// "END-SYS-PROC",
            "LOC-DD",// "END-LOC-DD",
            "PROCEDURE",// "END-PROC",
            "EXEC-PROC",// "END-PROC",
            "FUNCTION",// "END-FUNCTION",
            "SYS-PROC-REN",// "END-SYS-PROC",
            "AUTO-DD",// "END-AUTO-DD",
            "HEAD"//, "END-HEAD"
    };

    public DelimiterScanner() {
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
        String text = getFirstToken(statement.getText());
        if (!statement.isDirectCode()) {
            for (String s : delimiters) {
                if (text.equals(s)) {
                    count++;
                    break;
                }
            }
        }
    }
}
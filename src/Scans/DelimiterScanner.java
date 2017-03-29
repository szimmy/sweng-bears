package Scans;

import CMS2Statements.Statement;

/**
 * A scanner which extends the LineScan class which uses LineScan's methods to scan and count the number of
 * delimiter statements in a CMKS2-Y code.
 *
 * @author Jeff
 */


public class DelimiterScanner extends LineScan {
    // Array containing all Delimiters in the CMS2 language
    // The ending delimiters are not included
    String[] delimiters = new String[] {
            "SYSTEM", //"END-SYSTEM",
            "SYS-DD", //"END-SYS-DD",
            "SYS-PROC", //"END-SYS-PROC",
            "LOC-DD",	//"END-LOC-DD",
            "PROCEDURE",//	"END-PROC",
            "EXEC-PROC",//	"END-PROC",
            "FUNCTION", //	"END-FUNCTION",
            "SYS-PROC-REN",	//"END-SYS-PROC",
            "AUTO-DD",  	//"END-AUTO-DD",
            "HEAD"//,	"END-HEAD"
    };

    public DelimiterScanner(){
        KEYWORD = "Delimiter";
        count = 0;
    }

    /**
     * Delimiters are used to start and end parts of a CMS2 code, therefore it will only be written in the start or
     * end of a statement; meaning there will only be one per line.
     * Uses .contains() for this reason
     *
     * @param statement The statement to be scanned
     */
    public void scan(Statement statement){
        String text=statement.getText();
        if(!statement.isDirectCode()){
            for (String s : delimiters)
            {
                if (text.contains(s))
                {
                    count++;
                    break;
                }
            }
        }
    }
}
package Scans;

import CMS2Statements.Statement;

/**
 * Created by Zim on 4/15/2017.
 */
public class IncorrectFileExtension extends Scan {
    public IncorrectFileExtension(){
        KEYWORD = "File Extn";
        count = 0;
    }

    public void scan(Statement statement){

    }
}

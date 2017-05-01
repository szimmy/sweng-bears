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

    /**
     * This scanner does not check statements, it checks the extension of a file once.
     * Therefore this is necessary and the scan function is not.
     */
    public void checkExtension(String extension){
        if(extension.equals("txt")){
            count++;
        }
    }
}

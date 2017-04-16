package Scans;


import CMS2Statements.Statement;
import Reports.Entry;

import java.util.ArrayList;

public class MaxStructureLevelScanner extends Scan{
    private int maxStructLevel;
    private int currentStructLevel;


    public MaxStructureLevelScanner(){
        super();
        maxStructLevel = 0;
        currentStructLevel = 0;
        KEYWORD = "MSL";
    }

    public void scan(Statement statement){
        int maxStructLevel = 0;
        int currentStructLevel = 0;
        String[] structIncreasers = new String[]{
                "SYSTEM", // "END-SYSTEM",
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
        String[] structDecreasers = new String[]{
                "END-SYSTEM",
                "END-SYS-DD",
                "END-SYS-PROC",
                "END-LOC-DD",
                "END-PROC",
                "END-FUNCTION",
                "END-SYS-PROC",
                "END-AUTO-DD",
                "END-HEAD"
                };
        for(String structIncreaser : structIncreasers)
        if(getFirstToken(statement.getText()).equalsIgnoreCase(structIncreaser)){
            updateStructLevel(currentStructLevel + 1);
        }
        else for(String structDecreaser : structDecreasers){
            if(getFirstToken(statement.getText()).equalsIgnoreCase(structIncreaser)) {
                updateStructLevel(currentStructLevel - 1);
            }
        }
    }

    public ArrayList<Entry> getData() {
        ArrayList<Entry> data = new ArrayList<Entry>();

        data.add(new Entry(KEYWORD, count));

        return data;
    }

    private void updateStructLevel(int newStructLevel){
        currentStructLevel = newStructLevel;
        if(currentStructLevel > maxStructLevel){
            maxStructLevel = currentStructLevel;
        }
    }


}

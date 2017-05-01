package Scans;

import CMS2Statements.Statement;

public class DirectDataScanner extends Scan {

    private String[] keyWords = new String[]{
            "OR", "MS", "XOR", "ALP", "NLP", "SLP", "SSUM", "SDIF", "DS", "RNLP",
            "DL", "LBMP", "LIM", "LA", "LXB", "LDIF", "LSUM", "LNA", "LM", "LB",
            "SB", "SA", "SXB", "SNA", "SM", "BZ", "BS", "RA", "RI", "RAN", "MD",
            "LBJ", "LCT", "LCI", "HLCT",
            "HLCI", "HM", "HD", "HRT", "HLB", "HSIM", "HSTC", "HPI", "HAI", "HALT",
            "HWFI", "SZ"
    };

    public DirectDataScanner() {
        KEYWORD = "Data";
        count = 0;
    }

    public void scan(Statement statement) {
        String text = statement.getText();
        if (statement.isDirectCode()) {
            for (String s : keyWords) {
                if (!getFirstToken(text).equals(".") && text.contains(" " + s + " ")) {
                    count++;
                    break;
                }
            }
        }
    }
}
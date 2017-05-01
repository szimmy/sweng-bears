package Scans;

import CMS2Statements.Statement;

public class DirectExecScanner extends Scan {

    private String[] keyWords = new String[]{
            "SC", "LLP", "LLPN", "CNT", "XR", "XRL", "ROR", "RSC", "RMS",
            "RXOR", "TSF", "DA", "DAN", "DC", "FA", "FAN", "FM", "FD",
            "FAR", "FANR", "FMR", "FDR", "XS", "IPI", "AEI", "PEI", "IO", "IR", "RP",
            "ANA", "AA", "AB", "ANB", "M", "D", "BC", "CXI", "C", "CL", "CM", "CG",
            "JEP", "JOP", "DJZ", "DJNZ", "JP", "JN", "JZ", "JNZ", "JBNZ", "JS", "JL",
            "JNF", "JOF", "JNE", "JE", "JG", "JGE", "JLT", "JLE", "JNW", "JW", "RJ",
            "JNC", "RJSC", "J", "JC", "JSC", "SCT", "SCI", "HSCT", "HSCI",  "HLC", "HDLC",
            "HRZ", "HDRZ", "HRS", "HDRS", "HSF", "HDSF", "HCP", "HDCP", "HOR", "HA", "HAN",
            "HXOR", "HAND", "HC", "HCL", "HCM", "HCB", "ZA", "ZB", "NOOP", "HNO"
    };

    public DirectExecScanner() {
        KEYWORD = "Exec";
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

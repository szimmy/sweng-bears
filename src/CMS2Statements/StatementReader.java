package CMS2Statements;

import CMS2Statements.Statement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jay on 3/3/2017.
 */
public class StatementReader {
    private File file;
    private ArrayList<Statement> statements;
    private int lineNum;

    /**
     *
     */
    public StatementReader(File file) {
        this.file = file;
        this.statements = new ArrayList<Statement>();
        this.lineNum = 0;
        build();
    }

    /**
     *
     */
    public void build() {
        // Boolean to keep track if the scan is currently in a Direct Code block
        boolean inDirectBlock = false;

        int stmtStartLine = -1;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String statementText = "";

            while ((line = br.readLine()) != null) {
                lineNum++;
                //Change tabs into spaces
                line = transformTabs(line);
                //Take the first ten characters off. They are not used.
                if (line.length() > 10) {
                    line = line.substring(10);
                } else {
                    line = "";
                }
                ArrayList<String> parts = divide(line, '$');
                for(String part : parts) {
                    if(stmtStartLine == -1){
                        stmtStartLine = lineNum;
                    }
                    statementText += part;
                    if (!inDirectBlock) {
                        if (statementText.contains("$")) {
                            statements.add(new Statement(statementText,
                                    stmtStartLine, lineNum, false));
                            if (getFirstToken(statementText).equals("DIRECT")) {
                                inDirectBlock = true;
                            }
                            //clear appropriate values
                            statementText = "";
                            stmtStartLine = -1;
                        }
                    } else { //current statement is in a direct code block
                        statementText = line;
                        if (getFirstToken(statementText).equals("CMS-2")) {
                            inDirectBlock = false;
                            statements.add(new Statement(statementText, stmtStartLine, lineNum, false));
                        } else {
                            statements.add(new Statement(statementText, stmtStartLine, lineNum, true));
                        }
                        statementText = "";
                        stmtStartLine = -1;
                    }
                }
                //statement = "";
                //System.out.println("TEST: " + line + " trimmed to " + afterDelim(line, '$'));
            }
        }
        catch(IOException ie) {

        }
    }

    public int numStmts(){
        return statements.size();
    }

    public int numLines(){
        return lineNum;
    }

    public Statement getStatement(int index){
        return statements.get(index);
    }

    public ArrayList<Statement> getStatements(){
        return statements;
    }

    /**
     * Returns the given string with everything after the first instance of the delimiter removed.
     */
    private String trimDelim(String line, char delimiter){
        int delimiterIndex = line.indexOf(delimiter);
        if(delimiterIndex == -1) {
            return line;
        }
        return line.substring(0, delimiterIndex + 1);
    }
    //If it is possible that the first token isn't followed by a space, needs to be changed.
    private String getFirstToken(String statement){
        if(statement.trim().indexOf(" ") != -1){
            return statement.trim().substring(0, statement.trim().indexOf(" ")).trim();
        }
        return statement.trim();
    }

    /**
     * Returns the original string with tabs turned into spaces
     */
    private String transformTabs(String string){
        String[] segments = string.split("\t");
        String result = "";
        for(String seg : segments){
            result = result.concat(seg + "    ");
        }
        if(result.length() >= 4) {
            result = result.substring(0, result.length() - 4);
            return result;
        }
        return string; //check.
    }

    private String afterDelim(String string, char delimiter){
        if(string.indexOf(delimiter) > -1){
            return string.substring(string.indexOf(delimiter) + 1);
        }
        return string;
    }

    private ArrayList<String> divide(String string, char delimiter){
        ArrayList<String> result = new ArrayList<String>();
        while(string.indexOf(delimiter) > -1){
            result.add(trimDelim(string, delimiter));
            string = afterDelim(string, delimiter);
        }
        result.add(string);
        return result;
    }
}

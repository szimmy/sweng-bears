import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Zim on 2/22/2017.
 */
public class FileScanner {

    private File file;
    private ReportContent scan;
    private HashMap<String, Integer> data;

    public FileScanner(File file, ReportContent scan) {
        this.file = file;
        this.scan = scan;
        this.data = new HashMap<>();
    }

    public HashMap<String, Integer> run() {
        int numLines = 0;
        int numCommentStmts = 0;
        int numCommentLines = 0;
        int numDirCommentsStmts = 0;
        //This will need to be split into two in a later sprint.
        int numDirOther = 0;
        int numCMSOtherStmts = 0;
        int numCMSOtherLines = 0;

        boolean inDirectBlock = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String statement = "";

            boolean stmtBegun = false;
            int stmtBeginningLine = 0;

            while ((line = br.readLine()) != null) {
                numLines++;
                //Change tabs into spaces
                line = transformTabs(line);
                //Take the first ten characters off. They are not used.
                if(line.length() > 10){
                    line = line.substring(10);
                    if(stmtBegun == false){ //May incorrectly handle labels on blank lines proceeding statements.
                        stmtBeginningLine = numLines;
                        stmtBegun = true;
                    }
                }
                else{
                    line = "";

                }
                //TODO Pad the back with spaces. (?)
                if(! inDirectBlock) {
                    statement = statement.concat(trimDelim(line, '$'));
                    if (statement.contains("$")) {  //TODO Change this to check if it ends with a dollar sign. (?)
                        //Perform analysis of CMS-2Y statements here. (Not Direct Code, that is below.)
                        if (getFirstToken(statement).equals("COMMENT")) {
                            numCommentStmts++;
                            numCommentLines += (numLines - stmtBeginningLine) + 1;
                        }
                        else if(getFirstToken(statement).equals("DIRECT") ||
                                getFirstToken(statement).equals("DIRECT$")){ //I don't know if this would be legal
                            numCMSOtherStmts++; //Test this line and the next
                            numCMSOtherLines += (numLines - stmtBeginningLine) + 1;
                            inDirectBlock = true;
                        }else{
                            numCMSOtherStmts++; //Test this line and the next
                            numCMSOtherLines += (numLines - stmtBeginningLine) + 1;
                        }
                        //clear appropriate values
                        statement = "";
                        stmtBeginningLine = 0; //This seems like a dangerous default value, but should work.
                        stmtBegun = false;
                    }
                }
                else{ //current statement is in a direct code block
                    statement = line;
                    //Perform analysis of Direct Code statements here.
                    if(getFirstToken(statement).equals(".")){
                        numDirCommentsStmts++;
                    }
                    else if(getFirstToken(statement).equals("CMS-2") ||
                            getFirstToken(statement).equals("CMS-2$")){
                        //I'm counting the direct code block ending-delimiter as CMS-2, not direct code.
                        numCMSOtherStmts++;
                        numCMSOtherLines += numCMSOtherLines += (numLines - stmtBeginningLine) + 1;
                        inDirectBlock = false;
                    }
                    else{
                        numDirOther++;
                    }
                    statement = "";
                }
            }
        } catch(IOException ie) {
        }

        data.put("Lines", numLines);
        data.put("Comment Statements", numCommentStmts);
        data.put("Comment Lines", numCommentLines);
        data.put("CMS-2Y Other Statements", numCMSOtherStmts);
        //data.put("CMS-2Y Other Lines", numCMSOtherLines);
        data.put("Direct Code Comments", numDirCommentsStmts);
        data.put("Direct Code Other", numDirOther);

        return data;
    }

    /**
     * Returns the given string with everything after the first instance of the delimiter removed.
     */
    private String trimDelim(String line, char delimiter){ //Change the name of this, but not to trim
        int delimiterIndex = line.indexOf(delimiter);
        if(delimiterIndex == -1) {
            return line;
        }
        return line.substring(0, delimiterIndex + 1);
    }
    //If it is possible that the first token isn't followed by a space, needs to be changed.
    private String getFirstToken(String statement){
            return statement.trim().substring(0, statement.trim().indexOf(" ")).trim();
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
        result = result.substring(0, result.length() - 4);
        return result;
    }
}

package CMS2Statements;

/**
 * Created by Jay on 3/11/2017.
 */
public class Statement {
    String text;
    int beginningLine;
    int endingLine;
    boolean directCode;

    public Statement(){
        text = "";
        beginningLine = 0;
        endingLine = 0;
        directCode = false;
    }

    public Statement(String text, int beginningLine, int endingLine, boolean directCode){
        this.text = text;
        this.beginningLine = beginningLine;
        this.endingLine = endingLine;
        this.directCode = directCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getBeginningLine() {
        return beginningLine;
    }

    public void setBeginningLine(int beginningLine) {
        this.beginningLine = beginningLine;
    }

    public int getEndingLine() {
        return endingLine;
    }

    public void setEndingLine(int endingLine) {
        this.endingLine = endingLine;
    }

    public boolean isDirectCode() {
        return directCode;
    }

    public void setDirectCode(boolean directCode) {
        this.directCode = directCode;
    }

    public int getNumLines(){
        return endingLine - beginningLine + 1;
    }
}

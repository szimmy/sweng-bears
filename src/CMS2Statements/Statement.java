package CMS2Statements;

/**
 * Created by Jay on 3/11/2017.
 */
public class Statement {
    private String text;
    private int beginningLine;
    private int endingLine;
    private boolean directCode;
    private String label;

    /**
     *
     * @param text              The text of a statement
     * @param beginningLine     The line the statement begins on
     * @param endingLine        The line the statement end on
     * @param directCode        Whether this statement is in a direct code block
     * @param label             This lines label. If there is none, its is an empty string
     */
    public Statement(String text, int beginningLine, int endingLine, boolean directCode, String label){
        this.text = text;
        this.beginningLine = beginningLine;
        this.endingLine = endingLine;
        this.directCode = directCode;
        this.label = label;
    }

    public String getText() {
        return text;
    }

    public int getBeginningLine() {
        return beginningLine;
    }

    public int getEndingLine() {
        return endingLine;
    }

    public boolean isDirectCode() {
        return directCode;
    }

    public int getNumLines(){
        return endingLine - beginningLine + 1;
    }
}

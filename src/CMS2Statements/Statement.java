package CMS2Statements;

/**
 * Represents a statement of code.
 *
 * Created by Jay on 3/11/2017.
 */
public class Statement {
    String text;
    int beginningLine;
    int endingLine;
    boolean directCode;

    /**
     * Constructor for the Statement
     * @param text              The text of a statement
     * @param beginningLine     The line the statement begins on
     * @param endingLine        The line the statement end on
     * @param directCode        Whether this statement is in a direct code block
     */
    public Statement(String text, int beginningLine, int endingLine, boolean directCode){
        this.text = text;
        this.beginningLine = beginningLine;
        this.endingLine = endingLine;
        this.directCode = directCode;
    }

    /**
     * Accessor for text
     * @return the statement's text
     */
    public String getText() {
        return text;
    }

    /**
     * Accessor for beginningLine
     * @return the location of the line the statement starts on
     */
    public int getBeginningLine() {
        return beginningLine;
    }

    /**
     * Accessor for endinggLine
     * @return the location of the line the statement ends on
     */
    public int getEndingLine() {
        return endingLine;
    }

    /**
     * Accessor for directCode
     * @return whether or not the statement is Direct Code
     */
    public boolean isDirectCode() {
        return directCode;
    }

    /**
     * Accessor for getNumLines
     * @return the number of lines the statement occupies
     */
    public int getNumLines(){
        return endingLine - beginningLine + 1;
    }
}

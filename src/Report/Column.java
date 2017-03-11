package Report;

import java.util.ArrayList;

/**
 * Represents a column to be used in a table like format to generate a well formatted reporot.
 *
 * @author Sean Zimmerman
 */
public class Column {
    // The number of spaces used between columns when printing the report
    public static final int SPACES_BETWEEN_COLUMNS = 1;

    // The data of the column
    private ArrayList<String> data;
    private boolean rightJustify;
    private int width;

    // For printf the format is "%-xs" for left justify and "%xs" for right justify
    // x is the number of characters in the column. Spaces will be used for padding

    public Column(boolean rightJustify, int width) {
        data = new ArrayList<String>();

        this.rightJustify = rightJustify;
        this.width = width;
    }

    /**
     * Adds a row of data to the column
     * @param cell The data to be added
     */
    public void addData(String cell) {
        this.data.add(cell);
    }

    /**
     * Returns the value of the specified row of this column in correct formatting for the table.
     * @param num The row number wanted
     * @return The correctly formatted value in row num
     */
    public String getFormattedRowNum(int num) {
        if(rightJustify) {
            return padLeft(data.get(num), width);
        } else {
            return padRight(data.get(num), width);
        }
    }

    /**
     * @return The total of all the integers in the column
     */
    public int totalNum() {
        return 0; // TODO but cba since it isn't currently important
    }

    public int getWidth() {
        return this.width;
    }

    public ArrayList<String> getData() {
        return this.data;
    }

    /**
     * Pad with " " to the right to the given length (n), used for left justify
     */
    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    /**
     * Pad with " " to the left to the given length (n), used for right justify
     */
    public static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }
}

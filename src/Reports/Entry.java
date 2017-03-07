package Reports;

/**
 * An Entry is a node which contains a key-value pairing.
 * This is used in replacement of a HashMap, so we can use an ArrayList<Entry>
 * and keep the same structure of the HashMap but while maintaining insertion order.
 *
 * @author Sean Zimmerman
 */
public class Entry {
    private String key;

    private int value;

    public Entry(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public int getValue() {
        return this.value;
    }
}

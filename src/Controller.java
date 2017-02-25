import java.io.File;
/**
 * Contains the main method of the program. Takes in file(s) and runs selected reports on them.
 *
 * @author Sean Zimmerman
 */

public class Controller {

    public static void main(String args[]) {
        //Hardcoded test REMOVE
        FileScanner fileScanner = new FileScanner(new File("C:/Users/Jay/Documents/SampleCMS2/TEST7.txt"),
                new ReportContent());
        System.out.println(fileScanner.run());
    }
}

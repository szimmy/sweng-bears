import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Contains the main method of the program. Takes in file(s) and runs selected reports on them.
 *
 * @author Sean Zimmerman
 */
public class Controller {

    private final static JFileChooser chooser = new JFileChooser();

    public static void main(String args[]) {
        chooseFile();
    }

    private static void chooseFile() {
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "CMS-2Y", "cts", "ct2");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(new JFrame());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }

        String extension = getExtension(returnVal);

        System.out.println(validExtension(extension));
    }

    private static String getExtension(int returnVal) {
        String extension = "";

        int i = chooser.getSelectedFile().getAbsolutePath().lastIndexOf('.');
        int p = Math.max(chooser.getSelectedFile().getAbsolutePath().lastIndexOf('/'),
                chooser.getSelectedFile().getAbsolutePath().lastIndexOf('\\'));

        if (i > 0 && i > p) {
            extension = chooser.getSelectedFile().getAbsolutePath().substring(i+1);
        }

        return extension;
    }

    private static boolean validExtension(String ext) {
        return !ext.equals("cts") && !ext.equals("ct2");
    }
}

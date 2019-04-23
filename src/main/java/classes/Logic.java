package classes;

import org.kohsuke.args4j.*;
import java.io.File;
import java.util.*;

public class Logic {

    public List<File> getFiles() {
        return files;
    }

    @Argument
    private List<File> files = new ArrayList<File>();

    public File getOut() {
        return out;
    }

    @Option(name="-out")
    private File out = new File("output.txt");
}

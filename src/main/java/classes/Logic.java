package classes;

import org.kohsuke.args4j.*;

import java.io.File;
import java.util.*;

public class Logic {

    public List<File> getFiles() {
        return files;
    }

    @Argument
    private List<File> files = new ArrayList<>();

    @Option(name="-out")
    private File globalFile = new File("Global File");




}

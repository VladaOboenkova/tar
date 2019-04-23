package classes;

import org.kohsuke.args4j.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

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

    //private boolean addSeparator(List<File> files){

    //}

    private void showFile(File file) throws FileNotFoundException {   // вывод содержимого файла
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine()){
            String line = scanner.nextLine();
            System.out.println(line);
        }
    }
}

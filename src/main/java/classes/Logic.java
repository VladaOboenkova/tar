
package classes;

import org.kohsuke.args4j.*;

import java.io.*;
import java.util.*;

class Logic {

    @Argument
    private List<File> files = new ArrayList<File>();

    List<File> getFiles() {
        return files;
    }

    @Option(name = "-out")
    private File out = new File("C:/Users/Влада/IdeaProjects/tar/src/main/java/Texts/output.txt");

    private File getOut() {
        return out;
    }

    @Option(name = "-show")
    private File fileToShow = new File("fileToShow.txt");

    File getFileToShow() {
        return fileToShow;
    }

    @Option(name = "-u")
    private File fileName = new File("C:/Users/Влада/IdeaProjects/tar/src/main/java/Texts/output.txt");

    File getFileName() {
        return fileName;
    }

    void showFile(File fileToShow) throws IOException {// вывод содержимого файла
        FileReader fileReader = new FileReader(fileToShow);
        char[] textToShow = new char[(int) fileToShow.length()];
        while (fileReader.ready()) {
            fileReader.read(textToShow);
            String stringText = new String(textToShow);
            System.out.println(stringText);
        }
        fileReader.close();
    }

    void out(List<File> files) throws IOException {
        for (File file : files) {
            FileReader fileReader = new FileReader(file);
            FileWriter fileWriter = new FileWriter(getOut(), true);
            char[] text = new char[(int) file.length()];
            while (fileReader.ready()) {
                fileReader.read(text);
                String stringText = new String(text);
                stringText += "ᅠ";
                stringText += "\n";
                fileWriter.write(stringText);
            }
            fileWriter.flush();
            fileWriter.close();
            fileReader.close();
        }
    }

    List<File> u(File fileName) throws IOException {
        List<File> outTexts = new ArrayList<File>();
        String[] texts = null;
        if (fileName.length() != 0) {
            FileReader fileReader = new FileReader(fileName);
            char[] text = new char[(int) fileName.length()];
            while (fileReader.ready()) {
                fileReader.read(text);
                String stringText = new String(text);
                texts = stringText.split("ᅠ");
            }
            fileReader.close();
            System.out.println(Arrays.toString(texts));
            if (texts != null) {
                for (int i = 0; i < texts.length; i++) {
                    File file = new File("C:/Users/Влада/IdeaProjects/tar/src/main/java/Texts/text" + (i + 1) + ".txt");
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(texts[i]);
                    outTexts.add(file);
                    fileWriter.close();
                }
            }
        }return outTexts;
    }
}


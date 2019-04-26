
package classes;

import org.kohsuke.args4j.*;

import java.io.*;
import java.util.*;

class Logic {

    @Argument
    private List<File> files = new ArrayList<File>();

    public List<File> getFiles() {
        return files;
    }

    @Option(name = "-out")
    private File out = new File("output.txt");

    public File getOut() {
        return out;
    }

    @Option(name = "-show")
    private File fileToShow = new File("fileToShow.txt");

    public File getFileToShow() {
        return fileToShow;
    }

    @Option(name = "-u")
    private File fileName = new File("output.txt");

    public File getFileName() {
        return fileName;
    }

    /**
     * Приводит файлы к типу "длина имени -> имя -> длина файла в символах -> содержимое файла
     */
    public List<File> fileRedactor(List<File> files) throws IOException {
        List<File> newFiles = new ArrayList<File>();
        for (File file : files){
            File newFile = new File(file.getName());
            FileWriter fileWriter = new FileWriter(newFile);
            FileReader fileReader = new FileReader(file);
            String name = file.getName();
            String nameSize = name.length() + "";
            String size = (int) file.length() + "";
            char[] origText = new char[(int) file.length()];
            while (fileReader.ready()){
                fileReader.read(origText);
                String origTextString = new String(origText);
                fileWriter.write(nameSize);
                fileWriter.write(name);
                fileWriter.write(size);
                fileWriter.write(origTextString);
            }
            fileReader.close();
            fileWriter.flush();
            fileWriter.close();
            newFiles.add(newFile);
        }
        return newFiles;
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
                stringText += "\n";               //System.pathSeparator
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


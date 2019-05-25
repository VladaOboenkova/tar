package classes;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Logic {

    @Argument
    private List<File> files = new ArrayList<>();

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<File> getFiles() {
        return files;
    }

    @Option(name = "-out", forbids = "-split")
    private File out;

    public File getOut() {
        return out;
    }

    public void setOut(File out) {
        this.out = out;
    }

    @Option(name = "-show")
    private File fileToShow = new File("fileToShow.txt"); //boolean

    public File getFileToShow() {
        return fileToShow;
    }

    @Option(name = "-split", forbids = "-out")
    private File fileName;

    public File getFileName() {
        return fileName;
    }

    public void setFileName(File fileName) {
        this.fileName = fileName;
    }

    public List<String> load(List<File> files) throws IOException {
        List<String> texts = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            FileReader fileReader = new FileReader(file);
            String lineSeparator = System.getProperty("line.separator");
            String name = file.getName() + lineSeparator;
            char[] origText = new char[(int) file.length()];
            fileReader.read(origText);
            String textToAdd;
            String origTextString = new String(origText);
            textToAdd = name + origTextString;
            if (i != files.size() - 1){
                textToAdd += "!end!" ;
            }
            texts.add(textToAdd);
        }
        return texts;
    }

    /**
     * Показывает содержимое файла.
     */
    public void showFile(File fileToShow) throws IOException {
        FileReader fileReader = new FileReader(fileToShow);
        char[] textToShow = new char[(int) fileToShow.length()];
        while (fileReader.ready()) {
            fileReader.read(textToShow);
            String stringText = new String(textToShow);
            System.out.println(stringText);
        }
        fileReader.close();
    }

    public File out(List<String> files, File textFile) throws IOException {
        for (String file : files) {
            FileWriter fileWriter = new FileWriter(textFile, true);
                fileWriter.write(file);
            fileWriter.flush();
            fileWriter.close();
            }
            return textFile;
        }

     public List<File> split(File fileName) throws IOException {
         List<File> outTexts = new ArrayList<>();
         String[] texts = null;
         if (fileName.length() != 0) {
             FileReader fileReader = new FileReader(fileName);
             char[] text = new char[(int) fileName.length()];
             while (fileReader.ready()) {
                 fileReader.read(text);
                 String stringText = new String(text);
                 texts = stringText.split("!end!");
             }
             fileReader.close();
             System.out.println(Arrays.toString(texts));
             if (texts != null) {
                 for (String str : texts) {
                     List<String> lines = new ArrayList<>();
                     try (Scanner s = new Scanner(str)) {
                         while (s.hasNextLine()) {
                             lines.add(s.nextLine());
                             System.out.println(lines);
                         }
                     }
                     String name = lines.get(0);
                     File file = new File(name);
                     FileWriter fileWriter = new FileWriter(file, true);
                     fileWriter.write(str);
                     fileWriter.flush();
                     fileWriter.close();
                     outTexts.add(file);
                 }
             }
         }
         return outTexts;
     }
}


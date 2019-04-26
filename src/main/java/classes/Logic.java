package classes;

import org.kohsuke.args4j.*;

import java.io.*;
import java.util.*;

import static com.sun.javafx.binding.StringFormatter.concat;

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
     * Приводит файлы к типу "длина имени -> имя -> длина файла в символах -> содержимое файла.
     */
    public List<File> fileRedactor(List<File> files) throws IOException {
        List<File> newFilesBefore = new ArrayList<File>();
        List<File> newFilesAfter = new ArrayList<File>();
        files.remove(files.size() - 1);
        for (File file : files){
            File newFile = new File(file.getName());
            FileReader fileReader = new FileReader(file);
            FileWriter fileWriter = new FileWriter(newFile);
            char[] text = new char[(int) file.length()];
            while (fileReader.ready()) {
                fileReader.read(text);
                String s = new String(text);
                String str = s + "!end!";
                fileWriter.write(str);
            }
            fileWriter.flush();
            fileWriter.close();
            fileReader.close();
            newFilesBefore.add(newFile);
        }
        newFilesBefore.add(getFiles().get(getFiles().size() - 1));
        System.out.println(newFilesBefore);
        for (File file : newFilesBefore){
            File newFile = new File(file.getName());
            FileWriter fileWriter = new FileWriter(newFile);
            FileReader fileReader = new FileReader(file);
            String lineSeparator = System.getProperty("line.separator");
            String name = file.getName() + lineSeparator;
            char[] origText = new char[(int) file.length()];
            while (fileReader.ready()){
                fileReader.read(origText);
                String origTextString = new String(origText);
                fileWriter.write(name);
                fileWriter.write(origTextString);
            }
            fileReader.close();
            fileWriter.flush();
            fileWriter.close();
            newFilesAfter.add(newFile);
        }
        return newFilesAfter;
    }

    /**
     * Показывает содержимое файла.
     */
    void showFile(File fileToShow) throws IOException {
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
                String lineSeparator = System.getProperty("line.separator");
                stringText += lineSeparator;
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
                texts = stringText.split("!end!");
            }
            fileReader.close();
            System.out.println(Arrays.toString(texts));
            if (texts != null) {
                for (String str :texts){
                    Scanner scanner = new Scanner(str);
                    String lineSeparator = System.getProperty("line.separator");
                    scanner.useDelimiter(lineSeparator);
                    String nameOfFile = scanner.nextLine();
                    String stringText = str.substring(str.lastIndexOf(nameOfFile) + 1);
                    System.out.println(stringText);
                }
                /*for (int i = 0; i < texts.length; i++) {
                    File file = new File("C:/Users/Влада/IdeaProjects/tar/src/main/java/Texts/text" + (i + 1) + ".txt");
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(texts[i]);
                    outTexts.add(file);
                    fileWriter.close();
                }*/
            }
        }return outTexts;
    }
}


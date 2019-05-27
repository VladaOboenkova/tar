package classes;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import java.io.*;
import java.util.*;

class Logic {

    @Argument
    private List<File> files = new ArrayList<>();

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Option(name = "-out", forbids = "-split")
    private File out;

    public void setOut(File out) {
        this.out = out;
    }

    @Option(name = "-u", forbids = "-out")
    private File fileName;

    public void setFileName(File fileName) {
        this.fileName = fileName;
    }

    public List<String> load() throws IOException {
        List<String> texts = new ArrayList<>();
        for (int i = 0; i < this.files.size(); i++) {
            File file = this.files.get(i);
            FileReader fileReader = new FileReader(file);
            String lineSeparator = System.getProperty("line.separator");
            String name = file.getName() + lineSeparator;
            char[] origText = new char[(int) file.length()];
            fileReader.read(origText);
            String textToAdd;
            String origTextString = new String(origText);
            textToAdd = name + origTextString;
            if (i != this.files.size() - 1) {
                textToAdd += "!end!";
            }
            texts.add(textToAdd);
        }
        return texts;
    }

    public File out(List<String> files) throws IOException {
        FileWriter fileWriter = new FileWriter(this.out);
        for (String file : files) {
            fileWriter.write(file);
        }
        fileWriter.flush();
        fileWriter.close();
        return this.out;
    }

    public List<File> split() throws IOException {
        List<File> outTexts = new ArrayList<>();
        String[] texts = null;
        if (this.fileName.length() != 0) {
            FileReader fileReader = new FileReader(this.fileName);
            char[] text = new char[(int) this.fileName.length()];
            while (fileReader.ready()) {
                fileReader.read(text);
                String stringText = new String(text);
                texts = stringText.split("!end!");
            }
            fileReader.close();
            if (texts != null) {
                for (String str : texts) {
                    List<String> lines = new ArrayList<>();
                    try (Scanner s = new Scanner(str)) {
                        while (s.hasNextLine()) {
                            lines.add(s.nextLine());
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



package classes;

import org.apache.commons.io.*;
import org.junit.*;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class LogicTest {

    private static final String END_OF_FILE = "!end!";

    @Test
    public void load() throws IOException {
        Logic logic = new Logic();
        File firstFile = new File("Texts/text1.txt");
        File secondFile = new File("Texts/text2.txt");

        List<File> files = new ArrayList<>();
        files.add(firstFile);
        files.add(secondFile);
        logic.setFiles(files);
        List<String> result = logic.load();

        List<String> expected = new ArrayList<>();

        File firstTestFile = new File("input (Test)/text1.txt");
        File secondTestFile = new File("input (Test)/text2.txt");
        List<File> testFiles = new ArrayList<>();
        testFiles.add(firstTestFile);
        testFiles.add(secondTestFile);

        for (int i = 0; i < testFiles.size(); i++){
            FileReader fileReader = new FileReader(testFiles.get(i));
            char[] charText = new char[(int) testFiles.get(i).length()];
            fileReader.read(charText);
            String stringText = new String(charText);
            stringText.trim();
            if (i != testFiles.size() - 1){
                stringText += END_OF_FILE;
            }
            expected.add(stringText);
            fileReader.close();
        }
        Assert.assertEquals(expected, result);
    }

    @Test
    public void out() throws IOException {
        Logic logic = new Logic();

        File expectedFile = new File("input (Test)/output.txt");
        String expected = FileUtils.readFileToString(expectedFile, "UTF-8");

        List<String> strings = new ArrayList<>();
        File firstTestFile = new File("input (Test)/text1.txt");
        File secondTestFile = new File("input (Test)/text2.txt");
        List<File> testFiles = new ArrayList<>();
        testFiles.add(firstTestFile);
        testFiles.add(secondTestFile);

        for (int i = 0; i < testFiles.size(); i++){
            File file = testFiles.get(i);
            String stringText = FileUtils.readFileToString(file);
            stringText.trim();
            if (i != testFiles.size() - 1){
                stringText += END_OF_FILE;
            }
            strings.add(stringText.trim());
        }

        File resultFile = new File("test.txt");
        logic.setOut(resultFile);
        logic.out(strings);

        String result = FileUtils.readFileToString(resultFile, "UTF-8");
        Assert.assertEquals(expected.trim(), result.trim());
        resultFile.delete();
    }

    @Test
    public void split() throws IOException {
        Logic logic = new Logic();

        File firstTestFile = new File("input (Test)/text1.txt");
        File secondTestFile = new File("input (Test)/text2.txt");

        List<File> testFiles = new ArrayList<>();
        testFiles.add(firstTestFile);
        testFiles.add(secondTestFile);
        List<String> expected = new ArrayList<>();

        for (File testFile : testFiles){
            String stringTextFile = FileUtils.readFileToString(testFile);
            expected.add(stringTextFile.trim());
        }

        File test = new File("input (Test)/output.txt");
        logic.setFileName(test);

        List<File> resultFiles = logic.split();
        List<String> result = new ArrayList<>();

        for (File file : resultFiles){
            String stringFile = FileUtils.readFileToString(file);
            result.add(stringFile.trim());
        }

        Assert.assertEquals(expected, result);
        for (File file : resultFiles){
            file.delete();
        }
    }
}
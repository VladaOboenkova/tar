package classes;

import org.kohsuke.args4j.*;

import java.util.Arrays;


public class tar {

    public static void main(String[] args) throws Exception {

        Logic logic = new Logic();
        CmdLineParser parser = new CmdLineParser(logic);

        try {
            parser.parseArgument(args);
                if (Arrays.asList(args).contains("-show")) {
                    logic.showFile(logic.getFileToShow());
                }
                if (Arrays.asList(args).contains("-out")) {
                    logic.out(logic.load(/*logic.getFiles()*/), logic.getOut());
                }
                if (Arrays.asList(args).contains("-u")) {
                    logic.split(logic.getFileName());
                }
        } catch (CmdLineException e) {
            System.err.println("e = " + e.toString());
            parser.printUsage(System.out);
        }
        }
    }



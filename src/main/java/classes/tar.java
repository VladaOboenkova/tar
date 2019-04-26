package classes;

import org.kohsuke.args4j.*;

import java.io.File;
import java.util.List;

public class tar {

    public static void main(String[] args) throws Exception {

        Logic logic = new Logic();
        CmdLineParser parser = new CmdLineParser(logic);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println("e = " + e.toString());
            parser.printUsage(System.out);
        }

        for (String arg : args) {
            if (arg.equals("-show")) {
                logic.showFile(logic.getFileToShow());
            }
            if (arg.equals("-out")){
                logic.out(logic.fileRedactor(logic.getFiles()));
            }
            if (arg.equals("-u")){
                logic.u(logic.getFileName());
            }
        }
    }
}


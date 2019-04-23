package classes;

import java.util.*;
import java.io.*;
import org.kohsuke.args4j.*;

public class tar {

    public static void main(String[] args) throws Exception {

        Logic arguments = new Logic();
        CmdLineParser parser = new CmdLineParser(arguments);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println("e = " + e.toString());
            parser.printUsage(System.out);
        }
        System.out.println(arguments.getFiles());
        System.out.println(arguments.getOut());
    }
}


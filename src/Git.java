import CommandObjects.*;
import KeyValueObjects.*;
import org.apache.commons.cli.*;
import java.util.*;

public class Git {

    public static void main(String[] args) {
        try {
            switch(args[0]) {
                case "add":
                    add(args[1]);
                case "branch":
                    branch(args[1]);
                case "checkout":
                    checkout(args[1]);
                case "commit":
                    String[] newArgs1 = Arrays.copyOfRange(args, 1, args.length);
                    commit(newArgs1);
                case "init":
                    init();
                case "log":
                    log();
                case "reset":
                    String[] newArgs2 = Arrays.copyOfRange(args, 1, args.length);
                    commit(newArgs2);
                default:
                    throw new IllegalArgumentException("Illeagal Command");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void add(String fileName) {
        try {
            new add().add(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void branch(String branchName) {
        try {
            branch b = new branch();
            if (branchName == null)
                b.branch();
            else
                b.branch(branchName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkout(String branchName) {
        try {
            new checkout().checkout(branchName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void commit(String[] args) {
        try {
            Options options = new Options();
            CommandLineParser parser = new DefaultParser();
            options.addOption(new Option("m", true, "description for command"));
            options.addOption(new Option("a", true, "author"));
            options.addOption(new Option("c", true, "committer"));
            CommandLine cmd = parser.parse(options, args);
            String comment = cmd.getOptionValue("m");
            String author = cmd.getOptionValue("a");
            String committer = cmd.getOptionValue("c");
            new commit().commit(author, committer, comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        new init().init();
    }

    public static void log() {
        new log().log();
    }

    public static void reset(String[] args) throws Exception {
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();
        options.addOption(new Option("s", false, "soft"));
        options.addOption(new Option("m", false, "mixed"));
        options.addOption(new Option("h", false, "hard"));
        CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption("s")) {
            new reset().resetSoft();
        } else if (cmd.hasOption("h")) {
            new reset().resetHard();
        } else {
            new reset().resetMixed();
        }
    }
}
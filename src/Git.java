import CommandObjects.*;
import org.apache.commons.cli.*;
import java.util.*;

public class Git {

    public static void main(String[] args) {
        try {
            String cmd = args[0];
            if(cmd.equals("add")){
                add(args[1]);
            }
            else if(cmd.equals("branch")){
                if(args.length==1) {
                    branch(null);
                    return;
                }
                branch(args[1]);
            }
            else if(cmd.equals("checkout")){
                checkout(args[1]);
            }
            else if(cmd.equals("commit")) {
                String[] newArgs1 = Arrays.copyOfRange(args, 1, args.length);
                commit(newArgs1);
            }
            else if(cmd.equals("init")){
                init();
            }
            else if(cmd.equals("log")){
                log();
            }
            else if(cmd.equals("reset")){
                String[] newArgs2 = Arrays.copyOfRange(args, 1, args.length);
                reset(newArgs2);
            }
            else if(cmd.equals("diff")){
                diff();
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

    public static void diff() throws Exception{
        new diff().diff();
    }
}
package CommandObjects;

import KeyValueObjects.Commit;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.PrintWriter;

public class commit extends Command{
    public void commit(String author, String committer, String comment) throws Exception{
        Commit c = new Commit(author, committer, comment, this.index);
        c.write();
        PrintWriter p = new PrintWriter(this.index);
        p.print("");
        p.close(); // clear index
    }
    public static void main(String[] args){

        try {
            Options options = new Options();
            CommandLineParser parser = new DefaultParser();
            options.addOption(new Option("m",true,"description for command"));
            options.addOption(new Option("a",true,"author"));
            options.addOption(new Option("c",true,"committer"));
            CommandLine cmd = parser.parse( options, args);
            String comment = cmd.getOptionValue("m");
            String author = cmd.getOptionValue("a");
            String committer = cmd.getOptionValue("c");
            new commit().commit(author, committer, comment);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        }
}

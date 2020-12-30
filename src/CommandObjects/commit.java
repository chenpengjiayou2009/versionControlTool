package CommandObjects;

import KeyValueObjects.Commit;
import org.apache.commons.cli.*;

import java.io.File;

public class commit extends Command{
    public void commit(String author, String committer, String comment) throws Exception{
        File index = new File(path + "index");
        Commit c = new Commit(author, committer, comment, index);

        c.genKey(index);
        c.write();
    }
    public static void main(String[] args){

        try {
            Options options = new Options();
            CommandLineParser parser = new DefaultParser();
            options.addOption(new Option("m",true,"description for command"));
            CommandLine cmd = parser.parse( options, args);
            String comment = cmd.getOptionValue("m");
            new commit().commit(args[0], args[1], comment);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        }
}

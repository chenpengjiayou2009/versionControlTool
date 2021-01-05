package CommandObjects;

import java.io.*;

import org.apache.commons.cli.*;
import util.Copy;
import util.Read;

public class reset extends Command{

    private File branch = new File(wrapGit(Read.readBranchFromHead(head)));

    private File commit = new File(wrapObjects(Read.readCommitFromBranch(branch)));
    private String parent = Read.readParentFromCommit(commit);

    // reset --soft
    public void resetSoft() throws Exception{
//        PrintWriter out = new PrintWriter(this.branch);
//        if(this.parent.equals("")){
//            System.out.print("can't reset back");
//            return;
//        }
//        else {
//            out.print(this.parent);
//            System.out.println("HEAD now back to " + this.parent);
//        }
//        out.close();
    }

    // reset --mixed
    public void resetMixed() throws Exception{
        resetSoft();
        File index = new File(wrapGit("index"));
        File tree = new File(wrapObjects(Read.readTreeFromCommit(this.commit)));
        Copy.copy(tree, index);
    }

    // reset --hard
    public void resetHard() throws Exception{
        resetMixed();
        System.out.println("commit is " + this.commit.getName());
        File tree = new File(wrapObjects(Read.readTreeFromCommit(this.commit)));
        System.out.println("tree is " + tree.getName());
        Copy.copyTree(tree,new File(workingDir));
    }


    public static void main(String[] args) throws Exception{
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();
        options.addOption(new Option("s",false,"soft"));
        options.addOption(new Option("m",false,"mixed"));
        options.addOption(new Option("h",false,"hard"));
        CommandLine cmd = parser.parse( options, args);
        if(cmd.hasOption("s")){
            new reset().resetSoft();
        }
        else if (cmd.hasOption("h")){
            new reset().resetHard();
        }
        else{
            new reset().resetMixed();
        }
    }
}

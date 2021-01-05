package CommandObjects;

import util.Copy;
import util.Read;

import java.io.File;
import java.io.PrintWriter;

public class checkout extends Command{
    public void checkout(String branchName) throws Exception{
        String newHead = "refs/heads/" + branchName;
        PrintWriter printWriter = new PrintWriter(this.head);
        printWriter.print(newHead);
        printWriter.close();
        String currentBranch = Read.readBranchFromHead(this.head);
        String currentCommit = Read.readCommitFromBranch(new File(wrapGit(currentBranch)));
        String currentTree = Read.readTreeFromCommit(new File(wrapObjects(currentCommit)));
        Copy.copy(new File(wrapObjects(currentTree)), this.index);
        new reset().resetHard();
    }
    public static void main(String[] args){
        try{
            new checkout().checkout(args[0]);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

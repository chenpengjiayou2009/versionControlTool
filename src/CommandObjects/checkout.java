package CommandObjects;

import util.Copy;
import util.Read;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
    public String commonAncestor(File branch1, File branch2){
        String commit1 = Read.readCommitFromBranch(branch1);
        String commit2 = Read.readCommitFromBranch(branch2);
        while(!commit1.equals(commit2)){
            commit1 = Read.readParentFromCommit(new File(wrapObjects(commit1)));
            commit2 = Read.readParentFromCommit(new File(wrapObjects(commit2)));
        }
        return commit1;
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

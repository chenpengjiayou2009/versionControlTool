package CommandObjects;

import util.Read;

import java.io.File;

public class merge extends Command{
    public void merge(String branchName) throws Exception{
        String currentBranch = Read.readBranchFromHead(this.head);
        String currentCommit = Read.readCommitFromBranch(new File(wrapGit(currentBranch)));
        String currentTree = Read.readTreeFromCommit(new File(wrapObjects(currentCommit)));
        String otherCommit = Read.readCommitFromBranch(new File(wrapGit("refs/heads/" + branchName)));
        String otherTree = Read.readTreeFromCommit(new File(wrapObjects(otherCommit)));
    }
}

package CommandObjects;

import util.Read;

import java.io.File;

public class log extends Command{
    File currentBranch = new File(path + Read.readBranchFromHead(this.head));
    File currentCommit = new File(wrapObjects(Read.readCommitFromBranch(currentBranch)));
    public static void log(){
        return;
    }
}

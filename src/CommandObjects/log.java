package CommandObjects;

import util.Read;

import java.io.File;

public class log extends Command{
    File currentBranch = new File(path + Read.readBranchFromHead(this.head));
    String currentCommit = Read.readCommitFromBranch(currentBranch);
    String parent = Read.readParentFromCommit(new File(wrapObjects(currentCommit)));
    StringBuilder res = new StringBuilder(currentCommit);
    public void log(){
        while(!parent.equals("")){
            res.append("\n");
            res.append(parent);
            parent = Read.readParentFromCommit(new File(wrapObjects(parent)));
        }
        System.out.println(res);
    }

    public static void main(String[] args){
        new log().log();
    }
}

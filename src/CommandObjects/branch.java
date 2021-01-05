package CommandObjects;

import KeyValueObjects.Branch;
import util.Copy;
import util.Read;

import java.io.File;

public class branch extends Command{
    public void branch(){
        File branchDir = new File(wrapGit("refs/heads"));
        StringBuilder res = new StringBuilder();
        for(File f:branchDir.listFiles()){
            res.append(f.getName());
            res.append("\n");
        }
        System.out.println(res);
    }
    public void branch(String branchName) throws Exception{
        Branch newBranch = new Branch(branchName,"");
        newBranch.write();
        Copy.copy(new File(wrapGit(Read.readBranchFromHead(this.head))),new File(wrapGit("refs/heads/"+branchName)));
    }
    public static void main(String[] args){
        try{
            branch b = new branch();
            if(args.length==0) b.branch();
            else b.branch(args[0]);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

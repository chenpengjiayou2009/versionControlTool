package util;

import java.io.File;
import java.util.Scanner;

public class Read {

    public static String readBranchFromHead(File head){
        try{
            Scanner in = new Scanner(head);
            String branch = in.next();
            in.close();
            System.out.println("branch is " + branch);
            return branch;
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public static String readCommitFromBranch(File branch){
        try{
            Scanner in = new Scanner(branch);
            String commit = in.nextLine();
            in.close();
            System.out.println("commit is "+commit);
            return commit;
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public static String readParentFromCommit(File commit){
        try{
            System.out.println("commit is " + commit.getAbsolutePath());
            Scanner in = new Scanner(commit);
            String line1 = in.nextLine();
            String line2 = in.nextLine();
            String[] line2Splited = line2.split("\\ ");
            String parent = line2Splited[1];
            in.close();
            if(parent.length()!=40){
                return "";
            }
            else{
                return parent;
            }
        }
        catch (Exception e){
//            e.printStackTrace();
            return "";
        }
    }

    public static String readTreeFromCommit(File commit) throws Exception{
        try(Scanner in = new Scanner(commit)){
            while(in.hasNext()){
                String line = in.nextLine();
                String[] lineSplited = line.split(" ");
                String hashcode = lineSplited[1];
                return hashcode;
            }
        }
        return null;
    }
}

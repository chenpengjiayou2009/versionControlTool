package KeyValueObjects;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Commit extends KeyValueObject{
    private String tree;
    private String parent;
    private String author;
    private String committer;
    private String comment;

    public Commit(String author, String committer, String comment) throws Exception{
        Scanner scanner = new Scanner(new File(this.path + "HEAD")); // check out where head points because commit will update on the basis of the branch it is on
        String branch =  this.path + scanner.next();  // get branch address such as ref/heads/master
        scanner = new Scanner(new File(branch)); // read last commit hash code from the branch file
        if(scanner.hasNext()) this.parent = scanner.next();
        else this.parent = ""; // the parent of the first commit is empty
        scanner.close(); // don't forget to close
        System.out.println("parent is " + this.parent); // print it to verify the result

        this.tree = new Tree(new File(path)).getKey(); // compute the hash code of root directory
        this.author = author;
        this.committer = committer;
        this.comment = comment;

        this.content = "tree " + this.tree + "\nparent" + this.parent + "\nauthor " + this.author + "\ncommitter " + this.committer + "\n\n" + this.comment;
        genKey(content); // generate new hash code based on the content
        System.out.println("now the hashcode is "+ this.key);
        PrintWriter printWriter = new PrintWriter(branch);
        System.out.println("write key " + " to " + branch); // update the content of refs/head/<branchName>
        printWriter.print(this.key);
        printWriter.close();

        this.path += "objects/"; // prepare the path to the method write
    }

}

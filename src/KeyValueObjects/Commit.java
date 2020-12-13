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
        Scanner scanner = new Scanner("HEAD");
        String branch =  scanner.next();
        scanner = new Scanner(branch);
        this.parent = scanner.next();

        this.tree = new Tree(new File(path)).getKey();
        this.author = author;
        this.committer = committer;
        this.comment = comment;

        this.content = "tree " + this.tree + "\nauthor " + this.author + "\ncommitter " + this.committer + "\n\n" + this.comment;
        genKey(content);
        PrintWriter printWriter = new PrintWriter(branch);
        printWriter.print(this.key);
        printWriter.close();

        this.path += "objects/";
    }

}

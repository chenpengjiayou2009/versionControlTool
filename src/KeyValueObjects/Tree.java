package KeyValueObjects;

import java.io.File;
import java.io.PrintWriter;

public class Tree extends KeyValueObject {
    private String content = ""; // content of tree, such as "10644 blob sha1-code a.txt..."

    public Tree(File file) throws Exception {
        for(File f:file.listFiles()){
            if(f.isFile()){
                content = content + "\n" + "100644 blob " + new Blob(f).getKey() + " " + f.getName();
            }
            else if(f.isDirectory()){
                content = content + "\n" + "100644 tree " + new Tree(f).getKey() + " " + f.getName();
            }
        }
        File tree = new File("temp");
        PrintWriter printWriter = new PrintWriter(tree);
        printWriter.print(content);
        printWriter.close();
        genKey(tree);
        tree.delete();

    }


    public String getKey() {
        return this.key;
    }

    public String getContent(){
        return this.content;
    }

    @Override
    public String toString() {
        return "100644 tree " + this.key;
    }
}

package KeyValueObjects;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;

public class Tree extends KeyValueObject {// content of tree, such as "10644 blob sha1-code a.txt..."

    public Tree(File file) throws Exception {
        for(File f:file.listFiles()){
            String append;
            if(f.isFile()){
                Blob blob = new Blob(f);
                blob.write();
                content.append("100644 blob " + blob.getKey() + " " + f.getName() + "\n");
            }
            else if(f.isDirectory()){
                content.append("100644 tree " + new Tree(f).getKey() + " " + f.getName() + "\n");
            }
        }
        genKey(content.toString());
        this.path += "objects/";
        this.file = file;
    }

    public Tree(StringBuilder index) throws Exception{
        this.content = index;
        System.out.println("content is " + this.content.toString());
        genKey(content.toString());
        this.path += "objects/";
    }



    public String getKey() {
        return this.key;
    }

    public String getContent(){
        return this.content.toString();
    }

    @Override
    public String toString() {
        return "100644 tree " + this.key + " " + this.file.getName();
    }
}

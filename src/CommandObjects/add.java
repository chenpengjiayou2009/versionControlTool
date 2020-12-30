package CommandObjects;

import KeyValueObjects.Blob;
import KeyValueObjects.SHA1CheckSum;
import KeyValueObjects.Tree;
import org.apache.commons.cli.Options;

import java.io.*;

public class add extends Command{
    public void add(String fileName) throws Exception {
        File f = new File(workingDir + fileName);
        if(!f.exists()) {
            System.out.println("wrong file name");
            return;
        }
        String append;
        if(f.isDirectory()){
            Tree tree = new Tree(f);
            append = tree.toString();
            tree.write();
//            append = "10064 tree " + new SHA1CheckSum(f).getSha1() + " " + f.getName();
        }
        else{
            Blob blob = new Blob(f);
            append = "10064 blob " + new SHA1CheckSum(f).getSha1() + " " + f.getName();
            blob.write();
        }
        FileWriter fw = new FileWriter(path+"index", true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(append);
        fw.close();
        pw.close();
    }

    public static void main(String[] args){
        try{
            new add().add(args[0]);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

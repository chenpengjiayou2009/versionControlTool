import CommandObjects.Command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class writeUtil {
    private static String workingDir = System.getProperty("user.dir") + "/";
    public static void write(File f) throws Exception{
        Scanner s = new Scanner(f);
        while(s.hasNext()){
            String mode = s.next();
            String type = s.next();
            String hashcode = s.next();
            String filename = s.next();
            if(type=="tree") writeTree(hashcode,workingDir+filename
            +"/");
            else writeBlob(hashcode,workingDir,filename);
        }
    }
    private static void writeBlob(String sourceFilename, String path, String desFilename) throws Exception{
        FileInputStream source = new FileInputStream(workingDir + "/.git/objects/"+sourceFilename);
        FileOutputStream destination = new FileOutputStream(path + desFilename);
        int numRead = 0;
        byte[] buffer = new byte[1024];
        do {
            numRead = source.read(buffer);
            if(numRead>0){
                destination.write(buffer);
            }
        }while(numRead!=-1);
        source.close();
        destination.close();
    }

    private static void writeTree(String sourceFilename, String path) throws Exception{
        Scanner s = new Scanner(workingDir + "/.git/objects/" + sourceFilename);
        while(s.hasNext()){
            String mode = s.next();
            String type = s.next();
            String hashcode = s.next();
            String filename = s.next();
            if(type=="tree") writeTree(hashcode,path+filename+"/");
            else writeBlob(hashcode,path,filename);
        }
    }
}

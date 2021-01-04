package util;

import java.io.*;
import java.util.Scanner;

public class Copy {
    public static void copy(File source, File des) throws Exception{
        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(source));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(des))){
            int r, numRead = 0;
            while((r=in.read())!=-1){
                out.write((byte)r);
                numRead++;
            }
            System.out.println(numRead + " bytes copied");
        }
    }

    public static void copyTree(File tree, File des) throws Exception{
        try(Scanner in = new Scanner(tree)){
            while(in.hasNext()){
                String line = in.nextLine();
                String[] lineSplited = line.split(" ");
                String type = lineSplited[1];
                String hashcode = lineSplited[2];
                String fileName = lineSplited[3];
                if(type.equals("blob")){
                    Copy.copy(new File(tree.getParent(),hashcode),new File(des,fileName));
                }
                else if(type.equals("tree")){
                    File newDes = new File(des,fileName);
                    copyTree(new File(tree.getParent(), hashcode),newDes);
                }
            }
        }
    }
}

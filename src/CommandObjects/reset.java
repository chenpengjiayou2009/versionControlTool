package CommandObjects;

import java.io.*;
import java.util.Scanner;

public class reset extends Command{
    private String head = path + "HEAD";

    private String branch = setBranch();

    private String commit = setCommit(branch);
    private String parent = setParent(commit);
    private String tree = setTree(commit);

    private String setBranch(){
        try{
            Scanner in = new Scanner(new File(head));
            String branch = in.next();
            in.close();
            return path + branch;

        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    private String setCommit(String branch){
        File f = new File(branch);
        try{
            Scanner in = new Scanner(f);
            String commit = in.next();
            in.close();
            return path + "objects/" +commit;
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }


    }

    private String setParent(String commit){
        try{
            Scanner in = new Scanner(new File(commit));
            String line1 = in.nextLine();
            String line2 = in.nextLine();
            String[] line2Splited = line2.split(" ");
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
            e.printStackTrace();
            return "";
        }
    }

    private String setTree(String commit){
        try{
            Scanner in = new Scanner(new File(commit));
            String line1 = in.nextLine();
            String[] line1Splited = line1.split(" ");
            String tree = line1Splited[1];
            in.close();
            if(tree.length()!=40){
                return "";
            }
            else{
                return path + "objects/" + tree;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    private void resetSoft() throws Exception{
        PrintWriter out = new PrintWriter(this.commit);
        if(this.parent.equals("")){
            System.out.print("can't reset back");
            return;
        }
        else {
            out.print(this.parent);
            System.out.println("HEAD now back to " + this.parent);
        }
        out.close();
    }

    private void resetMixed() throws Exception{
        File index = new File(path+"index");
        File tree = new File(this.tree);
        copy(index, tree);
    }

    private void resetHard() throws Exception{
        File tree = new File(this.tree);
        try(Scanner in = new Scanner(tree)){
            while(in.hasNext()){
                String line = in.nextLine();
                String[] lineSplited = line.split(" ");
                String type = lineSplited[1];
                String hashcode = lineSplited[2];
                String fileName = lineSplited[3];
                if(type.equals("blob")){
                    File source = new File(path + "objects/" + hashcode);
                    File des = new File(workingDir + fileName);
                    copy(source,des);
                }
                else if(type.equals("tree")){
                    File subTree = new File(path+"objects/"+fileName);
                    copyTree(subTree,new File(workingDir));
                }
            }
        }
    }

    private void copy(File source, File des) throws Exception{
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

    private void copyTree(File tree, File des) throws Exception{
        try(Scanner in = new Scanner(tree)){
            while(in.hasNext()){
                String line = in.nextLine();
                String[] lineSplited = line.split(" ");
                String type = lineSplited[1];
                String hashcode = lineSplited[2];
                String fileName = lineSplited[3];
                if(type.equals("blob")){
                    copy(new File(path + "objects/" + hashcode),des);
                }
                else if(type.equals("tree")){
                    File newDes = new File(des,fileName);
                    copyTree(new File(path + "objects/" + hashcode),newDes);
                }
            }
        }
    }


}

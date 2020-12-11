/*
    unitTest will generate test dir imitating ./git in the present directory
 */

import KeyValueObjects.*;

import java.io.File;
import java.io.PrintWriter;

public class unitTest {
    public static void testName(String filename){
        File file = new File(filename);
        try{
            if(file.isDirectory()) {
                Tree tree = new Tree(file);
                System.out.println(tree);
                assert tree.getKey().length() == 40;
            }
            else{
                Blob blob = new Blob(file);
                System.out.println(blob);
                assert blob.getKey().length() == 40;
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void testContent(String filename){
        File file = new File(filename);
        try{
            if(file.isDirectory()){
                System.out.println(new Tree(file).getContent());
            }
            else{
                System.out.println(new Blob(file));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void testName(File file){
        try{
            if(file.isDirectory()){
                Tree tree = new Tree(file);
                System.out.println(tree);
                assert tree.getKey().length() == 40;
            }
            else{
                Blob blob = new Blob(file);
                System.out.println(blob);
                assert blob.getKey().length() == 40;
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void testTree(File file){
        try{
            if(file.isDirectory()){
                System.out.println(new Tree(file).getContent());
            }
            else{
                System.out.println(new Blob(file));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void testBranch(String branch) throws Exception{
        Branch b = new Branch(branch,"");
        b.write(); // generate a branch
    }

    public static void testHead(String branch) throws Exception{
        Head head = new Head(branch);
        head.write(); // generate HEAD file
    }

    public static void testCommit(String path) throws Exception{
        Commit commit1 = new Commit("chenpeng","chenpeng","first commit");
        System.out.println(commit1.getContent());
        commit1.write();

        PrintWriter printWriter = new PrintWriter(path+"/a.txt"); // don't forget /
        printWriter.print("hello world");
        printWriter.close();
        Commit commit2 = new Commit("chenpeng","chenpeng","second commit");
        System.out.println(commit2.getContent());
        commit2.write();

    }

    public static File genFile(){
        File root = new File("root");
        File blob1 = new File(root,"blob1");
        File tree1 = new File(root,"tree1");
        File blob2 = new File(tree1,"blob2");
        return root;
    }

    public static void main(String[] args){
        try {
            String path = "./test";
            testBranch("master");
            testHead("master");
            testCommit(path);
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}

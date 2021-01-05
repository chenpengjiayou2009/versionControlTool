package CommandObjects;

import java.io.File;
import java.util.Scanner;

import static util.Compare.compareFile;

public class diff extends Command{
    public void compareTree(File Tree, File workingDir) throws Exception{
        Scanner input = new Scanner(Tree);
        while(input.hasNext()){
            String line = input.nextLine();
            String[] words = line.split("\\ ");
            String type = words[1];
            String hashcode = words[2];
            String fileName = words[3];
            if(type.equals("blob")){
                System.out.println("Now compare " + new File(wrapObjects(hashcode)).getAbsolutePath()+ new File(workingDir,fileName).getAbsolutePath());
                compareFile(new File(workingDir,fileName),new File(wrapObjects(hashcode)));
            }
            else if(type.equals("tree")){
                compareTree(new File(wrapObjects(hashcode)),new File(workingDir,fileName));
            }
        }
        input.close();
    }
    public void diff() throws Exception{
        compareTree(this.index,new File(workingDir));
    }
    public static void main(String[] args){
        try{
            new diff().diff();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

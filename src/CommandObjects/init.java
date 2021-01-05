package CommandObjects;

import KeyValueObjects.Branch;
import KeyValueObjects.Head;

import java.io.File;

public class init extends Command{
    public void init(){
        File f = new File(path);
        if(f.exists()) System.out.println("this directory already initialted");
        File objects = new File(f,"objects");
        File refs = new File(f,"refs");
        File heads = new File(refs, "heads");
        Branch master = new Branch("master","");
        Head head = new Head("master");


        if(f.mkdir()&&objects.mkdir()&&refs.mkdir()&&heads.mkdir()){
            System.out.println("git initiated");
        }
        try{
            master.write();
            head.write();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new init().init();
    }
}

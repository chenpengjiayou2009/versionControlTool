package CommanObjects;

import java.io.File;

public class Init {
    public void init(String path){
        File f = new File(path + "/.git");
        if(f.mkdir()){
            System.out.println("git initiated");
        }
    }
    public static void main(String[] args){
        String path = args[0];
        new Init().init(path);
    }
}

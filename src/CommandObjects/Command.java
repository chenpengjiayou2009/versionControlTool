package CommandObjects;

import java.io.File;
import java.util.Scanner;

public class Command {
    protected String path;
    protected String workingDir;
    protected File index;
    protected File head;
    public Command(){
        workingDir = System.getProperty("user.dir") +"/";
        path = workingDir + ".git/";
        index = new File(path + "index");
        head = new File(path + "HEAD");
//        System.out.println("Working Directory = " + workingDir);
    }

    protected String wrapGit(String s){
        return path + s;
    }
    protected String wrapObjects(String s){
        return path + "objects/" + s;
    }
    public String getPath(){
        return path;
    }
    public String getWorkingDir(){
        return workingDir;
    }
}

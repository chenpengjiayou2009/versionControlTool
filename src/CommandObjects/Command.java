package CommandObjects;

import java.util.Scanner;

public class Command {
    protected String path;
    protected String workingDir;
    public Command(){
        workingDir = System.getProperty("user.dir") +"/";
        path = workingDir + ".git/";
        System.out.println("Working Directory = " + workingDir);
    }
    public String getPath(){
        return path;
    }
    public String getWorkingDir(){
        return workingDir;
    }
}

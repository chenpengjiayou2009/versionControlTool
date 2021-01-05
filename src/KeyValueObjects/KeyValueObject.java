package KeyValueObjects;

import util.Copy;

import java.io.*;

public abstract class KeyValueObject {
    protected String type;
    protected String key;
    protected File file;
    protected StringBuilder content = new StringBuilder();
    protected String path = System.getProperty("user.dir") + "/.git/";
    protected  KeyValueObject(){

    }
    public void genKey(File file) throws Exception{
        SHA1CheckSum s = new SHA1CheckSum(file);
        this.key = s.getSha1();
        this.file = file;
    }

    public void genKey(String content) throws Exception{
        SHA1CheckSum s = new SHA1CheckSum(content);
        this.key = s.getSha1();
    }


    public void writeFile() throws Exception{
        File source = this.file;
        File des = new File(this.path + this.key);
        Copy.copy(source,des);
    };

    public void write() throws Exception{
        File f = new File(path);
        if(!f.exists())f.mkdirs();
        PrintWriter p = new PrintWriter(this.path + this.key);
        p.print(this.content);
        p.close();
    }

    public String getContent() {
        return content.toString();
    }
}

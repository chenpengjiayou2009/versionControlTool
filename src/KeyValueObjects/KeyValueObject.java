package KeyValueObjects;

import KeyValueObjects.SHA1CheckSum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public abstract class KeyValueObject {
    protected String type;
    protected String key;
    protected File file;
    protected  KeyValueObject(){

    }
    protected void genKey(File file) throws Exception{
        SHA1CheckSum s = new SHA1CheckSum(file);
        this.key = s.getSha1();
        this.file = file;
    }

    protected void genKey(String content) throws Exception{
        SHA1CheckSum s = new SHA1CheckSum(content);
        this.key = s.getSha1();
    }


    public void write() throws Exception{
        FileInputStream fileInputStream = new FileInputStream(this.file);
        FileOutputStream output = new FileOutputStream(this.key);
        byte[] buffer = new byte[1024];
        int numRead = 0;
        do {
            numRead = fileInputStream.read(buffer);
            if(numRead > 0){
                output.write(buffer);
            }
        }while(numRead!=-1);
        fileInputStream.close();
        output.close();
    };
}

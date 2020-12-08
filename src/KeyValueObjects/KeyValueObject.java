package KeyValueObjects;

import KeyValueObjects.SHA1CheckSum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public abstract class KeyValueObject {
    protected String type;
    protected String key;
    protected FileInputStream fileInputStream;
    protected  KeyValueObject(){

    }
    protected void genKey(File file) throws Exception{
        SHA1CheckSum s = new SHA1CheckSum(file);
        this.key = s.getSha1();
        this.fileInputStream = new FileInputStream(file);
    }
    public void write() throws Exception{
        FileOutputStream output = new FileOutputStream(this.key);
        byte[] buffer = new byte[1024];
        int numRead = 0;
        do {
            numRead = fileInputStream.read(buffer);
            if(numRead > 0){
                output.write(buffer);
            }
        }while(numRead!=-1);
        this.fileInputStream.close();
        output.close();
    };
}

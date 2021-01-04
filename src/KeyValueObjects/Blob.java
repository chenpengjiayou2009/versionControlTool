package KeyValueObjects;

import java.io.*;

public class Blob extends KeyValueObject {
    public Blob(File file) throws Exception {
        genKey(file);
        this.type = "blob";
        this.path += "objects/";

    } // a blob object generated from given file name

    @Override
    public void write() throws Exception {
        writeFile();
    }

    public String getType(){
        return type;
    }

    public String getKey(){
        return key;
    }

    @Override
    public String toString() {
        return "100644 blob " + key + " " + this.file.getName();
    }
}

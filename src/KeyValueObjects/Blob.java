package KeyValueObjects;

import java.io.*;

public class Blob extends KeyValueObject {
    public Blob(File file) throws Exception {
        genKey(file);
        this.type = "blob";

    } // a blob object generated from given file name


    public String getType(){
        return type;
    }

    public String getKey(){
        return key;
    }

    @Override
    public String toString() {
        return "100644 blob " + key;
    }
}

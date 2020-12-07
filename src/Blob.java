import java.io.*;

public class Blob {
    private String type; // type is always blob
    private String name; // hash code of the content
    FileInputStream input;
    public Blob(String filename) throws Exception {
        this.type = "blob";
        SHA1CheckSum s = new SHA1CheckSum(filename,true);
        this.name = s.getSha1(); // get Sha-1 for the content
        this.input = new FileInputStream(filename); // start read the file

    } // a blob object generated from given file name

    // generate file whose filename is sha-1 code , content is the original content
    public void writeBlob() throws IOException {
        FileOutputStream output = new FileOutputStream(this.name);
        byte[] buffer = new byte[1024];
        int numRead = 0;
        do {
            numRead = input.read(buffer);
            if(numRead > 0){
                output.write(buffer);
            }
        }while(numRead!=-1);
        input.close();
        output.close();
    }

    public String getType(){
        return type;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "100644 blob " + name;
    }
}

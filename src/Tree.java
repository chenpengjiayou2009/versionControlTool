import java.io.File;
import java.io.FileOutputStream;

public class Tree {
    private String type;
    private String name;
    private String content = "";

    public Tree(String filename) throws Exception {
        File file = new File(filename);
        for(File f:file.listFiles()){
            if(f.isFile()){
                content = content + "\n" + "100644 blob " + new Blob(f.getAbsolutePath()).getName() + " " + f.getName();
            }
            else if(f.isDirectory()){
                content = content + "\n" + "100644 tree " + new Tree(f.getAbsolutePath()).getName() + " " + f.getName();
            }
        }
        SHA1CheckSum sha1CheckSum = new SHA1CheckSum(content,false);
        this.name = sha1CheckSum.getSha1();

    }

    public void writeTree() throws Exception{
        String out = this.name;
        FileOutputStream os = new FileOutputStream(out);
        os.write(content.getBytes());
    }

    public String getName() {
        return name;
    }

    public String getContent(){
        return this.content;
    }

    @Override
    public String toString() {
        return "100644 tree " + this.name;
    }
}

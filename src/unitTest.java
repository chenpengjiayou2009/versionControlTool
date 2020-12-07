import java.io.File;

public class unitTest {
    public static void testName(String filename){
        File file = new File(filename);
        try{
            if(file.isDirectory()){
                System.out.println(new Tree(file.getAbsolutePath()));
            }
            else{
                System.out.println(new Blob(file.getAbsolutePath()));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void testContent(String filename){
        File file = new File(filename);
        try{
            if(file.isDirectory()){
                System.out.println(new Tree(file.getAbsolutePath()).getContent());
            }
            else{
                System.out.println(new Blob(file.getAbsolutePath()));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args){
        String filename = ".";
        testName(filename);
        testContent(filename);

    }
}

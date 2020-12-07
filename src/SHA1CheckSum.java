import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.MessageDigest;

public class SHA1CheckSum {
    private byte[] sha1;
    private String outFile;
    public SHA1CheckSum(String inFile,boolean isFile) throws Exception{
        if(isFile){
            FileInputStream input = new FileInputStream(inFile);
            this.sha1 = Sha1Checksum(input);
        }
        else{
            this.sha1 = Sha1Checksum(inFile);
        }
    }



    public static byte[] Sha1Checksum(InputStream is) throws Exception{
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("SHA-1");
        int numRead = 0;
        do {
            numRead = is.read(buffer);
            if(numRead>0){
                complete.update(buffer, 0 , numRead);
            }
        }while(numRead!=-1);
        is.close();
        return complete.digest();
    }

    public static byte[] Sha1Checksum(String s) throws Exception{
        MessageDigest complete = MessageDigest.getInstance("SHA-1");
        int numRead = 0;
        complete.update(s.getBytes());
        return complete.digest();
    }

    public String getSha1(){
        outFile = "";
        int n = sha1.length;
        for(int i = 0;i< n;i++){
            if((int) sha1[i] < 16){
                outFile = outFile + "0" + Integer.toString(sha1[i]&0xFF, 16); // 如果不满2位字符，则将其前面补一个"0"
                continue;
            }
            outFile += Integer.toString(sha1[i]&0xFF, 16);
        }
        return outFile;
    }
}

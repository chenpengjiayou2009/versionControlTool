package KeyValueObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class SHA1CheckSum {
    private byte[] sha1;
    private String out;
    public SHA1CheckSum(File inFile) throws Exception{
        FileInputStream input = new FileInputStream(inFile);
        this.sha1 = Sha1Checksum(input);
    }

    public SHA1CheckSum(String content)throws Exception{
        this.sha1 = Sha1Checksum(content);
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

    public static byte[] Sha1Checksum(String content) throws Exception{
        MessageDigest complete = MessageDigest.getInstance("SHA-1");
        complete.update(content.getBytes());
        return complete.digest();
    }



    public String getSha1(){
        out = "";
        int n = sha1.length;
        for(int i = 0;i< n;i++) {
            String append = Integer.toString(sha1[i] & 0xFF, 16);
            if (append.length()<2) {
                out = out + "0" + append; // 如果不满2位字符，则将其前面补一个"0"
            }
//            else if(append.length()>2){
//                System.out.println("now we have more than 2 digits");
//                System.out.println(sha1[i]);
//                outFile += append;
//            }
            else {
                out += append;
            }
        }
        return out;
    }
}

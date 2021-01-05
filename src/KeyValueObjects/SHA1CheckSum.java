/*
 * @Author: Chen Peng 
 * @Date: 2021-01-02 16:35:50 
 * @Last Modified by: Luo Zijin
 * @Last Modified time: 2021-01-02 17:19:47
 */
package KeyValueObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * The {@code SHA1Checksum} class providesa method to generate hash value for a
 * single file or a directory
 * 
 */
public class SHA1CheckSum {
    private byte[] sha1; // save the sha1 value in bytes
    private String out;

    /**
     * generate sha1 for a File class
     * 
     * @param inFile file to be calculated
     * @throws Exception
     */
    public SHA1CheckSum(File inFile) throws Exception {
        FileInputStream input = new FileInputStream(inFile);
        this.sha1 = Sha1Checksum(input);
    }

    /**
     * generate sha1 for a string
     * 
     * @param content string to be calculated
     * @throws Exception
     */
    public SHA1CheckSum(String content) throws Exception {
        this.sha1 = Sha1Checksum(content);
    }

    /**
     * calculate sha1 for InputStream, calculate 1024 bytes each time
     * 
     * @param is InputStream to be calculated
     * @return the array of bytes for the resulting hash value
     * @throws Exception
     */
    public static byte[] Sha1Checksum(InputStream is) throws Exception {
        byte[] buffer = new byte[1024]; // create a buffer to save temp bytes
        MessageDigest complete = MessageDigest.getInstance("SHA-1");
        int numRead = 0;
        do {
            numRead = is.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);
        is.close();
        return complete.digest();
    }

    /**
     * caculate sha1 for String
     * @param content
     * @return the array of bytes for the resulting hash value
     * @throws Exception
     */
    public static byte[] Sha1Checksum(String content) throws Exception {
        MessageDigest complete = MessageDigest.getInstance("SHA-1");
        complete.update(content.getBytes());
        return complete.digest();
    }

    /**
     * convert raw sha1 value to a string with 40 digits in 16 base
     * @return sha1 string with 40 digits in 16 base
     */
    public String getSha1() {
        out = "";
        int n = sha1.length;
        for (int i = 0; i < n; i++) {
            String append = Integer.toString(sha1[i] & 0xFF, 16);
            if (append.length() < 2) {
                out = out + "0" + append; // 如果不满2位字符，则将其前面补一个"0"
            }
            // else if(append.length()>2){
            // System.out.println("now we have more than 2 digits");
            // System.out.println(sha1[i]);
            // outFile += append;
            // }
            else {
                out += append;
            }
        }
        return out;
    }
}

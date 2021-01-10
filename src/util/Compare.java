package util;

import CommandObjects.Command;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Compare{
    public static void compareFile(File f1, File f2) throws Exception{
        System.out.println(f1.getAbsolutePath());
        Scanner input1 = new Scanner(f1);
        Scanner input2 = new Scanner(f2);
        StringBuilder res = new StringBuilder();

        while(input1.hasNextLine()&&input2.hasNextLine()){
            int count = 0;
            ArrayList<String> content1 = new ArrayList<>();
            ArrayList<String> content2 = new ArrayList<>();
            while(true){
                content1.add(input1.nextLine());
                content2.add(input2.nextLine());
                if(!input1.hasNextLine()) break;
                if(!input2.hasNextLine()) break;
                if(++count>100) break;
            }
//            System.out.println(content1.toString());
//            System.out.println(content2.toString());
//            System.out.println("-------");
            res.append(minDistance(content1,content2));
        }
        ArrayList<String> content1 = new ArrayList<>();
        ArrayList<String> content2 = new ArrayList<>();
        while(input1.hasNextLine()){
            content1.add(input1.nextLine());
        }
        while (input2.hasNextLine()){
            content2.add(input2.nextLine());
        }
//        System.out.println(content1.toString());
//        System.out.println(content2.toString());
        res.append(minDistance(content1,content2));
        input1.close();
        input2.close();
        System.out.println(res.toString());
    }

    public static String minDistance(ArrayList word1, ArrayList word2) {
        int length1 = word1.size();
        int length2 = word2.size();
        int[][] dp  = new int[length1+1][length2+1];
        String[][] dpString = new String[length1+1][length2+1];
        dpString[0][0] = "";
        for(int i = 1;i<length1+1;i++){
            dp[i][0] = dp[i-1][0] + 1;
            dpString[i][0] = "+" + word1.get(i-1) + "\n";
        }
        for(int i = 1;i<length2+1;i++){
            dp[0][i] = dp[0][i-1] + 1;
            dpString[0][i] = "-" + word2.get(i-1) + "\n";
        }
        for(int i=1;i<length1+1;i++){
            for(int j =1;j<length2+1;j++){
                dpString[i][j] = dpString[i-1][j] + dpString[i][j-1];
                int price = 1;
                if(word1.get(i-1).equals(word2.get(j-1))){
                    price = 0;
                    dpString[i][j] = dpString[i-1][j-1];
                }
                int min = dp[i-1][j-1] +price;
                if(dp[i-1][j]+1<min){
                    min = dp[i-1][j]+1;
                    dpString[i][j] = dpString[i-1][j] + "+" + word1.get(i-1) + "\n";
                }
                if(dp[i][j-1]+1<min){
                    min = dp[i][j-1]+1;
                    dpString[i][j] = dpString[i][j-1] + "-" + word2.get(j-1) + "\n";
                }
                dp[i][j] = min;
            }
        }
//        for(int i = 0;i<length1+1;i++){
//            for(int j = 0;j<length2+1;j++){
//                System.out.print(dpString[i][j] +"/");
//            }
//            System.out.println();
//        }
        return dpString[length1][length2];
    }

}

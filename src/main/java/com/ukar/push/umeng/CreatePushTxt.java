package com.ukar.push.umeng;

import java.io.*;

/**
 * Created by jyou on 2017/10/19.
 */
public class CreatePushTxt {
    private static String basePath = "C:\\Users\\jyou\\Desktop\\umeng\\push10-19\\";

    public static void main(String[] args) throws Exception{
        read("ANDROID.txt");
        read("IOS.txt");
    }


    public static void write(String content, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName),true));
        writer.write(content);
        writer.close();
    }

    public static void read(String fileName) throws IOException {
        File file = new File(basePath + fileName);
        StringBuffer str = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        int i = 1;
        int max = readLine(fileName);
        while((line = br.readLine())!=null){//使用readLine方法，一次读一行
            if( i % 10000 != 0){
                str.append(line);
                str.append("\r\n");
            }
            if(i % 10000 == 0){//第5000条
                str.append(line);
                String s = str.toString();
                String name = basePath +  + i + "_" + fileName.toLowerCase();
                write(s, name);
                str = new StringBuffer();
            }

            if(i == max){
                String s = str.toString();
                int a = i % 10000;
                i = i - a + 10000;
                String name = basePath +  + i + "_" + fileName.toLowerCase();
                write(s, name);
                break;
            }
            i++;
        }
        br.close();
        System.out.println("读写文件执行完毕.........................");
    }

    /**
     * 推送读取文件有多少行数据
     * @return
     * @throws IOException
     */
    public static int readLine(String fileName) throws IOException {
        File file = new File(basePath + fileName);
        StringBuffer str = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        int i = 0;
        while((line = br.readLine())!=null){
            i++;
        }
        return i;
    }
}

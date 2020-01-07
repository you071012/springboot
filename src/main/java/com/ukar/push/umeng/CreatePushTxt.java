package com.ukar.push.umeng;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jyou on 2017/10/19.
 */
public class CreatePushTxt {
    //TODO 每次都要修改
    private static String basePath = "C:\\Users\\jyou\\Desktop\\umeng\\push12-18\\";

    public static void main(String[] args) throws Exception {
        /**
         * 生成推送文件
         */
//        read("ANDROID.txt");
//        read("IOS.txt");


        /**
         * 查询推送结果
         */
//        UMeng umeng = new UMeng();
//        umeng.search("C:\\Users\\jyou\\Desktop\\umeng\\push.txt");


        int i = readLine("30000_android.txt");
        System.out.println(i);
    }


    public static void write(String content, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName), true));
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
        while ((line = br.readLine()) != null) {//使用readLine方法，一次读一行
            if (i % 10000 != 0) {
                str.append(line);
                str.append("\r\n");
            }
            if (i % 10000 == 0) {
                str.append(line);
                String s = str.toString();
                String name = basePath + +i + "_" + fileName.toLowerCase();
                write(s, name);
                str = new StringBuffer();
            }

            if (i == max) {
                String s = str.toString();
                int a = i % 10000;
                i = i - a + 10000;
                String name = basePath + +i + "_" + fileName.toLowerCase();
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
     *
     * @return
     * @throws IOException
     */
    public static int readLine(String fileName) throws IOException {
        File file = new File(basePath + fileName);
        StringBuffer str = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        int i = 0;
        while ((line = br.readLine()) != null) {
            i++;
        }
        br.close();
        return i;
    }

    public static int test(String fileName) throws IOException {
        Map<String, Object> map = new HashMap<>();
        File file = new File(basePath + fileName);
        StringBuffer str = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        int i = 0;
        while ((line = br.readLine()) != null) {
            String[] temp = new String[6];
            String[] split = line.split("\\|");
            temp[0] = split[4].trim();//业务流水号
            map.put("" + i, temp);
            i++;
        }
        br.close();
        System.out.println(map.size());
        return i;
    }
}

package com.ukar.task;

import com.ukar.push.umeng.UMeng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by jyou on 2017/10/24.
 *
 * 友盟定时任务
 */
@Component
public class UmengTask {

    private final Logger logger = LoggerFactory.getLogger(UmengTask.class);

    private int i = 10000;
    private String end = "_ios.txt";
    private boolean boo = true;

//    @Scheduled(cron="0/10 0 * * * ?") //每10分钟执行一次
    public void task() {
        try{
            //TODO fileName自定义，地址需要改动
            String fileName = "C:\\Users\\jyou\\Desktop\\umeng\\push12-18\\";
            fileName = fileName + i + end;
            UMeng umeng = new UMeng();
            String tokens = umeng.readFile(fileName);
            String result = "";

            //TODO title需要改变
            String title = "失信者必将处处受限，mo9信用钱包积极响应“信联”共建信用机制！";
            if(fileName.endsWith("android.txt")){
                result = umeng.sendAndroidFilecast(tokens, title);
            }else if(fileName.endsWith("ios.txt")){
                result = umeng.sendIOSFilecast(tokens, title);
            }else{
                logger.error("读取文件出现错误................................");
            }
            result = i + end.split("\\.")[0] + "--" + result + "\r\n";
            write(result, "C:\\Users\\jyou\\Desktop\\umeng\\push.txt");
            String name = i + end;
            if(boo){
                if("_ios.txt".equals(end)){
                    end = "_android.txt";
                }else if("_android.txt".equals(end)){
                    end = "_ios.txt";
                    i = i + 10000;
                }else{
                    logger.error("计算i出现错误................................");
                }
            }else{
                i = i + 10000;
            }
            logger.info(name + "执行完毕，" + "文件切换为：" + i + end + "准备执行................................");

        }catch (Exception e){
            e.printStackTrace();
            boo = false;
            //TODO 可能需要改动，当ios文件多时end = "_ios.txt"，当android文件多时，end = "_android.txt";
            end = "_ios.txt";
            i = i + 10000;
            logger.info("文件切换为：" + i + end + "准备执行................................");
        }
    }

    public void write(String content, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName),true));
        writer.write(content);
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) throws Exception {
        UMeng umeng = new UMeng();
        //TODO
        String fileName = "C:\\Users\\jyou\\Desktop\\umeng\\push11-27\\90000_android.txt";
        String title = "恭喜老哥被九妹翻牌，只送礼不表白！";
        String tokens = umeng.readFile(fileName);
        String result = umeng.sendAndroidFilecast(tokens, title);
        System.out.println(result);
    }
}

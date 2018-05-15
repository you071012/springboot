package com.ukar.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Properties;

/**
 * javamail邮件发送工具类
 * Created by xzhang on 2017/11/13.
 */
@Component
public class JavaMailUtil {

    private static final Logger logger = LoggerFactory.getLogger(JavaMailUtil.class);

    // 收件人邮箱
    String to = "ljyao@mo9.com";

    // 发件人电子邮箱
    String from = "jyou@mo9.com";

    // 发件人邮箱密码
    String password = "Ukar071012";

    // 指定发送邮件的主机
    String host = "smtp.exmail.qq.com";

    Properties properties = System.getProperties();

    public void sendEmail(String subject, String text) {
        logger.info("接收到发送邮件请求, 主题为: {} , 内容为: {}", subject, text);
        // 获取session对象
        Session session = Session.getInstance(properties, new Authenticator() {
            // 在session中设置账户信息，Transport发送邮件时会使用
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // 发件人
            message.setFrom(new InternetAddress(from));
            // 收件人
            message.setRecipients(Message.RecipientType.TO, new InternetAddress().parse(to));
            // 主题
            message.setSubject(subject);
            // 设置消息体
            message.setText(text);
            // 发送消息
            Transport.send(message);
            logger.info("邮件发送成功");
        }catch (MessagingException mex) {
            mex.printStackTrace();
            logger.info("邮件发送失败");
        }
    }

    @PostConstruct
    public void init() {
        logger.info("正在初始化JavaMailUtil");
        // 用ssl连接
        String sslFactory = "javax.net.ssl.SSLSocketFactory";
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        // 获取系统属性
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.socketFactory.class", sslFactory);
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        // 发送服务器需要身份验证
        properties.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        properties.setProperty("mail.transport.protocol", "smtp");
    }
}

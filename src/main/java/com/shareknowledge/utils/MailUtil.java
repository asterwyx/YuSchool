package com.shareknowledge.utils;

import com.shareknowledge.constant.ErrorCode;
import com.sun.mail.util.MailSSLSocketFactory;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

public class MailUtil {
    private String destEmail;
    private String content;

    public MailUtil(String destEmail, String content) {
        this.destEmail = destEmail;
        this.content = content;
    }

    public int send() {
        // 获取默认配置
        Properties props = this.getDefaultProperties();
        MailSSLSocketFactory sf;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return ErrorCode.FAIL_OP;
        }
        sf.setTrustAllHosts(true);
        props.setProperty("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("senderAddress"), props.getProperty("senderAuth"));
            }
        });
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(props.getProperty("senderAddress"));
            msg.setRecipients(Message.RecipientType.TO, this.destEmail);
            msg.setSubject("Account verify");
            msg.setSentDate(new Date());
            msg.setContent(this.content, "text/html;charset=UTF-8"); // 设置邮件内容
            Transport.send(msg);
            System.out.println("邮件发送成功!");
            return ErrorCode.SUCCESS;
        } catch (MessagingException e) {
            e.printStackTrace();
            return ErrorCode.FAIL_OP;
        }
    }

    private Properties getDefaultProperties() {
        Properties props = System.getProperties(); // 获取系统配置
        // 读取配置文件
        try (InputStream in = MailUtil.class.getResourceAsStream("/mail.properties")) {
            try {
                props.load(in);
            } catch (IOException e) {
                System.err.println("读取邮件服务默认配置文件失败");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println("找不到指定的资源文件!");
            e.printStackTrace();
        }
        return props;
    }

    public String getDestEmail() {
        return destEmail;
    }

    public void setDestEmail(String destEmail) {
        this.destEmail = destEmail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

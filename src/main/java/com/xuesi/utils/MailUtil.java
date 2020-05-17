package com.xuesi.utils;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {
    private static final String EMAIL_SOURCE = "1844167725@qq.com";
    //邮箱里的POP3/SMTP等服务授权码
    private static final String ACCESS_AUTHCODE = "okxrnykwodmpebce";
    /**
     * 发送邮件
     */
    public static void sendEmail(String email, String content,String subject) throws Exception {
        //创建连接对象 连接到邮件服务器
        Properties properties = new Properties();

       properties.load((MailUtil.class.getClassLoader().getResourceAsStream("mail.properties")));
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_SOURCE, ACCESS_AUTHCODE);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EMAIL_SOURCE));
        //设置收件人
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        //Subject: 邮件主题
        message.setSubject(subject);
        //设置邮件正文  第二个参数是邮件发送的类型
        message.setContent(content, "text/html;charset=UTF-8");
        Transport.send(message);
    }

    public static void main(String[] args) throws Exception {
        sendEmail("576698993@qq.com","666666","dddd");
    }
}


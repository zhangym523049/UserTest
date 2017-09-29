package com.ym.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	private final static String THEME = "验证码";
	private final static String DEFAULT_CHARSET = "UTF-8";

	public static void send(String addr) throws UnsupportedEncodingException, MessagingException {
		
        Properties props = new Properties();                // 用于连接邮件服务器的参数配置（发送邮件时才需要用到）
        Session session= Session.getDefaultInstance(props); // 根据参数配置，创建会话对象（为了发送邮件准备的）
        MimeMessage message = new MimeMessage(session);     // 创建邮件对象
        /*
         * 也可以根据已有的eml邮件文件创建 MimeMessage 对象
         * MimeMessage message = new MimeMessage(session, new FileInputStream("MyEmail.eml"));
         */

        // 2. From: 发件人
        //    其中 InternetAddress 的三个参数分别为: 邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
        //    真正要发送时, 邮箱必须是真实有效的邮箱。
        message.setFrom(new InternetAddress(addr, "USER_AA", DEFAULT_CHARSET));
        // 3. To: 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(addr, "USER_CC",DEFAULT_CHARSET));
        // 4. Subject: 邮件主题
        message.setSubject(THEME, "UTF-8");
        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(VerifyCodeUtils.generateVerifyCode(4), "text/html;charset=UTF-8");
        // 6. 设置显示的发件时间
        message.setSentDate(new Date());

        // 7. 保存前面的设置
        message.saveChanges();
	}
	
}

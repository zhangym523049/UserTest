package com.ym.test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestEverything {

	/*
	 * public static void main(String[] args){ //这个类主要是设置邮件 MailSenderInfo mailInfo
	 * = new MailSenderInfo(); mailInfo.setMailServerHost("smtp.qq.com");
	 * mailInfo.setMailServerPort("465"); mailInfo.setValidate(true);
	 * mailInfo.setUserName("1063150549@qq.com");
	 * mailInfo.setPassword("zym258456+++");//您的邮箱密码
	 * mailInfo.setFromAddress("1063150549@qq.com");
	 * mailInfo.setToAddress("353874626@qq.com");
	 * mailInfo.setSubject("设置邮箱标题 如http://www.guihua.org 中国桂花网");
	 * mailInfo.setContent("设置邮箱内容 如http://www.guihua.org 中国桂花网 是中国最大桂花网站==");
	 * //这个类主要来发送邮件 SimpleMailSender sms = new SimpleMailSender();
	 * sms.sendTextMail(mailInfo);//发送文体格式 }
	 */
	public static void main(String[] args) throws Exception {

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", "zym940325"), // 注册的用户名
				new NameValuePair("Key", "00ea92fb8ef27aaa8fb5"), // 注册成功后,登录网站使用的密钥
				new NameValuePair("smsMob", "13818698304"), // 手机号码
				new NameValuePair("smsText", "以后给我老实点哈") };// 设置短信内容
		post.setRequestBody(data);

		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
		System.out.println(result);
		post.releaseConnection();
	}
}
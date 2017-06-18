package com.amaker.shop.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 邮件发送的工具类
 * @author ChangchunLong
 *
 */
public class MailUtil {
     /**
      * 发送邮件的方法
      * @param to :收件人
      * @param code :激活码
      */
	public static void sendMail(String to, String code){
		/**
		 * 1.获得一个Session对象
		 * 2.创建一个代表邮件的对象Message
		 * 3.发送邮件transport
		 */
		//1. 获得连接对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@shop.com", "123");
			}
		});
		// 2.创建邮件对象:
		Message message = new MimeMessage(session);
		try {
			// 设置发件人:
			message.setFrom(new InternetAddress("service@shop.com"));
			//设置收件人
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			//抄送CC 密送BCC
			//设置标题
			message.setSubject("来自购物天堂丰田商城官方激活邮件");
			// 设置邮件正文:
			message.setContent("<h1>购物天堂传智商城官方激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://115.156.186.193:8080/onlineshop/user_active.action?code="+code+"'>http://115.156.186.193:8080/onlineshop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			// 3.发送邮件:
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package com.ccloomi.core.component.mail;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccloomi.core.component.mail.bean.Mail;


/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：MailSender
 * 类 描 述：邮件发送
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:13:42
 */
public class MailSender {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	private Session session;
	
	/**发件人姓名*/
	private String fromname;
	
	/**获取 发件人姓名*/
	public String getFromname() {
		return fromname;
	}

	/**设置 发件人姓名*/
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}

	/**获取 session*/
	public Session getSession() {
		return session;
	}

	/**设置 session*/
	public void setSession(Session session) {
		this.session = session;
	}
	public MailSender(Session session,String fromname){
		this.session=session;
		this.fromname=fromname;
	}
	/***
	 * 方法描述:发送邮件
	 * @param mail
	 * @return
	 */
	public boolean sendMail(Mail mail){
		try{
			MimeMessage mmsg=new MimeMessage(getSession());
			//设置收件地址
			if(!mail.isToAddressEmpty()){
				mmsg.setRecipients(RecipientType.TO,mail.getToAddress());
			}else{
				log.error("没有收件地址");
				return false;
			}
			//设置抄送地址
			if(!mail.isCcAddressEmpty()){
				mmsg.setRecipients(RecipientType.CC,mail.getCcAddress());
			}
			//设置发件人名称
			mmsg.setFrom(new InternetAddress(fromname));
			//设置主题
			mmsg.setSubject(mail.getSubject());
			if(mail.hasFilesToSend()){//有附件
				Multipart mt=new MimeMultipart();
				BodyPart contentPart=new MimeBodyPart();
				if(mail.isHtml()){
					contentPart.setDataHandler(new DataHandler(mail.getHtmlBody(),"text/html;charset=UTF-8"));
				}else{
					contentPart.setDataHandler(new DataHandler(mail.getBody(), "text/plain;charset=UTF-8"));
				}
				mt.addBodyPart(contentPart);
				//添加附件
				for(String f:mail.getFileList()){
					File file=new File(f);
					BodyPart bp=new MimeBodyPart();
					DataSource ds=new FileDataSource(file);
					bp.setDataHandler(new DataHandler(ds));
					try{
						bp.setFileName(MimeUtility.encodeText(file.getName()));
					}catch(Exception e){
						log.error("邮件附件名设置异常", e);
					}
					mmsg.setContent(mt);
				}
			}else{//无附件
				if(mail.isHtml()){
					mmsg.setContent(mail.getHtmlBody(), "text/html;charset=UTF-8");
				}else{
					mmsg.setContent(mail.getBody(), "text/plain;charset=UTF-8");
				}
			}
			mmsg.saveChanges();
			Transport.send(mmsg);
			return true;
		}catch(Exception e){
			log.error("发送邮件失败", e);
			return false;
		}
	}
}

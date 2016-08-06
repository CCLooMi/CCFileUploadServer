package com.ccloomi.core.component.mail;

import java.util.Properties;

import javax.mail.Session;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：MailSenderFactory
 * 类 描 述：MailSender工厂类
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:14:06
 */
public class MailSenderFactory {
	private static MailSenderFactory mailSenderFactory;
	private Properties properties;
	/**邮件服务器地址*/
	private String server;
	/**服务器端口*/
	private String port;
	/**用户名*/
	private String username;
	/**密码*/
	private String password;
	/**发件人姓名*/
	private String fromname;
	
	public static synchronized MailSenderFactory getInstance(){
		if(mailSenderFactory==null){
			mailSenderFactory=new MailSenderFactory();
		}
		return mailSenderFactory;
	}
	
	public void init(){
		properties=new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", server);
		properties.put("mail.smtp.port", port);
	}
	public Session getMailSession(){
		return Session.getInstance(properties, new MailAuthenticator(username, password));
	}
	/**
	 * 描述：获取邮件发送类
	 * 作者：Chenxj
	 * 日期：2015年7月13日 - 下午9:43:29
	 * @return
	 */
	public MailSender getMailSender(){
		return new MailSender(getMailSession(), (fromname==null)?username:(fromname+"<"+username+">"));
	}
	
	/**获取 properties*/
	public Properties getProperties() {
		return properties;
	}
	/**设置 properties*/
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**获取 server*/
	public String getServer() {
		return server;
	}
	/**设置 server*/
	public void setServer(String server) {
		this.server = server;
	}
	/**获取 port*/
	public String getPort() {
		return port;
	}
	/**设置 port*/
	public void setPort(String port) {
		this.port = port;
	}
	/**获取 username*/
	public String getUsername() {
		return username;
	}
	/**设置 username*/
	public void setUsername(String username) {
		this.username = username;
	}
	/**获取 password*/
	public String getPassword() {
		return password;
	}
	/**设置 password*/
	public void setPassword(String password) {
		this.password = password;
	}
	/**获取 fromname*/
	public String getFromname() {
		return fromname;
	}
	/**设置 fromname*/
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	
}

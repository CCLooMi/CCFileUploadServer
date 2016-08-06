package com.ccloomi.core.component.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：MailAuthenticator
 * 类 描 述：邮箱权限验证
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:13:32
 */
public class MailAuthenticator extends Authenticator{
	/**用户名*/
	private String username;
	/**密码*/
	private String password;
	/**获取 用户名*/
	public String getUsername() {
		return username;
	}
	/**设置 用户名*/
	public void setUsername(String username) {
		this.username = username;
	}
	/**获取 密码*/
	public String getPassword() {
		return password;
	}
	/**设置 密码*/
	public void setPassword(String password) {
		this.password = password;
	}
	
	public MailAuthenticator(String username,String password){
		this.username=username;
		this.password=password;
	}
	
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}

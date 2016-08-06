package com.ccloomi.system.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.server.standard.SpringConfigurator;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：SocketAppender
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:21:07
 */
@ServerEndpoint(value="/socket/log",configurator=SpringConfigurator.class)
public class SocketAppender extends AppenderBase<ILoggingEvent>{
	protected static List<SocketAppender>appenders=new ArrayList<>();
	protected PatternLayoutEncoder encoder;
	protected Session session;
	/**获取 appenders*/
	public static List<SocketAppender> getAppenders() {
		return appenders;
	}
	/**设置 appenders*/
	public static void setAppenders(List<SocketAppender> appenders) {
		SocketAppender.appenders = appenders;
	}
	/**获取 encoder*/
	public PatternLayoutEncoder getEncoder() {
		return encoder;
	}
	/**设置 encoder*/
	public void setEncoder(PatternLayoutEncoder encoder) {
		this.encoder = encoder;
	}
	public void sendMessage(String message){
		try {this.session.getBasicRemote().sendText(message);}
		catch (IOException e) {
			SocketAppender.appenders.remove(this);
		}
	}
	@OnOpen
	public void onOpen(Session session){
		this.session=session;
		SocketAppender.appenders.add(this);
	}
	@OnClose
	public void onClose(){
		SocketAppender.appenders.remove(this);
	}
	@OnError
	public void onError(Throwable t){
		t.printStackTrace();
		SocketAppender.appenders.remove(this);
	}
	@Override
	public void start(){
		if(this.encoder==null){
			addError("No encoder set for the appender named ["+ name +"].");
			return;
		}else{
			this.encoder.setPattern("<span class=\"datetime\">%d{yyyy-MM-dd HH:mm:ss.SSS}</span><span class=\"thread\">[%thread]</span><span class=\"level\">%-5level</span><span class=\"logger\">%logger{5}</span> :: <span class=\"msg\">%msg</span>%n");
			this.encoder.start();
		}
		super.start();
	}
	@Override
	protected void append(ILoggingEvent eventObject) {
		for(SocketAppender appender:SocketAppender.appenders){
			appender.sendMessage(this.encoder.getLayout().doLayout(eventObject));
		}
	}
}

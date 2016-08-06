package com.ccloomi.system.fileUpload.server;

import java.io.File;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.ccloomi.core.component.fileUpload.bean.FileInfo;
import com.ccloomi.core.component.fileUpload.server.BaseFileUploadServer;
import com.ccloomi.core.util.StringUtil;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：UploadServer
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月14日-下午8:47:34
 */
@ServerEndpoint(value="/springSocket/fileup",configurator=SpringConfigurator.class)
public class UploadServer extends BaseFileUploadServer<SimpleFileTarget>{

	@Value("${ZIMG.path}")
	private String basePath;
	
	@Override
	public boolean isFileOK(FileInfo fileInfo){
		boolean isOK=false;
		String id=fileInfo.getFileId();
		long v1=StringUtil.strtol(id.substring(0, 3), 16);
		long v2=StringUtil.strtol(id.substring(3, 6), 16);
		StringBuilder pathSB=new StringBuilder(basePath);
		pathSB.append("/").append(v1/4).append("/").append(v2/4)
		.append("/").append(id)
		.append("/0*0");
		if(new File(pathSB.toString()).exists()||fileInfo.getFileSize()==0){
			isOK=true;
		}
		return isOK;
	}

	@Override
	public String getBathPath() {
		return basePath;
	}
}

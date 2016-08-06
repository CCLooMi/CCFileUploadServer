package com.ccloomi.system.fileUpload.server;

import com.ccloomi.core.component.fileUpload.core.BaseFileTarget;
import com.ccloomi.core.util.StringUtil;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：SimpleFileTarget
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月17日-下午6:32:07
 */
public class SimpleFileTarget extends BaseFileTarget{
	
	@Override
	public String getFilePath() {
		//为了生成zimg的目录结构
		String id=getFileInfo().getFileId();
		long v1=StringUtil.strtol(id.substring(0, 3), 16);
		long v2=StringUtil.strtol(id.substring(3, 6), 16);
		return "/"+v1/4+"/"+v2/4+"/"+id+"/";
	}

	@Override
	public String getFileName() {
		return "0*0";
	}
}

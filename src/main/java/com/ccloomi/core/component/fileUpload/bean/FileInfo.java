package com.ccloomi.core.component.fileUpload.bean;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：FileInfo
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月16日-下午9:40:33
 */
public class FileInfo {
	private String fileId;
	private String fileName;
	private long fileSize;
	private String fileInfo;
	/**获取 fileId*/
	public String getFileId() {
		return fileId;
	}
	/**设置 fileId*/
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	/**获取 fileName*/
	public String getFileName() {
		return fileName;
	}
	/**设置 fileName*/
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**获取 fileSize*/
	public long getFileSize() {
		return fileSize;
	}
	/**设置 fileSize*/
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	/**获取 fileInfo*/
	public String getFileInfo() {
		return fileInfo;
	}
	/**设置 fileInfo*/
	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
	}
}

package com.ccloomi.core.component.fileUpload.bean;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：UploadCommand
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月14日-下午10:32:07
 */
public class UploadCommand {
	private final String type="uploadCommand";
	private String fileId;
	private int index;
	private long indexStart;
	private long indexEnd;
	private long blobSize;
	private float completePercent;
	public static UploadCommand getSucccessCommand(String fileId){
		UploadCommand uc=new UploadCommand();
		uc.setBlobSize(-1);
		uc.setCompletePercent(1);
		uc.setFileId(fileId);
		uc.setIndex(-1);
		uc.setIndexEnd(-1);
		uc.setIndexStart(-1);
		return uc;
	}
	/**获取 fileId*/
	public String getFileId() {
		return fileId;
	}
	/**设置 fileId*/
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	/**获取 index*/
	public int getIndex() {
		return index;
	}
	/**设置 index*/
	public void setIndex(int index) {
		this.index = index;
	}
	/**获取 indexStart*/
	public long getIndexStart() {
		return indexStart;
	}
	/**设置 indexStart*/
	public void setIndexStart(long indexStart) {
		this.indexStart = indexStart;
	}
	/**获取 indexEnd*/
	public long getIndexEnd() {
		return indexEnd;
	}
	/**设置 indexEnd*/
	public void setIndexEnd(long indexEnd) {
		this.indexEnd = indexEnd;
	}
	/**获取 blobSize*/
	public long getBlobSize() {
		return blobSize;
	}
	/**设置 blobSize*/
	public void setBlobSize(long blobSize) {
		this.blobSize = blobSize;
	}
	/**获取 completePercent*/
	public float getCompletePercent() {
		return completePercent;
	}
	/**设置 completePercent*/
	public void setCompletePercent(float completePercent) {
		this.completePercent = completePercent;
	}
	/**获取 typeId*/
	public String getType() {
		return type;
	}
}

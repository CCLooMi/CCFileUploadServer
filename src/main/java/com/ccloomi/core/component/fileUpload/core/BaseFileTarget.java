package com.ccloomi.core.component.fileUpload.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccloomi.core.component.fileUpload.bean.FileInfo;
import com.ccloomi.core.component.fileUpload.bean.UploadCommand;
import com.ccloomi.core.component.fileUpload.server.BaseFileUploadServer;
import com.ccloomi.core.constant.Constant;


/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BaseFileTarget
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月16日-下午9:17:29
 */
public abstract class BaseFileTarget {
	protected Logger log=LoggerFactory.getLogger(getClass());
	protected byte[] blobCompleteDetail;
	/**本地文件名*/
	protected String fileName;
	protected String basePath;
	/**用于tempFile重命名*/
	protected File file;
	protected File siFile;
	protected File tempFile;
	protected RandomAccessFile raFile;
	protected FileChannel fileChannel;
	/**上传该文件的客户端列表*/
	protected Set<BaseFileUploadServer<? extends BaseFileTarget>>uploadServerList;
	protected FileInfo fileInfo;
	protected long completeFileSize=0;
	/**文件上传进度*/
	protected float completePercent;
	public BaseFileTarget(){
		super();
	}
	public BaseFileTarget(FileInfo fileInfo,String basePath){
		this.initTarget(fileInfo, basePath);
	}
	/**文件相对于basePath的路径*/
	public abstract String getFilePath();
	public abstract String getFileName();
	public FileInfo getFileInfo(){
		return this.fileInfo;
	}
	public Set<BaseFileUploadServer<? extends BaseFileTarget>> getUploadServerList(){
		return this.uploadServerList;
	}
	public void initTarget(FileInfo fileInfo,String basePath){
		this.fileInfo=fileInfo;
		this.basePath=checkDirExistThenReturn(basePath+this.getFilePath());
		int n=0;
		if(fileInfo.getFileSize()%Constant.blobSize==0){
			n=(int) (fileInfo.getFileSize()/Constant.blobSize);
		}else{
			n=(int) (fileInfo.getFileSize()/Constant.blobSize+1);
		}
		this.blobCompleteDetail=new byte[n];
		for(int i=0;i<n;i++){
			this.blobCompleteDetail[i]=0;
		}
		this.completePercent=(float)completeFileSize/(float)this.fileInfo.getFileSize();
		this.fileName=getFileName();
		this.file=new File(this.basePath+this.fileName);
		//前面家点隐藏文件
		this.siFile=new File(this.basePath+"."+this.fileInfo.getFileId()+".si");
		this.tempFile=new File(this.basePath+"."+this.fileInfo.getFileId()+".temp");
		this.uploadServerList=new HashSet<>();
		if(this.siFile.exists()){
			try{
				FileInputStream in=new FileInputStream(siFile);
				in.read(blobCompleteDetail);
				in.close();
			}catch(Exception e){
				log.error("读取进度异常", e);
			}
		}
		this.openFileWriteAccessChannel();
	}
	
	public synchronized UploadCommand getUploadCommand(){
		for(int i=0;i<blobCompleteDetail.length;i++){
			if(blobCompleteDetail[i]==0){
				UploadCommand command=new UploadCommand();
				command.setFileId(fileInfo.getFileId());
				command.setIndex(i);
				command.setIndexStart(i*Constant.blobSize);
				command.setBlobSize(Constant.blobSize);
				long indexEnd=(command.getIndexStart()+command.getBlobSize())>fileInfo.getFileSize()
						?fileInfo.getFileSize()
						:(command.getIndexStart()+command.getBlobSize());
				command.setIndexEnd(indexEnd);
				command.setCompletePercent(completePercent);
				blobCompleteDetail[i]=-1;
				return command;
			}
		}
		return UploadCommand.getSucccessCommand(fileInfo.getFileId());
	}
	public synchronized UploadCommand saveData(ByteBuffer bb,UploadCommand command){
		if(command.getIndex()!=-1){
			try{
				this.fileChannel.write(bb, command.getIndexStart());
				this.blobCompleteDetail[command.getIndex()]=1;
				this.completeFileSize+=command.getBlobSize();
				this.completePercent=(float)completeFileSize/(float)this.fileInfo.getFileSize();
				if(this.completePercent>=1){
					this.fileUploadComplete();
				}
				return this.getUploadCommand();
			}catch(Exception e){
				log.error("保存数据失败", e);
				return null;
			}
		}else{
			return command;
		}
	}
	public void addUploadServer(BaseFileUploadServer<? extends BaseFileTarget>server){
		this.uploadServerList.add(server);
	}
	public void removeUploadServer(BaseFileUploadServer<? extends BaseFileTarget>server){
		this.uploadServerList.remove(server);
	}
	public void cancelCommand(UploadCommand command){
		this.blobCompleteDetail[command.getIndex()]=0;
	}
	public void saveProgress(){
		log.info("保存文件[{}]进度...",this.fileInfo.getFileName());
		try{
			FileOutputStream out=new FileOutputStream(siFile);
			out.write(blobCompleteDetail);
			out.flush();
			out.close();
			log.info("保存文件[{}]进度完成",this.fileInfo.getFileName());
		}catch(Exception e){
			log.error("保存进度异常", e);
		}
	}
	public void release(){
		this.closeFileWriteAccessChannel();
	}
	/**
	 * 描述：检查文件路径是否存在，如果不存在则创建
	 * 作者：Chenxj
	 * 日期：2016年1月17日 - 下午12:44:46
	 * @param path
	 */
	private String checkDirExistThenReturn(String path){
		File file=new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		return path;
	}
	public void openFileWriteAccessChannel(){
		log.info("开启文件[{}]写入流...",this.fileInfo.getFileName());
		if(this.raFile==null&&this.fileChannel==null){
			try{
				this.raFile=new RandomAccessFile(this.tempFile, "rw");
				this.raFile.setLength(fileInfo.getFileSize());
				this.fileChannel=this.raFile.getChannel();
				log.info("开启文件[{}]写入流完成",this.fileInfo.getFileName());
			}catch(Exception e){
				log.error("Open File Write Access Channel异常",e);
			}
		}
	}
	private void closeFileWriteAccessChannel(){
		if(this.fileChannel.isOpen()){
			try{
				this.fileChannel.close();
				this.raFile.close();
			}catch(Exception e){
				log.error("Close File Write Access Channel异常", e);
			}finally {
				this.raFile=null;
				this.fileChannel=null;
			}
		}
	}
	private void fileUploadComplete(){
		this.closeFileWriteAccessChannel();
		log.info("关闭文件[{}]写入流",this.fileInfo.getFileName());
		this.tempFile.renameTo(this.file);
		log.info("重命名文件[{}]",this.fileInfo.getFileName());
		this.siFile.delete();
		log.info("删除文件[{}]进度纪录文件",this.fileInfo.getFileName());
	}
}

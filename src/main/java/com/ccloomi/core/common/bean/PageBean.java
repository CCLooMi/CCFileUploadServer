package com.ccloomi.core.common.bean;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：PageBean
 * 类 描 述：分页对象
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:09:19
 */
public class PageBean extends BaseBean{

	private static final long serialVersionUID = -343000405266227450L;
	
	/**分页大小*/
	private int pageSize;
	/**页码*/
	private int pageNumber;
	/**总数据条数*/
	private long totalNumber;
	
	public PageBean(){
		this.pageNumber=1;
		this.pageSize=10;
	}
	public PageBean(int pageSize,int pageNumber){
		this.pageSize=pageSize;
		this.pageNumber=pageNumber;
	}
	public PageBean(int pageSize,int pageNumber,long totalNumber){
		this.pageSize=pageSize;
		this.pageNumber=pageNumber;
		this.totalNumber=totalNumber;
	}
	
	/**
	 * 获取：分页大小
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 设置：分页大小
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * 获取：页码
	 */
	public int getPageNumber() {
		return pageNumber-1<0?0:pageNumber-1;
	}
	/**
	 * 设置：页码
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	/**
	 * 获取：总数据条数
	 */
	public long getTotalNumber() {
		return totalNumber;
	}
	/**
	 * 设置：总数据条数
	 */
	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}
}

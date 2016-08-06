package com.ccloomi.core.common.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：VisData
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:09:42
 */
public class VisData extends BaseBean{
	private static final long serialVersionUID = 6084612132784261572L;
	private List<Map<String, Object>>nodes=new ArrayList<Map<String,Object>>();
	private List<Map<String, Object>>edges=new ArrayList<Map<String,Object>>();
	/**
	 * 描述：添加一个node
	 * 作者：Chenxj
	 * 日期：2015年8月1日 - 下午2:04:18
	 * @param node
	 * @return
	 */
	public VisData addNode(Map<String, Object>node){
		this.nodes.add(node);
		return this;
	}
	/**
	 * 描述：添加一个edge
	 * 作者：Chenxj
	 * 日期：2015年8月1日 - 下午2:04:22
	 * @param edge
	 * @return
	 */
	public VisData addEdge(Map<String, Object>edge){
		this.edges.add(edge);
		return this;
	}
	/**
	 * 描述：添加多个node
	 * 作者：Chenxj
	 * 日期：2015年8月1日 - 下午2:04:25
	 * @param nodes
	 * @return
	 */
	public VisData addNodes(Collection<? extends Map<String, Object>>nodes){
		this.nodes.addAll(nodes);
		return this;
	}
	/**
	 * 描述：添加多个edge
	 * 作者：Chenxj
	 * 日期：2015年8月1日 - 下午2:04:30
	 * @param edges
	 * @return
	 */
	public VisData addEdges(Collection<? extends Map<String, Object>>edges){
		this.edges.addAll(edges);
		return this;
	}
	/**
	 * 方法描述：添加vidData
	 * 作        者：Chenxj
	 * 日        期：2015年8月5日-下午1:59:02
	 * @param vd
	 * @return
	 */
	public VisData addVisData(VisData vd){
		return this.addNodes(vd.nodes).addEdges(vd.edges);
	}
	/**获取 nodes*/
	public List<Map<String, Object>> getNodes() {
		return nodes;
	}
	/**设置 nodes*/
	public void setNodes(List<Map<String, Object>> nodes) {
		this.nodes = nodes;
	}
	/**获取 edges*/
	public List<Map<String, Object>> getEdges() {
		return edges;
	}
	/**设置 edges*/
	public void setEdges(List<Map<String, Object>> edges) {
		this.edges = edges;
	}
	
}

package com.syzc.RestfulDemo.model;
/**
 * 
 * @author 25659
 * 任务模型
 */
public class Task 
{
	private int id;
	private String content;
	private String createdTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	@Override
	public String toString()
	{
		return  "{id:"+ id +",content:"+content+",createdTime:"+createdTime+"}";
	}
}

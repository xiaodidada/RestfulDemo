package com.syzc.RestfulDemo;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.syzc.RestfulDemo.RestfulDemoApplication;
import com.syzc.RestfulDemo.data.TasksData;
/**
 * 
 * @author 25659
 * 测试数据处理部分
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskDataTest {

	@Autowired
	private TasksData tasksData;
	
	//【通过id获取任务】测试:id=1
	@Test
	public void test_getById1() 
	{
		JSONObject json = tasksData.getById(1);
		assertEquals("{\"createdTime\":\"2020-05-15T00:00:00Z\",\"id\":1,\"content\":\"第二次测试\"}",json.toJSONString());
	}
	//【通过id获取任务】测试:id=-1 -不存在
	@Test
	public void test_getById2() 
	{
		JSONObject json = tasksData.getById(-1);
		assertEquals(null,json);
	}
	//【通过id删除任务】 测试：id=1
	@Test
	public void test_deleteById1() 
	{
		boolean check = tasksData.deleteById(0);
		assertEquals(true,check);
	}
	//【通过id删除任务】测试:id=-1 -不存在
	@Test
	public void test_deleteById2() 
	{
		boolean check = tasksData.deleteById(-1);
		assertEquals(false,check);
	}
}

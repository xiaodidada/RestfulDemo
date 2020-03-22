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
 * �������ݴ�����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskDataTest {

	@Autowired
	private TasksData tasksData;
	
	//��ͨ��id��ȡ���񡿲���:id=1
	@Test
	public void test_getById1() 
	{
		JSONObject json = tasksData.getById(1);
		assertEquals("{\"createdTime\":\"2020-05-15T00:00:00Z\",\"id\":1,\"content\":\"�ڶ��β���\"}",json.toJSONString());
	}
	//��ͨ��id��ȡ���񡿲���:id=-1 -������
	@Test
	public void test_getById2() 
	{
		JSONObject json = tasksData.getById(-1);
		assertEquals(null,json);
	}
	//��ͨ��idɾ������ ���ԣ�id=1
	@Test
	public void test_deleteById1() 
	{
		boolean check = tasksData.deleteById(0);
		assertEquals(true,check);
	}
	//��ͨ��idɾ�����񡿲���:id=-1 -������
	@Test
	public void test_deleteById2() 
	{
		boolean check = tasksData.deleteById(-1);
		assertEquals(false,check);
	}
}

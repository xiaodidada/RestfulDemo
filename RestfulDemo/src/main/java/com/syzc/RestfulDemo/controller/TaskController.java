package com.syzc.RestfulDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.syzc.RestfulDemo.data.TasksData;
import com.syzc.RestfulDemo.model.Task;

@RestController
public class TaskController 
{
	private final TasksData tasksData;

	@Autowired
	public TaskController(TasksData tasksData)
	{
		this.tasksData = tasksData;
	}
	
	@PostMapping(value = "/api/tasks")
	public String post(@RequestParam String content,@RequestParam String createdTime,@RequestParam int id)
	{//����
		Task task = new Task();
		task.setId(id);
		task.setContent(content);
		task.setCreatedTime(createdTime);
		JSONObject json = new JSONObject();
		if(tasksData.save(task))
		{
			System.out.println("�û����� ����ɹ�\n"+task.toString());
			json.put("data", task);
			json.put("status", "��ӳɹ�");
		}
		else
			json.put("status", "���ʧ�ܣ�id�ظ�");
		return json.toJSONString();
	}
	@GetMapping(value = "/api/tasks")
	public String getAll()
	{//��ȡȫ���������б�
		JSONArray tasks = null;
		JSONObject json = new JSONObject();
		tasks = tasksData.findAll();
		if(tasks == null)
			json.put("status", "��ȡ�����б�ʧ��");
		else
		{
			json.put("data", tasks);
			json.put("status", "�ɹ���ȡ�����б�");
		}
		return json.toJSONString();
	}
	@GetMapping(value = "/api/task")
	public String getById(@RequestParam int id)
	{//����һ��ָ��ID��������
		JSONObject json = new JSONObject();
		JSONObject task = tasksData.getById(id);
		if(task == null)
			json.put("status", "δ�ҵ���Ӧ������");
		else
		{
			json.put("data", task);
			json.put("status", "�ɹ���ȡ��Ӧ����");
		}
		return json.toJSONString();
	}
	@PostMapping(value = "/api/task")
	public String deleteById(@RequestParam int id)
	{//����һ��ָ��ID��������
		JSONObject json = new JSONObject();
		boolean check = tasksData.deleteById(id);
		if(check)
			json.put("status", "�ɹ�ɾ��IDΪ"+id+"������");
		else
			json.put("status", "δ�ҵ�IDΪ"+id+"�������޷�ִ��ɾ������");
		return json.toJSONString();
	}
}

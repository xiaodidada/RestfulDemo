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
	{//新增
		Task task = new Task();
		task.setId(id);
		task.setContent(content);
		task.setCreatedTime(createdTime);
		JSONObject json = new JSONObject();
		if(tasksData.save(task))
		{
			System.out.println("用户对象 保存成功\n"+task.toString());
			json.put("data", task);
			json.put("status", "添加成功");
		}
		else
			json.put("status", "添加失败，id重复");
		return json.toJSONString();
	}
	@GetMapping(value = "/api/tasks")
	public String getAll()
	{//获取全部的任务列表
		JSONArray tasks = null;
		JSONObject json = new JSONObject();
		tasks = tasksData.findAll();
		if(tasks == null)
			json.put("status", "获取任务列表失败");
		else
		{
			json.put("data", tasks);
			json.put("status", "成功获取任务列表");
		}
		return json.toJSONString();
	}
	@GetMapping(value = "/api/task")
	public String getById(@RequestParam int id)
	{//返回一个指定ID的任务列
		JSONObject json = new JSONObject();
		JSONObject task = tasksData.getById(id);
		if(task == null)
			json.put("status", "未找到对应的任务");
		else
		{
			json.put("data", task);
			json.put("status", "成功获取对应任务");
		}
		return json.toJSONString();
	}
	@PostMapping(value = "/api/task")
	public String deleteById(@RequestParam int id)
	{//返回一个指定ID的任务列
		JSONObject json = new JSONObject();
		boolean check = tasksData.deleteById(id);
		if(check)
			json.put("status", "成功删除ID为"+id+"的任务");
		else
			json.put("status", "未找到ID为"+id+"的任务，无法执行删除操作");
		return json.toJSONString();
	}
}

package com.syzc.RestfulDemo.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.syzc.RestfulDemo.model.Task;
@Repository
public class TasksData {
	/**
	 * 存储到tasksdata.json文件
	 */
	private final static String path = "src/main/resources/tasksdata.json";
	/**
	 * 
	 * @param task
	 * @return 保存成功返回true,否则返回false
	 */
	public static String readFile()
	{
		FileReader fileReader = null;
		BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
	    try {
	    	fileReader = new FileReader(path);
	    	br = new BufferedReader(fileReader);
	        String str = null;
	        
	        while((str=br.readLine())!=null) {
                sb.append(str);
            }
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally
		{
			try {
				br.close();
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    return sb.toString();
	}
	/**
	 * 写入文件
	 * @return
	 */
	public static String writeFile(String json)
	{
		File file = null;
        FileWriter fileWriter = null;
        BufferedWriter bw = null;
        StringBuffer sb = new StringBuffer();
	    try {
	    	file = new File(path);
	        fileWriter = new FileWriter(file);
	        bw=new BufferedWriter(fileWriter);
	        bw.write(json);  
            // bw.newLine();  
            bw.flush();  
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				bw.close();
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    return sb.toString();
	}
	public boolean save(Task task)
	{
		JSONArray jsonArray = JSONArray.parseArray(readFile());
		if (jsonArray != null)//文件不为空
		{
			for(int i=0;i<jsonArray.size();i++)
			{
	            JSONObject json = jsonArray.getJSONObject(i);
	            int id =(int)json.get("id"); //得到每个task中的id值
	            if(id==task.getId()){
	            	return false;//id已存在
	            }
			}
			jsonArray.add(JSONObject.toJSON(task));	
		}
		else//文件为空
		{
			jsonArray = new JSONArray();
			jsonArray.add(JSONObject.toJSON(task));
		}
		writeFile(jsonArray.toJSONString());
		return true;
	}
	/**
	 * 返回所有任务列表
	 * @return
	 */
	public JSONArray findAll()
	{
		JSONArray jsonArray = JSONArray.parseArray(readFile());
		if(jsonArray == null)
			return null;
		return jsonArray;
		
	}

}

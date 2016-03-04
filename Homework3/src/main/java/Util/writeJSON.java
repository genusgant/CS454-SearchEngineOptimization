package Util;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.Doc;
import model.Page;
import model.TDF;
import model.TF;


public class writeJSON {
	
     public static void main(String[] args) {
     }
     
     public static void execute(Page p)
     {

	JSONObject obj = new JSONObject();
	obj.put("name", "mkyong.com");
//	obj.put("age", new Integer(100));
//
//	JSONArray list = new JSONArray();
//	list.add("msg 1");
//	list.add("msg 2");
//	list.add("msg 3");

	obj.put("messages", p);

	try {
		
		File file = new File("C:/DRIVE/test.json");

		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fileWriter = new FileWriter(file, true);
		fileWriter.write(obj.toJSONString());
		fileWriter.flush();
		fileWriter.close();

	} catch (IOException e) {
		e.printStackTrace();
	}

	//System.out.print(obj);

     }
     
     public static void execute(Doc d)
     {

	JSONObject obj = new JSONObject();
	
	
	
	JSONArray list = new JSONArray();
	
	for( TF t : d.getWordList())
	{
		JSONObject obj1 = new JSONObject();
		obj1.put(t.getWord(), t.getCount());
		list.add(obj1);
	}
	
	obj.put( d.getsNo(), list);
//	obj.put("age", new Integer(100));

//	JSONArray list = new JSONArray();
//	list.add("msg 1");
//	list.add("msg 2");
//	list.add("msg 3");
//
//	obj.put("messages", list);

	try {
		
		
		File file = new File("C:/DRIVE/Doc/"+d.getsNo()+".json");

		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fileWriter = new FileWriter(file, true);
		fileWriter.write(obj.toJSONString());
		fileWriter.flush();
		fileWriter.close();

	} catch (IOException e) {
		e.printStackTrace();
	}

	//System.out.print(obj);

     }
     
    public static void execute(ConcurrentMap<String, ArrayList<TDF>> input)
    {
    	
    	System.out.println("input  ....."+input);
    	 
    	JSONObject obj = new JSONObject();
    	 
    	Iterator it = input.entrySet().iterator();
    	ArrayList<TDF> list = new ArrayList<TDF>();
    	int counter = 0;
			
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
				
			String word = "";
			word = (String) pairs.getKey();
			list = (ArrayList<TDF>) pairs.getValue();
				
			JSONArray jlist = new JSONArray();
				
			for( TDF t : list)
			{
				JSONObject obj1 = new JSONObject();
				obj1.put(t.getsNO(), t.getCount());
				jlist.add(obj1);
									
			}		
			obj.put(word, jlist);
			
		}


		try {
	
			FileWriter file = new FileWriter("C:/DRIVE/big.json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
    }

}
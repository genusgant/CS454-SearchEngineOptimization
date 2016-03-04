package Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import model.Page;

public class writeExtractJSON {

	@SuppressWarnings("unchecked")
	public static void execute(Page p) throws IOException
	{
		JSONObject obj = new JSONObject();
		obj.put("sNo", p.getsNo());
		
		obj.put("id", p.getId());
		
		obj.put("url", p.getUrl());
		
		obj.put("title", p.getTitle());
		
		obj.put("path", p.getPath());
		
		obj.put("crawldate", p.getCrawldate());
		
		obj.put("bodytext", p.getBodytext());
		
		obj.put("description", p.getDescription());
		
		obj.put("keywords", p.getKeywords());
		
		obj.put("extractdate", p.getExtractdate());
		
		JSONArray metadata = new JSONArray();
		for(String s: p.getMetadata())
			metadata.add(s);
		obj.put("metadata", metadata);
		
		JSONArray img = new JSONArray();
		for(String s1: p.getImg())
			img.add(s1);
		obj.put("img", img);
		
		JSONArray script = new JSONArray();
		for(String s2:p.getScript())
			script.add(s2);
		obj.put("script", script);
		
		JSONArray imports = new JSONArray();
		for(String s3:p.getImports())
			imports.add(s3);
		obj.put("imports", imports);
		
		FileWriter file = new FileWriter("C:/Users/Ajay/Desktop/college/CS454");
		try{
			File f= new File("C:/Users/Ajay/Desktop/college/CS454");
			if (!f.exists()) 
				f.createNewFile();
			
			file.write(obj.toJSONString());
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally{
			file.flush();
			file.close();
		}
	}
	public void main(String[] args)
	{		
	}
}

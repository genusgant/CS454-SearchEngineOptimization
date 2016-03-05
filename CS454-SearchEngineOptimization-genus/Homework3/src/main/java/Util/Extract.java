package Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import MultiThreading.ExtractThread;
import model.Page;
import model.Raw;
import core.index;

public class Extract {
	
	final static Logger logger = LogManager.getLogger(Extract.class.getName());
	private Raw input;

	
	public Extract(Raw inp) {
		input = inp;
		// TODO Auto-generated constructor stub
	}

	public synchronized Page getDetails()
	{
		int sNo;
		String file ="";
		String path ="";
		
		Page p = new Page();
		
		sNo = input.getsNo();
		file = input.getFileName();
		path = input.getFilePath();
		
		try {
		
			
			
//			logger.trace("Extracting : "+path);
						
//			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			Date date = new Date();
			
			p.setsNo(sNo);
			p.setId(MD5(file));
			p.setUrl(file);
			p.setPath(path);
//			System.out.println("file :"+file+" md5 :"+MD5(file));
			
//			String filename = URLEncoder.encode(inputUrl, "UTF-8");
//			Path currentRelativePath = Paths.get("");
//			String s = currentRelativePath.toAbsolutePath().toString();
//						
//			WriteFile(s+"/data/"+filename+".html", ViewSource);
//			
//						
//			Document source = Jsoup.parse(ViewSource);
			
			File f = new File(path);
			Document source = Jsoup.parse(f, "UTF-8");
			
			
			String title = "";
			if(source.title()!=null)
				title = source.title();
			p.setTitle(title);
			
			String Bodytext = "";
			if(source.body().text()!=null)
				Bodytext = source.body().text();
			p.setBodytext(Bodytext);
			
			
			String description = "";
			try
			{
			if(source.select("meta[name=description]").get(0)!=null)
				description = source.select("meta[name=description]").get(0).attr("content");
			
			}catch(IndexOutOfBoundsException e){
//				logger.error("description not found");
			}
			p.setDescription(description);
			
			String keywords = "";
			try
			{
			if(source.select("meta[name=keywords]").first()!=null)
				keywords = source.select("meta[name=keywords]").first().attr("content");
			}catch(NullPointerException e){
			}
			p.setKeywords(keywords);
			
			
			
			Elements media = source.select("[src]");
			Elements imports = source.select("link[href]");
		    Elements links = source.select("a[href]");
		    Elements metadata = source.select("META");
		    
		    
		    ArrayList<String> Img = new ArrayList<String>();
		    ArrayList<String> Script = new ArrayList<String>();
		    ArrayList<String> Imports = new ArrayList<String>();
		    ArrayList<String> Links = new ArrayList<String>();
		    ArrayList<String> Metadata = new ArrayList<String>();
		    
		    
		    for (Element src : media) {
		    	if (src!= null && !src.equals(""))
	        	{
		    		if (src.tagName().equals("img"))
		            {
		    			if (!src.attr("abs:src").equals(""))
		    				Img.add(src.attr("abs:src"));
		            }
		            else
		            {
		            	if (!src.attr("abs:src").equals(""))
		            		Script.add(src.attr("abs:src"));
		            }
	        	}   	
	           
	        }
		    
		    p.setImg(Img);
		    p.setScript(Script);
		    
		    
		    for (Element link : imports) {
		    	
		    	if (link!= null && !link.equals(""))
	        	{
		    		if (!link.attr("abs:href").equals(""))
		    			Imports.add(link.attr("abs:href"));
	        	}
	            
	        }
		    
		    p.setImports(Imports);
		    
		    for (Element link : links) {
	        	if (link!= null && !link.equals(""))
	        	{
	        	
	        		if (!link.attr("abs:href").equals(""))
	        		Links.add(link.attr("abs:href"));
	        	
	        	}
	        }
		    p.setLinks(Links);
//		     
//		    
//		    for (Element meta : metadata)
//		    {
//		    	if (meta!= null && !meta.equals(""))
//	        	{
//		    		
//		    		for (Element m : meta.getAllElements())
//		    		{
//		    			if (m!= null && !m.equals(""))
//		    			{
//		    				Metadata.add(m.toString());
//		    			}
//		    			
//		    		}
//			        	
//	        	}
//		    }
//		 
//		
		
//		    index.UrlsExtracted.add(p);
	
		    
		} catch (IllegalArgumentException e) {
	    	
	    	logger.error(" IllegalArgumentException : Must supply a valid URL: " + path);
	        e.printStackTrace();
	        
	    }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
		
		
		
	}
	
	public static String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}
}

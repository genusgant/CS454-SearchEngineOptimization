package extractor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import crawler.Crawler;
import database.DbClient;
import model.Link;
import model.Raw;

public class Extractor {
	
	
	public static Queue<Raw> UrlsToExtract;
	
	final static Logger logger = LogManager.getLogger(Extractor.class.getName());
	
	private static final DbClient connect = new DbClient("starbucks"+"_ext");

	public Extractor(Queue<Raw> UrlsToExtract) {
		
		this.UrlsToExtract = UrlsToExtract;

	}
	
	
	
	public static synchronized void PollContinuously()
	{

//		logger.entry();		
		
		String inputUrl ="";
		int level;
		Elements ExtLinks;
			
		Raw toExt = new Raw();
		toExt = UrlsToExtract.poll();
		
		if (toExt!=null)
		{
			
			getDetails(toExt);
			
		}
		
//		logger.exit();
	}
	
	
	public static void getDetails(Raw input)
	{
		String inputUrl ="";
		String ViewSource ="";
		String CrawlDate ="";
		
		inputUrl = input.getUrl();
		ViewSource = input.getSource();
		CrawlDate = input.getDateTime();
		
		
		
		Elements hyperLinks = null;
		
		
		
		try {
			
			Document source;
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			   
			Date date = new Date();;
			
			source = Jsoup.connect(inputUrl).userAgent("User-Agent").timeout(10000).execute().parse();
            
			hyperLinks = source.select("a[href]");
			
//			hyperLinks = Jsoup.connect(inputUrl).userAgent("User-Agent").timeout(10000).execute().parse().select("a[href]");
			
			connect.Insert(inputUrl, source.outerHtml(),dateFormat.format(date).toString());
			
		} catch (UnsupportedMimeTypeException e) {
			
	        logger.error("Unsupported Mime type. Aborting crawling for URL: " + inputUrl);
	        e.printStackTrace();
	        
	    } catch (MalformedURLException e) {

	    	logger.error("Unsupported protocol for URL: " + inputUrl);
	        e.printStackTrace();
	        
	    } catch (HttpStatusException e) {
	    	
	    	logger.error("Error (status=" + e.getStatusCode() + ") fetching URL: " + inputUrl);
	        e.printStackTrace();
	        
	    }catch (IllegalArgumentException e) {
	    	
	    	logger.error(" IllegalArgumentException : Must supply a valid URL: " + inputUrl);
	        e.printStackTrace();
	        
	    }catch (IOException e) {
	    	
	    	logger.error("Timeout fetching URL: " + inputUrl);
	        e.printStackTrace();
	        
	    }
		
		
	}

	
	
	

}

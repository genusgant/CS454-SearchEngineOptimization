package crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import model.Link;

public class Crawler {
	
	final static Logger logger = LogManager.getLogger(Crawler.class.getName());
	
	private String inputUrl;
	private int level;
	private List[] ExtLinks;
	private Document source;
	public static Queue<Link> UrlsToCrawl;
	public static Queue<Link> UrlsCrawled;
	
	public Crawler(Link InitialUrl, Queue<Link> UrlsToCrawl, Queue<Link> UrlsCrawled)
	{
		
		this.inputUrl = InitialUrl.getLink();
		this.level = InitialUrl.getLevel();
		this.UrlsToCrawl = UrlsToCrawl;
		this.UrlsCrawled = UrlsCrawled;
		
		getInnerUrl();
	}
	
	public Crawler(Queue<Link> UrlsToCrawl, Queue<Link> UrlsCrawled)
	{
		

	}
	
	public Crawler()
	{
		
	}
	
	public void getInnerUrl()
	{
		try {
			Elements hyperLinks;			
			
			source = Jsoup.connect(inputUrl).userAgent("User-Agent").timeout(10000).execute().parse();
            
			hyperLinks = source.select("a[href]");
			
			Link cl = new Link();
			cl.setLevel(this.level);
			cl.setLink(inputUrl);
			UrlsCrawled.add(cl);
			
			for (Element link : hyperLinks) {
				System.out.println("\nlink : " + ((Node)link).attr("abs:href"));
//	//			list.add(((Node)link).attr("abs:href"));
				Link l = new Link();
				l.setLevel(this.level+1);
				l.setLink(((Node)link).attr("abs:href"));
				UrlsToCrawl.add(l);
//				System.out.println("text : " + ((org.jsoup.nodes.Element) link).text());	
			}
			
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
		
	}
	
	public void PollContinuously()
	{
		int depth = 1;
		logger.entry();
		
		while (UrlsToCrawl.peek()!= null )
		{
			
		Link toCrawl = new Link();
		toCrawl = UrlsToCrawl.poll();
		this.inputUrl = toCrawl.getLink();
		this.level = toCrawl.getLevel();
		if (depth>=this.level)
		{
			boolean flag = true; // hash set find method
			
			for(Link l : UrlsCrawled)
			{
				if(l.getLink().equals(inputUrl))
				{
					flag = false;	
				}
			}
		
			if (flag)
			{
				getInnerUrl();
			}
		}
		
		}
		logger.exit();
	}
	
	public String getInputUrl() {
		return inputUrl;
	}
	public void setInputUrl(String inputUrl) {
		this.inputUrl = inputUrl;
	}
	public List[] getExtLinks() {
		return ExtLinks;
	}
	public void setExtLinks(List[] extLinks) {
		ExtLinks = extLinks;
	}
	public Document getSource() {
		return source;
	}
	public void setSource(Document source) {
		this.source = source;
	}
	
}

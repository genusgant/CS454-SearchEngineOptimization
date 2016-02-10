package crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import model.Link;

public class Crawler {
	
	private String inputUrl;
	private int level;
	private List[] ExtLinks;
	private Document source;
	private Queue<Link> UrlsToCrawl = new LinkedList<Link>();
	private Queue<Link> UrlsCrawled = new LinkedList<Link>();
	
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
		
		this.UrlsToCrawl = UrlsToCrawl;
		this.UrlsCrawled = UrlsCrawled;

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
//				System.out.println("\nlink : " + ((Node)link).attr("abs:href"));
//	//			list.add(((Node)link).attr("abs:href"));
				Link l = new Link();
				l.setLevel(this.level+1);
				l.setLink(((Node)link).attr("abs:href"));
				UrlsToCrawl.add(l);
//				System.out.println("text : " + ((org.jsoup.nodes.Element) link).text());	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void PollContinuously()
	{
		Link toCrawl = new Link();
		toCrawl = UrlsToCrawl.poll();
		this.inputUrl = toCrawl.getLink();
		this.level = toCrawl.getLevel();
		boolean flag = true;
		
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

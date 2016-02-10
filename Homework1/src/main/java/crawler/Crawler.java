package crawler;

import java.io.IOException;
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
	private List[] ExtLinks;
	private Document source;
	
	public Crawler(Link InitialUrl, Queue<Link> UrlsToCrawl, Queue<Link> UrlsCrawled)
	{
		this.inputUrl = InitialUrl.getLink();
				
	}
	
	public void PollContinuously()
	{
		try {
			Elements hyperLinks;
			
			
			source = Jsoup.connect(inputUrl).userAgent("User-Agent").timeout(10000).execute().parse();

			hyperLinks = source.select("a[href]");
			
			for (Element link : hyperLinks) {
				System.out.println("\nlink : " + ((Node)link).attr("abs:href"));
				System.out.println("text : " + ((org.jsoup.nodes.Element) link).text());	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
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

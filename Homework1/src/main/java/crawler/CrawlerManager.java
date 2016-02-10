package crawler;

import java.util.LinkedList;
import java.util.Queue;

import model.Link;

public class CrawlerManager {
	
	public static Queue<Link> UrlsToCrawl = new LinkedList<Link>(); 
	public static Queue<Link> UrlsCrawled = new LinkedList<Link>(); 
	
	

	public static void main(String[] args) {
		
		Link InitialUrl = new Link();		
		InitialUrl.setLink("http://stackoverflow.com/questions/921262/how-to-download-and-save-a-file-from-internet-using-java");
		InitialUrl.setLevel(0);
		
		Crawler c1 = new Crawler(InitialUrl, UrlsToCrawl, UrlsCrawled);
		
		c1.PollContinuously();
		
		
		
		
		
		

	}

}

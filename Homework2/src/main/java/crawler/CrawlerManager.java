package crawler;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import MultiThreading.CrawlerThreadManager;
import model.Link;

public class CrawlerManager {
	
	final static Logger logger = LogManager.getLogger(CrawlerManager.class.getName());
	
	public static Queue<Link> UrlsToCrawl = new LinkedList<Link>(); 
	public static ArrayList<Link> UrlsCrawled = new ArrayList<Link>(); 
	public static ArrayList<String> UrlsSeen = new ArrayList<String>(); 
	
	public static String Coll ="cs454";
	
	public static int depth = 1;
	
	public static int counter = 0;
	public static int o_count = 0; 
	public static int n_count = 0;
	

	public static void main(String[] args) throws InterruptedException {
		
		Link InitialUrl = new Link();		
		InitialUrl.setLink("https://github.com/csula/cs454-winter-2016");
		InitialUrl.setLevel(0);
		int counter = 0;
		
		Crawler c1 = new Crawler(InitialUrl, UrlsToCrawl, UrlsCrawled, UrlsSeen);
//		count();
		CrawlerThreadManager t1 = new CrawlerThreadManager("Crawl Thread 1",c1);
		t1.start();
		Crawler c2 = new Crawler();
		CrawlerThreadManager t2 = new CrawlerThreadManager("Crawl Thread 2",c2);
		t2.start();
//		count();
		Crawler c3 = new Crawler();
		CrawlerThreadManager t3 = new CrawlerThreadManager("Crawl Thread 3",c3);
		t3.start();
//		count();
		Crawler c4 = new Crawler();
		CrawlerThreadManager t4 = new CrawlerThreadManager("Crawl Thread 4",c4);
		t4.start();
//		count();
		Crawler c5 = new Crawler();
		CrawlerThreadManager t5 = new CrawlerThreadManager("Crawl Thread 5",c5);
		t5.start();
		Crawler c6 = new Crawler();
		CrawlerThreadManager t6 = new CrawlerThreadManager("Crawl Thread 6",c6);
		t6.start();
		Crawler c7 = new Crawler();
		CrawlerThreadManager t7 = new CrawlerThreadManager("Crawl Thread 7",c7);
		t7.start();
		Crawler c8 = new Crawler();
		CrawlerThreadManager t8 = new CrawlerThreadManager("Crawl Thread 8",c8);
		t8.start();
		Crawler c9 = new Crawler();
		CrawlerThreadManager t9 = new CrawlerThreadManager("Crawl Thread 9",c9);
		t9.start();
		Crawler c10 = new Crawler();
		CrawlerThreadManager t10 = new CrawlerThreadManager("Crawl Thread 10",c10);
		t10.start();

		Thread.sleep(10000); 
		
		while (UrlsToCrawl.peek()!= null )
		{
			Thread.sleep(1000); 
		}

		for (Link l : UrlsCrawled)
		{

			logger.trace("crawled : "+l.getLevel()+" "+l.getLink());
			counter++;

		}
		logger.trace("Crawling completed Successfully...");
		logger.trace("Totally "+counter+" pages crawled... ");
		
		

	}
	
	
	

}

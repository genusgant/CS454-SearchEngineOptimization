package crawler;

import java.util.LinkedList;
import java.util.Queue;

import model.Link;

public class CrawlerManager {
	
	public static Queue<Link> UrlsToCrawl = new LinkedList<Link>(); 
	public static Queue<Link> UrlsCrawled = new LinkedList<Link>(); 
	
	public static int counter = 0;
	public static int o_count = 0; 
	public static int n_count = 0;
	

	public static void main(String[] args) {
		
		Link InitialUrl = new Link();		
		InitialUrl.setLink("http://stackoverflow.com/questions/921262/how-to-download-and-save-a-file-from-internet-using-java");
		InitialUrl.setLevel(0);
		int counter = 0;
		
		Crawler c1 = new Crawler(InitialUrl, UrlsToCrawl, UrlsCrawled);
		
		for(Link l : UrlsToCrawl)
		{
			l.getLink();
			System.out.println(l.getLevel()+" "+l.getLink());
			counter++;
		}
		System.out.println("total "+counter+" links" );
		
		Crawler c2 = new Crawler(UrlsToCrawl, UrlsCrawled);
		
		c2.PollContinuously();
		System.out.println("-------------------------------------------------------------------");
		counter =0;
		int count =0; 
		int count1 = 0;
		for(Link l : UrlsToCrawl)
		{
			l.getLink();
			System.out.println(l.getLevel()+" "+l.getLink());
			counter++;
			if (l.getLevel() == 1)
			{
				count++;
			}
			else
			{
				count1++;
			}
		}
		System.out.println("total "+counter+" new links. previous "+count+" now "+count1 );
	
		
		Crawler c3 = new Crawler(UrlsToCrawl, UrlsCrawled);
		
		c3.PollContinuously();
		count();
		c2.PollContinuously();
		count();
		
		
		

	}
	
	public static void count()
	{
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		int temp;
		temp =counter;
		counter =0;
		o_count=0;
		n_count=0;
		
		
		for(Link l : UrlsToCrawl)
		{
			l.getLink();
			System.out.println(l.getLevel()+" "+l.getLink());
			counter++;
			if (l.getLevel() == 1)
			{
				o_count++;
			}
			else
			{
				n_count++;
			}
		}
		System.out.println("total "+counter+" new links. previous "+o_count+" now "+n_count+" diff "+temp );
		System.out.println("-------------------------------------------------------------------");

	}
	

}

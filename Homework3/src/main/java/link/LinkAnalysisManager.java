package link;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import model.Page;

public class LinkAnalysisManager {
	
	public static ConcurrentLinkedQueue<Page> UrlsToAnalyse = new ConcurrentLinkedQueue<Page>();


	public LinkAnalysisManager(Queue<Page> UrlsExtracted) {
		super();
		UrlsToAnalyse = (ConcurrentLinkedQueue<Page>) UrlsExtracted;
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void execute()
	{
		System.out.println("total URL for Link analysis : "+UrlsToAnalyse.size());
		
		ConcurrentMap<String, ArrayList<String>> outgoing = new ConcurrentHashMap<>();
		
		for(Page p : UrlsToAnalyse)
		{
			String url="";
			ArrayList<String> links = new ArrayList<String>();
			
			url = p.getId();
			links = p.getLinks();
			
			for (Page p1 : UrlsToAnalyse)
				if (p.getId().equals(p1.getId()))
	            outgoing.put(url, links);
			
		}
		
		System.out.println("total URL in map : "+outgoing.size());
		
		for (Entry<String, ArrayList<String>> entry : outgoing.entrySet()) 
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		
		
//		outgoing.forEach((k,v)->System.out.println("Key : " + k + " Value : " + v));
	}
}

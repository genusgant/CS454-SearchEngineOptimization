package rank;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import rank.ReadWriteFile;
import index.IndexManager;
import model.Page;
import rank.PageRank;
import model.TDF;

public class LinkAnalysisManager {
	
	public static DecimalFormat df = new DecimalFormat("#.#####");
	
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
		
		ConcurrentMap<String, ArrayList<String>> incoming = new ConcurrentHashMap<>();
		
		for(Page p : UrlsToAnalyse)
		{
			String url="";
			
			
			url = p.getId();
			
			ArrayList<String> links = new ArrayList<String>();
			
			links = p.getLinks();	
			
			Set<String> hs = new HashSet<>();
			hs.addAll(links);
			
			p.setOutlinks(hs.size());
			
			for (String l : hs)
			{
				
				ArrayList<String> list;
				if(incoming.containsKey(l)){
				    // if the key has already been used,
				    // we'll just grab the array list and add the value to it
				    list = incoming.get(l);
				    list.add(url);
				} else {
				    // if the key hasn't been used yet,
				    // we'll create a new ArrayList<String> object, add the value
				    // and put it in the array list with the new key
				    list = new ArrayList<String>();
				    list.add(url);
				    incoming.put(l, list);
				}
				
//				outgoing.put(url, links);
			}        
			
			
			
			
//			for (Page p1 : UrlsToAnalyse)
//			{
//				if (!p.getId().equals(p1.getId()))
//				{
//					ArrayList<String> links = new ArrayList<String>();
//					links = p1.getLinks();	
//					
//				}
//			}
			
			
			
		}
		
		
//		for (Map.Entry<String, ArrayList<String>> entry : incoming.entrySet()) {
//			System.out.println(" ");
//			System.out.print("Key : " + entry.getKey() + " Value : ");
//			for (String s : entry.getValue())
//			{
//			System.out.print(s+" ");
//			}
//		}
		
		int count = 0;
		
		
		HashMap<String, PageRank> webpages = new HashMap<>();
		
		int i =0 ;
		for(Page p : UrlsToAnalyse)
		{
			System.out.println("Id : "+p.getId());
			
			
			ArrayList<String> list = new ArrayList<String>();
			
//			if(i<3)
//			{
//				System.out.println("Id : "+p.getId());
//				System.out.println("inlinks : "+p.getInlinks());
//				System.out.println("Inlinks : "+incoming.get(p.getId()));
//				System.out.println("inlinks count : "+p.getInlinks().size());
//			}
			i++;
			
			list = incoming.get(p.getId());
			
			if (list!=null){
				System.out.println("list"+list.size());
			}
			
			
			
			p.setInlinks(list);
			
//			System.out.println(p.getId() +" : "+p.getInlinks().size()+" "+list);
			
			count++;
			
			int incount = 0;
			int outcount = 0;
			
			if (p.getInlinks()!= null)
			{
				incount = p.getInlinks().size();
			}
			
//			if (p.getOutlinks()== null)
			{
				outcount = p.getOutlinks();
			}
			
			PageRank pr = new PageRank(p.getId(),p,0.0,0.0,incount,outcount);
			
			webpages.put(p.getId(), pr);
			
			
		}
		
		
		
		System.out.println("total URL in map : "+incoming.size());
		
//		for (Entry<String, ArrayList<String>> entry : incoming.entrySet()) {
//			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue().size());
//		}
		
		System.out.println("total incoming : "+count);
		
		
		
		
		
		Rank(webpages);
		
//		outgoing.forEach((k,v)->System.out.println("Key : " + k + " Value : " + v));
	}
	
	public static void Rank(HashMap<String, PageRank> toRank)
	{
		boolean doIterate = true;
		
		Double N = (double) toRank.size();
		
		
		System.out.println("Rank Input : "+toRank.size());
		
		Double init_rank = 1/N;
		
		System.out.println("init_rank--->"+init_rank);
		
//		System.out.println("init_rank--->"+init_rank*1e8);
		
		for (Entry<String, PageRank> entry : toRank.entrySet()) {
			
			if (entry.getKey()==entry.getValue().getId())
			{
				entry.getValue().setRank(init_rank);
			}
			
		}
		
		int i = 0;
		
//		while(doIterate)
		while(i<10)
		{
			
			for (Entry<String, PageRank> entry : toRank.entrySet())
			{
				String inpage ="";
				
				inpage = entry.getKey();
				
				Double sum =0.000000;
				Double rank=0.000000;			
				
				
				if (entry.getValue().getP().getInlinks()!= null)
				{
					ArrayList<String> inrank = new ArrayList<String>();
					
					inrank = entry.getValue().getP().getInlinks();
					
					for (String s : inrank)
					{
						
						
						rank = toRank.get(s).getRank()/toRank.get(s).getOutgo();
						
						System.out.println("rank "+rank);
						
						sum = sum + rank;
					}
					System.out.println("sum "+sum);
					
					rank=PR(N,sum);
					System.out.println("Calculated : "+rank);
					
					entry.getValue().setCurr(rank);
				}
				else
				{
					entry.getValue().setCurr(entry.getValue().getRank());
				}
				
				
					
					
						
			
			// Ranking    (1-d)/N + d ( PR(A) / C(A) ) 
			
			
//					N     = number of incoming links to B
//					PR(A) = PageRank of incoming link A
//					C(A)  = number of outgoing links from page A
				

			}
			i++;
			System.out.println("Iteration : "+i);
			
			
			
			
			 Map.Entry<String,PageRank> entry1=toRank.entrySet().iterator().next();
			 String key= entry1.getKey();
			 System.out.println("key : "+key+" rank :"+df.format(entry1.getValue().getRank())+" curr : "+df.format(entry1.getValue().getCurr()) );
			 System.out.println("key : "+key+" rank :"+entry1.getValue().getRank()+" curr : "+entry1.getValue().getCurr() );
			 
			 for (Entry<String, PageRank> entry : toRank.entrySet())
				{
//					entry1.getValue().setRank(entry1.getValue().getCurr());
//					System.out.println("key : "+entry.getKey()+" rank :"+df.format(entry.getValue().getRank())+" curr : "+df.format(entry.getValue().getCurr()) );
					entry.getValue().setRank(entry.getValue().getCurr());
				}
			System.out.println("Initial rank --->"+init_rank);
		}
		
		
		ReadWriteFile.writeFile(toRank);

	}
	
	public static Double PR(Double N, Double r)
	{
		Double d = 0.85;
		
//		System.out.println("N "+N);
		
		Double rank =0.0;
		
		
		rank = (1-d)/N + d*r;
		
//		System.out.println("rank "+rank);
		
		return rank;
		
	}
}

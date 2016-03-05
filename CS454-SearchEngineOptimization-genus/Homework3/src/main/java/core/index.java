package core;

import java.util.LinkedList;
import java.util.Queue;

import Util.InputReader;
import extractor.Extractor;
import extractor.ExtractorManager;
import model.Page;
import model.Raw;

public class index {
	
	public static Queue<Raw> UrlsToExtract = new LinkedList<Raw>();
	
	public static Queue<Page> UrlsExtracted = new LinkedList<Page>();
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int input;
		
//		UrlsToExtract = InputReader.execute("C:/Users/genus_000/Downloads/wiki-small/en/articles/a/e");
		UrlsToExtract = InputReader.execute("C:/Users/genus_000/Downloads/wiki-small/en");
		System.out.println(UrlsToExtract.size());
		
		input = UrlsToExtract.size();
		
//		Extractor e = new Extractor(UrlsToExtract,UrlsExtracted);
		
		ExtractorManager em = new ExtractorManager();
		
		em.execute();
		
		while (input - UrlsExtracted.size() > 0)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (UrlsExtracted.size()==input)
		System.out.println("ha ha comppleted"+UrlsExtracted.size());
		
		
		System.out.println("ha ha "+UrlsExtracted.size());
	}
	
	public static synchronized void extracted(Page p){
		UrlsExtracted.add(p);
	}

}

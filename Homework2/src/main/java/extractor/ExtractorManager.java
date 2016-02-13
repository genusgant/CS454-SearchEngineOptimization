package extractor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import database.DbClient;
import model.Link;
import model.Raw;

public class ExtractorManager {
	
	private static final DbClient connect = new DbClient("starbucks");
	
	public static Queue<Raw> UrlsToExtract = new LinkedList<Raw>(); 
	
	public static int start = 0;
	
	public static int end = 0;
	 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		RetriveData();
		
		
		Extractor e1 = new Extractor(UrlsToExtract);
		
		
		
		System.out.println("hi");
		
        

	}
	
	public static void RetriveData()
	{
		
		ArrayList<Raw> list = new ArrayList<Raw>();
		
		list = connect.Retrive(3480);
		
		if (list!= null && !list.isEmpty())
		{
			int count = list.size();
			
			for (Raw r : list)
			{
				UrlsToExtract.add(r);
			}
			
			System.out.println("Records Retrived : "+count);
			
			start = start + count;			
			
			System.out.println("Total Records Retrived : "+start);
			
			System.out.println("Records added : "+UrlsToExtract.size());
		}
	}

}

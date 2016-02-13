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
		
		ArrayList<Raw> list = new ArrayList<Raw>();
		
		
		Extractor e1 = new Extractor();
		
		
		list = connect.Retrive(0,1);
		
		if (list != null )
		
		System.out.println("hi");
		
		
		
		
		
		
		
		
		
        
        
        

	}

}

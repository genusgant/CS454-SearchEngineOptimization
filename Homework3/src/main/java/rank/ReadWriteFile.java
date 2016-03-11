package rank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.concurrent.ConcurrentMap;

import rank.PageRank;

public class ReadWriteFile {
	
	
	public static void writeFile(HashMap<String, PageRank> map)
	{
		 try {
			 File file = new File("C:/DRIVE/rank.txt");
			 FileOutputStream f = new FileOutputStream(file);
			 ObjectOutputStream s = new ObjectOutputStream(f);
	        s.writeObject(map);
	        s.close();
	        
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (NotSerializableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}

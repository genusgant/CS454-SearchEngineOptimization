package DataInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


 
public class DataReader {
    private int totalCountries;
    private String data = "Afghanistan, Albania, Zimbabwe";
    private List<String> countries;
    public DataReader() {
        countries = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(data, ",");
        
        countries = readIndex();
         
        while(st.hasMoreTokens()) {
            countries.add(st.nextToken().trim());
        }
        totalCountries = countries.size();
    }
     
    public List<String> getData(String query) {
        String country = null;
        query = query.toLowerCase();
        List<String> matched = new ArrayList<String>();
        for(int i=0; i<totalCountries; i++) {
            country = countries.get(i).toLowerCase();
            if(country.startsWith(query)) {
                matched.add(countries.get(i));
            }
        }
        return matched;
    }
    
    
    public static ArrayList<String> readIndex()
    {
   	JSONParser parser = new JSONParser();
   	
   	ArrayList<String> list  = new ArrayList<String>();
   	
   	

   	try {
   			
   		File file = new File("C:/DRIVE/big.json");

   		Object obj = parser.parse(new FileReader(file));
   			
   		JSONObject jsonObject = (JSONObject) obj;
   		
   		
   		HashMap<String,ArrayList<JSONObject>> map = (HashMap<String, ArrayList<JSONObject>>) jsonObject.get("wordlist");
   		
   		for (Entry<String, ArrayList<JSONObject>> entry : map.entrySet()) {
   			list.add(entry.getKey());
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue().size());
		}
   		
   		
   		int sno =1;
   		for (String s : list)
   		{
   			System.out.println(sno+" : "+s);
   			sno++;
   		}
   		
////   		String name = file.getName();
////   		int pos = name.lastIndexOf(".");
////   		if (pos > 0) {
////   		    name = name.substring(0, pos);
////   		}
//   		
//   		// loop array
//   		JSONArray msg = (JSONArray) jsonObject.get(name);
//   		Iterator<JSONObject> iterator = msg.iterator();
//   		
//   		while (iterator.hasNext()) {
//   			
//   			String key = "";
//   			int value;
//
//   			JSONObject wordSet = (JSONObject) iterator.next();
//
//   			key =wordSet.keySet().toArray()[0].toString();
//   			
//   			value = Integer.parseInt(wordSet.values().toArray()[0].toString());
//   			
//   			TDF td =new TDF();
//   			td.setsNO(Integer.parseInt(name));
//   			td.setCount(value);
//   			td.setId(null);
//   			
//   			Index i = new Index();
//   			i.setWord(key);
//   			i.setSite(td);
//   			
//   			list.add(i);
////   			System.out.println(i.getWord()+" : "+i.getSite().getsNO()+" : "+i.getSite().getCount());
//   			
//   		}
//   		
//   		
//   			
   	} catch (FileNotFoundException e) {
   		e.printStackTrace();
   	} catch (IOException e) {
   		e.printStackTrace();
   	} catch (ParseException e) {
   		e.printStackTrace();
   	}
   	
   	return list;
   	
   }
}
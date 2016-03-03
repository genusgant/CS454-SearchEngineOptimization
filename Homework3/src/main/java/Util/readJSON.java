package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.TF;

public class readJSON {
     public static void main(String[] args) {

	JSONParser parser = new JSONParser();

	try {
		
		File file = new File("C:/DRIVE/doc.json");

		Object obj = parser.parse(new FileReader(file));
		
//		JSONObject jsonObject = (JSONObject) obj;
//
//		String name = (String) jsonObject.get("name");
//		System.out.println(name);
//
//		long age = (Long) jsonObject.get("age");
//		System.out.println(age);
//
//		// loop array
//		JSONArray msg = (JSONArray) jsonObject.get("messages");
//		Iterator<String> iterator = msg.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}

		JSONObject jsonObject = (JSONObject) obj;
		
	 //loop array
//		JSONArray msg = (JSONArray) jsonObject.get(3);
//		Iterator<TF> iterator = msg.iterator();
//		while (iterator.hasNext()) {
//			TF t = new TF();
//			t = iterator.next();
//			System.out.println(t.getWord()+ " : "+t.getCount());
//		}
//		System.out.println(t.);

//		long age = (Long) jsonObject.get("age");
//		System.out.println(age);
//
//		

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}

     }

}

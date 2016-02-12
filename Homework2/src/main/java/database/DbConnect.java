package database;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import crawler.Crawler;

public class DbConnect {
	
	private MongoClient mg = null;
	private DB db = null;
	private DBCollection collection = null;
	
	final static Logger logger = LogManager.getLogger(Crawler.class.getName());
	
	private String host;
	private String port;
	private String dbName;
	private String colName;
	
	
	public DbConnect(String host, String port, String dbName, String colName) {
		
		this.host = host;
		this.port = port;
		this.dbName = dbName;
		this.colName = colName;
		
		logger.trace("Host : "+this.host+"Port : "+this.port+"DB : "+this.dbName+"Collection : "+this.colName);
		
		connect();
	}




	public void connect()
	{
		 try {
			  
			   mg= new MongoClient(host,Integer.parseInt(port));
			   db = mg.getDB(dbName);
		       logger.trace("Connect to database successfully");
		       collection = db.getCollection(colName);
		       logger.trace("Connect to Collection "+colName);
		} catch (UnknownHostException e) {
			
			logger.error(e);
			e.printStackTrace();
			
		} 
	}
	
	
	public boolean Insert(String link, String doc, String date) {

        try {
        	BasicDBObject document = new BasicDBObject();
           	document.put("Url", link);
           	document.put("Source", doc);
           	document.put("Date Time", date);

//           	BasicDBObject documentDetail = new BasicDBObject();
//           	documentDetail.put("records", 99);
//           	documentDetail.put("index", "vps_index1");
//           	documentDetail.put("active", "true");
//           	document.put("detail", documentDetail);

           	collection.insert(document);

        }
        catch (MongoException e) {
        	
        	logger.error(e);
        	e.printStackTrace();
            return false;
            
        }

        return true;
    }
	
	

}

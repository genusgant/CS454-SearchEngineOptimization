package stem;

import java.awt.List;
import java.util.*;


public class TfIdf {
	public double tf(ArrayList<String> doc, String term) {
	    double result = 0;
	    for (String word : doc) {
	       if (term.equalsIgnoreCase(word))
	              result++;
	       }
	    return result / doc.size();
	}
	public double idf(ArrayList<ArrayList<String>> docs, String term) {
	    double n = 0;
	    for (ArrayList<String> doc : docs) {
	        for (String word : doc) {
	            if (term.equalsIgnoreCase(word)) {
	                n++;
	                break;
	            }
	        }
	    }
	    return Math.log(docs.size() / n);
	}
	public double tfIdf(ArrayList<String> doc, ArrayList<ArrayList<String>> docs, String term) {
	    return tf(doc, term) * idf(docs, term);
	}
	
	public static void main(String[] args) {
		 
		ArrayList<String> doc1 = (ArrayList<String>) Arrays.asList("Lorem", "ipsum", "dolor", "ipsum", "sit", "ipsum");
		ArrayList<String> doc2 = (ArrayList<String>) Arrays.asList("Vituperata", "incorrupte", "at", "ipsum", "pro", "quo");
		ArrayList<String> doc3 = (ArrayList<String>) Arrays.asList("Has", "persius", "disputationi", "id", "simul");
		ArrayList<ArrayList<String>> documents = (ArrayList<ArrayList<String>>) Arrays.asList(doc1, doc2, doc3);
	 
	    TfIdf calculator = new TfIdf();
	    double tfidf = calculator.tfIdf(doc1, documents, "ipsum");
	    System.out.println("TF-IDF (ipsum) = " + tfidf);
	 
	}
}
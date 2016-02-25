
package stemming; 


import java.awt.font.TextAttribute;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.lang.Object;

public class Stem {
	

public static String removeStopWordsAndStem(String input) throws IOException {
    Set<String> stopWords = new HashSet<String>();
    stopWords.add("a");
    stopWords.add("I");
    stopWords.add("the");

    StreamTokenizer tokenStream = new StreamTokenizer(Version.LUCENE_30, new StringReader(input));
    tokenStream = new StopFilter(true, tokenStream, stopWords);
    tokenStream = new PorterStemFilter(tokenStream);

    StringBuilder sb = new StringBuilder();
    TextAttribute termAttr = tokenStream.getAttribute(TextAttribute.class);
    while (tokenStream.incrementToken()) {
        if (sb.length() > 0) {
            sb.append(" ");
        }
        sb.append(termAttr.term());
    }
    return sb.toString();
}
public static void main(String[] args) throws IOException {
    String one = "I decided buy something from the shop.";
    String two = "Nevertheless I decidedly bought something from a shop.";
    System.out.println(removeStopWordsAndStem(one));
    System.out.println(removeStopWordsAndStem(two));
}
}

/**
 * -------------------------------------------------
 * File name: App.java
 * Project name: Phone email scraper
 * -------------------------------------------------
 * Creator's name: Joshua Trimm
 * Email: joshua@trimwebdesign.com
 * Website: https://joshuatrimm.com
 * Creation date: Jul 13, 2019
 * -------------------------------------------------
 */

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;
/**
 * <b>[Enter purpose here]</b>
 * <hr>
 * Date created: Jul 13, 2019
 * <hr>
 * @author Joshua Trimm
 */
public class App
{

	/**
	 * Method description: 
	 * Date: Jul 13, 2019
	 * @param args
	 * @return void
	 */
	public static void main(String[] args)
	{
	     try {
	         // Here we create a document object and use JSoup to fetch the website
	         Document doc = Jsoup.connect("https://trimwebdesign.com").get();
	         

	         // With the document fetched, we use JSoup's title() method to fetch the title
	         System.out.printf("Title: %s\n", doc.title());
	         
		     Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
		     Matcher matcher = p.matcher(doc.text());
		     Set<String> emails = new HashSet<String>();
		     while (matcher.find()) {
		        emails.add(matcher.group());
		     }
		     
		     for(String each : emails)
		     {
		    	 System.out.print(each);
		     }

	       // In case of any IO errors, we want the messages written to the console
	       } catch (IOException e) {
	         e.printStackTrace();
	       }


	     
	}

}




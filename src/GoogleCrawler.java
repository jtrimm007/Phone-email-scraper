import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * -------------------------------------------------
 * File name: GoogleCrawler.java
 * Project name: Phone email scraper
 * -------------------------------------------------
 * Creator's name: Joshua Trimm
 * Email: joshua@trimwebdesign.com
 * Website: https://joshuatrimm.com
 * Creation date: Jul 15, 2019
 * -------------------------------------------------
 */

/**
 * <b>[Enter purpose here]</b>
 * <hr>
 * Date created: Jul 15, 2019
 * <hr>
 * 
 * @author Joshua Trimm
 */
public class GoogleCrawler
{
	 
	ArrayList<String> urls = new ArrayList<String>(); // Create an ArrayList object 
	private static Pattern patternDomainName;
	private Matcher matcher;
	private static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
	static
	{
		patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
	}

	public GoogleCrawler(String searchQueryString)
	{
		//FunnyCrawler obj = new FunnyCrawler();
		Set<String> result = getDataFromGoogle(searchQueryString);
		for (String temp : result)
		{
			//System.out.println(temp);
			urls.add(temp);
		}
		System.out.println(result.size());
	}

	public String getDomainName(String url)
	{

		String domainName = "";
		matcher = patternDomainName.matcher(url);
		if (matcher.find())
		{
			domainName = matcher.group(0).toLowerCase().trim();
		}
		return domainName;

	}

	private Set<String> getDataFromGoogle(String query)
	{

		Set<String> result = new HashSet<String>();
		String request = "https://www.google.com/search?q=" + query + "&num=20";
		System.out.println("Sending request..." + request);

		try
		{

			// need http protocol, set this as a Google bot agent :)
			Document doc = Jsoup.connect(request)
					.userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)").timeout(5000)
					.get();

			// get all links
			Elements links = doc.select("a[href]");
			for (Element link : links)
			{

				String temp = link.attr("href");
				if (temp.startsWith("/url?q="))
				{
					// use regex to get domain name
					result.add(getDomainName(temp));
				}

			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return result;
	}
	
	public ArrayList<String> getEmailFromWebsite(String website)
	{
	    ArrayList<String> emailAddresses = new ArrayList<String>();
		
		try {
			//System.out.println(website);
	         // Here we create a document object and use JSoup to fetch the website
	         Document doc = Jsoup.connect("https://" + website).get();
	         

	         // With the document fetched, we use JSoup's title() method to fetch the title
	         System.out.printf("Title: %s\n", doc.title());
	         
		     Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
		     Matcher matcher = p.matcher(doc.text());
		     Set<String> emails = new HashSet<String>();
		     while (matcher.find()) {
		        emails.add(matcher.group());
		     }
		     int oneEmail = 0;
		     for(String each : emails)
		     {
		    	 
		    	 if(oneEmail < 1)
		    	 {
		    		 oneEmail++;
			    	 //System.out.print(each);
			    	 emailAddresses.add(each); 
		    	 }

		     }
		     return emailAddresses;

	       // In case of any IO errors, we want the messages written to the console
	       } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	        }catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	         e.printStackTrace();
	       }
		return emailAddresses;

	}
	
	public ArrayList<String> getPhoneFromWebsite(String website)
	{
	    ArrayList<String> phoneNumbers = new ArrayList<String>();
		
		try {
			//System.out.println(website);
	         // Here we create a document object and use JSoup to fetch the website
	         Document doc = Jsoup.connect("https://" + website).get();
	         

	         // With the document fetched, we use JSoup's title() method to fetch the title
	         //System.out.printf("Title: %s\n", doc.title());
	         
		     Pattern p = Pattern.compile("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$");
		     
		     Matcher matcher = p.matcher(doc.text());
		     
		     Set<String> numbers = new HashSet<String>();
		     
		     while (matcher.find()) {
		    	 numbers.add(matcher.group());
		     }
		     
		     for(String each : numbers)
		     {
		    	 System.out.print(each);
		    	 phoneNumbers.add(each);
		     }
		     return phoneNumbers;

	       // In case of any IO errors, we want the messages written to the console
	       } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	        }catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	         e.printStackTrace();
	       }
		return phoneNumbers;

	}

}

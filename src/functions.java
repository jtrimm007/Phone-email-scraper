import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 */

/**
 * @author Joshua
 *
 */
public class functions {
	



	public static HashMap<String, ArrayList<String>> searchAndScrap(String searchCred)
	{		
		HashMap<String, ArrayList<String>> companyObject = new HashMap<String, ArrayList<String>>();
	 

		GoogleCrawler test = new GoogleCrawler(searchCred);
		
		for(String url : test.urls)
		{
			System.out.println("Crawling: " + url);
			ArrayList<String> emailAddresses = test.getEmailFromWebsite(url);


			ArrayList<String> companyEmail = new ArrayList<String>();
			
			//System.out.println(emailAddresses);
			if(emailAddresses.size() >= 1)
			{

				for(String email : emailAddresses)
				{

					System.out.println(email);


					companyEmail.add(email);

	
				}
				companyObject.put(url, companyEmail);

			}

		}
		return companyObject;
	}
}

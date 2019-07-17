import java.io.IOException;
import java.util.ArrayList;

/**
 * -------------------------------------------------
 * File name: Driver.java
 * Project name: Phone email scraper
 * -------------------------------------------------
 * Creator's name: Joshua Trimm
 * Email: joshua@trimwebdesign.com
 * Website: https://joshuatrimm.com
 * Creation date: Jul 1, 2019
 * -------------------------------------------------
 */

/**
 * <b>[Enter purpose here]</b>
 * <hr>
 * Date created: Jul 1, 2019
 * <hr>
 * @author Joshua Trimm
 */
public class Driver
{

	/**
	 * Method description: 
	 * Date: Jul 1, 2019
	 * @param args
	 * @return void
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GoogleCrawler test = new GoogleCrawler("hvac+company");
		
		for(String url : test.urls)
		{
			System.out.println(url);
			ArrayList<String> emailAddresses = test.getEmailFromWebsite(url);
			for(String email : emailAddresses)
			{
				System.out.println(email);
			}

	
		}
		

	}

}

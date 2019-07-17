import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

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

		
		HashMap<String, ArrayList<String>> list = functions.searchAndScrap("hvac+johnson+city+tn");
		System.out.println(list);


		Set<Entry<String, ArrayList<String>>> test = list.entrySet();
		
		System.out.println(test);

		
		
		
		
		

	}

}

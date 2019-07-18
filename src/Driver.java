import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
 * 
 * @author Joshua Trimm
 */
public class Driver {

	/**
	 * Method description: Date: Jul 1, 2019
	 * 
	 * @param args
	 * @return void
	 * @throws SQLException
	 * @throws IOException 
	 */
	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub

		List<List<String>> cityList = functions.getCitiesFromCsv();
		
		for(List<String> each : cityList)
		{
			StringBuilder sb = new StringBuilder();
			
			sb.append(each);
			
			String location = sb.toString();
			location = location.replace("[", "");
			location = location.replace("]", "");
			location = location.replace(", ", "+");
			location = location.replace(" ", "+");

					
			
			HashMap<String, ArrayList<String>> list = functions.searchAndScrap("hvac+" + location);
			System.out.println(list);

			Set<Entry<String, ArrayList<String>>> company = list.entrySet();

			System.out.println(company);
			
			functions.checkDataAndInsert(company);
		}
		
//		HashMap<String, ArrayList<String>> list = functions.searchAndScrap("seo+tennessee");
//		System.out.println(list);
//
//		Set<Entry<String, ArrayList<String>>> company = list.entrySet();
//
//		System.out.println(company);
//		
//		functions.checkDataAndInsert(company);


	}
}

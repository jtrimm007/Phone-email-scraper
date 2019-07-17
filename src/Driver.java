import java.io.IOException;
import java.sql.SQLException;
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
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		HashMap<String, ArrayList<String>> list = functions.searchAndScrap("hvac+bristol+tn");
		System.out.println(list);

		Set<Entry<String, ArrayList<String>>> company = list.entrySet();

		System.out.println(company);
		
		functions.checkDataAndInsert(company);


	}
}

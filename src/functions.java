import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 
 */

/**
 * @author Joshua
 *
 */
public class functions {

	public static boolean checkForDuplication(String url) {
		try {
			ArrayList<String> urlsInDatabase = DatabaseConnection.selectAllUrls();

			if (!urlsInDatabase.contains(url)) 
			{
				return false;
			} 

		} catch (Exception i)

		{
			i.printStackTrace();
		}

		return true;
	}

	public static void insertData(String url, String email) throws SQLException {

		DatabaseConnection.insertUrlAndEmails(url, email);
	}
	
	public static void checkDataAndInsert(Set<Entry<String, ArrayList<String>>> company) throws SQLException
	{
		for (Entry<String, ArrayList<String>> each : company) {
			System.out.println(each.getKey());
			System.out.println(each.getValue());
			StringBuilder sb = new StringBuilder();
			sb.append(each.getValue());
			String emails = sb.toString();
			emails = emails.replace("[", "");
			emails = emails.replace("]", "");

			boolean checkForUrlInDatabase = functions.checkForDuplication(each.getKey());

			if (checkForUrlInDatabase != true) {
				functions.insertData(each.getKey(), emails);

			}
		}
	}

	public static HashMap<String, ArrayList<String>> searchAndScrap(String searchCred) {
		HashMap<String, ArrayList<String>> companyObject = new HashMap<String, ArrayList<String>>();

		GoogleCrawler test = new GoogleCrawler(searchCred);

		for (String url : test.urls) {
			System.out.println("Crawling: " + url);
			ArrayList<String> emailAddresses = test.getEmailFromWebsite(url);

			ArrayList<String> companyEmail = new ArrayList<String>();

			// System.out.println(emailAddresses);
			if (emailAddresses.size() >= 1) {

				for (String email : emailAddresses) {

					System.out.println(email);

					companyEmail.add(email);

				}
				companyObject.put(url, companyEmail);

			}

		}
		return companyObject;
	}
}

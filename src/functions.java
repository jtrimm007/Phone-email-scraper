import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

	public static String city;
	private static String[] exclude = new String[] { "quora", "youtube", "amazon", "google", "gov", "app", "homedepot",
			"lowes", "walmart", "indeed", "angieslist", "yelp", "yellowpages", "manta", "porch", "zillow", "YP",
			"tripadvisor", "yellowbook", "dexknows", "airbnb", "bbb" };
	private static List<String> list = Arrays.asList(exclude);

	public static List<List<String>> getCitiesFromCsv() throws FileNotFoundException, IOException {
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(
				"C:\\Users\\Joshua\\Dropbox (Personal)\\BackLinqs\\Scraper\\Phone email scraper\\src\\cities.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				records.add(Arrays.asList(values));
			}

			return records;
		}
	}

	public static boolean checkForDuplication(String url) {
		try {
			ArrayList<String> urlsInDatabase = DatabaseConnection.selectAllUrls();

			if (!urlsInDatabase.contains(url)) {
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

	public static void checkDataAndInsert(Set<Entry<String, ArrayList<String>>> company) throws SQLException {
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

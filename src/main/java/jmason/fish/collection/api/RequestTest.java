package jmason.fish.collection.api;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping(value = "reqtest", produces = { MediaType.APPLICATION_JSON_VALUE })
public class RequestTest {

	/**
	 * Jsonを返す.
	 *
	 * @return 適当な情報.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Map<Object, Object> getSample() {

		// for test
		String resText = executeGet();
		extractHref(resText);
		Map<Object, Object> result = new HashMap<Object, Object>();
		result.put("reqtest", "fish-collection");
		return result;
	}

	private void extractHref(String target) {
		
//		String regex = "\\href=\"(.+)\\\"";
		String regex = "\\Best guess for this image(.+)</div>\\";
		System.out.println("Result: " + extractMatchString(regex, target));

	}
	private String extractMatchString(String regex, String target) {
		
		System.out.println("extractMatchString : target " + target);

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(target);
		if (matcher.find()) {
			return matcher.group(1);
		} else {
			System.out.println("No match found.");
			return "";
//			throw new IllegalStateException("No match found.");
			
		}
	}
	private String executeGet() {
		System.out.println("===== HTTP GET Start =====");
		String resText = "";
		try {
			URL url = new URL("https://www.google.com/searchbyimage?site=search&sa=X&image_url=http://www.kitanogurume.com/item/img/shake/2040-400-300.jpg");

			HttpURLConnection connection = null;

			try {
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
//				connection.setRequestProperty("Accept-Language", "en");
				
				if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(),
//							StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(isr)) {
							"SJIS"); BufferedReader reader = new BufferedReader(isr)) {
						String line;
						while ((line = reader.readLine()) != null) {
							System.out.println(line);
							resText.concat(line);
						}
					}
				}
			} finally {
				if (connection != null) {
					connection.disconnect();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("===== HTTP GET End =====");
		resText = "Best guess for this image aaaa </div>";
		return resText;
	}
}

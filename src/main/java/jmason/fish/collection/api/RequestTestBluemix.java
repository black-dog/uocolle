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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jmason.fish.collection.util.json.fb.Fruitbowl;
import jmason.fish.collection.util.json.prez.Prez;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping(value = "bluemixtest", produces = { MediaType.APPLICATION_JSON_VALUE })
public class RequestTestBluemix {

	/**
	 * Jsonを返す.
	 *
	 * @return 適当な情報.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Map<Object, Object> getSample() {
//		testJSON();
		testJSONPrez();
		Map<Object, Object> result = new HashMap<Object, Object>();
		result.put("bluemixtest", "fish-collection");
		return result;
	}
	
	
	
	private void testJSONFB() {
		
		String target = "{\"custom_classes\":0,\"images\":[{\"classifiers\":[{\"classes\":[{\"class\":\"banana\",\"score\":0.81,\"type_hierarchy\":\"/fruit/banana\"},{\"class\":\"fruit\",\"score\":0.922},{\"class\":\"mango\",\"score\":0.554,\"type_hierarchy\":\"/fruit/mango\"},{\"class\":\"olivecolor\"\"score\":0.951},{\"class\":\"olivegreencolor\"\"score\":0.747}],\"classifier_id\":\"default\",\"name\":\"default\"}],\"image\":\"fruitbowl.jpg\"}],\"images_processed\":1}";
		
		try {
			Fruitbowl fb = new ObjectMapper().readValue(target, Fruitbowl.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void testJSONPrez() {
		
		String target = "{\"images\":[{\"faces\":[{\"age\":{\"max\":54,\"min\":45,\"score\":0.364876}}],\"image\":\"prez.jpg\"}],\"images_processed\":1}";
		
		try {
			Prez prez = new ObjectMapper().readValue(target, Prez.class);
			System.out.println(prez);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

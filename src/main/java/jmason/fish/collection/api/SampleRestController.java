package jmason.fish.collection.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * サンプルのRestコントローラーです
 *
 * @author seko_yuya@ogis-ri.co.jp
 *
 */
@RestController
@RequestMapping(value = "sample", produces = {MediaType.APPLICATION_JSON_VALUE})
public class SampleRestController {

	/**
	 * Jsonを返す.
	 *
	 * @return 適当な情報.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Map<Object, Object> getSample() {
		Map<Object, Object> result = new HashMap<Object, Object>();
		result.put("sample", "fish-collection");
		return result;
	}
}

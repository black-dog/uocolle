package jmason.fish.collection.api;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * サンプルのRestコントローラーです
 *
 * @author seko_yuya@ogis-ri.co.jp
 *
 */
@RestController
@RequestMapping(value = "sample", produces = {MediaType.APPLICATION_JSON_VALUE})
public class SampleRestController {
	
	@Value("${multipart.maxFileSize}")
	String aaa;
	
	/**
	 * Jsonを返す.
	 *
	 * @return 適当な情報.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Map<Object, Object> getSample() {
		System.out.println(aaa);
		Map<Object, Object> result = new HashMap<Object, Object>();
		result.put("sample", "fish-collection");
		return result;
	}

	/**
	 * Jsonを返す.
	 *
	 * @return 適当な情報.
	 */
	@RequestMapping(method = RequestMethod.POST, produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Map<Object, Object> postSample(@RequestParam("file") MultipartFile mFile) {
//		File uploadDir = mkdirs("C:/ユーザー/jmason/デスクトップ");
		System.out.println(mFile.getSize());
		File file = new File("C:/workspace", "sample.jpeg");
		try {
			mFile.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(file.toString());
		Map<Object, Object> result = new HashMap<Object, Object>();
		result.put("sample", "fish-collection");
		return result;
	}
}
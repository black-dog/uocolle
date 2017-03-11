package jmason.fish.collection.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.CollectionImage;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.FindSimilarImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ImageClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

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
	@Value("${uocolle.watson.api-key}")
	String apikey;
	@Value("${uocolle.watson.collectionid}")
	String collectionid;
	/**
	 * Jsonを返す.
	 *
	 * @return 適当な情報.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getSample() {
		System.out.println(aaa);
		File file = new File("C:/workspace/uocolle-web/work", "kasumiaji.jpg");
		return "["+findSimilarImage(file)+"]";
	}

	/**
	 * Jsonを返す.
	 *
	 * @return 適当な情報.
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "multipart/form-data")
	public List<Map<String, String>> postSample(@RequestParam("file") MultipartFile mFile) {
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
		return findSimilarImage(file);
	}
	
	/**
	 * bluemix へ データを送り、分類されたデータを取得します。
	 * @param file
	 */
	private List<Map<String, String>> findSimilarImage(File file) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
		service.setApiKey(apikey);
		FindSimilarImagesOptions findImageOptions = new FindSimilarImagesOptions.Builder()
				.collectionId(collectionid)
				.image(file)
				.build();
		List<CollectionImage> similarImages = service.findSimilarImages(findImageOptions).execute();
		
		//scoreが一番高いものを返す
		double hiscore = 0;
		for(CollectionImage image : similarImages) {
			if(hiscore < image.getScore()) {
				hiscore = image.getScore();
				result.add(image.getMetadata());
			}
		}
		System.out.println(hiscore);
		System.out.println(result);
		return result;
	}
}

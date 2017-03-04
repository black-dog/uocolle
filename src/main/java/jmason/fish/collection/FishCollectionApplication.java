package jmason.fish.collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * このクラスでSpringBootを起動する
 *
 * @author seko_yuya@ogis-ri.co.jp
 *
 */

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
public class FishCollectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishCollectionApplication.class, args);
	}
}

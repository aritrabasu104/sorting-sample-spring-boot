package hello.configuration;

import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class AppConfigurer {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("hello"))
				.build().apiInfo(metaInfo());
	}


	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo("Employee List API", "UBS Custom API challenge",
				"1.0", "Terms of Service",
				new Contact("Aritra Basu", "https://www.linkedin.com/in/aritra-basu-full-stack/",
						"aritrabasu104@gmail.com"),
				"Apache License Version 2.0", "https://www.apache.org/licesen.html", Collections.emptyList());

		return apiInfo;
	}
}

package com.shadowspring.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.context.annotation.Configuration;

/*http://localhost:8080/swagger-ui.html*/

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

	/*@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.shadowspring"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}*/
	
	/*private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Cadastro Cliente")
				.description("Api para cadastrar um cliente")
				.version("1.0")
				.contact(contact())
				.build();
	}
	*/
	/*private Contact contact() {
		return new Contact("Valnei Pereira",
							"http://github.com/ValneiPereira",
							"valneipereirabarboza@gmail.com");
	}*/
}
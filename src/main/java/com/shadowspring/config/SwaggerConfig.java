package com.shadowspring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*http://localhost:8080/swagger-ui.html*/

@Configuration
//@OpenAPIDefinition
public class SwaggerConfig {

    @Value("${info.app.version:0.0.1}")
    private String appVersion;

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info().title("shadowspring").description("Api para gerenciamento de clientes").version(appVersion));
    }

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
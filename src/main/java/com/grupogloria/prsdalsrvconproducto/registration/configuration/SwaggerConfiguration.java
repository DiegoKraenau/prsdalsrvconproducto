package com.grupogloria.prsdalsrvconproducto.registration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration  {

	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors
                .basePackage("com.grupogloria.prsdalsrvconproducto.registration"))
            .paths(PathSelectors.regex("/.*"))
            .build().apiInfo(apiEndPointsInfo());
    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("service-registration")
            .description("Gloria Registration API")
            .contact(new Contact("Raul", "", "raul.ramos@tcs.com"))
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("1.0.0")
            .build();
    }
}

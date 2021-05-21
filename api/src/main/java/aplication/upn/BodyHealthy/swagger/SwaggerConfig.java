package aplication.upn.BodyHealthy.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Tutorial JWT",
                "Descripción",
                "2.0",
                "Términos y Condiciones",
                new Contact("Diego Sullon", "https://github.com/DiegoSullon", "jdiego.sullon@gmail.com"),
                "Licencia",
                "www.licencia.com",
                Collections.emptyList()
        );
    }
}

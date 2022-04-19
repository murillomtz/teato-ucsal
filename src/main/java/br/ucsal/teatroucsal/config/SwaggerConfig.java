package br.ucsal.teatroucsal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

//@Configuration
//@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    public static final String BILHETE = "Bilhete";
    public static final String CADEIRA = "Cadeira";
    public static final String CLIENTE = "Cliente";
    public static final String ESPETACULO = "Espetaculo";

//    @Bean
//    public Docket teatroUcsalApi() {
//        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).groupName("sv0").select()
//                .apis(RequestHandlerSelectors.basePackage("br.ucsal.teatroucsal")).build()
//                .apiInfo(this.metaData()).tags(new Tag(BILHETE, "Operações referentes a manipulação da entidade Bilhete."))
//                .tags(new Tag(CADEIRA, "Operações referentes a manipulação da entidade Cadeira."))
//                .tags(new Tag(CLIENTE, "Operações referentes a manipulação da entidade Cliente."))
//                .tags(new Tag(ESPETACULO, "Operações referentes a manipulação da entidade Espetaculo."));
//    }


    /**
     * PARA QUEM ESTARA USANDO O HANLDER O SWAGER SO ACESSA ASSIM
     */
    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> listPlugins = new ArrayList<>();
        listPlugins.add(new CollectionJsonLinkDiscoverer());

        return new LinkDiscoverers(SimplePluginRegistry.create(listPlugins));
    }

//    private ApiInfo metaData() {
//
//        return new ApiInfoBuilder().title("Teatro UCSAL")
//                .description("Api resonsável pela gestão de bilheteria do Teatro UCSAL")
//                .version("1.0.0").license("").build();
//
//    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:META-INF/resources/webjars/");
    }


}

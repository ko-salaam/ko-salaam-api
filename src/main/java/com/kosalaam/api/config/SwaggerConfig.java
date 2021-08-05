package com.kosalaam.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Demo")
                .description("API EXAMPLE")
                .build();
    }

//    @Bean
//    public Docket commonApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("example")
//                .apiInfo(this.apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors
//                        .basePackage("com.kosalaam.api.web"))
//                .paths(PathSelectors.ant("/**"))
//                .build();
//    }

    /**
     * [restaurant] group swagger Docket
     *
     * @return <Docket>
     */
    @Bean
    public Docket prayerroomApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("기도실").select()
                .apis(RequestHandlerSelectors.basePackage("com.kosalaam.api.modules.prayerroom")).paths(PathSelectors.any())
                .build();

        return setDocketCommonConfig(docket, "[Prayerroom] API", "기도실 API");
    }

    /**
     * [restaurant] group swagger Docket
     *
     * @return <Docket>
     */
    @Bean
    public Docket restaurantApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("식당").select()
                .apis(RequestHandlerSelectors.basePackage("com.kosalaam.api.modules.restaurant")).paths(PathSelectors.any())
                .build();

        return setDocketCommonConfig(docket, "[Restaurant] API", "식당 API");
    }

    /**
     * [restaurant] group swagger Docket
     *
     * @return <Docket>
     */
    @Bean
    public Docket accommodationApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("숙소").select()
                .apis(RequestHandlerSelectors.basePackage("com.kosalaam.api.modules.accommodation")).paths(PathSelectors.any())
                .build();

        return setDocketCommonConfig(docket, "[Accommodation] API", "숙소 API");
    }

    /**
     * [restaurant] group swagger Docket
     *
     * @return <Docket>
     */
    @Bean
    public Docket userApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("사용자").select()
                .apis(RequestHandlerSelectors.basePackage("com.kosalaam.api.modules.kouser")).paths(PathSelectors.any())
                .build();

        return setDocketCommonConfig(docket, "[KoUser] API", "사용자 API");
    }

    /**
     * Set Docket Common Config
     *
     * @param docket    <Docket>
     * @param group     <String>
     * @param groupName <String>
     * @return <Docket>
     */
    private Docket setDocketCommonConfig(Docket docket, String group, String groupName) {
        return docket.apiInfo(this.getApiInfo(group, groupName))
                /**
                 * useDefaultResponseMessages(true) TRUE 기본에러코드 사용 FALSE 모든 메서드 기본에러코드 초기화(세팅한 값
                 * 외에도 초기화 됨)
                 */
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, this.responseMessageSetGet())
                .globalResponseMessage(RequestMethod.POST, this.responseMessageSetPost())
                .globalResponseMessage(RequestMethod.DELETE, this.responseMessageSetDelete())
                .globalResponseMessage(RequestMethod.PUT, this.responseMessageSetPut())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()));
    }

    /**
     * API Information
     *
     * @param title     <String>
     * @param description <String>
     * @return <ApiInfo>
     */
    private ApiInfo getApiInfo(String title, String description) {
        return new ApiInfoBuilder().title(title).description(description).version("1.0.0").build();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // Private Area (Error Model)
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private ArrayList<ResponseMessage> responseMessageSetCommon() {
        ArrayList<ResponseMessage> responseMessageCommon = new ArrayList<>();
        // 400 : BAD_REQUEST
        responseMessageCommon.add(new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase()).build());

        // 404 : NOT_FOUND
        responseMessageCommon.add(new ResponseMessageBuilder().code(HttpStatus.NOT_FOUND.value())
                .message(HttpStatus.NOT_FOUND.getReasonPhrase()).build());

        // 422 : UNPROCESSABLE_ENTITY
        responseMessageCommon.add(new ResponseMessageBuilder().code(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase()).build());

        // 500 : INTERNAL_SERVER_ERROR
        responseMessageCommon.add(new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).build());

        return responseMessageCommon;
    }

    /**
     * GET Method Response Message Set
     *
     * @return <ArrayList>
     */
    private List<ResponseMessage> responseMessageSetGet() {
        return this.responseMessageSetCommon();
    }

    /**
     * POST Method Response Message Set
     *
     * @return <ArrayList>
     */
    private List<ResponseMessage> responseMessageSetPost() {
        ArrayList<ResponseMessage> responseMessageSetPost = this.responseMessageSetCommon();

        // 409 : CONFLICT
        responseMessageSetPost.add(new ResponseMessageBuilder().code(HttpStatus.CONFLICT.value())
                .message(HttpStatus.CONFLICT.getReasonPhrase()).build());

        return responseMessageSetPost;
    }

    /**
     * PUT Method Response Message Set
     *
     * @return <ArrayList>
     */
    private List<ResponseMessage> responseMessageSetPut() {
        return this.responseMessageSetCommon();
    }

    /**
     * DELETE Method Response Message Set
     *
     * @return <ArrayList>
     */
    private List<ResponseMessage> responseMessageSetDelete() {
        return this.responseMessageSetCommon();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return springfox
                .documentation
                .spi.service
                .contexts
                .SecurityContext
                .builder()
                .securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

}
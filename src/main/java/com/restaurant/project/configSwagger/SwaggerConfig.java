package com.restaurant.project.configSwagger;

import com.restaurant.project.utils.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2 //开启swagger功能
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)  // DocumentationType.SWAGGER_2 固定的，代表swagger2
//                .groupName("分布式任务系统") // 如果配置多个文档的时候，那么需要配置groupName来分组标识
                .apiInfo(apiInfo()) // 用于生成API信息
                .select() // select()函数返回一个ApiSelectorBuilder实例,用来控制接口被swagger做成文档
                .apis(RequestHandlerSelectors.basePackage("com.restaurant.project.controller")) // 用于指定扫描哪个包下的接口
                .paths(PathSelectors.any())// 选择所有的API,如果你想只为部分API生成文档，可以配置这里
                .build()
                .globalOperationParameters(this.getParameterList()) // 全局配置
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());
    }

    private List<SecurityContext> securityContexts(){
        List<SecurityContext> result=new ArrayList<>();
        result.add(getContextByPath("/.*"));
        return result;
    }


    private List<ApiKey> securitySchemes(){
//         设置请求头信息
        List<ApiKey> result=new ArrayList<>();
        ApiKey apiKey=new ApiKey(Const.Authorization,Const.Authorization,"Header");
        result.add(apiKey);
        return result;
    }

    /**
     * 添加head参数配置
     */
    private List<Parameter> getParameterList() {
        ParameterBuilder clientIdTicket = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        clientIdTicket.name("DeviceId").description("DeviceId")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true).build();
        pars.add(clientIdTicket.build());
        return pars;
    }


    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result=new ArrayList<>();
        AuthorizationScope authorizationScope=new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes=new AuthorizationScope[1];
        authorizationScopes[0]=authorizationScope;
        result.add(new SecurityReference("Authorization",authorizationScopes));
        return result;
    }


    //设置表述文件中的info,参数类型 ApiInfo
    private ApiInfo apiInfo() {
        //用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                //设置标题
                .title("接口文档")
                //描述
                .description("我是描述信息")
                //版本
                .version("版本号：V.1")
                //协议
                .license("The Apache License")
                //协议url
                .licenseUrl("http://www.baidu.com")
                .build();

    }
}

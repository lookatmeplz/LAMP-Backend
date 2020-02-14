package com.LAMP.LAMPBackend.config

import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration {
    fun swaggerApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.LAMP.LAMPBackend.api"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
    }

    private fun swaggerInfo(): ApiInfo {
        return ApiInfoBuilder().title("LAMP Spring Server Swagger Documentation")
                .description("Documentation for server API to develop LAMP Spring server")
                .license("Kim HyeockJin").version("1").build()
    }
}
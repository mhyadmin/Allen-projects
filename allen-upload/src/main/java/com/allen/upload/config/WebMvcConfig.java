package com.allen.upload.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * MVC配置
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = "E:/视频制作/图片/";
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + path); // 上传路径映射 会使spring boot的自动配置失效
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/"); // 配置这个，使项目可以访问这个static下的文件，不配置，访问不了，受上面配置的影响
        super.addResourceHandlers(registry);
    }
}

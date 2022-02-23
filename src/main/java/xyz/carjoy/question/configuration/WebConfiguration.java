package xyz.carjoy.question.configuration;

import xyz.carjoy.question.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    //增加拦截器
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new MyInterceptor())    //指定拦截器类
                .addPathPatterns("/base/user/*");        //指定url
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //开启路径后缀匹配
        configurer.setUseRegisteredSuffixPatternMatch(true);
    }

}
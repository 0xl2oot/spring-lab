package tech.yonghong.sign;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author yonghong
 * @date 2020/3/13
 **/
@SpringBootConfiguration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private NeedSignInterceptor needSignInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(needSignInterceptor).addPathPatterns("/api/**");
    }
}

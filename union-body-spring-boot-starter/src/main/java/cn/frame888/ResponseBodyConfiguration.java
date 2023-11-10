package cn.frame888;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author SwayJike
 */
@Slf4j
public class ResponseBodyConfiguration implements WebMvcConfigurer {

    public ResponseBodyConfiguration(){
        log.info("Initializing 'ResponseBodyConfiguration'");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ResponseBodyInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/", "/css/**", "/images/**", "/js/**", "/fonts/**");
    }

}

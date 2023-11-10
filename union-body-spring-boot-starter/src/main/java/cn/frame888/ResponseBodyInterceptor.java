package cn.frame888;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class ResponseBodyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(ResponseBodyWrapper.class)){
                log.info("找到了" + clazz.getName() + "上标注了" + ResponseBodyWrapper.class.getName() + "注解");
                request.setAttribute(ResponseBodyWrapper.RESPONSE_BODY_WRAPPER_FLAG,
                        clazz.getAnnotation(ResponseBodyWrapper.class));
            }else if (method.isAnnotationPresent(ResponseBodyWrapper.class)){
                log.info("找到了{}-{}上标注了{}注解", clazz.getName(), method.getName(), ResponseBodyWrapper.class.getName());
                request.setAttribute(ResponseBodyWrapper.RESPONSE_BODY_WRAPPER_FLAG,
                        method.getAnnotation(ResponseBodyWrapper.class));
            }
        }
        return true;
    }


}

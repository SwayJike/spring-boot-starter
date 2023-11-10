package cn.frame888;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
@ControllerAdvice
@ConditionalOnBean(annotation = EnableResponseBody.class)
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {

    private static final String CODE_KEY = "code";

    private static final String MESSAGE_KEY = "message";

    private static final String DATA_KEY = "data";

    public ResponseBodyHandler(){
        log.info("Initializing 'ResponseBodyHandler'");
    }

    /**
     * 判断请求是否被标记了 ResponseBodyWrapper注解,没有就直接返回不需要重写返回体
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        ResponseBodyWrapper responseBodyWrapper = (ResponseBodyWrapper) request.getAttribute(ResponseBodyWrapper.RESPONSE_BODY_WRAPPER_FLAG);
        return responseBodyWrapper != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return wrapResponse(body);
    }

    /**
     * 包装响应
     * @param body
     * @return
     */
    public Object wrapResponse(Object body) {
        log.info("Start ReWrite ResponseBody...");
        if (body instanceof Error){
            log.error("ResponseBody Error...");
            return new HashMap<String, Object>(){
                {
                    put(CODE_KEY, HttpStatus.BAD_REQUEST.value());
                    put(MESSAGE_KEY, HttpStatus.BAD_REQUEST.getReasonPhrase());
                    put(DATA_KEY, null);
                }
            };
        }
        HashMap<String, Object> res = new HashMap<String, Object>() {
            {
                put(CODE_KEY, HttpStatus.OK.value());
                put(MESSAGE_KEY, HttpStatus.OK.getReasonPhrase());
                put(DATA_KEY, body);
            }
        };
        //解决mvc对返回值为String的问题
        //java.lang.Error: 响应结果 cannot be cast to java.lang.String
        if (body instanceof String){
            return JSON.toJSONString(res);
        }
        return res;
    }


}

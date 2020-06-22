package com.scbm.gateway.exception;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常处理
 * <br>
 * <p>出现异常时默认返回的是HTML，这里做一下转换，返回json格式的异常信息</p>
 *
 * @author 刘凯峰
 * @date 2018-11-28 16-51
 */
public class CmpGatewayExceptionHandler extends DefaultErrorWebExceptionHandler {

    public CmpGatewayExceptionHandler( ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
                                       ErrorProperties errorProperties, ApplicationContext applicationContext ) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    /**
     * 获取异常属性
     */
    @Override
    protected Map<String, Object> getErrorAttributes( ServerRequest request, boolean includeStackTrace ) {
        int code = 500;
        Throwable error = super.getError(request);
        if (error instanceof org.springframework.cloud.gateway.support.NotFoundException) {
            code = 404;
        }
        return response(code, this.buildMessage(request, error));
    }

    /**
     * 指定响应处理方法为JSON处理的方法
     *
     * @param errorAttributes 错误属性对象
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction( ErrorAttributes errorAttributes ) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    /**
     * 根据code获取对应的HttpStatus
     *
     * @param errorAttributes 错误属性对象
     */
    @Override
    protected HttpStatus getHttpStatus( Map<String, Object> errorAttributes ) {
        int statusCode = (int) errorAttributes.get("code");
        return HttpStatus.valueOf(statusCode);
    }

    /**
     * 构建异常信息
     *
     * @param request 请求对象
     * @param ex      抛出的异常信息
     * @return 返回构建的异常信息
     */
    private String buildMessage( ServerRequest request, Throwable ex ) {
        StringBuilder message = new StringBuilder("Failed to handle request [");
        message.append(request.methodName());
        message.append(" ");
        message.append(request.uri());
        message.append("]");
        if (ex != null) {
            message.append(": ");
            message.append(ex.getMessage());
        }
        return message.toString();
    }

    /**
     * 构建返回的JSON数据格式
     *
     * @param status       状态码
     * @param errorMessage 异常信息
     * @return 返回的对象格式
     */
    private static Map<String, Object> response( int status, String errorMessage ) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", status);
        map.put("msg", errorMessage);
        map.put("data", null);
        return map;
    }

}



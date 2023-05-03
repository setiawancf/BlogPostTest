package com.sigma.BlogPostTest.response;


import com.sigma.BlogPostTest.common.Response;
import com.sigma.BlogPostTest.enums.ResponsCodeTypeEnum;

public class ResponseUtil {


    public static <T> Response<T> getErrorMessageResponse(Integer code, T obj) {
        Response<T> response = new Response();
        response.setCode(code);
        response.setMessage(Response.RESPONSE_MAP.get(code));
        response.setResult(obj);
        return response;
    }


    public static <T> Response<T> getErrorMessageResponse(String msg) {
        Response<T> response = new Response();
        response.setCode(ResponsCodeTypeEnum.FAILURE.getCode());
        response.setMessage(msg);
        return response;
    }

    public static <T> Response<T> getSuccessResponse(T obj) {
        Response<T> response = new Response<T>();
        response.setCode(ResponsCodeTypeEnum.SUCCESS.getCode());
        response.setMessage(ResponsCodeTypeEnum.SUCCESS.getMessage());
        response.setResult(obj);
        return response;
    }

    public static <T> Response<T> getSuccessResponse(T obj, int code) {
        Response<T> response = new Response<T>();
        response.setCode(code);
        response.setMessage(Response.RESPONSE_MAP.get(code));
        response.setResult(obj);
        return response;
    }

    public static <T> Response<T> getResponse(Integer code) {
        Response<T> response = new Response<T>();
        response.setCode(code);
        response.setMessage(Response.RESPONSE_MAP.get(code));
        return response;
    }

    public static <T> Response<T> getResponse(Integer code, String message) {
        Response<T> response = new Response<T>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}

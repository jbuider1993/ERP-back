package com.kunlun.common.utils;

import com.kunlun.common.model.ResultCodeEnum;
import com.kunlun.common.model.ResponseMessage;

/**
 * Response工具类
 */
public class ResponseUtil {
    public static <T> ResponseMessage<T> successResponse(T data) {
        ResponseMessage result = new ResponseMessage();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getDescribe());
        result.setData(data);
        return result;
    }

    public static ResponseMessage failedResponse(String message, String error) {
        ResponseMessage result = new ResponseMessage();
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(message);
        result.setError(error);
        result.setData(null);
        return result;
    }

    public static ResponseMessage unAuthorized() {
        ResponseMessage result = new ResponseMessage();
        result.setCode(ResultCodeEnum.UNAUTHORIZED.getCode());
        result.setMessage(ResultCodeEnum.UNAUTHORIZED.getDescribe());
        result.setData(null);
        return result;
    }
}
package ru.crud.utils;

import ru.crud.entity.BaseResponse;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.Callable;

public class ResponseUtils {

    private ResponseUtils() {
    }

    public static <T> Response listResponse(List<T> list) {
        BaseResponse<List<T>> response = new BaseResponse<>();
        response.setSuccess(true);
        response.setBody(list);
        return Response.ok(response).build();
    }

    public static <T> Response objectResponse(T obj) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setSuccess(true);
        response.setBody(obj);
        return Response.ok(response).build();
    }

    public static Response booleanResponse(Boolean success) {
        BaseResponse<String> response = new BaseResponse<>();
        response.setSuccess(success);
        return Response.ok(response).build();
    }

    public static Response insertResponse(Long id) {
        BaseResponse<Long> response = new BaseResponse<>();
        response.setSuccess(true);
        response.setBody(id);
        return Response.ok(response).build();
    }

    public static Response execute(Callable<Response> response) {
        try {
            return response.call();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            BaseResponse<String> errorResponse = new BaseResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setEx(e);
            return Response.ok(errorResponse).build();
        }
    }
}

package ru.crud.entity;

/**
 * Базовый класс ответа
 * Created by vasilev-ad on 11.05.24
 */
public class BaseResponse<T> {

    private Boolean success;
    private T body;
    private Exception ex;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Exception getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }
}

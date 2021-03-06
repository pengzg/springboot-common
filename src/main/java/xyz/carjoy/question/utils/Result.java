package xyz.carjoy.question.utils;

import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = 1188275509308264519L;
    private int code;
    private HttpCode httpCode;
    private String desc;
    private Object data;

    public Result() {
    }

    public HttpCode getHttpCode() {
        return this.httpCode;
    }

    public void setHttpCode(HttpCode httpCode) {
        this.httpCode = httpCode;
    }

    public String getDesc() {
        return this.desc;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(HttpCode httpCode, Object data, String desc) {
        this.data = data;
        this.setHttpCode(httpCode);
        this.desc = desc;
    }

    public void setData(HttpCode httpCode, Object data) {
        this.data = data;
        this.setHttpCode(httpCode);
    }

    public void setSuccess(String desc) {
        this.setHttpCode(HttpCode.OK);
        this.desc = desc;
    }

    public void setError(HttpCode httpCode, String desc) {
        this.setHttpCode(httpCode);
        this.desc = desc;
    }

    public void setError(String desc) {
        this.setHttpCode(HttpCode.INTERNAL_SERVER_ERROR);
        this.desc = desc;
    }

    public int getCode() {
        if (this.httpCode != null) {
            this.code = this.httpCode.value();
        }

        return this.code;
    }

    public void setExecRes(String code, String desc) {
        this.code = Integer.parseInt(code);
        this.desc = desc;
    }
}

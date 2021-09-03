package com.codegym.demo.dto.response;

public enum Response {
    SUCCESS("0000", "Success"),
    USERNAME_IS_EXISTS("0001", "Username is existed"),
    EMAIL_IS_EXISTS("0002", "Email is existed"),
    OBJECT_IS_EXISTS("0003", "Object is existed"),
    OBJECT_NOT_FOUND("0004", "Object is not founded"),
    OBJECT_INVALID("0005","Object is invalid"),
    NAME_IS_EXISTS("0006","Name is invalid"),
    SYSTEM_ERROR("9999", "System errors");
    private String responseCode;
    private String responseMessage;

    private Response(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}

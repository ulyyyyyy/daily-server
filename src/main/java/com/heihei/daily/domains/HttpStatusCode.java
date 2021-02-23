package com.heihei.daily.domains;

/**
 *
 */
public enum HttpStatusCode {

    /**
     *
     */
    HTTP_OK("请求成功", 200),
    /**
     *
     */
    HTTP_ERROR("请求失败", 401),
    /**
     *
     */
    HTTP_INFO_ID_NOT_FOUND("未找到请求id", 422),
    /**
     *
     */
    HTTP_INFO_UPDATED("记录更新成功", 200),

    /**
     *
     */
    HTTP_DELETE_OK("记录删除成功", 200),

    /**
     *
     */
    HTTP_DELETE_ERROR("记录删除失败", 401)
    ;


    private String message;

    private int statusCode;

    HttpStatusCode(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }


    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

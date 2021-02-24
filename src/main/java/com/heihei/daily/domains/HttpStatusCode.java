package com.heihei.daily.domains;

/**
 *
 */
public enum HttpStatusCode {

    /**
     * 请求成功通用返回
     */
    HTTP_OK("请求成功", 200),
    /**
     * 请求失败通用返回
     */
    HTTP_ERROR("请求失败", 401),
    /**
     *
     */
    HTTP_INFO_ID_NOT_FOUND("未找到请求id", 422),
    /**
     * 数据更新成功通用返回
     */
    HTTP_UPDATED_OK("记录更新成功", 200),
    /**
     * 数据更新失败通用返回
     */
    HTTP_UPDATED_ERROR("记录更新失败", 422),
    /**
     * 数据删除成功通用返回
     */
    HTTP_DELETE_OK("记录删除成功", 200),
    /**
     * 数据删除失败通用返回
     */
    HTTP_DELETE_ERROR("记录删除失败", 401),
    /**
     * 数据创建成功通用返回
     */
    HTTP_CREATE_OK("数据创建成功", 200),
    /**
     * 数据创建失败通用返回
     */
    HTTP_CREATE_ERROR("数据创建失败", 422),
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

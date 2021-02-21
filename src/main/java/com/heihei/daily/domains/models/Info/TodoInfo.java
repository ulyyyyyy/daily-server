package com.heihei.daily.domains.models.Info;

import lombok.Data;

import java.util.Date;

@Data
public class TodoInfo {

    /**
     * 截止时间
     */
    private Date todoTime;

    /**
     * 内容
     */
    private String content;

    /**
     * id
     */
    private String infoId;

    /**
     * 处理人
     */
    private String owner;

    public void updateTodoInfo(TodoInfo todoInfo) {
        this.content = todoInfo.getContent();
        this.todoTime = todoInfo.getTodoTime();
        this.owner = todoInfo.getOwner();
    }
}

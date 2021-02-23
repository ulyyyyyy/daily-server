package com.heihei.daily.domains.models.Info;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class TodoInfo {

    /**
     * id
     */
    @Id
    private String Id;

    /**
     * 内容
     */
    private String content;

    /**
     * 截止时间
     */
    private Date todoTime;

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

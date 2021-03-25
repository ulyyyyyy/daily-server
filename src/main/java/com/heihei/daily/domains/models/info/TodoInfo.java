package com.heihei.daily.domains.models.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoInfo {

    /**
     * id
     */
    private int id;

    /**
     * 内容
     */
    private String content;

    /**
     * 截止时间
     */
    private Date todoTime = new Date();

    /**
     * 处理人
     */
    private String owner;

    public TodoInfo(String content, String owner) {
        this.content = content;
        this.owner = owner;
    }

    public void updateTodoInfo(TodoInfo todoInfo) {
        this.content = todoInfo.getContent();
        this.todoTime = todoInfo.getTodoTime();
        this.owner = todoInfo.getOwner();
    }
}

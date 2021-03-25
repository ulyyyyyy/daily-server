package com.heihei.daily.domains.models.info;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class DoneInfo {

    /**
     * Id
     */
    private int id;

    /**
     * 更新内容
     */
    private String content;

    /**
     * 完成时间
     */
    private Date doneTime;

    /**
     * 完成人
     */
    private String finisher;

    public void updateInfo(DoneInfo doneInfo) {
        this.content = doneInfo.getContent();
        this.doneTime = doneInfo.getDoneTime();
        this.finisher = doneInfo.getFinisher();
    }

    public DoneInfo change2Todo(TodoInfo todoInfo, String finisher) {
        this.content = todoInfo.getContent();
        this.id = todoInfo.getId();
        this.finisher = finisher;
        this.doneTime = new Date();
        return this;
    }
}

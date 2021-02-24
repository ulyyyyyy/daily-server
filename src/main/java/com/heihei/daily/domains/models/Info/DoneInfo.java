package com.heihei.daily.domains.models.Info;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
public class DoneInfo {

    /**
     * Id
     */
    @Field("id")
    @Id
    private String id;

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
}

package com.heihei.daily.domains.models.Info;

import lombok.Data;

import java.util.Date;

@Data
public class DoneInfo {

    private String content;

    private String infoId;

    private Date doneTime;

    private String finisher;

    public void updateInfo(DoneInfo doneInfo){
        this.content = doneInfo.getContent();
        this.doneTime = doneInfo.getDoneTime();
        this.finisher = doneInfo.getFinisher();
    }
}

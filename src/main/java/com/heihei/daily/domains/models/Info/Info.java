package com.heihei.daily.domains.models.Info;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Info {

    /**
     * Info Id
     */
    @Id
    private String infoId;

    /**
     * 用户名
     */
    private String nickName;

    /**
     * TodoInfo类
     */
    private List<TodoInfo> todo = new ArrayList<>();

    /**
     * DoneInfo类
     */
    private List<DoneInfo> done = new ArrayList<>();


    public Info(String nickName) {
        this.nickName = nickName;
    }

    public boolean isNull(){
        return this.getInfoId() != null && this.getInfoId().length() != 0;
    }
}

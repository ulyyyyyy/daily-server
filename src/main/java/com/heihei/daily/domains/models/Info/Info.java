package com.heihei.daily.domains.models.Info;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
public class Info {

    /**
     * Info Id
     */
    @Field("id")
    private String infoId;

    /**
     * 用户名
     */
    private String nickName;

    /**
     * TodoInfo类
     */
    private List<TodoInfo> todo;

    /**
     * DoneInfo类
     */
    private List<DoneInfo> done;

    public Info(String infoId) {
        this.infoId = infoId;
    }
}

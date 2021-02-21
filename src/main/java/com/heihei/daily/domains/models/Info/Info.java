package com.heihei.daily.domains.models.Info;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Info {

    /**
     * User Id
     */
    private String id;

    /**
     * 用户名
     */
    private String nickName;

    /**
     * 允许的列表
     */
    private List<String> allowedList;

    /**
     * TodoInfo类
     */
    private List<TodoInfo> todo;

    /**
     * DoneInfo类
     */
    private List<DoneInfo> done;

    public Info(String id) {
        this.id = id;
    }
}

package com.heihei.daily.domains.storage;

import com.heihei.daily.domains.models.Info.DoneInfo;
import com.heihei.daily.domains.models.Info.Info;
import com.heihei.daily.domains.models.Info.TodoInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InfoListRepository extends MongoRepository<Info, String> {

    List<TodoInfo> findTodoInfoById(String id);

    List<DoneInfo> findDoneInfoById(String id);

    void saveTodoInfoById(List<TodoInfo> todoInfo, String id);

    void saveDoneInfoById(List<DoneInfo> todoInfo, String id);

}

package com.heihei.daily.domains.storage;

import com.heihei.daily.domains.models.Info.DoneInfo;
import com.heihei.daily.domains.models.Info.Info;
import com.heihei.daily.domains.models.Info.TodoInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface InfoRepository extends MongoRepository<Info, String> {

    /**
     * 根据Info　id 查询所有TodoInfo
     *
     * @param infoId 查询的info id
     * @return List<TodoInfo>
     */
    @Query(value = "select todo from daily where infoId = %:infoId%")
    List<TodoInfo> findTodoInfoByInfoId(String infoId);

    /**
     * 根据Done id 查询所有DoneInfo
     *
     * @param infoId 查询的info id
     * @return
     */
    @Query(value = "select done from daily where infoId = %:infoId%")
    List<DoneInfo> findDoneInfoByInfoId(String infoId);

    /**
     * @param todoInfo
     * @param infoId
     */
    @Query(value = "update todo set todo=?1 where infoId=?3 and id=?2")
    void saveTodoInfoByInfoId(TodoInfo todoInfo, String id, String infoId);

    /**
     * @param doneInfo
     * @param infoId
     */
    @Query(value = "update done set done=?1 where infoId=?3 and id=?2")
    void saveDoneInfoByInfoId(DoneInfo doneInfo, String id, String infoId);

}

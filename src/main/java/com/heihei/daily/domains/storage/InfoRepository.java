package com.heihei.daily.domains.storage;

import com.heihei.daily.domains.models.info.DoneInfo;
import com.heihei.daily.domains.models.info.Info;
import com.heihei.daily.domains.models.info.TodoInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface InfoRepository extends MongoRepository<Info, String> {

    /**
     * 根据infoId获取当前Info数据
     *
     * @param infoId 当前的info Id
     * @return 当前Info数据
     */
//    @Query(value = "select * from daily where id = %:infoId%")
//    Info findInfoByInfoId(String infoId);

    /**
     * 根据Info　id 查询所有TodoInfo
     *
     * @param infoId 查询的info id
     * @return List<TodoInfo>
     */
//    @Query(value = "{'id' = '?0'}")
//    List<TodoInfo> findTodoInfoByInfoId(String infoId);

    /**
     * 根据Done id 查询所有DoneInfo
     *
     * @param infoId 查询的info id
     * @return 所有Done Info数据
     */
//    @Query(value = "select done from daily where infoId = %:infoId%")
//    List<DoneInfo> findDoneInfoByInfoId(String infoId);


//    @Query(value = "insert info.todo where info.$.todo.id=%:infoId%")
//    void saveTodoInfo(TodoInfo todoInfo, String id);


    /**
     * 根据Info Id 更新一个已存在的Todo Info
     *
     * @param todoInfo 保存的todo Info数据
     * @param id       需要更新的todo id信息
     * @param infoId   查询的info id
     */
//    @Query(value = "update info set info.todo=?1 where infoId=?3 and id=?2")
//    void saveTodoInfoByInfoId(TodoInfo todoInfo, String id, String infoId);

    /**
     * 根据Info Id 保存一个新的Todo Info
     *
     * @param todoInfo 保存的todo Info数据
     * @param infoId   查询的info id
     */
//    @Query(value = "insert info.todo where id=%:infoId%")
//    void saveTodoInfoByInfoId(TodoInfo todoInfo, String infoId);

    /**
     * 根据Info Id 保存一个新的Done Info
     *
     * @param doneInfo 保存的done Info数据
     * @param infoId   查询的info id
     */
//    @Query(value = "insert done where id = ?2")
//    void saveDoneInfoByInfoId(DoneInfo doneInfo, String infoId);

    /**
     * 根据Info Id 更新一个已存在的Done Info
     *
     * @param doneInfo 保存的done Info数据
     * @param id       需要更新的todo id信息
     * @param infoId   查询得info id
     */
//    @Query(value = "update done set done=?1 where infoId=?3 and id=?2")
//    void saveDoneInfoByInfoId(DoneInfo doneInfo, String id, String infoId);
}

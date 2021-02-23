package com.heihei.daily.services;

import com.heihei.daily.domains.HttpStatusCode;
import com.heihei.daily.domains.MyResponseContent;
import com.heihei.daily.domains.models.Info.DoneInfo;
import com.heihei.daily.domains.models.Info.Info;
import com.heihei.daily.domains.models.Info.TodoInfo;
import com.heihei.daily.domains.storage.InfoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfoService {

    @Autowired
    InfoListRepository infoListRepository;


    public List<Info> getAllInfos() {
        return infoListRepository.findAll();
    }

    /**
     * 获取用户下所有Info
     *
     * @param userId 用户id
     * @return Info
     */
    public Info getInfoById(String userId) {

        Optional<Info> optionalInfo = infoListRepository.findById(userId);
        return optionalInfo.orElse(new Info());
    }

    /**
     * 新增一条todoInfo
     *
     * @param todoInfo todoInfo
     * @param userId   用户id
     * @return 新增的数据
     */
    public TodoInfo createInfo(TodoInfo todoInfo, String userId) {
        Info info;
        Optional<Info> optionalInfo = infoListRepository.findById(userId);
        if (optionalInfo.isPresent()) {
            info = optionalInfo.get();
            List<TodoInfo> todoInfos = info.getTodo();
            todoInfos.add(todoInfo);
        } else {
            info = new Info();
            List<TodoInfo> todoInfos = info.getTodo();
            todoInfos.add(todoInfo);
            info.setTodo(todoInfos);
        }
        infoListRepository.save(info);
        return todoInfo;
    }

    /**
     * 更新todo info
     *
     * @param todoInfo 更新的Todo Info信息
     * @param id       需要更新的todo info Id
     * @param infoId   操作的Info Id
     * @return 更新的Todo Info信息
     */
    public MyResponseContent<TodoInfo> updateTodo(TodoInfo todoInfo, String id, String infoId) {
        List<TodoInfo> todoInfos = infoListRepository.findTodoInfoById(infoId);
        if (todoInfos != null && todoInfos.size() > 0) {
            for (TodoInfo ele : todoInfos) {
                if (id.equals(ele.getId())) {
                    ele.updateTodoInfo(todoInfo);
                    break;
                }
            }
            infoListRepository.saveTodoInfoById(todoInfos, infoId);
            return new MyResponseContent<>(true, todoInfo, HttpStatusCode.HTTP_INFO_UPDATED);
        } else {
            return new MyResponseContent<>(false, todoInfo, HttpStatusCode.HTTP_INFO_ID_NOT_FOUND);
        }
    }

    /**
     *
     * @param id       需要更新的todo info Id
     * @param infoId   操作的Info Id
     * @return
     */
    public MyResponseContent<String> deleteTodo(String id, String infoId) {
        List<TodoInfo> todoInfos = infoListRepository.findTodoInfoById(infoId);
        todoInfos.removeIf(todoInfo -> id.equals(todoInfo.getId()));
        return new MyResponseContent<>(true, id, HttpStatusCode.HTTP_DELETE_OK);
    }

    public DoneInfo createDone(DoneInfo doneInfo, String userId) {
        Info info;
        Optional<Info> optionalInfo = infoListRepository.findById(userId);
        if (optionalInfo.isPresent()) {
            info = optionalInfo.get();
            List<DoneInfo> doneInfos = info.getDone();
            doneInfos.add(doneInfo);
            info.setDone(doneInfos);
        } else {
            info = new Info();
            List<DoneInfo> doneInfos = info.getDone();
            doneInfos.add(doneInfo);
            info.setDone(doneInfos);
        }
        infoListRepository.save(info);
        return doneInfo;
    }

    public MyResponseContent<DoneInfo> updateDone(DoneInfo doneInfo, String id, String userId) {
        List<DoneInfo> doneInfos = infoListRepository.findDoneInfoById(userId);
        if (doneInfos != null && doneInfos.size() > 0) {
            for (DoneInfo ele : doneInfos) {
                if (id.equals(ele.getId())) {
                    ele.updateInfo(doneInfo);
                    break;
                }
            }
            // 更新数据库
            infoListRepository.saveDoneInfoById(doneInfos, userId);
            return new MyResponseContent<>(true, doneInfo, HttpStatusCode.HTTP_INFO_UPDATED);
        } else {
            return new MyResponseContent<>(false, doneInfo, HttpStatusCode.HTTP_INFO_ID_NOT_FOUND);
        }
    }

    public MyResponseContent<String> deleteDone(String id, String infoId) {
        List<DoneInfo> doneInfos = infoListRepository.findDoneInfoById(infoId);
        doneInfos.removeIf(doneInfo -> id.equals(doneInfo.getId()));
        return new MyResponseContent<>(true, id, HttpStatusCode.HTTP_DELETE_OK);
    }
}

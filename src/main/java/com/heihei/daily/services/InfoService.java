package com.heihei.daily.services;

import com.heihei.daily.domains.HttpStatusCode;
import com.heihei.daily.domains.MyResponseContent;
import com.heihei.daily.domains.models.Info.DoneInfo;
import com.heihei.daily.domains.models.Info.Info;
import com.heihei.daily.domains.models.Info.TodoInfo;
import com.heihei.daily.domains.storage.InfoListRepository;
import com.sun.tools.javac.comp.Todo;
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

    public MyResponseContent<TodoInfo> updateTodo(TodoInfo todoInfo, String id, String userId) {
        List<TodoInfo> todoInfos = infoListRepository.findTodoInfoById(userId);
        if (todoInfos != null && todoInfos.size() > 0) {
            for (TodoInfo ele : todoInfos) {
                if (id.equals(ele.getInfoId())) {
                    ele.updateTodoInfo(todoInfo);
                    break;
                }
            }
            infoListRepository.saveTodoInfoById(todoInfos, userId);
            return new MyResponseContent<>(true, todoInfo, HttpStatusCode.HTTP_INFO_UPDATED);
        } else {
            return new MyResponseContent<>(false, todoInfo, HttpStatusCode.HTTP_INFO_ID_NOT_FOUND);
        }
    }

    public Object deleteTodo(String id, String userId) {
        return "";
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
                if (id.equals(ele.getInfoId())) {
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

    public Object deleteDone(String id, String userId) {
        return "";
    }
}

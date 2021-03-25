package com.heihei.daily.services;

import com.heihei.daily.domains.HttpStatusCode;
import com.heihei.daily.domains.MyResponseContent;
import com.heihei.daily.domains.models.info.DoneInfo;
import com.heihei.daily.domains.models.info.Info;
import com.heihei.daily.domains.models.info.TodoInfo;
import com.heihei.daily.domains.models.user.User;
import com.heihei.daily.domains.storage.InfoRepository;
import com.heihei.daily.domains.storage.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.heihei.daily.domains.HttpStatusCode.HTTP_UPDATED_ERROR;

@Service
@Slf4j
public class InfoService {

    @Autowired
    private InfoRepository infoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Info> getAllInfos() {
        return infoRepository.findAll();
    }

    /**
     * 获取用户下所有Info
     *
     * @param infoId 用户id
     * @return Info
     */
    public MyResponseContent<Info> getInfoById(String infoId) {
        try {
            Optional<Info> optionalInfo = infoRepository.findById(infoId);
            System.out.println(optionalInfo.get());
            // 为什么查不出来，因为之前Field设置成Id，与_id冲突
            Info info = optionalInfo.orElse(new Info());
            if (info.checkNull()) {
                return new MyResponseContent<>(true, info, HttpStatusCode.HTTP_OK);
            } else {
                return new MyResponseContent<>(false, null, HttpStatusCode.HTTP_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MyResponseContent<>(false, null, e.getMessage(), 422);
        }
    }

    /**
     * 创建一个新的info数据
     *
     * @param info 新的info数据
     * @return 创建完成的info数据，一般是
     */
    public MyResponseContent<Info> createInfo(Info info, String userId) {
        try {
            // 创建一条info
            Info retInfo = infoRepository.save(info);
            System.out.println(retInfo.getInfoId());

            // 绑定info到对应的uesrId
            Optional<User> userOptional = userRepository.findById(userId);
            User user = userOptional.orElse(new User());
            user.getAllowedInfoList().add(retInfo.getInfoId());
            userRepository.save(user);

            if (retInfo.getInfoId() != null) {
                return new MyResponseContent<>(true, retInfo, HttpStatusCode.HTTP_CREATE_OK);
            } else {
                return new MyResponseContent<>(false, null, HttpStatusCode.HTTP_CREATE_ERROR);
            }
        } catch (Exception e) {
            return new MyResponseContent<>(false, null, e.getMessage(), 422);
        }

    }

    /**
     * 新增一条todoInfo
     *
     * @param todoInfo todoInfo
     * @param infoId   infoId
     * @return 新增的数据
     */
    public TodoInfo createTodo(TodoInfo todoInfo, String infoId) {
        Info info;

        Optional<Info> optionalInfo = infoRepository.findById(infoId);
        info = optionalInfo.orElse(new Info());
        List<TodoInfo> todoInfos = info.getTodo();

        int size = todoInfos.size();
        todoInfo.setId(size + 1);
        todoInfos.add(todoInfo);

        infoRepository.save(info);
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
    public MyResponseContent<TodoInfo> updateTodo(TodoInfo todoInfo, int id, String infoId) {
//        infoRepository.saveTodoInfoByInfoId(todoInfo, id, infoId);
        return new MyResponseContent<>(true, todoInfo, HttpStatusCode.HTTP_UPDATED_OK);
    }

    /**
     * @param id     需要更新的todo info Id
     * @param infoId 操作的Info Id
     * @return
     */
    public MyResponseContent<Integer> deleteTodo(int id, String infoId) {
//        List<TodoInfo> todoInfos = infoRepository.findTodoInfoByInfoId(infoId);
//        todoInfos.removeIf(todoInfo -> id.equals(todoInfo.getId()));
        return new MyResponseContent<>(true, id, HttpStatusCode.HTTP_DELETE_OK);
    }

    /**
     * 创建一个DoneInfo
     *
     * @param doneInfo doneInfo信息
     * @param userId   绑定的用户Id
     * @return DoneInfo
     */
    public DoneInfo createDone(DoneInfo doneInfo, String userId) {
        Info info;

        Optional<Info> optionalInfo = infoRepository.findById(userId);
        info = optionalInfo.orElse(new Info());
        List<DoneInfo> doneInfos = info.getDone();

        int size = doneInfos.size();
        doneInfo.setId(size + 1);
        doneInfos.add(doneInfo);

        infoRepository.save(info);
        return doneInfo;
    }

    public MyResponseContent<DoneInfo> updateDone(DoneInfo doneInfo, int id, String userId) {
//        List<DoneInfo> doneInfos = infoRepository.findDoneInfoByInfoId(userId);
//        if (doneInfos != null && doneInfos.size() > 0) {
//            for (DoneInfo ele : doneInfos) {
//                if (id.equals(ele.getId())) {
//                    ele.updateInfo(doneInfo);
//                    break;
//                }
//            }
//            // 更新数据库
//            infoRepository.saveDoneInfoByInfoId(doneInfo, id, userId);
//            return new MyResponseContent<>(true, doneInfo, HttpStatusCode.HTTP_UPDATED_OK);
//        } else {
//            return new MyResponseContent<>(false, doneInfo, HttpStatusCode.HTTP_INFO_ID_NOT_FOUND);
//        }
        return new MyResponseContent<>(false, doneInfo, HttpStatusCode.HTTP_INFO_ID_NOT_FOUND);
    }

    public MyResponseContent<Integer> deleteDone(int id, String infoId) {
//        List<DoneInfo> doneInfos = infoRepository.findDoneInfoByInfoId(infoId);
//        doneInfos.removeIf(doneInfo -> id.equals(doneInfo.getId()));
        return new MyResponseContent<>(true, id, HttpStatusCode.HTTP_DELETE_OK);
    }

    /**
     * 将TodoInfo更新成为DoneInfo
     *
     * @param infoId   变更的infoId
     * @param id       变更的todoInfo的Id
     * @param operator 操作人
     * @return 操作的id
     */
    public MyResponseContent<String> accomplish(String infoId, int id, String operator) {
        Optional<Info> infoOptional = infoRepository.findById(infoId);
        if (!infoOptional.isPresent()) {
            return new MyResponseContent<>(false, infoId, HTTP_UPDATED_ERROR);
        }
        Info info = infoOptional.get();
        List<TodoInfo> todoInfoList = info.getTodo();
        List<DoneInfo> doneInfoList = info.getDone();

        for (TodoInfo todo : todoInfoList) {
            if (todo.getId() == id) {
                todoInfoList.remove(todo);
                doneInfoList.add(new DoneInfo().change2Todo(todo, operator));
                break;
            }
        }
        infoRepository.save(info);
        return null;
    }
}

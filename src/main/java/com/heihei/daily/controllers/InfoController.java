package com.heihei.daily.controllers;

import com.heihei.daily.domains.HttpStatusCode;
import com.heihei.daily.domains.MyResponseContent;
import com.heihei.daily.domains.models.info.DoneInfo;
import com.heihei.daily.domains.models.info.Info;
import com.heihei.daily.domains.models.info.TodoInfo;
import com.heihei.daily.services.InfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@Api(tags = "土豆数据's Api")
public class InfoController {

    @Autowired
    InfoService services;

    @GetMapping("/infos")
    @ApiOperation("获取所有Info List 数据")
    public MyResponseContent<List<Info>> getAllInfos() {
        return new MyResponseContent<>(true, services.getAllInfos(), HttpStatusCode.HTTP_OK);
    }

    @GetMapping("/info")
    @ApiOperation("获取指定id的Info List数据")
    public MyResponseContent<Info> getInfoById(@RequestParam("infoId") String infoId) {
        return services.getInfoById(infoId);
    }

    @PostMapping("/info")
    @ApiOperation("创建一个新的Info数据")
    public MyResponseContent<Info> createNewInfo(@RequestBody Info info,
                                                 @RequestParam("userId") String userId) {
        return services.createInfo(info, userId);
    }

    @PostMapping("/info/todo")
    @ApiOperation("提交一个新的todo list")
    public Object createNewTodo(@RequestBody TodoInfo todoInfo,
                                @RequestParam("infoId") String infoId) {
        return services.createTodo(todoInfo, infoId);
    }

    @PutMapping("/info/todo/{id}")
    @ApiOperation("修改一个新的todo list")
    public Object updateTodo(@RequestBody TodoInfo todoInfo,
                             @PathVariable("id") int id,
                             @RequestParam("infoId") String infoId) {
        return services.updateTodo(todoInfo, id, infoId);
    }

    @DeleteMapping("/info/todo/{id}")
    @ApiOperation("删除一个todo list")
    public Object deleteTodo(@PathVariable("id") int id,
                             @RequestParam("infoId") String infoId) {
        return services.deleteTodo(id, infoId);
    }

    @PostMapping("/info/done")
    @ApiOperation("创建当前Done List数据")
    public DoneInfo createNewDone(@RequestBody DoneInfo doneInfo,
                                  @RequestParam("infoId") String infoId) {
        return services.createDone(doneInfo, infoId);
    }

    @PutMapping("/info/done/{id}")
    @ApiOperation("修改一个新的done list")
    public Object updateTodo(@RequestBody DoneInfo doneInfo,
                             @PathVariable("id") int id,
                             @RequestParam("infoId") String infoId) {
        return services.updateDone(doneInfo, id, infoId);
    }

    @DeleteMapping("/info/done/{id}")
    @ApiOperation("删除一个todo list")
    public Object deleteDone(@PathVariable("id") int id,
                             @RequestParam("userId") String userId) {
        return services.deleteDone(id, userId);
    }

    @PatchMapping("/info/accomplish/{id}")
    @ApiOperation("完成一个Todo")
    public Object accomplish(@RequestParam("infoId") String infoId,
                             @PathVariable("id") int id,
                             @RequestBody String operator) {
        return services.accomplish(infoId, id, operator);
    }
}

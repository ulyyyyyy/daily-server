package com.heihei.daily.controllers;

import com.heihei.daily.domains.HttpStatusCode;
import com.heihei.daily.domains.MyResponseContent;
import com.heihei.daily.domains.models.Info.DoneInfo;
import com.heihei.daily.domains.models.Info.Info;
import com.heihei.daily.domains.models.Info.TodoInfo;
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
    @ApiOperation("获取当前Info List数据")
    public MyResponseContent<Info> getInfoById(@RequestParam("infoId") String infoId) {
        return new MyResponseContent<>(true, services.getInfoById(infoId), HttpStatusCode.HTTP_OK);
    }

    @PostMapping("/todo/info")
    @ApiOperation("提交一个新的todo list")
    public Object createNewTodo(@RequestBody TodoInfo todoInfo,
                                @RequestParam("infoId") String infoId) {
        return services.createInfo(todoInfo, infoId);
    }

    @PatchMapping("/todo/info/{id}")
    @ApiOperation("修改一个新的todo list")
    public Object updateTodo(@RequestBody TodoInfo todoInfo,
                             @PathVariable("id") String id,
                             @RequestParam("infoId") String infoId) {
        return services.updateTodo(todoInfo, id, infoId);
    }

    @DeleteMapping("/todo/info/{id}")
    @ApiOperation("删除一个todo list")
    public Object deleteTodo(@PathVariable("id") String id,
                             @RequestParam("infoId") String infoId) {
        return services.deleteTodo(id, infoId);
    }

    @PostMapping("/done/info")
    @ApiOperation("获取当前Done List数据")
    public DoneInfo createNewDone(@RequestBody DoneInfo doneInfo,
                                  @RequestParam("infoId") String infoId) {
        return services.createDone(doneInfo, infoId);
    }

    @PatchMapping("/done/info/{id}")
    @ApiOperation("修改一个新的done list")
    public Object updateTodo(@RequestBody DoneInfo doneInfo,
                             @PathVariable("id") String id,
                             @RequestParam("infoId") String infoId) {
        return services.updateDone(doneInfo, id, infoId);
    }

    @DeleteMapping("/done/info/{id}")
    @ApiOperation("删除一个todo list")
    public Object deleteDone(@PathVariable("id") String id,
                             @RequestParam("userId") String userId) {
        return services.deleteDone(id, userId);
    }
}

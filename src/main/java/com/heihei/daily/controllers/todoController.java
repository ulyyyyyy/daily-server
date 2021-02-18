package com.heihei.daily.controllers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/todo")
@Slf4j
public class todoController {

    @GetMapping("/info")
    public Object getAll() {
        return "Dai dai 大傻子";
    }

    @PostMapping("/info")
    public Object createNewTodo() {
        return "";
    }

    @PatchMapping("/info")
    public Object changeInfo() {
        return "";
    }
}

package com.force.labor.controller;

import com.force.labor.dto.FindTasksDTO;
import com.force.labor.dto.TaskDTO;
import com.force.labor.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createTask(@RequestBody @Validated TaskDTO task) {
        taskService.create(task);
    }

    @DeleteMapping("/delete")
    public void deleteTask(@PathParam("id") BigInteger id) {
        taskService.deleteById(id);
    }

    @PostMapping
    public List<TaskDTO> find(@RequestBody FindTasksDTO dto) {
        return taskService.findById(dto);
    }

    @GetMapping("/all")
    public List<TaskDTO> findAll() {
        return taskService.findAll();
    }

    @PutMapping
    public void update(List<TaskDTO> taskDTO) {
        taskService.updateTask(taskDTO);
    }

}

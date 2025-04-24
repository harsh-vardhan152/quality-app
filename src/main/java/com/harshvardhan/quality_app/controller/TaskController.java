package com.harshvardhan.quality_app.controller;


import com.harshvardhan.quality_app.DTO.TaskRequest;
import com.harshvardhan.quality_app.entity.Task;
import com.harshvardhan.quality_app.service.TaskAlignment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskAlignment taskAlignment;

    public TaskController (TaskAlignment taskAlignment){
        this.taskAlignment = taskAlignment;
    }
    @GetMapping
    public ResponseEntity<List<Task>> getAllTask(){
        return ResponseEntity.status(HttpStatus.OK).body(taskAlignment.getAllTask());
    }

    @PostMapping("/new-request")
    public ResponseEntity<Task> createNewTask(@RequestBody TaskRequest taskRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskAlignment.createTask(taskRequest));
    }

    @PutMapping("/update-request/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id ,@RequestBody TaskRequest taskRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskAlignment.updateTask(id,taskRequest));
    }

    @DeleteMapping("/delete-request/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        taskAlignment.deleteTask(id);
        return ResponseEntity.ok("Task is deleted now !!!!");
    }


}

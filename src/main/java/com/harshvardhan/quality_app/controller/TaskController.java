package com.harshvardhan.quality_app.controller;


import com.harshvardhan.quality_app.DTO.TaskRequest;
import com.harshvardhan.quality_app.DTO.TaskStatusUpdateRequest;
import com.harshvardhan.quality_app.entity.Task;
import com.harshvardhan.quality_app.service.StatusService;
import com.harshvardhan.quality_app.service.TaskAlignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskAlignmentService taskAlignmentService;

    private final StatusService statusService;

    public TaskController(TaskAlignmentService taskAlignmentService, StatusService statusService) {
        this.taskAlignmentService = taskAlignmentService;
        this.statusService = statusService;
    }
    @PreAuthorize("hasAnyRole('DEVELOPER', 'TESTER', 'PUBLISHER')")
    @GetMapping
    public ResponseEntity<List<Task>> getAllTask() {
        return ResponseEntity.status(HttpStatus.OK).body(taskAlignmentService.getAllTask());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new-request")
    public ResponseEntity<Task> createNewTask(@RequestBody TaskRequest taskRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskAlignmentService.createTask(taskRequest));
    }

    @PreAuthorize("hasAnyRole('DEVELOPER', 'TESTER', 'PUBLISHER')")
    @PutMapping("/update-request/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskAlignmentService.updateTask(id, taskRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-request/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskAlignmentService.deleteTask(id);
        return ResponseEntity.ok("Task is deleted now !!!!");
    }

    @PreAuthorize("hasAnyRole('DEVELOPER', 'TESTER', 'PUBLISHER')")
    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @RequestBody TaskStatusUpdateRequest request) {
        return ResponseEntity.ok(statusService.updateTaskStatus(id, request));
    }


}

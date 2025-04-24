package com.harshvardhan.quality_app.service;


import com.harshvardhan.quality_app.DTO.TaskStatusUpdateRequest;
import com.harshvardhan.quality_app.entity.*;
import com.harshvardhan.quality_app.repository.TaskRepository;
import com.harshvardhan.quality_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatusService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;


    public StatusService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task updateTaskStatus(Long taskId, TaskStatusUpdateRequest request) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        User userIsNotFound = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User is not found"));

        Set<RoleName> userRoles = userIsNotFound.getRoles().stream().map(Role::getName).collect(Collectors.toSet());

        Status oldStatus = task.getStatus();
        Status newStatus = request.getStatus();

        if (userRoles.contains(RoleName.DEVELOPER)) {
            if (oldStatus == Status.NEW && newStatus == Status.IN_PROGRESS ||
                    oldStatus == Status.IN_PROGRESS && newStatus == Status.READY_FOR_TEST) {
                task.setStatus(newStatus);
            } else {
                throw new RuntimeException("Invalid status transition for Developer");
            }
        } else if (userRoles.contains(RoleName.TESTER)) {
            if (oldStatus == Status.READY_FOR_TEST && newStatus == Status.TESTING ||
                    oldStatus == Status.TESTING && newStatus == Status.TESTED) {
                task.setStatus(newStatus);
            } else {
                throw new RuntimeException("Invalid status transition for Tester");
            }

        } else if (userRoles.contains(RoleName.PUBLISHER)) {
            if (oldStatus == Status.TESTED && newStatus == Status.READY_TO_PUBLISH ||
                    oldStatus == Status.READY_TO_PUBLISH && newStatus == Status.PUBLISHED) {
                task.setStatus(newStatus);
            } else {
                throw new RuntimeException("Invalid status transition for Publisher");
            }

        } else {
            throw new RuntimeException("Unauthorized user role");
        }
        return taskRepository.save(task);
    }
}

package com.harshvardhan.quality_app.service;

import com.harshvardhan.quality_app.DTO.TaskRequest;
import com.harshvardhan.quality_app.entity.RoleName;
import com.harshvardhan.quality_app.entity.Task;
import com.harshvardhan.quality_app.entity.User;
import com.harshvardhan.quality_app.repository.TaskRepository;
import com.harshvardhan.quality_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskAlignment {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    public TaskAlignment(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public Task createTask(TaskRequest request) {
        User user = userRepository.findById(request.getAssignedUserId())
                .orElseThrow(() -> new RuntimeException("Developer is not present"));

        boolean isDeveloper = user.getRoles().stream()
                .anyMatch(role -> role.getName() == RoleName.DEVELOPER);

        if (!isDeveloper) {
            throw new RuntimeException("The aligned user is not a developer");
        }

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus("NEW");
        task.setAssignedTo(user);
        task.setCreaterame(request.getCreatername());

        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId, TaskRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        if (request.getAssignedUserId() != null) {
            User assignedUser = userRepository.findById(request.getAssignedUserId())
                    .orElseThrow(() -> new RuntimeException("Assigned user not found"));

            boolean isDeveloper = assignedUser.getRoles().stream()
                    .anyMatch(role -> role.getName() == RoleName.DEVELOPER);

            if (!isDeveloper) {
                throw new RuntimeException("Assigned user is not a developer");
            }

            task.setAssignedTo(assignedUser);
        }

        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);
    }

}

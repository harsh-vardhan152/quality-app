package com.harshvardhan.quality_app.repository;

import com.harshvardhan.quality_app.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}

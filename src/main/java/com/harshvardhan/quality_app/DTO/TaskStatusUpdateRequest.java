package com.harshvardhan.quality_app.DTO;

import com.harshvardhan.quality_app.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatusUpdateRequest {

    private Status status;
    private Long userId;
}

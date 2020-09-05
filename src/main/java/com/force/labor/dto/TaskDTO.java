package com.force.labor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.force.labor.domain.Priority;
import com.force.labor.domain.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;


import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {

    @NonNull
    private BigInteger id;

    @NonNull
    private String title;

    private String description;

    private Integer difficultyLevel;

    private Priority priority;

    private LocalDateTime estimatedTime;

    private TaskStatus status;

    private LocalDateTime updated;

    private LocalDateTime created;

    private BigInteger assignedTo;

}

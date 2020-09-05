package com.force.labor.dto;

import com.force.labor.domain.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindTasksDTO {

    @NonNull
    private Criteria criteria;

    @Data
    public static class Criteria{
        private List<BigInteger> ids;
        private Priority priority;
        private LocalDateTime createdSince;
        private LocalDateTime createdBefore;
        private int diff;
    }
}

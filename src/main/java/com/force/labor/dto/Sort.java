package com.force.labor.dto;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.swing.SortOrder;

@Data
public class Sort {
    @NonNull
    private String sortBy;
    @NonNull
    private SortOrder type;
}
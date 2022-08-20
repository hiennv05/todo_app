package com.vti.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TaskResponse {
    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String description;

    private LocalDate startDate;

    private LocalDate dueDate;

    private Integer statusId;

    private Integer workSpaceId;

    private String statusName;
}

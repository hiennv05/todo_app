package com.vti.todo.dto.request;

import com.vti.todo.validation.WorkSpaceNotUnique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkSpaceRequest {
    @NotNull
    @NotBlank
    @WorkSpaceNotUnique
    private String workSpaceName;

}

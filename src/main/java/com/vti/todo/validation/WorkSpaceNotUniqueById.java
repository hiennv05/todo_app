package com.vti.todo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = WorkSpaceNotUniqueByIdValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkSpaceNotUniqueById {

    String message() default "{error.workspace.unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

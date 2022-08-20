package com.vti.todo.repository;

import com.vti.todo.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskStatusRepository extends JpaRepository<TaskStatus, Integer> {
}

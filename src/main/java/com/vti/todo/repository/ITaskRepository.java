package com.vti.todo.repository;

import com.vti.todo.dto.request.TaskRequest;
import com.vti.todo.entity.TaskStatus;
import com.vti.todo.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Tasks, Integer> {

    int countByWorkSpaceId(Integer id);

    @Query("select count(distinct id) from Tasks t ")
    long countAllTask( );


    List<Tasks> findByWorkSpaceId(Integer workspaceId);


}

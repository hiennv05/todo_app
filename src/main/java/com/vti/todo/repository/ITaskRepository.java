package com.vti.todo.repository;

import com.vti.todo.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Tasks, Integer> {

    int countByWorkSpaceId(Integer id);

//    @Query("select count(distinct id) from Tasks t ")
//    long countAllTask( );

    List<Tasks> findByWorkSpaceId(Integer workspaceId);

}

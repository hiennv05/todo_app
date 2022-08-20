package com.vti.todo.repository;

import com.vti.todo.dto.response.WorkSpaceResponse;
import com.vti.todo.entity.WorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWorkSpaceRepository extends JpaRepository<WorkSpace, Integer> {
    @Query("SELECT (count(w) = 0) FROM WorkSpace w WHERE name = ?1")
    boolean isWorkSpaceNotExists(String name);

//    @Query(value = "SELECT w FROM WorkSpace w WHERE w.account.id = ?1")
//    List<WorkSpace> findWorkSpaceByAccId(Integer id);

//    @Query(value = "SELECT w FROM WorkSpace w WHERE w.account.email= ?1")
//    List<WorkSpaceResponse> findWorkSpaceByAccEmail(String email);

    @Query(value = "SELECT NEW com.vti.todo.dto.response.WorkSpaceResponse(w.id, w.name, w.numberOfTask) FROM WorkSpace w WHERE w.account.email= ?1")
    List<WorkSpaceResponse> findWorkSpaceByAccEmail(String email);
//
//    SELECT NEW com.vti.todo.dto.response.WorkSpaceResponse(w.id, w.name, count(t))
//    FROM WorkSpaceEntity w left join TaskEntity t ON w = t.workSpace
//    where w.account.email = :email
//    GROUP BY w
}
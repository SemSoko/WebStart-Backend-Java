package com.semsoko.webstartbackend.todo.repository;

import com.semsoko.webstartbackend.todo.model.TodoEntity;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile("db")
@Repository
public interface JpaTodoRepository extends JpaRepository<TodoEntity, Integer>{

}

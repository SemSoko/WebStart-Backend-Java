package com.semsoko.webstartbackend.todo.repository;

import com.semsoko.webstartbackend.todo.model.Todo;

import java.util.List;

public interface TodoRepository {
    void save(Todo todo);
    List<Todo> findAll();
    void deleteById(Long id);
}

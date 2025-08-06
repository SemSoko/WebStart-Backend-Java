package com.semsoko.webstartbackend.todo.service;

import com.semsoko.webstartbackend.todo.dto.NewTodoRequest;
import com.semsoko.webstartbackend.todo.dto.TodoResponse;

import java.util.List;

public interface TodoService {
    TodoResponse createTodo(NewTodoRequest request);
    List<TodoResponse> findAll();

    /**
     * Loescht ein Todo anhand seiner ID.
     *
     * @param id ID des zu loeschenden Todos
     */
    void deleteById(Long id);
}

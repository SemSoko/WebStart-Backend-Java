package com.semsoko.webstartbackend.todo.service;

import com.semsoko.webstartbackend.todo.dto.NewTodoRequest;
import com.semsoko.webstartbackend.todo.dto.TodoResponse;

public interface TodoService {
    TodoResponse createTodo(NewTodoRequest request);
}

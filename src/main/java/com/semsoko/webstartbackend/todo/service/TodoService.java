package com.semsoko.webstartbackend.todo.service;

import com.semsoko.webstartbackend.todo.dto.NewTodoRequest;
import com.semsoko.webstartbackend.todo.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    /**
     * Temporaerer In-Memory-Speicher, wird spaeter ins Repository ausgelagert.
     */
    private final List<Todo> todos = new ArrayList<>();

    public Todo createTodo(NewTodoRequest request){
        Todo newTodo = new Todo(request.getTitle());
        todos.add(newTodo);

        return newTodo;
    }
}
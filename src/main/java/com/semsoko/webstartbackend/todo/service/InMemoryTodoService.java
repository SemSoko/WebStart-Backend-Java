package com.semsoko.webstartbackend.todo.service;

import com.semsoko.webstartbackend.todo.dto.NewTodoRequest;
import com.semsoko.webstartbackend.todo.model.Todo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@Service
@Qualifier("inMemory")
public class InMemoryTodoService implements TodoService{
    /**
     * Temporaerer In-Memory-Speicher, wird spaeter ins Repository ausgelagert.
     */
    private final List<Todo> todos = new ArrayList<>();

    @Override
    public Todo createTodo(NewTodoRequest request){
        Todo newTodo = new Todo(request.getTitle());
        todos.add(newTodo);

        return newTodo;
    }
}
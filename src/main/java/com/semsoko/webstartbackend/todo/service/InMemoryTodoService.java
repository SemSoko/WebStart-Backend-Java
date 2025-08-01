package com.semsoko.webstartbackend.todo.service;

import com.semsoko.webstartbackend.todo.dto.NewTodoRequest;
import com.semsoko.webstartbackend.todo.model.Todo;
import com.semsoko.webstartbackend.todo.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
@Qualifier("inMemory")
public class InMemoryTodoService implements TodoService{
    private final TodoRepository repository;

    public InMemoryTodoService(@Qualifier("inMemory") TodoRepository repository){
        this.repository = repository;
    }

    @Override
    public Todo createTodo(NewTodoRequest request){
        Todo newTodo = new Todo(request.getTitle());
        repository.save(newTodo);
        return newTodo;
    }
}
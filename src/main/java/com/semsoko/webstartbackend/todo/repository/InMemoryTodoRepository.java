package com.semsoko.webstartbackend.todo.repository;

import com.semsoko.webstartbackend.todo.model.Todo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@Repository
@Qualifier("inMemory")
public class InMemoryTodoRepository implements TodoRepository{
    private final List<Todo> todos = new ArrayList<>();

    @Override
    public void save(Todo todo){
        todos.add(todo);
    }

    @Override
    public List<Todo> findAll(){
        return todos;
    }
}

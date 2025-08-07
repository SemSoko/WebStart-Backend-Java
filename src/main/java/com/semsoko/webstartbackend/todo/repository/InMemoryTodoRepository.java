package com.semsoko.webstartbackend.todo.repository;

import com.semsoko.webstartbackend.todo.model.Todo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Profile("dev-inmemory")
@Repository
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

    @Override
    public void deleteById(Long id){
        todos.removeIf(todo -> todo.getId().equals(id));
    }

    @Override
    public Optional findById(Long id){
        return todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();
    }
}

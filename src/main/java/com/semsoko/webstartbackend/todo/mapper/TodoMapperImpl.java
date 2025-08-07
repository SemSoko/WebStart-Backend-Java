package com.semsoko.webstartbackend.todo.mapper;

import com.semsoko.webstartbackend.todo.mapper.TodoMapper;
import com.semsoko.webstartbackend.todo.model.Todo;
import com.semsoko.webstartbackend.todo.model.TodoEntity;
import com.semsoko.webstartbackend.todo.dto.TodoResponse;

import org.springframework.stereotype.Component;

/**
 * Implementierung des TodoMappers.
 *
 * Wandelt interne Datenmodelle (Todo, TodoEntity) in TodoResponse-Objekte um,
 * die fuer den API-Output genutzt werden.
 *
 * Diese Klasse ist als Spring-Component registriert, um ueber Dependency Injection
 * in Services verwendet werden zu koennen.
 */
@Component
public class TodoMapperImpl implements TodoMapper{
    @Override
    public TodoResponse mapToResponse(Todo todo){
        return new TodoResponse(
            todo.getId(),
                todo.getTitle(),
                todo.getDone()
        );
    }

    @Override
    public TodoResponse mapToResponse(TodoEntity entity){
        return new TodoResponse(
            entity.getId(),
                entity.getTitle(),
                entity.getDone()
        );
    }
}

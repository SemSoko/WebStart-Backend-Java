package com.semsoko.webstartbackend.todo.mapper;

import com.semsoko.webstartbackend.todo.dto.TodoResponse;
import com.semsoko.webstartbackend.todo.model.Todo;
import com.semsoko.webstartbackend.todo.model.TodoEntity;

/**
 * Mapper-Interface zur Umwandlung von internen Todo-Modellen in API-Response-DTOs.
 * Trennt die Geschaeftslogik von der Repraesentation der Daten in der API.
 */
public interface TodoMapper {
    TodoResponse mapToResponse(Todo todo);
    TodoResponse mapToResponse(TodoEntity entity);
}

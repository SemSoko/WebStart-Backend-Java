package com.semsoko.webstartbackend.todo.service;

import com.semsoko.webstartbackend.todo.dto.NewTodoRequest;
import com.semsoko.webstartbackend.todo.model.Todo;
import com.semsoko.webstartbackend.todo.model.TodoEntity;
import com.semsoko.webstartbackend.todo.repository.JpaTodoRepository;
import com.semsoko.webstartbackend.todo.mapper.TodoMapper;
import com.semsoko.webstartbackend.todo.dto.TodoResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * PostgresTodoService persistiert Todos in einer PostgreSQL-Datenbank ueber JPA.
 *
 * Diese Implementierung verwendet {@link JpaTodoRepository}, um Todos dauerhaft zu speichern.
 *
 * Aktiv bei gesetztem Profil "prod"
 *
 * @see TodoService
 */
@Profile("dev-postgres")
@Service
public class PostgresTodoService implements TodoService{
    private final JpaTodoRepository repository;
    private final TodoMapper mapper;

    /**
     * Konstruktor mit Injection von Repository und Mapper.
     *
     * @param repository    JPA-basiertes Repository fuer Datenbankoperationen
     * @param mapper        Mapper zur Umwandlung von {@link TodoEntity} in {@link TodoResponse}
     */
    public PostgresTodoService(
            JpaTodoRepository repository,
            TodoMapper mapper
    ){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TodoResponse createTodo(NewTodoRequest request){
        TodoEntity entity = new TodoEntity(request.getTitle());
        TodoEntity saved = repository.save(entity);

        return mapper.mapToResponse(saved);
    }
}

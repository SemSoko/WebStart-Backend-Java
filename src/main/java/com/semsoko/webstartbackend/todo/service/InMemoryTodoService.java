package com.semsoko.webstartbackend.todo.service;

import com.semsoko.webstartbackend.todo.dto.NewTodoRequest;
import com.semsoko.webstartbackend.todo.model.Todo;
import com.semsoko.webstartbackend.todo.repository.TodoRepository;
import com.semsoko.webstartbackend.todo.dto.TodoResponse;
import com.semsoko.webstartbackend.todo.mapper.TodoMapper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * InMemoryTodoService ist eine Entwicklungsimplementierung des {@Link TodoService}
 *
 * Diese Variante speichert Todos zur Laufzeit in einer einfachen In-Memory-Liste.
 * Sie eignet sich besonders fuer lokale Entwicklung und Tests ohne echte Datenbankverbindung.
 *
 * Aktiv, wenn das Spring-Profil "dev" gesetzt wird.
 *
 * @see TodoService
 */
@Profile("dev-inmemory")
@Service
public class InMemoryTodoService implements TodoService{
    private final TodoRepository repository;
    private final TodoMapper mapper;

    /**
     * Konstruktor mit Dependency Injection von Repository und Mapper.
     *
     * @param repository    In-Memory-Implementierung des {@link TodoRepository}
     * @param mapper        Mapper zur Umwandlung von internen {@link Todo} Objekten in {@link TodoResponse} DTOs
     */
    public InMemoryTodoService(
            @Qualifier("inMemory") TodoRepository repository,
            TodoMapper mapper
    ){
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Erstellt ein neues Todo anhand der eingehenden Nutzerdaten und gibt eine API-konforme Antwort zurueck.
     *
     * @param request Eingabedaten des neuen Todos (z.B. Titel)
     * @return {@link TodoResponse} - Darstellung des neuen Todos fuer die API
     */
    @Override
    public TodoResponse createTodo(NewTodoRequest request){
        Todo newTodo = new Todo(request.getTitle());
        repository.save(newTodo);
        return mapper.mapToResponse(newTodo);
    }
}
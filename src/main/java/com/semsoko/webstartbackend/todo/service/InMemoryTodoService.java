package com.semsoko.webstartbackend.todo.service;

import com.semsoko.webstartbackend.todo.dto.NewTodoRequest;
import com.semsoko.webstartbackend.todo.model.Todo;
import com.semsoko.webstartbackend.todo.repository.TodoRepository;
import com.semsoko.webstartbackend.todo.dto.TodoResponse;
import com.semsoko.webstartbackend.todo.mapper.TodoMapper;

import java.util.List;
import java.util.Optional;

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
            TodoRepository repository,
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

    /**
     * Ruft alle gespeicherten Todos aus dem In-Memory-Repository ab und
     * wandlet sie in {@link TodoResponse}-Objekte um.
     *
     * Diese Methode ist nur im Profil "dev-inmemory" aktiv und dient der lokalen Entwicklung
     * ohne persistente Datenbank.
     *
     * @return Liste aller Todos als API-geeignete {@link TodoResponse}-Objekte
     */
    @Override
    public List<TodoResponse> findAll(){
        return repository.findAll()
                .stream()
                .map(mapper::mapToResponse)
                .toList();
    }

    /**
     * Loescht ein Todo anhand seiner ID aus dem In-Memory-Repository.
     *
     * @param id ID des zu loeschenden Todos
     */
    @Override
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    @Override
    public TodoResponse toggleDoneStatus(Long id){
        return repository.findById(id)
                .map(todo -> {
                    todo.setDone(!todo.getDone());
                    return mapper.mapToResponse(todo);
                })
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
    }
}
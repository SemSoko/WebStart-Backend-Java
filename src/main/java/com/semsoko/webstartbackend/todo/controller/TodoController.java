package com.semsoko.webstartbackend.todo.controller;

import com.semsoko.webstartbackend.todo.dto.NewTodoRequest;
import com.semsoko.webstartbackend.todo.dto.TodoResponse;
import com.semsoko.webstartbackend.todo.service.TodoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * REST-Controller fuer das Todo-Modul.
 *
 * Basisroute: /api/todos
 *
 * Delegiert Aufrufe an {@link TodoService} - je nach aktiviertem Profil.
 */
@RestController
@RequestMapping("api/todos")
public class TodoController {
    private final TodoService todoService;

    /**
     * Konstruktor-basierte Abhaengigkeitsinjektion.
     * Spring injiziert autoamtisch die passende {@link TodoService}-Implementierung,
     * abhaengig vom aktiven Profil (z.B. "dev" oder "prod").
     *
     * @param todoService
     */
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    /**
     * Erstellt ein neues Todo auf Basis der Client-Anfrage.
     *
     * @param request Eingabedaten vom Client (JSON)
     * @return Datenstruktur zur API-Ausgabe (JSON)
     */
    @PostMapping
    public TodoResponse create(@RequestBody NewTodoRequest request){
        return todoService.createTodo(request);
    }

    /**
     * Gibt eine Liste aller gespeicherten Todos zurueck.
     *
     * @return Liste von {@link TodoResponse}-Objekten
     */
    @GetMapping
    public List<TodoResponse> findAll(){
        return todoService.findAll();
    }

}

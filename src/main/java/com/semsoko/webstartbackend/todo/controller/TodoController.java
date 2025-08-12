package com.semsoko.webstartbackend.todo.controller;

import com.semsoko.webstartbackend.shared.api.ApiResponse;
import com.semsoko.webstartbackend.shared.api.ApiResponseFactory;
import com.semsoko.webstartbackend.todo.dto.NewTodoRequest;
import com.semsoko.webstartbackend.todo.dto.TodoResponse;
import com.semsoko.webstartbackend.todo.service.TodoService;

import java.util.List;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST-Controller fuer das Todo-Modul.
 *
 * Basisroute: /api/v1/todos
 *
 * Delegiert Aufrufe an {@link TodoService} - je nach aktiviertem Profil.
 * Erfolgreiche Antworten werden über {@link ApiResponseFactory} in einheitlichem
 * JSON-Format zurückgegeben, Fehler werden zentral über den GlobalExceptionHandler verarbeitet.
 */
@RestController
@RequestMapping("api/v1/todos")
public class TodoController {
    private final TodoService todoService;
    private final ApiResponseFactory apiResponseFactory;

    /**
     * Konstruktor-basierte Abhaengigkeitsinjektion.
     * Spring injiziert autoamtisch die passende {@link TodoService}-Implementierung,
     * abhaengig vom aktiven Profil (z.B. "dev" oder "prod").
     *
     * @param todoService           Service-Implementierung fuer Todo-Operationen
     * @param apiResponseFactory    Factory fuer einheitliche API-Antworten
     */
    public TodoController(
            TodoService todoService,
            ApiResponseFactory apiResponseFactory
    ){
        this.todoService = todoService;
        this.apiResponseFactory = apiResponseFactory;
    }

    /**
     * Erstellt ein neues Todo auf Basis der Client-Anfrage.
     *
     * @param request Eingabedaten vom Client (JSON)
     * @return Erfolgsantwort mit dem erstellten Todo
     */
    @PostMapping
    public ResponseEntity<ApiResponse<TodoResponse>> create(
            @RequestBody NewTodoRequest request
    ){
        TodoResponse created = todoService.createTodo(request);
        return ResponseEntity.ok(apiResponseFactory.success(created));
    }

    /**
     * Gibt eine Liste aller gespeicherten Todos zurueck.
     *
     * @return Erfolgsantwort mit Liste von Todos
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<TodoResponse>>> findAll(){
        List<TodoResponse> todos = todoService.findAll();
        return ResponseEntity.ok(apiResponseFactory.success(todos));
    }

    /**
     * Loescht ein Todo anhand der ID.
     *
     * Beispiel: DELETE /api/v1/todos/3
     *
     * @param id ID des zu loeschenden Todos
     * @return Erfolgsantwort ohne Payload
     */
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id){
        todoService.deleteById(id);
        return ResponseEntity.ok(apiResponseFactory.success(null));
    }

    /**
     * Schaltet den Erledigt-Status (done) eines Todos um.
     *
     * Beispiel: PATCH /api/v1/todos/3/toggle
     *
     * @param id ID des zu toggelnden Todos
     * @return Erfolgsantwort mit dem aktualisierten Todo
     */
    @PatchMapping("{id}/toggle")
    public ResponseEntity<ApiResponse<TodoResponse>> toggle(@PathVariable Long id){
        TodoResponse updated = todoService.toggleDoneStatus(id);
        return ResponseEntity.ok(apiResponseFactory.success(updated));
    }
}
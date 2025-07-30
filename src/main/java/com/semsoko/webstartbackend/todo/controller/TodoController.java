package com.semsoko.webstartbackend.todo.controller;

import com.semsoko.webstartbackend.todo.dto.NewTodoRequest;
import com.semsoko.webstartbackend.todo.model.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * RestController -> macht aus der Klasse einen HTTP-Controller
 * RequestMapping("/api/todos") -> Basisroute
 */
@RestController
@RequestMapping("api/todos")
public class TodoController {
    private final List<Todo> todos = new ArrayList<>();

    /**
     * PostMapping -> Methode für POST-Anfragen
     */
    @PostMapping
    public Todo createTodo(
            /**
             * RequestBody -> Spring mappt das JSON in NewTodoRequest
             */
            @RequestBody NewTodoRequest request
    ){
        Todo newTodo = new Todo(request.getTitle());
        /**
         * Liste speichert Todos im Arbeitsspeicher (noch keine DB)
         */
        todos.add(newTodo);

        return newTodo;
    }
}

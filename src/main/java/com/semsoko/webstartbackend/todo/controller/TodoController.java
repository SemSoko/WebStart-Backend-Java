package com.semsoko.webstartbackend.todo.controller;

import com.semsoko.webstartbackend.todo.dto.NewTodoRequest;
import com.semsoko.webstartbackend.todo.model.Todo;
import com.semsoko.webstartbackend.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * RestController -> macht aus der Klasse einen HTTP-Controller
 * RequestMapping("/api/todos") -> Basisroute
 */
@RestController
@RequestMapping("api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(@Qualifier("inMemory") TodoService todoService){
        this.todoService = todoService;
    }
    /**
     * PostMapping -> Methode für POST-Anfragen
     */
    @PostMapping
    public Todo create(@RequestBody NewTodoRequest request){
        return todoService.createTodo(request);
    }
}

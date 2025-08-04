package com.semsoko.webstartbackend.todo.service;

import com.semsoko.webstartbackend.todo.dto.NewTodoRequest;
import com.semsoko.webstartbackend.todo.model.Todo;
import com.semsoko.webstartbackend.todo.model.TodoEntity;
import com.semsoko.webstartbackend.todo.repository.JpaTodoRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
@Qualifier("jpa")
public class PostgresTodoService implements TodoService{
    private final JpaTodoRepository repository;

    public PostgresTodoService(JpaTodoRepository repository){
        this.repository = repository;
    }

    @Override
    public TodoEntity createTodo(NewTodoRequest request){
        TodoEntity entity = new TodoEntity();
        entity.setTitle(request.getTitle());
        entity.setDone(false);

        return repository.save(entity);
    }
}

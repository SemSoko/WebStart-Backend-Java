package com.semsoko.webstartbackend.todo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Boolean done = false;

    public TodoEntity(){

    }

    public TodoEntity(String title){
        this.title = title;
    }

    /**
     * Getter
     */
    public Long getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public Boolean getDone(){
        return this.done;
    }

    /**
     * Setter
     */
    public void setDone(Boolean done){
        this.done = done;
    }

    public void setTitle(String title){
        this.title = title;
    }
}

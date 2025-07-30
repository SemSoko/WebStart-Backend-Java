package com.semsoko.webstartbackend.todo.model;

public class Todo {
    /**
     * static idCounter simuliert aktuell eine fortlaufende ID – in echten
     * Anwendungen übernimmt das später die Datenbank.
     */
    private static int idCounter = 1;
    private Integer id;
    private String title;
    private Boolean done;

    public Todo(String title){
        this.id = idCounter++;
        this.title = title;
        this.done = false;
    }

    public Integer getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public Boolean getDone(){
        return this.done;
    }

    public void setDone(Boolean status){
        this.done = status;
    }
}
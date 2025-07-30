package com.semsoko.webstartbackend.todo.dto;

/**
 * NewTodoRequest ist ein DTO für die Eingabe per JSON.
 * Es kapselt nur die Felder, die der Client beim Erstellen eines Todos mitschickt.
 * So trennen wir externe Eingabe von interner Logik (z.B. automatische ID-Vergabe).
 */

public class NewTodoRequest {
    private String title;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
package com.semsoko.webstartbackend.todo.dto;

/**
 * TodoResponse ist ein DTO (Data Transfer Object), das verwendet wird,
 * um Todo-Daten vom Server an den Client zurueckzugeben.
 *
 * Es kapselt nur die Felder, die im API-Response sichtbar sein sollen.
 * So bleibt die interne Implementierung (z.B. JPA-Entity) vom externen
 * Datenformat getrennt.
 */
public class TodoResponse {
    /**
     * Die eindeutige ID des Todos
     */
    private Long id;

    /**
     * Der Titel bzw. Text des Todos
     */
    private String title;

    /**
     * Status, ob das Todo abgeschlossen wurde
     */
    private Boolean done;

    /**
     * Konstruktor zum Erstellen eines vollstaendigen TodoResponse-Objekts.
     *
     * @param id        ID des Todos
     * @param title     Titel des Todos
     * @param done      Status des Todos
     */
    public TodoResponse(Long id, String title, Boolean done){
        this.id = id;
        this.title = title;
        this.done = done;
    }

    /**
     * @return ID des Todos
     */
    public Long getId(){
        return this.id;
    }

    /**
     * @return Titel des Todos
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * @return Status des Todos (true = erledigt)
     */
    public Boolean getDone(){
        return this.done;
    }
}

```
WebStart-Backend-Java/
├── src/
│    └─── main/                                                           # Haupt-Quellcode (Java, Ressourcen, etc.)
│         └─── java/                                                      # Einstiegspunkt für Java-Pakete (Source Root)
│              └─── com/semsoko/webstartbackend/                          # Root-Package für das Backend
│                        └─── todo/                                       # Modul für Todo-Funktionalität
│                             ├─── controller/                            # REST-Controller mit den HTTP-Endpunkten (z. B. POST /api/todos)
│                             │    └─── TodoController.java               # Steuerung eingehender HTTP-Anfragen
│                             ├─── dto/                                   # Datenübertragungsobjekte (Request/Response)
│                             │    └─── NewTodoRequest.java               # Eingabeobjekt für neue Todos (z. B. via JSON)
│                             ├─── model/                                 # Enthält interne Datenmodelle, die zentrale Strukturen im Code darstellen
│                             │    └─── Todo.java                         # Datenobjekt mit id, title, done
│                             └─── service/                               # Geschäftslogik für Todos (z. B. erstellen, speichern)
│                                  ├─── InMemoryTodoService.java          # Zentrale Logik zum Anlegen und Verwalten von Todos
│                                  └─── TodoService.java                  # Interface zur Definition der Todo-Service-Verträge
│
└─── README.md                                                            # Projektbeschreibung & Strukturübersicht
```

---

### Anwendungskonfiguration

Die Anwendung verwendet Spring Profile-Management zur Auswahl der Umgebung.\
Aktives Profil (in `application.properties`):

```properties\
spring.profiles.active=dev\
```

-> Steuert, welche Service-Implementierung aktiv ist (z. B. InMemoryTodoService bei "dev")
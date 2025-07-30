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
│                                  └─── TodoService.java                  # Zentrale Logik zum Anlegen und Verwalten von Todos
│
└─── README.md                                                            # Projektbeschreibung & Strukturübersicht
```
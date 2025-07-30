```
WebStart-Backend-Java/
├── src/
│    └─── main/                                                           # Haupt-Quellcode (Java, Ressourcen, etc.)
│         └─── java/                                                      # Einstiegspunkt für Java-Pakete (Source Root)
│              └─── com/semsoko/webstartbackend/                          # Root-Package für das Backend
│                        └─── todo/                                       # Modul für Todo-Funktionalität
│                             ├─── model/                                 # Enthält interne Datenmodelle, die zentrale Strukturen im Code darstellen
│                             │    └─── Todo.java                         # Datenobjekt mit id, title, done
│                             └─── dto/                                   # Datenübertragungsobjekte (Request/Response)
│                                  └─── NewTodoRequest.java               # Eingabeobjekt für neue Todos (z. B. via JSON)
│
└─── README.md                                                            # Projektbeschreibung & Strukturübersicht
```
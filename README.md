```
WebStart-Backend-Java/
├── src/
│    └─── main/                                                       # Haupt-Quellcode (Java, Ressourcen, etc.)
│         ├─── java/                                                  # Einstiegspunkt für Java-Pakete (Source Root)
│         │    ├─── com.semsoko.webstartbackend/                      # Root-Package für das Backend
│         │    │    ├─── todo/                                        # Modul für Todo-Funktionalität
│         │    │    │    ├─── controller/                             # REST-Controller mit den HTTP-Endpunkten (z. B. POST /api/todos)
│         │    │    │    │    └─── TodoController.java                # Steuerung eingehender HTTP-Anfragen
│         │    │    │    ├─── dto/                                    # Datenübertragungsobjekte (Request/Response)
│         │    │    │    │    └─── NewTodoRequest.java                # Eingabeobjekt für neue Todos (z. B. via JSON)
│         │    │    │    ├─── model/                                  # Enthält interne Datenmodelle, die zentrale Strukturen im Code darstellen
│         │    │    │    │    ├─── Todo.java                          # Datenobjekt mit id, title, done
│         │    │    │    │    └─── TodoEntity.java                    # JPA-Entity zur Abbildung von Todos in der Datenbank
│         │    │    │    ├─── repository/                             # Schnittstelle und Implementierung zur Speicherung von Todos
│         │    │    │    │    ├─── InMemoryTodoRepository.java        # In-Memory-Implementierung der Speicherung für das dev-Profil
│         │    │    │    │    └─── TodoRepository.java                # Repository-Interface für die Datenzugriffsschicht
│         │    │    │    └─── service/                                # Geschäftslogik für Todos (z. B. erstellen, speichern)
│         │    │    │               ├─── InMemoryTodoService.java     # Zentrale Logik zum Anlegen und Verwalten von Todos
│         │    │    │               └─── TodoService.java             # Interface zur Definition der Todo-Service-Verträge
│         │    │    │
│         │    │    └─── Application.java                             # Einstiegspunkt der Spring Boot Anwendung (main-Methode + Spring-Kontext)
│         │    │
│         │    └─── resources/                                        # Konfiguration der Spring-Anwendung
│         │         ├─── application.properties                       # Globale Konfiguration – aktiviert das gewünschte Profil (z. B. dev)
│         │         └─── application-dev.properties                   # Profilabhängige Konfiguration – PostgreSQL-Einstellungen für die lokale Entwicklung
│         │
│         └─── test/                                                  # Testverzeichnis mit isolierten Tests, Profilen und Konfigurationen
│              ├─── java/                                             # Test-Quellcode
│              │    └─── com.semsoko.webstartbackend/                 # Package-Struktur analog zur Hauptanwendung für Testklassen
│              │         └─── ApplicationTest.java                    # Basis-Test zum Starten des Spring-Kontexts
│              │
│              └─── resources/                                        # Ressourcen für Tests (z. B. Testdatenbanken, Profilkonfiguration)
│                   └─── application-test.properties                  # Konfiguration für das "test"-Profil mit H2-In-Memory-Datenbank
│
│
├─── build.gradle                                                     # Gradle-Buildskript mit Abhängigkeiten und Konfigurationen
└─── README.md                                                        # Projektbeschreibung & Strukturübersicht
```

---

### Anwendungskonfiguration

Die Anwendung verwendet Spring Profile-Management zur Auswahl der Umgebung.\
Aktives Profil (in `application.properties`):

```properties\
spring.profiles.active=dev\
```

-> Steuert, welche Service-Implementierung aktiv ist (z. B. InMemoryTodoService bei "dev")
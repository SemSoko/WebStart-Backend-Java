```
WebStart-Backend-Java/
├── src/
│    └─── main/                                                       # Haupt-Quellcode (Java, Ressourcen, etc.)
│         ├─── java/                                                  # Einstiegspunkt für Java-Pakete (Source Root)
│         │    ├─── com.semsoko.webstartbackend/                      # Root-Package für das Backend
│         │    │    ├─── auth/                                        # Modul für Authentifizierung & Autorisierung (JWT + Redis)
│         │    │    │    ├─── controller/                             # REST-Endpunkte für Login, Token-Refresh, Logout
│         │    │    │    │    └─── AuthController.java                # Controller für Authentifizierungsvorgänge
│         │    │    │    ├─── dto/                                    # Datenobjekte für Auth-Requests/-Responses
│         │    │    │    │    ├─── LoginRequest.java                  # Eingabeobjekt für Login (z.B. Benutzername, Passwort)
│         │    │    │    │    ├─── LoginResponse.java                 # Antwortobjekt mit Access-/Refresh-Token
│         │    │    │    │    ├─── LogoutRequest.java                 # Anfrageobjekt für Logout – optional mit allDevices-Flag
│         │    │    │    │    ├─── RefreshTokenRequest.java           # Eingabeobjekt für das Anfordern eines neuen Access-Tokens
│         │    │    │    │    └─── RefreshTokenResponse.java          # Antwortobjekt mit neuem Access-/Refresh-Token
│         │    │    │    ├─── model/                                  # Interne Authentifizierungs- und Autorisierungsmodelle
│         │    │    │    │    ├─── RefreshTokenMetadata.java          # Metadaten zum Refresh Token (z. B. issuedAt, IP, User-Agent)
│         │    │    │    │    └─── TokenClaims.java                   # Datenstruktur der JWT-Claims (z.B. User-ID, Rollen)
│         │    │    │    ├─── repository/                             # Zugriff auf externe Speichersysteme für Auth-Daten
│         │    │    │    │    └─── RedisTokenRepository.java          # Verwaltung von Tokens (z.B. Blacklist, Refresh Tokens) in Redis
│         │    │    │    ├─── security/                               # Sicherheitskonfiguration & Filter
│         │    │    │    │    ├─── JwtAuthenticationFilter.java       # Filter zur Prüfung von JWTs bei eingehenden Requests
│         │    │    │    │    └─── SecurityConfig.java                # Spring Security-Konfiguration (Routen, Auth-Mechanismen)
│         │    │    │    └─── service/                                # Geschäftslogik für Authentifizierung & Tokenmanagement
│         │    │    │         ├─── AuthService.java                   # Interface für Authentifizierungs-Operationen
│         │    │    │         ├─── AuthServiceImpl.java               # Implementierung der Authentifizierungslogik
│         │    │    │         ├─── RedisTokenStoreService.java        # Implementierung von TokenStoreService mit Redis-Logik
│         │    │    │         ├─── TokenService.java                  # Interface für Token-Erzeugung und -Verwaltung
│         │    │    │         ├─── TokenServiceImpl.java              # Implementierung der Token-Logik (JWT-Erstellung, Validierung)
│         │    │    │         └─── TokenStoreService.java             # Interface für Zugriff auf Redis (Refresh speichern, Blacklist prüfen, löschen)
│         │    │    ├─── booking/                                     # Modul für Buchungsfunktionalität
│         │    │    │    ├─── controller/                             # REST-Controller mit den HTTP-Endpunkten (z. B. POST /api/bookings)
│         │    │    │    │    └─── BookingController.java             # Steuerung eingehender HTTP-Anfragen für Buchungen
│         │    │    │    ├─── dto/                                    # Datenübertragungsobjekte für Request/Response im Booking-Modul
│         │    │    │    │    ├─── BookingResponse.java               # Antwort-DTO für die API – definiert, welche Buchungsdaten als JSON zurückgegeben werden
│         │    │    │    │    └─── NewBookingRequest.java             # Eingabeobjekt für neue Buchungen (z. B. via JSON)
│         │    │    │    ├─── mapper/                                 # Transformation zwischen Entities/Modellen und DTOs für Buchungen
│         │    │    │    │    ├─── BookingMapper.java                 # Interface für die Umwandlung zwischen Entity/Model und BookingResponse
│         │    │    │    │    └─── BookingMapperImpl.java             # Implementierung des Mappers – enthält konkrete Logik für Entity/Model → DTO
│         │    │    │    ├─── model/                                  # Interne Datenmodelle des Booking-Moduls
│         │    │    │    │    ├─── Booking.java                       # Geschäftsmodell (optional, für interne Logik)
│         │    │    │    │    └─── BookingEntity.java                 # JPA-Entity zur Abbildung von Buchungen in der Datenbank
│         │    │    │    ├─── repository/                             # Schnittstelle zur Speicherung und Abfrage von Buchungen
│         │    │    │    │    └─── BookingRepository.java             # Repository-Interface für die Datenzugriffsschicht (JPA)
│         │    │    │    └─── service/                                # Geschäftslogik für Buchungen (z. B. erstellen, prüfen, stornieren)
│         │    │    │         ├─── BookingService.java                # Interface zur Definition der Buchungs-Serviceverträge
│         │    │    │         └─── BookingServiceImpl.java            # Implementierung der Buchungslogik
│         │    │    ├─── shared/                                      # Gemeinsame, modulübergreifende Komponenten
│         │    │    │    ├─── api/                                    # Standardisierte API-Antwort- und Fehlerbehandlung
│         │    │    │    │    ├─── ApiError.java                      # Datenobjekt für strukturierte Fehlermeldungen (Code + Nachricht)
│         │    │    │    │    ├─── ApiErrorFactory.java               # Interface zur Erzeugung von ApiError-Objekten (DI-freundlich, testbar)
│         │    │    │    │    ├─── ApiResponse.java                   # Generische, einheitliche API-Response-Struktur für alle Endpunkte
│         │    │    │    │    ├─── ApiResponseFactory.java            # Interface zur Erzeugung von ApiResponse-Objekten (DI-freundlich)
│         │    │    │    │    ├─── DefaultApiErrorFactory.java        # Standardimplementierung von ApiErrorFactory
│         │    │    │    │    ├─── DefaultApiResponseFactory.java     # Standardimplementierung von ApiResponseFactory
│         │    │    │    │    └─── GlobalExceptionHandler.java        # Zentrales Exception-Handling, liefert standardisierte Fehler-Responses
│         │    │    │    ├─── config/                                 # Globale Konfigurationsklassen (z. B. Redis, Caching, Mapper)
│         │    │    │    │    └─── RedisConfig.java                   # RedisTemplate-Bean mit String-basierter Serialisierung für Schlüssel/Werte
│         │    │    │    └─── util/                                   # Hilfsklassen & Werkzeuge für Authentifizierung
│         │    │    │         ├─── JwtUtil.java                       # Interface für JWT-Operationen (Erzeugen, Validieren, Parsen von Tokens)
│         │    │    │         ├─── JwtUtilImpl.java                   # Standardimplementierung von JwtUtil mit konkreter JWT-Logik (z. B. via jjwt)
│         │    │    │         └─── PasswordHasher.java                # Utility-Klasse zum sicheren Hashen und Verifizieren von Passwörtern
│         │    │    ├─── todo/                                        # Modul für Todo-Funktionalität
│         │    │    │    ├─── controller/                             # REST-Controller mit den HTTP-Endpunkten (z. B. POST /api/todos)
│         │    │    │    │    └─── TodoController.java                # Steuerung eingehender HTTP-Anfragen
│         │    │    │    ├─── dto/                                    # Datenübertragungsobjekte (Request/Response)
│         │    │    │    │    ├─── NewTodoRequest.java                # Eingabeobjekt für neue Todos (z. B. via JSON)
│         │    │    │    │    └─── TodoResponse.java                  # Antwort-DTO für die API – definiert, welche Todo-Daten als JSON zurückgegeben werden
│         │    │    │    ├─── mapper/                                 # Transformation von internen Modellen und Entities in DTOs (und umgekehrt)
│         │    │    │    │    ├─── TodoMapper.java                    # Interface für die Umwandlung zwischen Entity/Model und TodoResponse
│         │    │    │    │    └─── TodoMapperImpl.java                # Implementierung des Mappers – enthält konkrete Logik für Entity/Model → DTO
│         │    │    │    ├─── model/                                  # Enthält interne Datenmodelle, die zentrale Strukturen im Code darstellen
│         │    │    │    │    ├─── Todo.java                          # Datenobjekt mit id, title, done
│         │    │    │    │    └─── TodoEntity.java                    # JPA-Entity zur Abbildung von Todos in der Datenbank
│         │    │    │    ├─── repository/                             # Schnittstelle und Implementierung zur Speicherung von Todos
│         │    │    │    │    ├─── InMemoryTodoRepository.java        # In-Memory-Implementierung der Speicherung für das dev-Profil
│         │    │    │    │    └─── TodoRepository.java                # Repository-Interface für die Datenzugriffsschicht
│         │    │    │    └─── service/                                # Geschäftslogik für Todos (z. B. erstellen, speichern)
│         │    │    │         ├─── InMemoryTodoService.java           # Zentrale Logik zum Anlegen und Verwalten von Todos
│         │    │    │         └─── TodoService.java                   # Interface zur Definition der Todo-Service-Verträge
│         │    │    ├─── user/                                        # Modul zur Benutzerverwaltung (Datenmodell, Rollen, DB-Zugriff, Service-Logik)
│         │    │    │    ├─── dto/                                    # Datenübertragungsobjekte für User-bezogene API-Antworten (z. B. /me-Endpunkt)
│         │    │    │    │    └─── UserProfileResponse.java           # Antwort-DTO mit Benutzerinformationen (z. B. username, Rollen, ID)
│         │    │    │    ├─── model/                                  # Domänenmodelle der Benutzerverwaltung (UserEntity & zugehörige Strukturen)
│         │    │    │    │    ├─── Role.java                          # Enum für Benutzerrollen (z. B. USER, ADMIN) – Grundlage für Autorisierung
│         │    │    │    │    └─── UserEntity.java                    # JPA-Entity für Benutzer mit Feldern wie ID, Username, Passwort, Rollen
│         │    │    │    ├─── repository/                             # Datenzugriffsschicht zur Verwaltung von Benutzerdaten via JPA
│         │    │    │    │    └─── UserRepository.java                # Interface für Datenbankzugriffe auf Benutzer (findByUsername etc.)
│         │    │    │    └─── service/                                # Geschäftslogik für Benutzeroperationen (z. B. Laden, Validieren)
│         │    │    │         ├─── UserService.java                   # Interface zur Definition von Benutzer-Operationen (z. B. findByUsername)
│         │    │    │         └─── UserServiceImpl.java               # Konkrete Implementierung des UserService mit Zugriff auf Repository
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

### Datenbank

Dieses Projekt verwendet PostgreSQL als relationale Datenbank. Die Datenbank
wird lokal über Docker bereitgestellt, was eine einfache und reproduzierbare
Entwicklungsumgebung ermöglicht.

Für Testzwecke wird eine H2 In-Memory-Datenbank verwendet, um schnelle und
isolierte Tests ohne externe Abhängigkeiten zu ermöglichen.

### Datenbankkonfiguration

## Entwicklungsumgebung (Profil: dev)

Die Verbindung zur PostgreSQL-Datenbank erfolgt über das Spring-Profil `dev`.\
- Konfiguration: `src/main/resources/application-dev.properties`
- Aktivierung des Profils:\
  - In `application.properties`, per:\
    `spring.profiles.active=dev`\
  - Ueber `@Profile("dev")` in:
    - `InMemoryTodoRepository.java`
	- `InMemoryTodoService.java`

# Hinweise

Zugangsdaten sind aktuell in application-dev.properties hinterlegt.\
Eine .env-Datei zur sicheren Verwaltung sensibler Daten (z. B. DB_PASSWORD) ist in Planung.\

In der Datei: `/var/lib/postgresql/data/pg_hba.conf` der PostgreSQL-Instanz wurde die\
Authentifizierungsmethode auf `md5` gesetzt.

## Testumgebung (Profil: test)

Für automatisierte Tests wird eine In-Memory-H2-Datenbank genutzt.\
- Konfiguration: `src/test/resources/application-test.properties`
- Aktivierung des Profils:
  - Über Annotation `@ActiveProfiles("test")` in:
  - `ApplicationTest.java`

### Anwendungskonfiguration

Die Anwendung verwendet Spring Profile-Management zur Auswahl der Umgebung.\
Das aktive Profil wird in `application.properties` gesetzt:

```properties\
spring.profiles.active=dev\
```

-> Steuert, welche Konfiguration und Service-Implementierung aktiv sind

### Infrastruktur & CI/CD (in Planung)

Zur besseren Trennung von Code und Infrastruktur ist ein separates Repository\
geplant, in dem alle relevanten Themen rund um:

- CI/CD (z. B. GitHub Actions, Tests, Deployments)
- Docker & Containerisierung
- Konfigurationsmanagement
- Sicherheitsaspekte (Secrets, .env, Vault, etc.)

dokumentiert und gepflegt werden.
# Configuration IDE et Développement

## VS Code Setup (Recommandé pour le backend)

### Extensions Recommandées

```json
{
  "extensions.recommendations": [
    "vscjava.extension-pack-for-java",
    "vscjava.vscode-spring-boot-dashboard",
    "vscjava.vscode-maven",
    "vscjava.vscode-java-dependency",
    "redhat.vscode-xml",
    "eamodio.gitlens",
    "sonarsource.sonarlint-vscode"
  ]
}
```

### Settings VS Code

```json
{
  "java.configuration.updateBuildConfiguration": "automatic",
  "java.import.gradle.wrapper.checksums": "ignore",
  "java.format.settings.url": "https://raw.githubusercontent.com/google/styleguide/gh-pages/eclipse-java-google-style.xml",
  "editor.formatOnSave": true,
  "[java]": {
    "editor.defaultFormatter": "redhat.java",
    "editor.formatOnSave": true,
    "editor.codeActionsOnSave": {
      "source.organizeImports": true
    }
  }
}
```

## IntelliJ IDEA Setup

### Project Structure
1. Ouvrir le dossier `backend/` comme project
2. Mark `src/main/java` comme Sources Root
3. Mark `src/main/resources` comme Resources Root
4. Mark `src/test/java` comme Test Sources Root

### Run Configuration
1. Create Maven Run Configuration
2. Command: `spring-boot:run`
3. Working directory: `backend/`

### Inspection Configuration
- Activer Inspections Spring
- Activer Spring Security inspections (optionnel)
- Activer JPA inspections

## Eclipse Setup

### Créer Dynamic Web Project
1. File → New → Dynamic Web Project
2. Project name: `energie-facile-backend`
3. Target runtime: Apache Tomcat (optionnel)

### Convertir en Maven Project
1. Right-click → Configure → Convert to Maven Project
2. Group Id: `com.energiefacile`
3. Artifact Id: `energie-backend`

## Lombok Configuration

### Installer Lombok
```bash
# Automatique via Maven
# Sinon, télécharger et exécuter
java -jar lombok.jar
```

### Eclipse/IntelliJ Setup
1. Les IDEs modernes le supportent nativement
2. Activer l'annotation processing
3. Optionnel: Installer plugin Lombok dédié

## Environment Variables

### Windows
```cmd
set JAVA_HOME=C:\Program Files\Java\jdk-17
set MAVEN_HOME=C:\apache-maven-3.8.1
set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%
```

### Mac/Linux
```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home
export MAVEN_HOME=/usr/local/opt/maven
export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH
```

### Vérification
```bash
java -version
mvn -version
docker --version
```

## Format Code Standard

### Google Java Style Guide
- 2 espaces d'indentation
- Max 80 caractères par ligne
- CamelCase pour variables
- UPPER_CASE pour constantes

### Commit Message Format
```
[TYPE] Description courte

Description détaillée si nécessaire

Type peut être:
- feat: Nouvelle fonctionnalité
- fix: Correction bug
- docs: Documentation
- style: Formatage code
- refactor: Refactorisation
- test: Tests
- chore: Maintenance
```

## Git Workflow

### Branches
```
main/master      - Production
develop          - Intégration
feature/*        - Nouvelles fonctionnalités
bugfix/*         - Corrections
```

### Workflow Local
```bash
# Cloner
git clone <repo>

# Feature branch
git checkout -b feature/new-feature

# Commit régulièrement
git add .
git commit -m "[feat] Description"

# Push
git push origin feature/new-feature

# Pull request
# Code review
# Merge
```

## Build Commands

### Clean Build
```bash
mvn clean
mvn clean compile
mvn clean package
```

### Skip Tests
```bash
mvn clean package -DskipTests
mvn spring-boot:run -DskipTests
```

### With Logging
```bash
mvn spring-boot:run -X
mvn clean install -e
```

### Generate JAR
```bash
mvn clean package
java -jar target/energie-backend-0.0.1-SNAPSHOT.jar
```

## Debug Mode

### VS Code Debug
1. Press F5
2. Sélectionner "Java" as environment
3. Breakpoints automatiquement supportés
4. Watch variables et call stack

### Maven Debug
```bash
mvn -Dmaven.surefire.debug test
```

### Spring Boot Debug
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--debug"
```

## Unit Tests (Structure pour futur)

### Test Directory
```
src/test/java/com/energiefacile/
├── controllers/
├── services/
├── repositories/
└── models/
```

### Dependencies
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <scope>test</scope>
</dependency>
```

### Run Tests
```bash
mvn test
mvn test -Dtest=ConsumptionRecordServiceTest
mvn test -DfailIfNoTests=false
```

## Package Management

### Check Dependencies
```bash
mvn dependency:tree
mvn dependency:analyze
```

### Update Dependencies
```bash
mvn versions:display-dependency-updates
mvn versions:use-latest-releases
```

## Performance Profiling

### JProfiler / YourKit
1. Configurer agent JVM
2. Attacher profiler pendant runtime
3. Analyser CPU, memory, threads

### Maven Shade Plugin
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>3.2.4</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

## Docker Development

### Build Docker Image
```bash
# Créer Dockerfile
# Puis:
docker build -t energie-facile-backend:latest .

# Run
docker run -p 8080:8080 energie-facile-backend:latest
```

### Docker Compose for Development
```bash
docker-compose up -d mysql
mvn spring-boot:run
```

## CI/CD Ready

### GitHub Actions Template
```yaml
name: Java CI/CD

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '17'
      - run: mvn clean package
```

## SonarQube Integration

### Local Analysis
```bash
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=energie-facile \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=<token>
```

## Useful Plugins

### Spring Boot Extension Pack (VS Code)
- Spring Boot extension
- Spring Cloud CLI
- Spring Boot Dashboard

### Maven Helper (IntelliJ)
- Visualize dependencies
- Run goals easily
- Plugin execution

### CheckStyle
- Code quality checks
- Style enforcement

## Keyboard Shortcuts

### VS Code
- `Ctrl+Shift+B` - Build
- `F5` - Debug
- `Ctrl+K Ctrl+0` - Fold all
- `Ctrl+/` - Comment/Uncomment

### IntelliJ
- `Ctrl+Shift+F10` - Run class
- `Shift+F9` - Debug
- `Alt+Shift+F` - Format code
- `Ctrl+M` - Go to line

## Remote Development

### SSH Connection
1. Configure SSH in VS Code
2. Remote Java Extension
3. Debug remotely

### WSL Setup (Windows)
```bash
wsl -l -v
wsl --set-default-version 2
```

## Optimization Tips

1. **Caching**: Implémenter Redis pour cache
2. **Async**: Utiliser @Async pour long-running tasks
3. **Pagination**: @PageableDefault(size = 20)
4. **Projection**: DTO pour ne charger que les champs nécessaires
5. **Connection Pool**: Déjà configuré via Spring

## Documentation Generation

### Javadoc
```bash
mvn javadoc:javadoc
open target/site/apidocs/index.html
```

### Springdoc OpenAPI (Swagger)
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.0</version>
</dependency>
```

Ensuite: `http://localhost:8080/swagger-ui.html`

## Useful Resources

- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Maven Central](https://mvnrepository.com/)
- [Baeldung Tutorials](https://www.baeldung.com/)
- [Spring Guides](https://spring.io/guides)

---

**Configuration IDE terminée! 🎉**

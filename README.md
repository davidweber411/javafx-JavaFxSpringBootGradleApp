### Description

A simple running application based on JavaFX, Spring Boot and Gradle.

### Used technologies

| Technology    | Version              |
|---------------|----------------------|
| Spring Boot   | 3.3.1                |
| Java          | 17                   |
| JavaFX        | 17.0.8               |
| JDK           | Open JDK 17.0.2      |
| Gradle        | 8.8 (Gradle Wrapper) |
| Module system | Non modular          |

### Features: Run and package

| Feature                         | Available | How to use                                |
|---------------------------------|-----------|-------------------------------------------|
| Run the application in the IDE. | Yes.      | Use the gradle task <code>bootRun</code>. |
| Package as executable JAR.      | Yes.      | Use the gradle task <code>bootJar</code>. |

### Features: While coding

| Feature                                        | Available | How to use                                                          |
|------------------------------------------------|-----------|---------------------------------------------------------------------|
| Dependency injection in Spring components.     | Yes.      | Use e. g. constructor or field injection, just like in Spring Boot. |
| Get Spring application context during runtime. | Yes.      | Use the bean <code>ApplicationContext</code>.                       |
| Get JavaFX application during runtime.         | Yes.      | Use the bean <code>Application</code>.                              |

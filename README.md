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

| Feature                                     | How to use                                                                    |
|---------------------------------------------|-------------------------------------------------------------------------------|
| Run the application in the IDE.             | Use the gradle task <code>runJfxSpringBootApp</code> or <code>bootRun</code>. |
| Package as executable JAR.                  | Use the gradle task <code>buildExecutableJar</code> or <code>bootJar</code>.  |
| Package as executable App Image (e.g. EXE). | Use the gradle task <code>packageAsAppImage</code>.                           |

### Features: While coding

| Feature                                        | How to use                                                          |
|------------------------------------------------|---------------------------------------------------------------------|
| Dependency injection in Spring components.     | Use e. g. constructor or field injection, just like in Spring Boot. |
| Get Spring application context during runtime. | Use the bean <code>ApplicationContext</code>.                       |
| Get JavaFX application during runtime.         | Use the bean <code>Application</code>.                              |

### Features: Other

| Feature                  | How to use                                                     |
|--------------------------|----------------------------------------------------------------|
| Display a splash screen. | Simply configure the class <code>SplashScreenPreloader</code>. |

### Example: FXML Controllers as Spring components

#### Step 1: Make the controller a Spring component

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @RequiredArgsConstructor
    public class UiController implements Initializable {
    
    }

#### Step 2: Configure the FXMLLoader

    FXMLLoader loader = new FXMLLoader(fxmlFileUrl);
    // The FXMLLoader shall use the spring context as controller factory. 
    loader.setControllerFactory(springApplicationContext::getBean);

#### Step 3: Profit!

Profit.

# Technical specifications

>*Project code = XXX (find a trigram code for your app)*
>*Base package = com.organization.xxx*
>*Main Framework Stack :*
>*+ Spring Boot*
>*+ Spring Framework*
>*+ AngularJS*
>*+ Bootstrap*

## Prerequisites

1 - Install current JDK 1.8
2 - Check your environment variables (PATH, JAVA_HOME, ...)
3 - Install MAVEN 3
4 - Optimize your web working environment with by using spring-dev-tools, install a live reload extension in your browser !

## Maven multi module project structure 
```
+ xxx
+ + xxx-front (web application)
+ + xxx-services (a set of api with domain model and implementations)
+ + xxx-zdao (a set of repositories interfaces with JPA entities and implementations)
```

## Running in dev mode

In root directory type `mvn clean install`

### To run the web application :

In a terminal type :
1 - `cd xxx-front`
2 - `mvn spring-boot:run`
3 - You should see this kind of message : `Listening for transport dt_socket at address: 5005`
>In order tu complete its start, the server waits a debugger to connect on the concerned port.

4 - Go to Edit Configuration in Run menu to configure a remote debugging task and launch it.
5 - You should verify that debug view and server finish starting
6 - Go to localhost:8080

>That's it. You should start your server occasionally because any modifications in java or resources will take effect :
>you will take advantage of live reload and hot swapping, in IntelliJ IDE after saving the file, press CTRL+F9 to live reload.
>Put a breakpoint into SampleController return statement and check that debug is working as well !
>Enjoy ...

### Integration tests :

Test your database connexion and entities interaction across integration tests system :
1 - in xxx-services go into 'src/it/java' this is where automatic tests code are related to its config in 'src/it/resources'
2 - launch a test by simply right click on it and choose run
3 - see in database the table creation ♫

>IT tests work by default with a rollback transaction to leave an empty DB, but if you want to feed a table, simply annotate
test method with @Comm

##Coding rules

### Layer interactions
xxx-front must not use classes of xxx-zdao but interact with it through xxx-services layer that is a typical bridge which permit to front and data access layer to vary independently. For instance RestController call implementations of services API defined in xxx-services. Implementations of services API call repository implementations of xxx-zdao.

### Layer models
xxx-front has a model package already known as representation model (simple POJO)
xxx-service has a model package known as domain model (intelligent bean)
xxx-zdao has a model package known as persistence beans (JPA POJO)

> To convert model beans from one layer to another, we use mapper component - see util package in each layer

### Naming conventions
>*TODO Complete this section* 

## Security Authentication

### LDAP
We use an LDAP in order to get an authorized user. This user must have a specific role to access this specific apps. Then he has specific functional roles in the application related to users stories to develop.
  
### LDAP configuration
See application.properties to configure :
   - Connexion with a technical account dedicated
   - User base branch research
   - User filter ldap request
   - Groups base branch research (aka ROLE_)
   - Groups filter ldap request

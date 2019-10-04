# spring-boot-rest-sample
Spring Boot sample Rest API implementation


## Build Installation

Maven is used to build a Rest API web application.

```bash
./mvnw clean install
```

## Build Location

After building an application, Build would present on the target directory of the project

```
target/welcome-test-0.0.1-SNAPSHOT.jar
```

## Start Application

Java 8 must be installed on the environment to run the application. The application will start on port 8080.

```
jar -jar welcome-test-0.0.1-SNAPSHOT.jar
```

# Rest Services

Rest API has been implemented to perform basic CRUD operation on the Message module.

## Operations 
- Get Messages
- Get Single Message Details
- Save Message
- Update Message
- Delete Message

Please refer attached Postman collection in the project root directory to review/execute the rest endpoints.

```File Name: Messages_Rest_Apis.json```

Note: Followed Rest API standards to return valid JSON for Success/Error cases. Global rest exception handler also configured to manage known/unknown errors.
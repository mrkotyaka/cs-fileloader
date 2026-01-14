# Cloud Storage

## Introduction
A REST service has been developed that provides a REST interface for uploading files and displaying a list of files already uploaded by the user.
All requests to the service are authorized. 
A pre-built web application (FRONT) connects to the developed service without any modifications, and FRONT functionality is used for authorization, uploading, and displaying a list of user files.


## Implemented application requirements
- The service provides a REST interface for integration with FRONT.
- The service implements all methods described in the [yaml file](./CloudServiceSpecification.yaml):
1. Displaying the list of files.
2. Adding a file.
3. Deleting a file.
4. Authorization.
- All settings are read from the settings file (yml).
- Information about service users (logins for authorization) and data are stored in the PostgreSQL-15 database.

## Implementation
- The application was developed using Spring Boot.
- The maven package builder was used.
- Docker and docker-compose are used for launching.
- The code is covered by unit tests using mockito.
- Integration tests using testcontainers have been added.

## Application authorization
The FRONT application uses the `auth-token` header, in which it sends a token (key string) to identify the user on the BACKEND.
To obtain a token, you need to authorize on the BACKEND and send your login and password to the `/login` method. 
If the verification is successful, the BACKEND must return a json object with the `auth-token` field and the token value. 
All subsequent requests from the FRONTEND, except for the `/login` method, are sent with this `header`.
To log out of the application, call the BACKEND `/logout` method, which will delete/deactivate the token. 
Subsequent requests with this token will not be authorized and will return a 401 code.

## Launching the application

The application runs on port 8080. 
1. Package the project and obtain the jar file (package).
2. Build docker images and launch containers via the [docker-compose.yml](./docker-compose.yml) file. 

The terminal will display a message that the containers are running:
```java
[+] Running 3/3
 ✔ Network cs-fileloader_default  Created                                                                                                                0.1s 
 ✔ Container postgresql           Started                                                                                                                1.9s 
 ✔ Container cs-fileloader-app-1  Started 
```

Translated with DeepL.com (free version)

## Endpoints (from the specification):

✔ `POST /login` — returns JSON with the auth-token field.

✔ `POST /logout` — requires auth-token in the header; deactivates the token.

✔ `POST /file?filename=...` — file upload (multipart/form-data), auth-token header.

✔ `GET /file?filename=...` — file download, auth-token header.

✔ `DELETE /file?filename=...` — file deletion, auth-token header.

✔ `PUT /file?filename=...` — rename (in body JSON { “name”: “newName” }), header auth-token.

✔ `GET /list?limit=...` — returns a list of files (JSON with filename and size), header auth-token.


# Sharing Platform Service

CRUD application for sharing platform service.
## API Reference

#### User must be register to use the other endpoints.

```http
  GET /login
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `string` | **Required**.  |


#### User can be able to create a new TedTalk with CSV file.

```http
  POST /v1/resources/upload
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `file` | `CSV file` | **Required**.  |


#### User can be able to create a new TedTalk in the platform.

```http
  POST /v1/resources
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `title` | `string` | **Required**. |
| `Author` | `string` | **Required**.  |
| `Date` | `string` | **Required**.  |
| `views` | `BigDecimal` | **Required**.  |
| `likes` | `BigDecimal` | **Required**.  |
| `url` | `String` | **Required**.  |

#### User can get the Tedtalk based on author,title,.

```http
  GET /v1/resources/author/{author}
  GET /v1/resources/title/{title}
  GET /v1/resources/view/{view}
  GET /v1/resources/like/{like}
  GET /v1/resources/url?url=?
  GET /v1/resources/date/{date}
```


#### User can update the TedTalk based on id.

```http
  PUT /v1/resources/{id}
```

#### User can delete all records.

```http
  DELETE /v1/resources/{id}
```

```http
  DELETE /v1/resources
```

#### Documentation 

```http
  localhost:8080/documentation 
```



## Tech Stack

**Technologies:** Java 17, Spring Boot, PostgreSQL database, Docker, TestContainer, Junit, Mockito, Integration Test, Spring Security.


## How to run

Please run provided docker-compose.yml file before starting the application.
With the terminal, go to project directory and run 'docker-compose up'. Then docker will create postgreSQL database on 5432 port with provided username/psw and database name.

Postman collection can be found in the project directory.

### Approach
Registration of user, is handled by Spring security and JWT token. With provided bearer token, user can be able to call endpoints.
Database is initialized with CSV file when the application starts. When user calls the endpoints for creating tedTalk, it has to be unique and should send required information. User can be able to find tedTalk by author,title,date, url, viewCount or likeCount.

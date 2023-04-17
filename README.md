# Projet Micro-Service

| Module          | Endpoint             | HTTP Method | 
|-----------------|----------------------|-------------|
| book_module      | /books                | GET         | 
|                  | /books                | POST        | 
|                  | /books/{isbn}         | GET         | 
|                  | /books/{isbn}         | PUT         | 
|                  | /books/{isbn}         | DELETE      | 
| lending_module   | /lendings             | POST        | 
|                  | /lendings/{id}        | GET         | 
|                  | /lendings             | GET         | 
|                  | /lendings/{id}        | PUT         | 
|                  | /lendings/{id}        | DELETE      | 
| reader_module    | /readers              | GET         | 
|                  | /readers              | POST        | 
|                  | /readers/{id}         | GET         | 
|                  | /readers/{id}         | PUT         | 
|                  | /readers/{id}         | DELETE      | 


## How to launch (locally) :

### Execute the following microservice (Java 17):

Name & port : 
- `book_module` : 8002
- `reader_module` : 8001
- `lending_module`: 8003
- `gateaway_module` : []

### **The PostGresSQL databases** :

- For the One common database version :
  - Name of the database : `micro_service`

To test the Database : 
- use the *Postman Collection* in the folder 


### Body request to add books
```json
POST /books HTTP/1.1
Host: localhost:8002
Content-Type: application/json

{
    "isbn": "9780321356680",
    "author": "Robert C. Martin",
    "title": "Clean Code",
    "editor": "Prentice Hall",
    "edition": 1
}

```

```json
POST /books HTTP/1.1
Host: localhost:8002
Content-Type: application/json

{
    "isbn": "9780061120084",
    "author": "Henry David Thoreau",
    "title": "Walden",
    "editor": "LaVieDansLesBois",
    "edition": 1
}
```

```json
POST /books HTTP/1.1
Host: localhost:8002
Content-Type: application/json

{
    "isbn": "9780679735779",
    "author": "Bret Easton Ellis",
    "title": "American Psycho",
    "editor": "Vintage",
    "edition": 1
}
```

## To add readers :
```json
POST /readers HTTP/1.1
Host: localhost:8001
Content-Type: application/json

{
    "gender": "Male",
    "firstName": "Michael",
    "lastName": "Scott",
    "birthDate": "1965-03-15",
    "address": "1725 Slough Avenue"
}
```

```json
POST /readers HTTP/1.1
Host: localhost:8001
Content-Type: application/json

{
    "gender": "Male",
    "firstName": "Linus",
    "lastName": "Torvalds",
    "birthDate": "1969-12-28",
    "address": "P.O. Box 546"
}
```

```json
POST /readers HTTP/1.1
Host: localhost:8001
Content-Type: application/json

{
    "gender": "Male",
    "firstName": "Patrick",
    "lastName": "Bateman",
    "birthDate": "1965-04-01",
    "address": "55 West 81st Street"
}
```

## For lending :

```json
POST /lendings HTTP/1.1
Host: localhost:8003
Content-Type: application/json

{
    "book": {
        "isbn": "9780061120084"
    },
    "reader": {
        "id": 1
    },
    "lendingDate": "2023-04-17",
    "returnDate": "2023-05-17"
}
```


```json
POST /lendings HTTP/1.1
Host: localhost:8003
Content-Type: application/json

{
    "book": {
        "isbn": "9780679735779"
    },
    "reader": {
        "id": 2
    },
    "lendingDate": "2023-04-18",
    "returnDate": "2023-05-18"
}
``` 

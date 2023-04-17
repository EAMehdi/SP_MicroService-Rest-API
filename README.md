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



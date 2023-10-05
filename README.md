# DemoApp
bankapp IN PROGRESS, NOT DONE with springboot, training basic react, spring security, creating apis. fullstack app with frontend on p 3000 and backend on 8080, db 3306

list of apis with examples: create new user, all fields are mandatory

POST http://localhost:8080/auth/register
{ 
  "username": "user2",
  "firstname": "John1",
  "lastname": "Doe1",
  "email": "email@test2.cz",
  "password": "password",
  "matchingPassword": "password"
}

login - this returns jwt and you need to bearer auth for every other request
POST http://localhost:8080/auth/login 
{ 
  "username": "user2",
  "password": "password"
}

list of registered users 
GET http://localhost:8080/api/users

details of currently logged in user 
GET http://localhost:8080/api/my-details

specific user details 
GET http://localhost:8080/api/users/{id}

create new role 
POST http://localhost:8080/api/role 
{ 
  "name": "ADMIN" 
}

show all roles 
GET http://localhost:8080/api/roles

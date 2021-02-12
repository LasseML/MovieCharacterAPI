# Task 5: Movie character API

The application is constructed by using Spring and Hibernate.
It uses PostgreSQL database which stores information about characters, the movies
they appear in, and the franchises they belong to. There is some data included, but notice it
drops all changes on server reboot.

## Developers

Tor Leeberg, Lasse Minet and Satu Heikkonen

## Api endpoints

Endpoints contain full CRUD for every model:
1. Get all 
2. Get one by id
3. Add new
4. Update
5. Delete

In addition to movie and franchise controllers contain:
1. Get all characters in a movie
2. Get all characters in a franchise
3. Get all movies in a franchise

Postman collection can be found in the postman folder.


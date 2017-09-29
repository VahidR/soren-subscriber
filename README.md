# Soren Subscriber Service
A RESTFul subscription service with infinite nodes to insert `categories`, 
`books`, `subscribers`, etc. written in Java 8 and Dropwizard micro-service. 

Prerequisite
---

1. Java 8 or newer
2. Postgresql (It has been tested in version 9.4, but it should be fine in the older versions too)
3. A UNIX based OS, like Linux, OSX, etc.

How to start the Soren Subscriber Service
---

1. Create a database in postgresql with the name of `soren_subscriber`
2. Pull this repository into your local machine
3. In the terminal, change your working directory to the `soren-subscriber` folder that you have just pulled
4. Issue the following commands: `chmod 755 init-db.sh` and `chmod 755 run-services.sh`
5. Run `./init-db.sh <your DB username>` to create the required tables in DB
6. Run `mvn clean test` to be sure that all the tests pass
7. Run `mvn clean install` to build the application
8. Start application with `./run-service.sh server config.yml`
9. To check that the application is running enter url `http://localhost:8080/api/v1/ping`

Health Check
---

To see the applications health enter url `http://localhost:8081/healthcheck`


How to populate the tables
---

**Populating Category Table**

*ATTENTION*: Please note that to populate the `Category` table, the `superCategoryCode` of the root node should be *null*!
This is how the application finds the *root node* in the *tree of nodes*, and builds the recursive tree 
out of it.

A typical way to populate the category tree should be something like the following: 

```bash
curl -H "Content-Type: application/json" -X POST -d '{ "code": "book", "title": "Books", "superCategoryCode": null }' -i http://localhost:8080/api/v1/categories

curl -H "Content-Type: application/json" -X POST -d '{ "code": "sci", "title": "Science", "superCategoryCode": "book" }' -i http://localhost:8080/api/v1/categories

curl -H "Content-Type: application/json" -X POST -d '{ "code": "art", "title": "Art", "superCategoryCode": "book" }' -i http://localhost:8080/api/v1/categories

curl -H "Content-Type: application/json" -X POST -d '{ "code": "lit", "title": "Literature", "superCategoryCode": "book" }' -i http://localhost:8080/api/v1/categories

curl -H "Content-Type: application/json" -X POST -d '{ "code": "eng", "title": "Engineering", "superCategoryCode": "sci" }' -i http://localhost:8080/api/v1/categories

... 
```

**Populating Book Table**
```bash
curl -H "Content-Type: application/json" -X POST -d '{"title": "Crime and Punishment", "categoryCodes": ["lit"] }' -i http://localhost:8080/api/v1/books
...
```

**Populating Subscriber Table**
```bash
curl -H "Content-Type: application/json" -X POST -d '{"email": "someone@somedomain.com", "categoryCodes": ["lit", "sci"] }' -i http://localhost:8080/api/v1/subscribers
...
```

**GET the List of Newsletters**
```bash
curl -H "Accept: application/json" -i http://localhost:8080/api/v1/newsletters
```



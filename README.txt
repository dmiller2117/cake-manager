Cake Manager Micro Service (fictitious)
=======================================

    Updates by David Miller

    Changes:

        * Have refactored to use SpringBoot
        * Added endpoints for:
            ** GET http://localhost:8080/cakes
            ** POST http://localhost:8080/cakes
            ** PUT http://localhost:8080/cakes
            ** DELETE http://localhost:8080/cakes/{cakeId}
        * Added code coverage with Jacoco, see report on build in target/site/jacoco/index.html
        * Added basic auth, user=user password=password
        * Unit tested

    How to run:
         `mvn spring-boot:run`

    cUrl examples:

    curl --location 'http://localhost:8080/cakes' \
    --header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='

    curl --location 'http://localhost:8080/cakes' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
    --data '{
            "title": "Caterpillar Cake",
            "description": "A cute caterpillar cake",
            "image": "caterpillar_cake.jpg"
        }'

    curl --location --request PUT 'http://localhost:8080/cakes' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
    --data '{
        "cakeId": 6,
        "title": "Caterpillar Cake",
        "description": "An updated cute caterpillar cake",
        "image": "caterpillar_cake.jpg"
    }'

    curl --location --request DELETE 'http://localhost:8080/cakes/6' \
    --header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='


    Further enhancements:

    * Use mutation coverage for test, use a framework like Pitest(https://pitest.org/)
    * Use an API testing framework such as Karate(https://www.karatelabs.io/), build upon Cucumber for BDD style testing
    * Use Springs' @RestControllerAdvice for exception handling.
    * Add CI/CD pipeline
    * Add Containerisation


=======================================

A summer intern started on this project but never managed to get it finished.
The developer assured us that some of the above is complete, but at the moment accessing the /cakes endpoint
returns a 404, so getting this working should be the first priority.

Requirements:
* By accessing /cakes, it should be possible to list the cakes currently in the system. JSON would be an acceptable response format.

* It must be possible to add a new cake.

* It must be possible to update an existing cake.

* It must be possible to delete an existing cake.

Comments:
* We feel like the software stack used by the original developer is quite outdated, it would be good to migrate the entire application to something more modern. If you wish to update the repo in this manner, feel free! An explanation of the benefits of doing so (and any downsides) can be discussed at interview.

* Any other changes to improve the repo are appreciated (implementation of design patterns, seperation of concerns, ensuring the API follows REST principles etc)

Bonus points:
* Add some suitable tests (unit/integration...)
* Add some Authentication / Authorisation to the API
* Continuous Integration via any cloud CI system
* Containerisation

Scope
* Only the API and related code is in scope. No GUI of any kind is required


Original Project Info
=====================

To run a server locally execute the following command:

`mvn jetty:run`

and access the following URL:

`http://localhost:8282/`

Feel free to change how the project is run, but clear instructions must be given in README
You can use any IDE you like, so long as the project can build and run with Maven or Gradle.

The project loads some pre-defined data in to an in-memory database, which is acceptable for this exercise.  There is
no need to create persistent storage.


Submission
==========

Please provide your version of this project as a git repository (e.g. Github, BitBucket, etc).

A fork of this repo, or a Pull Request would be suitable.

Good luck!
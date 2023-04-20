# Superhero API

A brief description of what this project does and who it's for

## Table of Contents

- [Superhero API](#superhero-api)
    - [About The Project](#about-the-project)
    - [Getting Started](#getting-started)
        - [Prerequisites](#prerequisites)
        - [Installation](#installation)
    - [Usage](#usage)
    - [Contact](#contact)

## About The Project

Simple API that allows us to manage a Superhero database where we can perform all basic
**CRUD** operations.

## Getting Started

### Prerequisites

Install the following software:

- IDE (Intellij IDEA, Eclipse, etc)
- Java 11
- Postman
- Docker Desktop

### Installation

- Import the project into your favorite IDE as **maven project**.

- Run `mvn clean install`
- If you want to run the app as a docker container, run `docker build -t superhero-api .`
  at the root level of the project `..\superhero-api` to build the docker image, then run
  `docker run -p 8080:8080 superhero-api`
- To check if everything went ok you can run `docker ps` to see a list of containers and
  their status like this:

| CONTAINER ID   | IMAGE         | COMMAND                | CREATED          | STATUS         | PORTS                   | NAMES                   |
|----------------|---------------|------------------------|------------------|------------------|-------------------------|-------------------------|
| d7ebe218356d   | superhero-api | "java -jar /superher…" | 31 seconds ago   | Up 30 seconds   | 0.0.0.0:8080->8080/tcp | xenodochial_chatterjee   |

- Finally, to test the endpoints import into **Postman** the collection located at
  the project root `Superhero API.postman_collection.json`

## Usage

To be able to access the endpoints we need a **Jason Web Token** as authentication.
To generate it use the `Get Bearer Token` request, once we get the **200 Ok** reponse,
the token can be found under the **Headers** tab on the response.

We can use the **default user**
already loaded into the h2 BBDD or we can create a new user in the file
`..\superhero-api\src\main\resources\data.sql` adding another **INSERT** query,
**but this method also requires logging the desired password in an encrypted
format to the console**, so using the default user is recommended.

Once we get the token we can authenticate on the rest of the requests with
**Bearer Token** auth type

## Contact

- Email: monty.merlos@gmail.com
- Linkedin Profile: www.linkedin.com/in/antonio-merlos-805275218
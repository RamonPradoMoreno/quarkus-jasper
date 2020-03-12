# quarkus-jasper

Offers a basic API to create Jasper reports through a Quarkus microservice. The objective is to be able to ask for some information and receive it three different ways:

1. As a json rest response.
2. As a jasper report in `.pdf` format attached to a rest response.
3. As a jasper report in `.xls` format attached to a rest response.

## Development environment

You need to have:

1. All the Quarkus environment installed &rarr; There is an environment set up section in [the the following tutorial](https://quarkus.io/quarkus-workshops/super-heroes/)

2. Any IDE compatible with Quarkus.

3.  The database running &rarr; Follow the next section to run the database in a container. 

4. To start the development server do:

   ```bash
   mvn quarkus:dev
   ```

###  Database ![elephant](https://github.githubassets.com/images/icons/emoji/unicode/1f418.png)

Download the [docker postgres image ](https://hub.docker.com/_/postgres):

- `docker pull postgres` 

Run the docker image with specific username, password and database name:

- `docker run --name employees-postgres -p 5433:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=employees -d postgres`

Right now the database is up with user `admin`, password `admin` and a database called `employees`. As we configured in our `application.properties`:

```
# configure your datasource
quarkus.datasource.url=jdbc:postgresql://localhost:5433/employees
quarkus.datasource.driver=org.postgresql.Driver
quarkus.datasource.username=admin
quarkus.datasource.password=admin
# drop and create the database at startup (use `update` to only update the schema)
quarkus.hibernate-orm.database.generation=drop-and-create
```

But there is no table `person` in database `employees`. That table is needed in order to persist our `Panache` entity `Person`. Even `Quarkus` will notify this, but thanks to the statement `quarkus.hibernate-orm.database.generation=drop-and-create` in `application.properties` all the `Panache` entities will be created as tables in database `employees`.

In case you want to check the database run the [`psql`](https://www.postgresql.org/docs/9.2/app-psql.html) interactive terminal:

- `docker exec -it employees-postgres psql -U admin -d employees`
- `\dt` (show all tables)
- `SELECT * FROM employees;` (show all rows)
- Learn more [here](https://www.postgresql.org/docs/)

## Testing

At the moment, the microservice has three separated endpoints that can be tested with the following `curl`:

```bash
curl http://127.0.0.1:8080/employees
```

There is no expected response yet.

```bash
curl http://127.0.0.1:8080/employees/report/xls
```

There is no expected response yet but the reports are being locally persisted

``` bash
curl http://127.0.0.1:8080/employees/report/pdf
```

There is no expected response yet but the reports are being locally persisted
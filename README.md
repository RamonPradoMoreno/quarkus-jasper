# quarkus-jasper

Shows a basic API that creates a simple JasperReport and allows the user to retrieve the info as a file (Excel or PDF) and a REST response.

## Database

Download the [docker postgres image ![elephant](https://github.githubassets.com/images/icons/emoji/unicode/1f418.png)](https://hub.docker.com/_/postgres):

- `docker pull postgres`

Run the docker image with specific username, password and database name:

- `docker run --name employees-postgres -p 5433:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=employees -d postgres`

Right now the database is up with user `sarah`, password `connor` and a database called `people`. As we configured in our `application.properties`:

```
# configure your datasource
quarkus.datasource.url=jdbc:postgresql://localhost:5433/employees
quarkus.datasource.driver=org.postgresql.Driver
quarkus.datasource.username=admin
quarkus.datasource.password=admin
# drop and create the database at startup (use `update` to only update the schema)
quarkus.hibernate-orm.database.generation=drop-and-create
```

But there is no table `person` in database `people`. That table is needed in order to persist our `Panache` entity `Person`. Even `Quarkus` will notify this, but thanks to the statement `quarkus.hibernate-orm.database.generation=drop-and-create` in `application.properties` all the `Panache` entities will be created as tables in database `people`.

In case you want to check the database run the [`psql`](https://www.postgresql.org/docs/9.2/app-psql.html) interactive terminal:

- `docker exec -it person-postgres psql -U admin -d admin`
- `\dt` (show all tables)
- `SELECT * FROM employees;` (show all rows)
- Learn more from [here](https://www.postgresql.org/docs/) ![stuck_out_tongue_closed_eyes](https://github.githubassets.com/images/icons/emoji/unicode/1f61d.png)



## Testing

The following `curl` allows testing in this early development stage:

```bash
curl http://127.0.0.1:8080/hello
```


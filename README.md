## Java Tech Challenge LendTech

# run postgres using docker
$ docker run --name postgresql -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -p 5432:5432 -v /data:/var/lib/postgresql/data -d postgres
# alternatively have postgres running with the database=postgres, username=postgres password=password

# Build and run project using cli
```bash
# Build a jar file using maven
$ ./mvnw package

Run the project
$ java -jar target/lendtech-william-0.0.1-SNAPSHOT.jar
```


# Build and run using docker
```bash
# build a docker container
$ docker build -t lendtech-backend-william .

# run the docker container
# exposes port 8080
$ docker run -p 8080:8080 lendtech-backend-william
```
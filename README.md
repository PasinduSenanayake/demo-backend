# Power Plant

### Assumptions

* The microservice is hosted under the subdomain of 'api.domain'. Hence, 'api' would not be in the url. 

* The Application is dedicated for this service. Hence, namespace separations are not required. (If it's a future requirement the existing application can be modified easily)

* A battery is not unique by the name.

* The postal codes are only related to Australia. (4 Digits code with trailing zeros)


### Application

* Utilizes and H2 In-memory database.

* The tables and database are automatically created with no data. 

* Binds to port 8080.

* All the api specifications are mentioned in swagger.
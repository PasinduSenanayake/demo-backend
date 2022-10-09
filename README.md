# Power Plant

### Use Case

* You are working on a virtual power plant system for aggregating distributed power sources into a single cloud-based energy provider. Please implement a REST API in spring boot that encompasses the following functionality:

* The API should have an endpoint that accepts - in the HTTP request body - a list of batteries, each containing: name, postcode, and watt capacity. This data should be persisted in a database (In-memory is acceptable).

* The API should have an endpoint that receives a postcode range. The response body will contain a list of names of batteries that fall within the range, sorted alphabetically. Additionally, there should be some statistics included for the returned batteries, such as total and average watt capacity.

### Assumptions

* The microservice is hosted under the subdomain of 'api.domain'. Hence, 'api' would not be in the url. 

* The Application is dedicated for this service. Hence, namespace separations are not required. (If it's a future requirement the existing application can be modified easily)

* A battery is not unique by the name.

* The postal codes are only related to Australia. (4 Digits code with leading zeros)


### Application

* Developed and tested with Java 11.

* Utilizes an H2 In-memory database.

* The tables and database are automatically created with no data. 

* Binds to port 8080.

* All the api specifications are mentioned in swagger.

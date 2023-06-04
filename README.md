# Shop Apotheke challenge

## Reference
### Github API documentation for searching repositories
https://docs.github.com/en/rest/search?apiVersion=2022-11-28#search-repositories

example:
`https://api.github.com/search/repositories?q=created:%3E2019-01-10+language:python&sort=stars&order=desc`

## How to run the application
`mvn clean install`

`mvn spring-boot:run`

sample request:
`http://localhost:8080/shop-apotheke/search/repositories?dateFrom=2023-01-01&repoNum=3&programmingLang=javascript`

## Application structure
Use Onion/Hexagonal architecture:
* core: contains domain objects, service layers as ports, business exceptions etc.
* proxy: as one of the infrastructures talking direct with GitHub API and translate the result to domain objects
* presentation: expose API to clients

## TODO
* extends/adjust response for repositories (structure, attributes etc.)
* finish tests including unit tests, ITs
* extend service error case handling and expose correspondingly to client
* make service robuster against external dependency instability
* extend logging
* if needed, add caching for request with same query params and externalize cache invalidation parameters
* extend application properties for different envs and additional env variables
* add dockerfile for containerization
* make infrastructure as code including jenkins pipeline, k8s deployment files etc.


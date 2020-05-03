# Load Balancer
- Divide the load among multiple instances of a service 
- Types of Load Balancer
  - Client Side 
  - Server Side

#### Client Side Load Balancer
- Load balancer is part of the client i.e Front End decides which instance to call

#### Server side Load Balancer
- Server decides which instance must the request be sent
- Client is not ware of the number of instace of a microservice vailable

## Load Balancer 
1. Netflix Ribbon 
2. @Load Balanced in Rest Template 
3. Load Balancing Algorithm 
   - Round Robin
   - Least Connection
   - Adaptive Load Balancing

## Netflix Ribbon
- Provided by Netflix
- Works with Spring Boot
- Client Side load Balancer

### Steps to use Ribbon 

#### On Client Side i.e doctor-portal
1. Add Ribbon dependency 
2. Specify `@RibbonClient(name = "doctor-service")` in the main Class
3. Create a rest template bean with @LoadBalanced  annotation and @Bean
4. Specify Differet instances in properties file (Same service name must be provided in main class annotation)
```
doctor-service:
  ribbon:
    listOfServers: localhost:9081,localhost:9082,localhost:9083
```
5. Now ever call to the microervie is made using the logical name instead of actual url i.e
```
String url = "http://doctor-service/allDoctors";
```

#### On microservice side i.e Portal Service

### Testing the Concept of Load Balancing 
1. Create a jar file of the microservice(doctor-service) using `mvn package`
2. Run it using 
```
java -jar app_name.jar --server.port=9081
java -jar app_name.jar --server.port=9082
java -jar app_name.jar --server.port=9083
```

## Load Balancing with Eureka 
1. Mark the service as Eureka Client using `@EnableEurekaClient`
2. Register it to eureka server as 
```
eureka:
  client:
    register-with-eureka: true
```
3. Create a discovery server with annotation `@EnableEurekaServer`
4. Make portal-service(Rest consumer/frontend) as `@EnableDiscoveryClient`
5. Create a RestTemplate bean using 
```
@Bean
@LoadBalanced
public RestTemplate getRestTemplate(){
  return new RestTemplate();
}
```
6. Make sure it doesnt register to eureka
```
eureka:
  client:
    register-with-eureka: false
```
7. Create Multiple instances of a service and run at different ports
8. Eureka server will manage the instances for us 

## Different between @LoadBalanced and @RibbonClient
`@LoadBalanced` 
- Simply a marker annotation
- Used with Service Discovery (eg. Eureka)
- Used to tell RestTemplate that we need Support for Load Balancing 
- Allows use of Logical service name i.e `doctor-service` instead of `localhost:xxxx`
- Will use service-name as logia name when using dicovery server.
- Will use serice name provided by ribbon when using with ribbon

`@RibbonClient`
- Used for configuring Load Balancing
- Can be used with or without discovery server 


**NOTE**
Without using `@LoadBalanced`, we will get the following error 
```
I/O error on GET request for "http://doctor-service-app/allDoctors": doctor-service-app; nested exception is java.net.UnknownHostException: doctor-service-app
```

## Ribbon Client Fetures 
- IPing - Interface
  - Interface for Healthcheck
- Dummy Ping
  - Call the configured server for test
- PingUrl
  - Check the health of Ribbon 
- NIWS
  - Contacts server discover to check if server is available instead of doing to directly

- IRule - Interface
  - Request Distribution 
- RandomRule
  - Randomly Distributes request
- Round Robin
  - Instance recieve requests in order
  - Default Implementation
- Zone Avoidance Rule
 - Used along with AWS usually
- etc.


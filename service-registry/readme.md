# service-registry

## Overview 

 **Brief description:** This microservice acts as the Service Registry (using Eureka) for the AppVerse platform. It is responsible for maintaining a directory of all running microservice instances and enabling service discovery. This allows other microservices, such as the API Gateway, to dynamically locate and communicate with each other without hardcoding network locations.

  **Responsibilities:** 
      Registers and manages instances of all other microservices in the AppVerse platform, allowing them to be discoverable by other services (like the API Gateway) for communication


  ## Technologies Used:
    * Spring boot
    * Actuator (for monitoring and management)
    * Spring cloud netflix Server (for Service Registry functionality)




  ## Dependencies
    * Spring starter web starter
    * Spring cloud netflix Eureka server
    * Spring boot Devtools (for development convinience)







    

 
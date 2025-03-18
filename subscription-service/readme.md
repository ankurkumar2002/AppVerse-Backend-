## Application-service
    Description: This microservice is responsible to provide applications based on some conditions like buyed, subscribed etc kind of applciations(For now all the applciations are presented there).


   ## Technologies Used:
        * Spring boot
        * Spring Security
        * Eureka Client
        * Dev tools 

   ## API-endpoints
        
   **/api/applications/{applicationId}**
        Http method: `GET`
        Description: `This endpoint is responsible to get the applciation with the help of application id`
        Response: `It responds with the application object and http status.`
   **/api/applications**
        Http method: `GET`
        Description: `This endpoint Is responsible to return all the application in for of list`
        Response: `It responds with the list of applications and http status`
   **/api/applications/{applicationId}**
        Http method: `PUT`
        Description: `This endpoint is responsible to update the application using applciation id`
        Response: `This endpoint responds with updated application details and http status.`
   **/api/applications/{applicationId}**
        Http method: `DELETE`
        Description: `This endpoint is responsible to delete the particular application bye identifying using application id`
        Response: `endpoint responds with a string message with the Http status`
   **/api/applications/{applicationId}**
        Http method: ``
        Description: ``
        Response: ``


   **/api/category/{categoryId}**
        Http method: `GET`
        Description: `This endpoint is responsible to get the category with the help of category id`
        Response: `It responds with the category object and http status.`
   **/api/category**
        Http method: `GET`
        Description: `This endpoint Is responsible to return all the categories in for of list`
        Response: `It responds with the list of category and http status`
   **/api/category/{categoryId}**
        Http method: `PUT`
        Description: `This endpoint is responsible to update the category using category id`
        Response: `This endpoint responds with updated category details and http status.`
   **/api/category/{categoryId}**
        Http method: `DELETE`
        Description: `This endpoint is responsible to delete the particular category bye identifying using category id`
        Response: `endpoint responds with a string message with the Http status`
   **/api/by-name/{categoryName}**
        Http method: `GET`
        Description: `This endpoint is used to get  the category by name`
        Response: `this is responsible to respond with category object and http status`
   **/api/category**
        Http method: `POST`
        Description: `This endpoint is used to create a new category.`
        Response: `This endpoint responds with category object and http status`
   **/api/category**
        Http method: `GET`
        Description: `This endpoint is responsible to get all the categories.`
        Response: `this endpoint responds with a list of category and http status.`


   **/api/images/icons/{imageName}**
        Http method: `GET`
        Description: `this endpoint is used to get the application icon based on the name.`
        Response: `it responds with the image`
    
   **/api/images/screenshots/{imageName}**
        Http method: `GET`
        Description: `this endpoint is used to get the screenshots based on the name.`
        Response: `it responds with the screenshots`
   **/api/images/icons/{imageName}**
        Http method: `GET`
        Description: `this endpoint is used to get the application icon based on the name.`
        Response: `it responds with the image`

    
   **/api/subscriptions/{subscriptionId}**
        Http method: `GET`
        Description: `this endpoint is responsible to get subscription object using subscription ID`
        Response: `Reponds with subscription object and http status`
   **/api/subscriptions**
        Http method: `GET`
        Description: `this endpoint is responsible to provide all the subscriptions in form of list`
        Response: `Responds with list of subscriptions with http status`
   **/api/subscriptions/users/{userId}/applications/{applciationId}**
        Http method: `POST`
        Description: `this endpoint is responsibe to adding particular to the user when he subscribes to a particular application it gets updated through this applciation`
        Response: `this endpoint responds with subscription object with http status`
   **/api/subscriptions/{subscriptionId}**
        Http method: `DELETE`
        Description: `this endpoint is resposible to delete particular subscription or unsubscribe.`
        Response: `This endpoint responds with message and http status.`
   **/api/subscriptions/users/{userId}**
        Http method: `GET`
        Description: `this endpoint is responsible to get all the applciation subscribed to the user`
        Response: `responds with applications subscribed by the user with http status`
   **/api/subscriptions/users/{userId}/count**
        Http method: `GET`
        Description: `this endpoint responds with count of applications subscribed by the user`
        Response: `responds with count and http status`
   **/api/subscriptions/applications/{applicationId}/users**
        Http method: `GET`
        Description: `this endpoint is responsible to provide user who has subscribed to that particular user`
        Response: `responds with users list`

   ## Technologies Used
        * Spring boot
        * Java 17
        * Spring security
        * Data JPA
        * Postgree
        * Eureka Client
        * Zipkin brave
        * JWT


  ## Notes:
   *No requests can be completed without jwtToken other than signup, login, forgot-password and reset-password*

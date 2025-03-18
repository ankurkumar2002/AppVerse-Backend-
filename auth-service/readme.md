# Auth-service

## Overview 

 **Brief description:** This app is reponsible for user creation, authentication, token generation , user updation , token verification, password encoding etc. This microservice provides core authentication and authorization services for the appVerse platform.

  **Responsibilities:** 
      * This microservice is responsible for user creation(Signing up) with password encoding and Microservice provides login endpoint which authenticates the user by checking the username and password, after authenticating the user it generates jwt token based on remember me which increase the expiry of the token.
      * Allows user to update profile details including profile picture.
      * Reset password and forgot password functionality is also there but due to some restrictions I am not able to implement that fully.
      * Backend Validation: Microservice is also responsible for validation of data which is going into the database.


  ## Technologies Used:
    * Spring boot
    * Spring Security
    * Actuator
    * Spring Validation
    * Eureka Client
    * PostgreSql
    * Jwt


  ## API Endpoints:

   **/auth/signup**
      Http method: `POST`
      Description: `This endpoint is responsible to create a new user , encrypting password setting role and setting default profile`
      Response: `The response is in JSON format of user and with status as OK`
   **/auth/login**
      Http method: `POST`
      Description: `This endpoint is responsible to authenticate the user by checking the username and password from the backend by decrypting the password and generate the jwt token and also generate the userDetails object to give user details.`
      Response: `The response is in form of Map which contains jwtToken and userDetails with response status`

   **/auth/users**
    Http method: `GET`
    Description: `This endpoint is only used to get all users which are present in the users table.`
    Response: `The response is in list form`

   **/auth/forgot-password and /auth/reset-password**
    Http method: `POST`
    Description: `(These endpoints are not completed or tested due to system restrictions)forgot-password is used to generate a token which will be used to identify the particular request and which will be sent to the user email which will also be used to identify wether the link has expired or not. reset-password is used to reset the password and set it to the database the new password after encrypting the password.`
    Response: `forgot-password response with a string of successful message with response status of OK and reset-password also responds with a string and response status.`

   **/auth/get-user-info**
    Http method: `GET`
    Description: `This endpoint is responsible to get all the details of the user using username`
    Response: `Response is in for of users`


   **/auth/edit**
    Http method: `PUT`
    Description: `This endpoint is responsible for updating the user details and if username is changed then using username it will generate new jwttoken and give it in a response.`
    Response: `This endpoint responds with updated user and header with udpated jwttoken.`

   **/auth/profile-image** 
    Http method: `POST`
    Description: `This endpoint is responsible for updating the profile image with new profile image`
    Response: `This endpoint is responds with updated user detials and http status`

   **/auth/profile-image**
    Http method: `GET`
    Description: `This endpoint is resposible to give profile iamge assocciated with the user.`
    Response: `This endpoint responds with the profile image.`




  ## Dependencies
    * Spring starter web
    * Spring Data Jpa
    * Webflux
    * Eureka client
    * PostgreSql
    * Spring Security
    * Actuator
    * JSONwebToken


  ## Notes:
   *No requests can be completed without a valid JWT token in the `Authorization` header, except for the `/auth/signup`, `/auth/login`, `/auth/forgot-password`, and `/auth/reset-password` endpoints, which are used for initial user registration and authentication.




    

 
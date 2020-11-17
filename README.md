# API test automation sample project
The test suite contains RestAssured based automated test cases for https://jsonplaceholder.typicode.com APIs. 
It validates important functional tests for posts and users resources.

All identified test scenarios are given below in Test Scenarios section.
The test scenarios are written assuming proper validations are in place in the BE APIs.
As the APIs doesn't have many validations, most of the negative tests are not automated.

Following features are included in the project.

  - RestAssured to invoke APIs
  - TestNG parallel execution at test method level
  - Jackson library is to manipulate request/response data
  - Gradle managed project dependency and artifacts

# How to run test cases
The test suite can be run in the same way as any testNG test case is run.

## Using IDE
Bellow steps are for IntellijIdea based test execution.
  - Open build.gradle using the IDE and open the file as a project.This will execute necessary steps to attach the code to the IDE and prepare development environment.
  - Provide JDK ( Higher than java 7 ) when IDE requests it.
  - Internet connectivity is required as the dependencies are downloaded when the project is built.
  - A VM option is required to be added in **Edit configuration--> Templates --> TestNG --> VM options** : `-ea -Dtestng.dtd.http=true`
  - Right click on testng.xml and create a run configuration for tesng.xml. Select **In whole project** in the dialog box opened.
  - Select above run configuration and run the tests.
  
#Test Scenarios
Format : Scenario title (Expected outcome)

## Create post/user
1. Create post/user with mandatory parameters (Status code : 201, Entity is created)
2. Create post/user with all parameters (Status code : 201, Entity is created) - **AUTOMATED**
3. Create post/user with max length values for parameters (Status code : 201, Entity is created)
4. Create post/user with identical content a second time - idempotent request (Status code : 200, Return previously created entity details)

5. Create post/user with value exceeding max length for parameters (Status code : 400, Proper error message)
6. Create post/user without mandatory parameters (Status code : 400, Proper error message)
7. Create post/user with null mandatory parameters (Status code : 400, Proper error message)
8. Create post/user with empty ("") mandatory parameters (Status code : 400, Proper error message)
9. Create post/user with empty (" ") mandatory parameters (Status code : 400, Proper error message)
10. Create post/user with special characters/not allowed characters (Status code : 400, Proper error message)
11. Create post/user with XSS content (Eg: "<script>alert('xss')</script>") for parameters (Status code : 400, Proper error message)
12. Create user with duplicate username (Status code : 400, Proper error message)
13. Create user with invalid format email (Status code : 400, Proper error message)
14. Create user with invalid format website (Status code : 400, Proper error message)

## Get post/user
1. Get post/user with valid id (Status code : 200, Return entity details) - **AUTOMATED**

2. Get post/user with non existing id  (Status code : 404, Proper error message)

## List post/user
1. List posts/users without any filtering (Status code : 200, Return all entities) - **AUTOMATED**
2. List posts/users with valid filtering (Status code : 200, Return filtered entities by given criteria) - **AUTOMATED**
3. List posts/users with invalid filtering - Eg: List posts filtered by a non existing user id (Status code : 200, Return empty list) - **AUTOMATED**
4. Pagination tests if pagination is in place

## Update post/user (PUT)
1. Update post/user with all parameters (Status code : 200, All parameters updated) - **AUTOMATED**
2. Update post/user set of/individual parameters (Status code : 200, Parameter/s sent in request updated, Other parameters removed) - **AUTOMATED**
3. Update post/user and set max length for parameters  (Status code : 200, Parameters updated)

4. Update post/user and remove/set null/set blank/set empty mandatory parameters  (Status code : 400, Proper error message)
5. Update post/user parameters which are not allowed to update (Status code : 400, Proper error message)
6. Update post/user and set values exceeding max length for parameters  (Status code : 400, Proper error message)
7. Update post/user and set special characters/not allowed characters for parameters (Status code : 400, Proper error message)
8. Update post/user and set XSS content (Eg: "<script>alert('xss')</script>") for parameters (Status code : 400, Proper error message)
9. Update user and set duplicate username (Status code : 400, Proper error message)
10. Update user and set invalid format email (Status code : 400, Proper error message)
11. Update user and set invalid format website (Status code : 400, Proper error message)

## Update post/user (PATCH)
1. Update post/user with all parameters (Status code : 200, All parameters updated) - **AUTOMATED**
2. Update post/user with set of/individual parameters (Status code : 200, Parameter/s sent in request updated, Other parameters unchanged) - **AUTOMATED**
3. Update post/user and set max length for parameters  (Status code : 200, Parameters updated)

4. Update post/user and remove/set null/set blank/set empty mandatory parameters  (Status code : 400, Proper error message)
5. Update post/user parameters which are not allowed to update (Status code : 400, Proper error message)
6. Update post/user and set values exceeding max length for parameters  (Status code : 400, Proper error message)
7. Update post/user and set special characters/not allowed characters for parameters (Status code : 400, Proper error message)
8. Update post/user and set XSS content (Eg: "<script>alert('xss')</script>") for parameters (Status code : 400, Proper error message)
9. Update user and set duplicate username (Status code : 400, Proper error message)
10. Update user and set invalid format email (Status code : 400, Proper error message)
11. Update user and set invalid format website (Status code : 400, Proper error message)

## Delete post/user
1. Delete post/user with valid id (Status code : 204, Entity is deleted) - **AUTOMATED**

2. Delete post/user with non existing id  (Status code : 404, Proper error message)

## Other functional scenarios for nested APIs
**Endpoint /posts/1/comments**
1. Get comments for a post by valid post id (Status code : 200, Comments for given post returned) - **AUTOMATED**

2. Get comments for a post by non existing post id (Status code : 404, Proper error message)

**Endpoint /users/1/posts**
1. Get posts for a user by valid user id (Status code : 200, Posts for given user returned) - **AUTOMATED**

2. Get posts for a user by non existing user id (Status code : 404, Proper error message)
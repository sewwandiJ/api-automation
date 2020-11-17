package com.jsonplaceholder.utils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ServiceClient {

    public RequestSpecification httpRequest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RequestSpecification httpRequest = RestAssured.given();
        return httpRequest;
    }

    //Posts API methods
    public Response createPost(String requestBody) {
        return httpRequest()
                .header("Content-type", "application/json; charset=UTF-8")
                .body(requestBody)
                .request(Method.POST, "/posts");
    }

    public Response getPostById(String id) {
        return httpRequest().request(Method.GET, "/posts/" + id);
    }

    public Response getPosts() {
        return httpRequest().request(Method.GET, "/posts");
    }

    public Response getPosts(String queryParam, String value) {
        return httpRequest().queryParam(queryParam, value).request(Method.GET, "/posts");
    }

    public Response updatePost(String id, String requestBody) {
        return httpRequest()
                .header("Content-type", "application/json; charset=UTF-8")
                .body(requestBody)
                .request(Method.PUT, "/posts/" + id);
    }

    public Response patchPost(String id, String requestBody) {
        return httpRequest()
                .header("Content-type", "application/json; charset=UTF-8")
                .body(requestBody)
                .request(Method.PATCH, "/posts/" + id);
    }

    public Response deletePost(String id) {
        return httpRequest().request(Method.DELETE, "/posts/" + id);
    }

    public Response getPostComments(String id) {
        return httpRequest().request(Method.GET, "/posts/" + id + "/comments");
    }

    //User API methods
    public Response createUser(String requestBody) {
        return httpRequest()
                .header("Content-type", "application/json; charset=UTF-8")
                .body(requestBody)
                .request(Method.POST, "/users");
    }

    public Response getUserById(String id) {
        return httpRequest().request(Method.GET, "/users/" + id);
    }

    public Response getUsers() {
        return httpRequest().request(Method.GET, "/users");
    }

    public Response getUsers(String queryParam, String value) {
        return httpRequest().queryParam(queryParam, value).request(Method.GET, "/users");
    }

    public Response updateUser(String id, String requestBody) {
        return httpRequest()
                .header("Content-type", "application/json; charset=UTF-8")
                .body(requestBody)
                .request(Method.PUT, "/users/" + id);
    }

    public Response patchUser(String id, String requestBody) {
        return httpRequest()
                .header("Content-type", "application/json; charset=UTF-8")
                .body(requestBody)
                .request(Method.PATCH, "/users/" + id);
    }

    public Response deleteUser(String id) {
        return httpRequest().request(Method.DELETE, "/users/" + id);
    }

    public Response getUserPosts(String id) {
        return httpRequest().request(Method.GET, "/users/" + id + "/posts");
    }

}
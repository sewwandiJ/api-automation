package com.jsonplaceholder.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jsonplaceholder.models.Comment;
import com.jsonplaceholder.models.Post;
import com.jsonplaceholder.models.User;

import java.util.List;

public class TestUtil {

    public static ServiceClient client = new ServiceClient();
    public static String NOT_NULL = "NOT_NULL";
    static ObjectMapper objectMapper = initMapper();

    static ObjectMapper initMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    public String serializePost(Post post) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(post);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public Post deserializePost(String json) {
        Post post = null;
        try {
            post = objectMapper.readValue(json, Post.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return post;
    }

    public List<Post> deserializePostList(String json) {
        List<Post> list = null;
        try {
            list = objectMapper.readValue(json, new TypeReference<List<Post>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String serializeUser(User user) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public User deserializeUser(String json) {
        User user = null;
        try {
            user = objectMapper.readValue(json, User.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> deserializeUserList(String json) {
        List<User> list = null;
        try {
            list = objectMapper.readValue(json, new TypeReference<List<User>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Comment> deserializeCommentList(String json) {
        List<Comment> list = null;
        try {
            list = objectMapper.readValue(json, new TypeReference<List<Comment>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }
}

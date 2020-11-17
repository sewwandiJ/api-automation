package com.jsonplaceholder;

import com.jsonplaceholder.models.Comment;
import com.jsonplaceholder.models.Post;
import com.jsonplaceholder.utils.TestUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PostsTest extends TestUtil {
    String post1Title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
    String post1Body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit " +
            "molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto";

    //Positive scenarios
    @Test()
    public void createPost() {
        Post createParam = getPost(null, "1", "Test Post", "Test post body");

        Response response = client.createPost(serializePost(createParam));
        Post actualResponsePost = deserializePost(response.getBody().asString());
        Post expectedResponsePost = getPost(NOT_NULL, "1", "Test Post", "Test post body");

        Assert.assertEquals(response.getStatusCode(), 201);
        verifyPost(actualResponsePost, expectedResponsePost);
    }

    @Test()
    public void getPostById() {
        Response response = client.getPostById("1");

        Post actualPost = deserializePost(response.getBody().asString());
        Post expectedPost = getPost("1", "1", post1Title, post1Body);

        Assert.assertEquals(response.getStatusCode(), 200);
        verifyPost(actualPost, expectedPost);
    }

    @Test()
    public void getPosts() {
        Response response = client.getPosts();

        List<Post> responsePostsList = deserializePostList(response.getBody().asString());
        Post expectedPost = getPost("1", "1", post1Title, post1Body);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responsePostsList.size(), 100, "Incorrect number of posts.");
        verifyPost(responsePostsList.get(0), expectedPost);
    }

    @Test()
    public void updatePostAllParameters() {
        Post updateParam = getPost("1", "1", "Updated Title", "Updated test post body");

        Response response = client.updatePost("1", serializePost(updateParam));
        Post actualResponsePost = deserializePost(response.getBody().asString());
        Post expectedResponsePost = getPost("1", "1", "Updated Title", "Updated test post body");

        Assert.assertEquals(response.getStatusCode(), 200);
        verifyPost(actualResponsePost, expectedResponsePost);
    }

    @Test()
    public void updatePostIndividualParameters() {
        Post updateParam = getPost("1", "1", null, "Updated test post body");

        Response response = client.updatePost("1", serializePost(updateParam));
        Post actualResponsePost = deserializePost(response.getBody().asString());
        Post expectedResponsePost = getPost("1", "1", null, "Updated test post body");

        Assert.assertEquals(response.getStatusCode(), 200);
        verifyPost(actualResponsePost, expectedResponsePost);
    }

    @Test()
    public void patchPostAllParameters() {
        Post updateParam = getPost(null, "1", "Updated Title", "Updated test post body");

        Response response = client.patchPost("1", serializePost(updateParam));
        Post actualResponsePost = deserializePost(response.getBody().asString());
        Post expectedResponsePost = getPost("1", "1", "Updated Title", "Updated test post body");

        Assert.assertEquals(response.getStatusCode(), 200);
        verifyPost(actualResponsePost, expectedResponsePost);
    }

    @Test()
    public void patchPostIndividualParameters() {
        Post updateParam = getPost(null, null, null, "Updated test post body");

        Response response = client.patchPost("1", serializePost(updateParam));
        Post actualResponsePost = deserializePost(response.getBody().asString());
        Post expectedResponsePost = getPost("1", "1", post1Title, "Updated test post body");

        Assert.assertEquals(response.getStatusCode(), 200);
        verifyPost(actualResponsePost, expectedResponsePost);
    }

    @Test()
    public void deletePost() {
        Response response = client.deletePost("1");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test()
    public void filterPostsByUserId() {
        Response response = client.getPosts("userId", "1");
        List<Post> responsePostsList = deserializePostList(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responsePostsList.size(), 10);
        responsePostsList.forEach(post -> Assert.assertEquals(post.getUserId(), "1"));
    }

    @Test()
    public void filterPostsByNonExistingUserId() {
        Response response = client.getPosts("userId", "1234");
        List<Post> responsePostsList = deserializePostList(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responsePostsList.size(), 0);
    }

    @Test()
    public void getCommentsForAPost() {
        Response response = client.getPostComments("1");
        List<Comment> responseCommentsList = deserializeCommentList(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseCommentsList.size(), 5);
        responseCommentsList.forEach(comment -> Assert.assertEquals(comment.getPostId(), "1"));
    }

    //Negative scenarios
    @Test
    public void getPostByInvalidId() {
        Response response = client.getPostById("1234");

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    //Utility methods
    private Post getPost(String id, String userId, String testPost, String testPostBody) {
        Post createParam = new Post();
        createParam.setId(id);
        createParam.setUserId(userId);
        createParam.setTitle(testPost);
        createParam.setBody(testPostBody);
        return createParam;
    }

    private void verifyPost(Post actual, Post expected) {
        if (expected.getId().equals(NOT_NULL))
            Assert.assertNotNull(actual.getId(), "Id is null.");
        else
            Assert.assertEquals(actual.getId(), expected.getId(), "Incorrect id.");

        Assert.assertEquals(actual.getUserId(), expected.getUserId(), "Incorrect userId.");
        Assert.assertEquals(actual.getTitle(), expected.getTitle(), "Incorrect title.");
        Assert.assertEquals(actual.getBody(), expected.getBody(), "Incorrect body.");
    }

}

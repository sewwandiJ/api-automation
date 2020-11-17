package com.jsonplaceholder;

import com.jsonplaceholder.models.Post;
import com.jsonplaceholder.models.User;
import com.jsonplaceholder.utils.TestUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UsersTest extends TestUtil {

    @Test()
    public void createUser() {
        User createParam = getUser();

        Response response = client.createUser(serializeUser(createParam));
        User actualResponseUser = deserializeUser(response.getBody().asString());
        User expectedResponseUser = getUser();
        expectedResponseUser.setId(NOT_NULL);

        Assert.assertEquals(response.getStatusCode(), 201);
        verifyUser(actualResponseUser, expectedResponseUser);
    }

    @Test()
    public void getUserById() {
        Response response = client.getUserById("1");

        User actualUser = deserializeUser(response.getBody().asString());
        User expectedUser = user1();

        Assert.assertEquals(response.getStatusCode(), 200);
        verifyUser(actualUser, expectedUser);
    }

    @Test()
    public void getUsers() {
        Response response = client.getUsers();

        List<User> responseUsersList = deserializeUserList(response.getBody().asString());
        User expectedUser = user1();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseUsersList.size(), 10, "Incorrect number of users.");
        verifyUser(responseUsersList.get(0), expectedUser);
    }

    @Test()
    public void updateUserAllParameters() {
        User updateParam = getUpdatedUser();

        Response response = client.updateUser("1", serializeUser(updateParam));
        User actualResponseUser = deserializeUser(response.getBody().asString());
        updateParam.setId("1");

        Assert.assertEquals(response.getStatusCode(), 200);
        verifyUser(actualResponseUser, updateParam);
    }

    @Test()
    public void updateUserIndividualParameters() {
        User updateParam = getUser();
        updateParam.setName("Updated");

        Response response = client.updateUser("1", serializeUser(updateParam));
        User actualResponseUser = deserializeUser(response.getBody().asString());
        updateParam.setId("1");

        Assert.assertEquals(response.getStatusCode(), 200);
        verifyUser(actualResponseUser, updateParam);
    }

    @Test()
    public void patchUserAllParameters() {
        User updateParam = getUpdatedUser();

        Response response = client.patchUser("1", serializeUser(updateParam));
        User actualResponseUser = deserializeUser(response.getBody().asString());
        updateParam.setId("1");

        Assert.assertEquals(response.getStatusCode(), 200);
        verifyUser(actualResponseUser, updateParam);
    }

    @Test()
    public void patchUserIndividualParameters() {
        User updateParam = new User();
        updateParam.setName("Updated name");

        Response response = client.patchUser("1", serializeUser(updateParam));
        User actualResponseUser = deserializeUser(response.getBody().asString());
        User expectedResponseUser = user1();
        expectedResponseUser.setName("Updated name");

        Assert.assertEquals(response.getStatusCode(), 200);
        verifyUser(actualResponseUser, expectedResponseUser);
    }

    @Test()
    public void deleteUser() {
        Response response = client.deleteUser("1");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test()
    public void filterUsersByUsername() {
        Response response = client.getUsers("username", "Antonette");
        List<User> responseUsersList = deserializeUserList(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseUsersList.size(), 1);
        Assert.assertEquals(responseUsersList.get(0).getUsername(), "Antonette");
    }

    @Test()
    public void filterUsersByNonExistingUsername() {
        Response response = client.getUsers("username", "1234");
        List<User> responseUsersList = deserializeUserList(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responseUsersList.size(), 0);
    }

    @Test()
    public void getPostsForAUser() {
        Response response = client.getUserPosts("1");
        List<Post> responsePostsList = deserializePostList(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(responsePostsList.size(), 10);
        responsePostsList.forEach(comment -> Assert.assertEquals(comment.getUserId(), "1"));
    }

    //Negative scenarios
    @Test
    public void getUserByInvalidId() {
        Response response = client.getUserById("1234");

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    //Utility methods
    private User user1() {
        User.Geo geo = new User.Geo();
        geo.setLat("-37.3159");
        geo.setLng("81.1496");

        User.Address address = new User.Address();
        address.setStreet("Kulas Light");
        address.setSuite("Apt. 556");
        address.setCity("Gwenborough");
        address.setZipcode("92998-3874");
        address.setGeo(geo);

        User.Company company = new User.Company();
        company.setName("Romaguera-Crona");
        company.setCatchPhrase("Multi-layered client-server neural-net");
        company.setBs("harness real-time e-markets");

        User user = new User();
        user.setId("1");
        user.setName("Leanne Graham");
        user.setUsername("Bret");
        user.setEmail("Sincere@april.biz");
        user.setAddress(address);
        user.setPhone("1-770-736-8031 x56442");
        user.setWebsite("hildegard.org");
        user.setCompany(company);

        return user;
    }

    private User getUser() {
        User.Geo geo = new User.Geo();
        geo.setLat("-37.3159");
        geo.setLng("81.1496");

        User.Address address = new User.Address();
        address.setStreet("street");
        address.setSuite("suite");
        address.setCity("Gwenborough");
        address.setZipcode("92998-3874");
        address.setGeo(geo);

        User.Company company = new User.Company();
        company.setName("Romaguera-Crona");
        company.setCatchPhrase("Multi-layered client-server neural-net");
        company.setBs("harness real-time e-markets");

        User user = new User();
        user.setName("John Smith");
        user.setUsername("jsmith123");
        user.setEmail("jsmith@gmail.com");
        user.setAddress(address);
        user.setPhone("1-770-736-8031 x56442");
        user.setWebsite("testorg.com");
        user.setCompany(company);

        return user;
    }

    private User getUpdatedUser() {
        User.Geo geo = new User.Geo();
        geo.setLat("-37.31592");
        geo.setLng("81.14962");

        User.Address address = new User.Address();
        address.setStreet("street2");
        address.setSuite("suite2");
        address.setCity("Gwenborough2");
        address.setZipcode("92998-38742");
        address.setGeo(geo);

        User.Company company = new User.Company();
        company.setName("Romaguera-Crona2");
        company.setCatchPhrase("Multi-layered client-server neural-net2");
        company.setBs("harness real-time e-markets2");

        User updateParam = new User();
        updateParam.setName("John Smith2");
        updateParam.setUsername("jsmith1232");
        updateParam.setEmail("jsmith2@gmail.com");
        updateParam.setAddress(address);
        updateParam.setPhone("1-770-736-8031 x564422");
        updateParam.setWebsite("testorg.com");
        updateParam.setCompany(company);
        return updateParam;
    }

    private void verifyUser(User actual, User expected) {
        if (expected.getId().equals(NOT_NULL))
            Assert.assertNotNull(actual.getId(), "Id is null.");
        else
            Assert.assertEquals(actual.getId(), expected.getId(), "Incorrect id.");

        Assert.assertEquals(actual.getName(), expected.getName(), "Incorrect name.");
        Assert.assertEquals(actual.getUsername(), expected.getUsername(), "Incorrect username.");
        Assert.assertEquals(actual.getEmail(), expected.getEmail(), "Incorrect email.");
        Assert.assertEquals(actual.getAddress().getStreet(), expected.getAddress().getStreet(), "Incorrect address > street.");
        Assert.assertEquals(actual.getAddress().getSuite(), expected.getAddress().getSuite(), "Incorrect address > suite.");
        Assert.assertEquals(actual.getAddress().getCity(), expected.getAddress().getCity(), "Incorrect address > city.");
        Assert.assertEquals(actual.getAddress().getZipcode(), expected.getAddress().getZipcode(), "Incorrect address > zipcode.");
        Assert.assertEquals(actual.getAddress().getGeo().getLat(), expected.getAddress().getGeo().getLat(), "Incorrect address > geo > lat.");
        Assert.assertEquals(actual.getAddress().getGeo().getLng(), expected.getAddress().getGeo().getLng(), "Incorrect address > geo > lng.");
        Assert.assertEquals(actual.getPhone(), expected.getPhone(), "Incorrect phone.");
        Assert.assertEquals(actual.getWebsite(), expected.getWebsite(), "Incorrect website.");
        Assert.assertEquals(actual.getCompany().getName(), expected.getCompany().getName(), "Incorrect company > name.");
        Assert.assertEquals(actual.getCompany().getCatchPhrase(), expected.getCompany().getCatchPhrase(), "Incorrect company > catchphrase.");
        Assert.assertEquals(actual.getCompany().getBs(), expected.getCompany().getBs(), "Incorrect company > bs.");
    }
}
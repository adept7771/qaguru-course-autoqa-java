package tests;

import helpers.ApiHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApiTests {
    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void getUsersTest() {
        Response response = apiHelper.getListOfUsersByPageNum(1);
        String code = String.valueOf(response.statusCode());
        Assertions.assertEquals("200", code);
        Assertions.assertTrue(response.body().asString().contains("\"page\":1"));
    }

    @Test
    public void singleUserNotFoundTest() {
        String statusCode = String.valueOf(apiHelper.getSingleUserByNum(500).statusCode());
        Assertions.assertEquals("404", statusCode);
    }

    @Test
    public void registerUserTest() {
        String userName = "testUserName", userJob = "testUserJob";
        Response response = apiHelper.createUser(userName, userJob);
        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertTrue(response.asString().contains(userName));
        Assertions.assertTrue(response.asString().contains(userJob));
    }

    @Test
    public void updateUserTest() {
        String userId = apiHelper.createUser("userName", "userJob").then().extract().path("id");
        String updatedName = "updatedName", updatedJob = "updatedJob";
        Response response = apiHelper.updateUser(updatedName, updatedJob, userId);
        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertTrue(response.asString().contains(updatedName));
        Assertions.assertTrue(response.asString().contains(updatedJob));
    }

    @Test
    public void deleteUserTest() {
        String userId = apiHelper.createUser("userName", "userJob").then().extract().path("id");
        int statusCode = apiHelper.deleteUser(userId).getStatusCode();
        Assertions.assertEquals(204, statusCode);
        String actualCode = String.valueOf(apiHelper.getSingleUserByNum(500).statusCode());
        Assertions.assertEquals("404", actualCode);
    }
}

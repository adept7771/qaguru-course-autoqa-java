package tests;

import helpers.ApiHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class ApiTestsWOCustomFilter {
    ApiHelper apiHelper = new ApiHelper(false);

    @BeforeAll
    public static void setUp() {
        RestAssured.filters(Collections.singletonList(new AllureRestAssured()));
    }

    @Test
    public void getUsersTest() {
        Response response = apiHelper.getListOfUsersByPageNum(1);
        Assertions.assertEquals("200", String.valueOf(response.statusCode()));
        Assertions.assertTrue(response.body().asString().contains("\"page\":1"));
    }

    @Test
    public void singleUserNotFoundTest() {
        String statusCode = String.valueOf(apiHelper.getSingleUserByNum(500).statusCode());
        Assertions.assertEquals("404", statusCode);
    }

    @Test
    public void registerUserTest() {
        String userName = "testUserName";
        String userJob = "testUserJob";
        Response response = apiHelper.createUser(userName, userJob);
        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertTrue(response.asString().contains(userName));
        Assertions.assertTrue(response.asString().contains(userJob));
    }

    @Test
    public void updateUserTest() {
        String userId = apiHelper.createUser("userName", "userJob").then().extract().path("id");
        String updatedName = "updatedName";
        String updatedJob = "updatedJob";
        Response response = apiHelper.updateUser(updatedName, updatedJob, userId);
        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertTrue(response.asString().contains(updatedName));
        Assertions.assertTrue(response.asString().contains(updatedJob));
    }

    @Test
    public void deleteUserTest() {
        String userId = apiHelper.createUser("userName", "userJob").then().extract().path("id");
        Assertions.assertEquals(204, apiHelper.deleteUser(userId).getStatusCode());
        String statusCode = String.valueOf(apiHelper.getSingleUserByNum(500).statusCode());
        Assertions.assertEquals("404", statusCode);
    }
}

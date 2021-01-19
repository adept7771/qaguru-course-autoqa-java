package tests;

import helpers.ApiHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApiTests {
    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void getUsersTest() {
        Response response = apiHelper.addItemToWishList();
    }


}

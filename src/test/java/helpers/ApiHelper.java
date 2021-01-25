package helpers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import template.ReportTemplate;

import java.util.Collections;

import static filter.CustomFilter.CUSTOM_ALLURE_REST;
import static io.restassured.RestAssured.given;

public class ApiHelper {

    private final String baseUrl = "https://reqres.in",
            singleUserUrl = "/api/users",
            getAllUsersUrl = "/api/users?page=";

    RequestSpecification spec;

    public ApiHelper(boolean enableFilter) {
        if (true) {
            spec = given()
                    .contentType(ContentType.JSON)
                    .baseUri(baseUrl)
                    .filter(ReportTemplate.customTemplates());
        } else {
            spec = given()
                    .contentType(ContentType.JSON)
                    .baseUri(baseUrl);
        }
    }

    public Response getListOfUsersByPageNum(int pageNum) {
        return given()
                .spec(spec)
                .with()
                .when()
                .log().all()
                .get(getAllUsersUrl + pageNum);
    }

    public Response getSingleUserByNum(int userId) {
        return given()
                .spec(spec)
                .with()
                .when()
                .log().all()
                .get(singleUserUrl + "/" + userId);
    }

    public Response createUser(String name, String job) {
        String requestBody = "{\n" + "\"name\": \"" + name + "\",\n" + "\"job\": \"" + job + "\"" + "\n}";
        return given()
                .spec(spec)
                .with()
                .body(requestBody)
                .when()
                .log().all()
                .post(singleUserUrl);
    }

    public Response updateUser(String name, String job, String userId) {
        String requestBody = "{\n" + "\"name\": \"" + name + "\",\n" + "\"job\": \"" + job + "\"" + "\n}";
        return given()
                .spec(spec)
                .with()
                .body(requestBody)
                .when()
                .log().all()
                .post(singleUserUrl + "/" + userId);
    }

    public Response deleteUser(String userId) {
        return given()
                .spec(spec)
                .with()
                .when()
                .log().all()
                .delete(singleUserUrl + "/" + userId);
    }
}

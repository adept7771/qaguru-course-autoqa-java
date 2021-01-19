package helpers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    private final String baseUrl = "https://reqres.in",
            createUserUrl = "/api/users",
            getAllUsersUrl = "/api/users?page=";

    RequestSpecification spec;

    public ApiHelper() {
        spec = given()
                .contentType(ContentType.JSON)
                .baseUri(baseUrl);
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
                .get(createUserUrl + "/" + userId);
    }

    public Response createUser(String name, String job) {
        String requestBody = "{\n" +
                "\"name\": \"" + name + "\",\n" +
                "\"job\": \"" + job + "\"" +
                "\n}";
        return given()
                .spec(spec)
                .with()
                .body(requestBody)
                .when()
                .log().all()
                .post(createUserUrl)
                ;
    }

    public Response updateUser(String name, String job, String userId) {
        String requestBody = "{\n" +
                "\"name\": \"" + name + "\",\n" +
                "\"job\": \"" + job + "\"" +
                "\n}";
        return given()
                .spec(spec)
                .with()
                .body(requestBody)
                .when()
                .log().all()
                .post(createUserUrl + "/" + userId)
                ;
    }

//    public Response deleteUser(String userId){
//        return given()
//                .spec(spec)
//                .with()
//                .body(requestBody)
//                .when()
//                .log().all()
//                .delete()
//                ;
//    }
}

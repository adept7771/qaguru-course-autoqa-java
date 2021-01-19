package helpers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    private final String baseUrl = "http://demowebshop.tricentis.com",
            wishListUrl = "/wishlist",
            addToWhishlist = "/addproducttocart/details/53/2",
            bodyForAddToWishList = "addtocart_53.EnteredQuantity=1";

    RequestSpecification spec;
    String cookie = "Nop.customer=cf4e6b33-2823-44b3-8f0e-1fee89039cad; ARRAffinity=7f10010dd6b12d83d6aefe199065b2e8fe0d0850a7df2983b482815225e42439; __utmc=78382081; __utmz=78382081.1611045863.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __RequestVerificationToken=iAPt5bMZfUxypUzAh7iygf032kLjgGPklPg-y_8WkrHl9Trw9DRR8lw4SRPWXDhfVeuuBp98h4QnD-QTBA-fqVyFSnm5UwNFOSBRulEs3ug1; ASP.NET_SessionId=v302dgp3mz3auqufjeesnwdb; __utma=78382081.373186156.1611045863.1611048018.1611051155.3; Nop.customer=cf4e6b33-2823-44b3-8f0e-1fee89039cad; nop.CompareProducts=CompareProductIds=31; _atshc={\"http://demowebshop.tricentis.com/simple-computer\":4}; __utmt=1; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=53&RecentlyViewedProductIds=36&RecentlyViewedProductIds=31&RecentlyViewedProductIds=75; __atuvc=12%7C3; __atuvs=6006b0b87dfde00000a; __utmb=78382081.32.10.1611051155";

    public ApiHelper() {
        spec = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(cookie)
                .baseUri(baseUrl);
    }

    public Response getWishListEntities() {
        return given()
                .spec(spec)
                .with()
                .when()
                .log().all()
                .get(wishListUrl + wishListUrl);
    }

    public Response addItemToWishList() {
        return given()
                .body(bodyForAddToWishList)
                .spec(spec)
                .when()
                .log().all()
                .post(addToWhishlist);
    }

//    public Response getSingleUserByNum(int userId) {
//        return given()
//                .spec(spec)
//                .with()
//                .when()
//                .log().all()
//                .get(singleUserUrl + "/" + userId);
//    }
//
//    public Response createUser(String name, String job) {
//        String requestBody = "{\n" +
//                "\"name\": \"" + name + "\",\n" +
//                "\"job\": \"" + job + "\"" +
//                "\n}";
//        return given()
//                .spec(spec)
//                .with()
//                .body(requestBody)
//                .when()
//                .log().all()
//                .post(singleUserUrl)
//                ;
//    }
//
//    public Response updateUser(String name, String job, String userId) {
//        String requestBody = "{\n" +
//                "\"name\": \"" + name + "\",\n" +
//                "\"job\": \"" + job + "\"" +
//                "\n}";
//        return given()
//                .spec(spec)
//                .with()
//                .body(requestBody)
//                .when()
//                .log().all()
//                .post(singleUserUrl + "/" + userId)
//                ;
//    }
//
//    public Response deleteUser(String userId) {
//        return given()
//                .spec(spec)
//                .with()
//                .when()
//                .log().all()
//                .delete(singleUserUrl + "/" + userId)
//                ;
//    }
}

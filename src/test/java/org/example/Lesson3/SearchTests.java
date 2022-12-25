package org.example.Lesson3;

import io.restassured.http.Method;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SpoonacularSearchRecipesTest extends TestClass {
    @Test
    void searchBurgerTest() {
        given()
                .queryParam("query", "burger")
                .queryParam("maxFat", "60")
                .queryParam(getApiKey())
                .when()
                .request(Method.GET, "recipes/complexSearch")
                .then()
                .statusCode(200);
    }

}

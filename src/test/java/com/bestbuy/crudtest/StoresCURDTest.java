package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StoresCURDTest  {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost:3030";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }
    @Test
    public void createStore() {
        StorePojo store = new StorePojo();
        store.setName("Test Store");
        store.setType("BigBox");
        store.setAddress("123 Test St");
        store.setAddress2("Suite 100");
        store.setCity("Test City");
        store.setState("TS");
        store.setZip("12345");
        store.setLat(44.96);
        store.setLng(-93.27);
        store.setHours("Mon: 9-5; Tue: 9-5; Wed: 9-5; Thu: 9-5; Fri: 9-5; Sat: 10-6; Sun: 10-6");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(store)
                .when()
                .post("/stores")
                .then()
                .statusCode(201)
                .extract()
                .response();

        int id = response.path("id");
        System.out.println("Created Store ID: " + id);
    }

    @Test
    public void getStore() {
        int storeId = 8921; // Use the actual ID from the created store

        given()
                .when()
                .get("/stores/" + storeId)
                .then()
                .statusCode(200)
                .body("name", equalTo("Test Store"));
    }

    @Test
    public void updateStore() {
        int storeId = 8921; // Use the actual ID from the created store

        StorePojo store = new StorePojo();
        store.setName("Updated Test Store");
        store.setType("BigBox");
        store.setAddress("123 Updated Test St");
        store.setAddress2("Suite 200");
        store.setCity("Updated Test City");
        store.setState("UT");
        store.setZip("54321");
        store.setLat(44.96);
        store.setLng(-93.27);
        store.setHours("Mon: 8-4; Tue: 8-4; Wed: 8-4; Thu: 8-4; Fri: 8-4; Sat: 9-5; Sun: 9-5");

        given()
                .header("Content-Type", "application/json")
                .body(store)
                .when()
                .put("/stores/" + storeId)
                .then()
                .statusCode(200)
                .body("name", equalTo("Updated Test Store"));
    }

    @Test
    public void deleteStore() {
        int storeId = 8921; // Use the actual ID from the created store

        given()
                .when()
                .delete("/stores/" + storeId)
                .then()
                .statusCode(200);
    }
}

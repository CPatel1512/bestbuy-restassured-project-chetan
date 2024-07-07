package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ProductsCRUDTest  {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost:3030";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }


    @Test
    public void createProduct() {
        ProductPojo product = new ProductPojo();
        product.setName("Test Product");
        product.setType("HardGood");
        product.setPrice(99.99);
        product.setUpc("123456789012");
        product.setShipping(0);
        product.setDescription("This is a test product.");
        product.setManufacturer("Test Manufacturer");
        product.setModel("TP1234");
        product.setUrl("http://testproduct.com");
        product.setImage("http://testproduct.com/image.jpg");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(product)
                .when()
                .post("/products")
                .then()
                .statusCode(201)
                .extract()
                .response();

        int id = response.path("id");
        System.out.println("Created Product ID: " + id);
    }

    @Test
    public void getProduct() {
        int productId = 9999683; // Use the actual ID from the created product

        given()
                .when()
                .get("/products/" + productId)
                .then()
                .statusCode(200)
                .body("name", equalTo("Test Product"));
    }

    @Test
    public void updateProduct() {
        int productId = 9999683; // Use the actual ID from the created product

        ProductPojo product = new ProductPojo();
        product.setName("Updated Test Product");
        product.setType("HardGood");
        product.setPrice(89.99);
        product.setUpc("123456789012");
        product.setShipping(0);
        product.setDescription("This is an updated test product.");
        product.setManufacturer("Updated Manufacturer");
        product.setModel("UTP1234");
        product.setUrl("http://updatedtestproduct.com");
        product.setImage("http://updatedtestproduct.com/image.jpg");

        given()
                .header("Content-Type", "application/json")
                .body(product)
                .when()
                .put("/products/" + productId)
                .then()
                .statusCode(200)
                .body("name", equalTo("Updated Test Product"));
    }

    @Test
    public void deleteProduct() {
        int productId = 9999683; // Use the actual ID from the created product

        given()
                .when()
                .delete("/products/" + productId)
                .then()
                .statusCode(200);
    }
}


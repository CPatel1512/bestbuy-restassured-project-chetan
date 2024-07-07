package com.bestbuy.testsuite;
/**
 * Write the following test inside ProductsExtractionTest class
 * 21. Extract the limit
 * 22. Extract the total
 * 23. Extract the name of 5th product
 * 24. Extract the names of all the products
 * 25. Extract the productId of all the products
 * 26. Print the size of the data list
 * 27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-
 * Pack)
 * 28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-
 * Pack)
 * 29. Get all the categories of 8th products
 * 30. Get categories of the store where product id = 150115
 * 31. Get all the descriptions of all the products
 * 32. Get id of all the all categories of all the products
 * 33. Find the product names Where type = HardGood
 * 34. Find the Total number of categories for the product where product name = Duracell - AA
 * 1.5V CopperTop Batteries (4-Pack)
 * 35. Find the createdAt for all products whose price < 5.49
 * 36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-
 * Pack)”
 * 37. Find the manufacturer of all the products
 * 38. Find the imge of products whose manufacturer is = Energizer
 * 39. Find the createdAt for all categories products whose price > 5.99
 * 40. Find the uri of all the products
 */

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
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
//21. Extract the limit
    @Test
    public void test021(){
       int limit =  response.extract().path("limit");
        System.out.println("limit:" + limit);
    }
// * 22. Extract the total
    @Test
    public void test022(){
        int total = response.extract().path("total");
        System.out.println("total:" + total);
    }
// * 23. Extract the name of 5th product
    @Test
    public void test023(){
        String productName = response.extract().path("data[4].name");
        System.out.println("Fifth Product Name : " + productName);
    }
// * 24. Extract the names of all the products
    @Test
    public void test024(){
        List<String> productNames = response.extract().path("data.name");
        System.out.println("Product Names: " + productNames);
    }
// * 25. Extract the productId of all the products
    @Test
    public void test025(){
        List<Integer> productsId = response.extract().path("data.id");
        System.out.println("Product Ids" + productsId);

    }
// * 26. Print the size of the data list
    @Test
    public void test026(){
        List<String> size  = response.extract().path("data");
        System.out.println("Size of data list " + size);
    }
// * 27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-
// * Pack)
    @Test
    public void test027(){
        List<Map<String, ?>> productValues = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("Product values where name is 'Energizer - MAX Batteries AA (4-Pack)': " + productValues);
    }
// * 28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-
// * Pack)
    @Test
    public void test028(){
        String model = response.extract().path("data.find{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("Model of product where name is 'Energizer - N Cell E90 Batteries (2-Pack)': " + model);
    }
// * 29. Get all the categories of 8th products
    @Test
    public void test029(){

        List<Map<String, ?>> categories = response.extract().path("data[7].categories");
        System.out.println("Categories of 8th product: " + categories);

    }
// * 30. Get categories of the store where product id = 150115
    @Test
    public void test030(){
        List<Map<String, ?>> categories = response.extract().path("data.find{it.id == 150115}.categories");
        System.out.println("Categories where product id is '150115': " + categories);


    }
// * 31. Get all the descriptions of all the products
    @Test
    public void test031(){
        List<String> descriptions = response.extract().path("data.description");
        System.out.println("Descriptions: " + descriptions);
    }
// * 32. Get id of all the all categories of all the products
    @Test
    public void test032(){
        List<Integer> categoryIds = response.extract().path("data.categories.id.flatten()");
        System.out.println("Category IDs: " + categoryIds);
    }
// * 33. Find the product names Where type = HardGood
    @Test
    public void test033(){
        List<String> productNames = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("Product names where type is 'HardGood': " + productNames);

    }
// * 34. Find the Total number of categories for the product where product name = Duracell - AA
// * 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test034(){
        int totalCategories = response.extract().path("data.find{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories.size()");
        System.out.println("Total number of categories for product where name is 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)': " + totalCategories);
    }
// * 35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test035(){
        List<String> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("CreatedAt for products where price < 5.49: " + createdAt);

    }
// * 36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-
// * Pack)”
    @Test
    public void test036(){
        List<String> categoryNames = response.extract().path("data.find{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("Category names where product name is 'Energizer - MAX Batteries AA (4-Pack)': " + categoryNames);

    }
// * 37. Find the manufacturer of all the products
    @Test
    public void test037(){
        List<String> manufacturers = response.extract().path("data.manufacturer");
        System.out.println("Manufacturers: " + manufacturers);
    }
// * 38. Find the imge of products whose manufacturer is = Energizer
    @Test
    public void test038(){

        List<String> images = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("Images of products where manufacturer is 'Energizer': " + images);
    }
// * 39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test039(){
        List<String> createdAt = response.extract().path("data.findAll{it.price > 5.99}.createdAt");
        System.out.println("CreatedAt for products where price > 5.99: " + createdAt);
    }
// * 40. Find the uri of all the products
    @Test
    public void test040(){
        List<Float> prices = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.price");
        System.out.println("Prices where product name is 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)': " + prices);
    }

}

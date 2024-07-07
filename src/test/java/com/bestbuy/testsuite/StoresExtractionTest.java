package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Write the following test inside StoresExtractionTest class.
 * Extraction Example
 * 1. Extract the limit
 * 2. Extract the total
 * 3. Extract the name of 5th store
 * 4. Extract the names of all the store
 * 5. Extract the storeId of all the store
 * 6. Print the size of the data list
 * 7. Get all the value of the store where store name = St Cloud
 * 8. Get the address of the store where store name = Rochester
 * 9. Get all the services of 8th store
 * 10. Get storeservices of the store where service name = Windows Store
 * 11. Get all the storeId of all the store
 * 12. Get id of all the store
 * 13. Find the store names Where state = ND
 * 14. Find the Total number of services for the store where store name = Rochester
 * 15. Find the createdAt for all services whose name = “Windows Store”
 * 16. Find the name of all services Where store name = “Fargo”
 * 17. Find the zip of all the store
 * 18. Find the zip of store name = Roseville
 * 19. Find the storeservices details of the service name = Magnolia Home Theater
 * 20. Find the lat of all the stores
 */

public class StoresExtractionTest {
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

    // 1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");

        System.out.println("The value of limit is :" + limit);
    }

    //2. Extract the total
    @Test
            public void test002(){
    int total = response.extract().path("total");
        System.out.println("The total value is:" + total);
    }
    // * 3. Extract the name of 5th store
    @Test
    public void test003(){
        String storeName = response.extract().path("data[4].name");
        System.out.println("Fifth store Name: " + storeName);

    }
    // * 4. Extract the names of all the store
    @Test
    public void test004(){
        List<String> storeNames = response.extract().path("data.name");
        System.out.println("Store Names: " + storeNames);
    }

    // * 5. Extract the storeId of all the store

    @Test
    public void test005(){
        List<String> storeIds = response.extract().path("data.id");
        System.out.println("Store Ids :" + storeIds);
    }
    // * 6. Print the size of the data list
    @Test
    public void test006(){
        List<String> data = response.extract().path("data");
        System.out.println("The size of data is : " + data.size());
    }
    // * 7. Get all the value of the store where store name == St Cloud
    @Test
    public void test007(){
        List<HashMap<String,?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("The value of Store name 'St Cloud' are: " + values);

    }
    // * 8. Get the address of the store where store name == Rochester
    @Test
            public void test008() {

        List<HashMap<String, ?>> address = response.extract().path("data.findAll{it.name == 'Rochester'}");
        System.out.println("The value of store address is: " + address);
    }

    // * 9. Get all the services of 8th store
    @Test
    public void test009() {

        List<Map<String, ?>> services = response.extract().path("data[7].services");
        System.out.println("Services of 8th store: " + services);

    }
    // * 10. Get storeservices of the store where service name = Windows Store

    @Test
    public void test010(){


        List<Map<String, ?>> storeServices = response.extract().path("data.services.flatten().findAll{it.name == 'Windows Store'}.storeservices");
        System.out.println("Store services where service name is 'Windows Store': " + storeServices);
    }
    // * 11. Get all the storeId of all the store
    @Test
    public void test011(){
        List<Integer> storeIds = response.extract().path("data.id");
        System.out.println("Store IDs: " + storeIds);

    }
    // * 12. Get id of all the store
    @Test
    public void test012(){

        List<Integer> ids = response.extract().path("data.id");
        System.out.println("IDs: " + ids);
    }
    // * 13. Find the store names Where state = ND
    @Test
    public void test013(){
        List<String> storeNames = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("Store names where state is 'ND': " + storeNames);
    }
    // * 14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014(){
        int totalServices = response.extract().path("data.find{it.name == 'Rochester'}.services.size()");
        System.out.println("Total number of services for store where name is 'Rochester': " + totalServices);
    }
    // * 15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015(){
        List<String> createdAt = response.extract().path("data.services.flatten().findAll{it.name == 'Windows Store'}.storeservices.createdAt");
        System.out.println("CreatedAt for services where name is 'Windows Store': " + createdAt);
    }
    // * 16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016(){

        List<String> serviceNames = response.extract().path("data.find{it.name == 'Fargo'}.services.name");
        System.out.println("Service names where store name is 'Fargo': " + serviceNames);
    }
    // * 17. Find the zip of all the store
    @Test
    public void test017(){
        List<String> zips = response.extract().path("data.zip");
        System.out.println("Zips: " + zips);
    }
    // * 18. Find the zip of store name = Roseville
    @Test
    public void test018(){
        String zip = response.extract().path("data.find{it.name == 'Roseville'}.zip");
        System.out.println("Zip where store name is 'Roseville': " + zip);
    }
    // * 19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019(){
        String zip = response.extract().path("data.find{it.name == 'Roseville'}.zip");
        System.out.println("Zip where store name is 'Roseville': " + zip);
    }
    // * 20. Find the lat of all the stores
    @Test
    public void test020(){

        List<Double> lats = response.extract().path("data.lat");
        System.out.println("Lats: " + lats);

    }


}

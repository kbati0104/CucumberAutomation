package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.v85.fetch.model.AuthChallengeResponse;

public class CashWiseAPI {

    @Test
    public void getCategories(){

        Response response=RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(response.statusCode());
        System.out.println(response.asString());
    }



}

package services;
import config.TestContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Setter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AutenticacaoService {

    public Response response;
    @Setter
    private Map<String, String> credentials;

    String baseUrl = "http://localhost:8080";

    public void realizarLogin(String endPoint) {
        String url = baseUrl + endPoint;
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(credentials)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    public void salvarToken(){
        String token = response.jsonPath().getString("jwt_token");
        TestContext.TOKEN = token;
    }


}

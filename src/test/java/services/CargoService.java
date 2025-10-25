package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import config.TestContext;
import io.restassured.response.Response;
import model.CargoModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CargoService {

    private CargoModel cargoModel;
    private final Gson gson = new Gson();
    private JsonSchema schema;

    public CargoService() {
        this.cargoModel = new CargoModel();
    }

    public void setFieldsCargo(String campo, String valor) {
        switch (campo.toLowerCase()) {
            case "nome":
                cargoModel.setNome(valor);
                break;
            case "nivel":
                cargoModel.setNivel(valor);
                break;
        }
    }

    public void createCargo(String endPoint) {
        Response response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + TestContext.TOKEN)
                .body(gson.toJson(this.cargoModel))
                .when()
                .post(endPoint)
                .then()
                .extract().response();

        if (response.getStatusCode() == 201) {
            TestContext.LAST_CREATED_CARGO = gson.fromJson(response.getBody().asString(), CargoModel.class);
        }
        TestContext.LATEST_RESPONSE = response;
    }

    public void deleteCargo(String endPoint) {
        String finalEndpoint = endPoint + "/" + TestContext.LAST_CREATED_CARGO.getId();
        Response response = given()
                .header("Authorization", "Bearer " + TestContext.TOKEN)
                .when()
                .delete(finalEndpoint)
                .then()
                .extract().response();

        TestContext.LATEST_RESPONSE = response;
    }

    public void setContract(String contractFile) throws IOException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        String schemaPath = "/schemas/" + contractFile.replace(" ", "-").toLowerCase() + ".json";
        try (InputStream is = getClass().getResourceAsStream(schemaPath)) {
            if (is == null) {
                throw new IOException("Não foi possível encontrar o arquivo de schema: " + schemaPath);
            }
            this.schema = factory.getSchema(is);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(TestContext.LATEST_RESPONSE.getBody().asString());
        return schema.validate(jsonNode);
    }
}
package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import config.TestContext;
import io.restassured.response.Response;
import model.ColaboradorModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class ColaboradorService {

    private ColaboradorModel colaboradorModel;
    private JsonSchema schema;

    public ColaboradorService() {
        this.colaboradorModel = new ColaboradorModel();
    }

    public void setFieldsColaborador(String campo, String valor) {
        if (valor == null) {
            return;
        }
        switch (campo.toLowerCase()) {
            case "nome":
                if (!valor.isEmpty()) colaboradorModel.setNome(valor);
                break;
            case "raca":
                colaboradorModel.setRaca(valor);
                break;
            case "genero":
                colaboradorModel.setGenero(valor);
                break;
            case "datanascimento":
                colaboradorModel.setDataNascimento(valor);
                break;
            case "dataadmissao":
                colaboradorModel.setDataAdmissao(valor);
                break;
            case "salario":
                colaboradorModel.setSalario(Double.parseDouble(valor));
                break;
            case "possuideficiencia":
                colaboradorModel.setPossuiDeficiencia(Boolean.parseBoolean(valor));
                break;
            case "orientacaosexual":
                colaboradorModel.setOrientacaoSexual(valor);
                break;
            default:
                throw new IllegalArgumentException("Campo desconhecido: " + campo);
        }
    }

    public void setAssociatedEntitiesFromContext() {
        this.colaboradorModel.setDepartamento(TestContext.LAST_CREATED_DEPARTAMENTO);
        this.colaboradorModel.setCargo(TestContext.LAST_CREATED_CARGO);
    }

    public void createColaborador(String endPoint) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("nome", colaboradorModel.getNome());
        requestBody.put("raca", colaboradorModel.getRaca());
        requestBody.put("genero", colaboradorModel.getGenero());
        requestBody.put("dataNascimento", colaboradorModel.getDataNascimento());
        requestBody.put("dataAdmissao", colaboradorModel.getDataAdmissao());
        requestBody.put("salario", colaboradorModel.getSalario());
        requestBody.put("possuiDeficiencia", colaboradorModel.getPossuiDeficiencia());
        requestBody.put("orientacaoSexual", colaboradorModel.getOrientacaoSexual());

        Map<String, Object> departamentoPayload = new HashMap<>();
        departamentoPayload.put("id", colaboradorModel.getDepartamento().getId());
        departamentoPayload.put("nome", colaboradorModel.getDepartamento().getNome());
        requestBody.put("departamento", departamentoPayload);

        Map<String, Object> cargoPayload = new HashMap<>();
        cargoPayload.put("id", colaboradorModel.getCargo().getId());
        cargoPayload.put("nome", colaboradorModel.getCargo().getNome());
        cargoPayload.put("nivel", colaboradorModel.getCargo().getNivel());
        requestBody.put("cargo", cargoPayload);

        Response response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + TestContext.TOKEN)
                .body(requestBody)
                .when()
                .post(endPoint)
                .then()
                .extract().response();

        TestContext.LATEST_RESPONSE = response;
    }

    public void deleteColaborador(String endPoint) {
        String finalEndpoint = endPoint + "/" + this.colaboradorModel.getId();
        Response response = given()
                .header("Authorization", "Bearer " + TestContext.TOKEN)
                .when()
                .delete(finalEndpoint)
                .then()
                .extract().response();

        TestContext.LATEST_RESPONSE = response;
    }

    public void retrieveIdColaborador() {
        long id = TestContext.LATEST_RESPONSE.jsonPath().getLong("id");
        this.colaboradorModel.setId(id);
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
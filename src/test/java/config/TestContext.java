package config;

import io.restassured.response.Response;
import model.CargoModel;
import model.DepartamentoModel;

public class TestContext {
    public static String TOKEN;
    public static Response LATEST_RESPONSE;
    public static DepartamentoModel LAST_CREATED_DEPARTAMENTO;
    public static CargoModel LAST_CREATED_CARGO;
}
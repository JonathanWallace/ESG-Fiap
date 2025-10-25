package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CargoService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CargoSteps {

    private final CargoService cargoService = new CargoService();

    @Dado("que eu tenha os dados de um novo cargo:")
    public void queEuTenhaOsDadosDeUmNovoCargo(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cargoService.setFieldsCargo(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de cargos")
    public void euEnviarARequisicaoParaOEndpointDeCadastroDeCargos(String endPoint) {
        cargoService.createCargo(endPoint);
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de cargos")
    public void euEnviarARequisicaoComOIDParaOEndpointDeCargos(String endPoint) {
        cargoService.deleteCargo(endPoint);
    }

    @E("que o arquivo de contrato esperado para cargo é o {string}")
    public void queOArquivoDeContratoEsperadoParaCargoÉO(String contract) throws IOException {
        cargoService.setContract(contract);
    }

    @E("a resposta da requisição de cargo deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisicaoDeCargoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = cargoService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
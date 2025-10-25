package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.DepartamentoService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DepartamentoSteps {

    private final DepartamentoService departamentoService = new DepartamentoService();

    @Dado("que eu tenha os dados de um novo departamento:")
    public void queEuTenhaOsDadosDeUmNovoDepartamento(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            departamentoService.setFieldsDepartamento(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de departamentos")
    public void euEnviarARequisicaoParaOEndpointDeCadastroDeDepartamentos(String endPoint) {
        departamentoService.createDepartamento(endPoint);
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de departamentos")
    public void euEnviarARequisicaoComOIDParaOEndpointDeDepartamentos(String endPoint) {
        departamentoService.deleteDepartamento(endPoint);
    }

    @E("que o arquivo de contrato esperado para departamento é o {string}")
    public void queOArquivoDeContratoEsperadoParaDepartamentoEO(String contract) throws IOException {
        departamentoService.setContract(contract);
    }

    @E("a resposta da requisição de departamento deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisicaoDeDepartamentoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = departamentoService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
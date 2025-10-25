package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.ColaboradorService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ColaboradorSteps {

    private final ColaboradorService colaboradorService = new ColaboradorService();

    @Dado("que eu tenha os dados de um novo colaborador e associe as dependências do contexto:")
    public void queEuTenhaOsDadosDeUmNovoColaboradorEAssocieAsDependenciasDoContexto(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            colaboradorService.setFieldsColaborador(columns.get("campo"), columns.get("valor"));
        }
        colaboradorService.setAssociatedEntitiesFromContext();
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de colaboradores")
    public void euEnviarARequisicaoParaOEndpointDeCadastroDeColaboradores(String endPoint) {
        colaboradorService.createColaborador(endPoint);
    }

    @Dado("que eu recupere o ID do colaborador criado no contexto")
    public void queEuRecupereOIDDoColaboradorCriadoNoContexto() {
        colaboradorService.retrieveIdColaborador();
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de colaboradores")
    public void euEnviarARequisicaoComOIDParaOEndpointDeColaboradores(String endPoint) {
        colaboradorService.deleteColaborador(endPoint);
    }

    @E("que o arquivo de contrato esperado para colaborador é o {string}")
    public void queOArquivoDeContratoEsperadoParaColaboradorEO(String contract) throws IOException {
        colaboradorService.setContract(contract);
    }

    @E("a resposta da requisição de colaborador deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisicaoDeColaboradorDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = colaboradorService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
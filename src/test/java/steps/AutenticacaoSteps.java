package steps;

import config.TestContext;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import services.AutenticacaoService;

import java.util.HashMap;
import java.util.Map;

public class AutenticacaoSteps {
    AutenticacaoService autenticacaoService = new AutenticacaoService();

    @Dado("que eu tenha as credenciais de um usuário válido")
    public void queEuTenhaAsCredenciaisDeUmUsuárioVálido(DataTable dataTable) {
        Map<String, String> credetials = dataTable.asMap(String.class, String.class);
        autenticacaoService.setCredentials(credetials);
    }

    @Quando("eu enviar a requisição para o endpoint {string} de autenticação")
    public void euEnviarARequisiçãoParaOEndpointDeAutenticação(String endPoint) {
        autenticacaoService.realizarLogin(endPoint);
    }

    @Então("o status code da resposta do login deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, autenticacaoService.response.statusCode());
    }

    @E("o corpo da resposta deve conter um token de autenticação")
    public void oCorpoDaRespostaDeveConterUmTokenDeAutenticação() {
        autenticacaoService.salvarToken();
    }

    @Dado("que eu sou um usuário autenticado")
    public void queEuSouUmUsuarioAutenticado() {
        if (TestContext.TOKEN == null) {
            Map<String, String> credentials = new HashMap<>();
            credentials.put("email", "jonathan233@email.com");
            credentials.put("senha", "124589");

            autenticacaoService.setCredentials(credentials);
            autenticacaoService.realizarLogin("/auth/login");
            autenticacaoService.response.then().statusCode(200);
            autenticacaoService.salvarToken();
        }
    }

}

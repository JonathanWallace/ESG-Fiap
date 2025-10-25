package steps;

import com.google.gson.Gson;
import config.TestContext;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import model.ErrorMessageModel;
import org.junit.Assert;

public class CommonSteps {

    private final Gson gson = new Gson();

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertNotNull("A resposta da API não foi salva no contexto", TestContext.LATEST_RESPONSE);
        Assert.assertEquals(statusCode, TestContext.LATEST_RESPONSE.statusCode());
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
        Assert.assertNotNull("A resposta da API não foi salva no contexto", TestContext.LATEST_RESPONSE);
        ErrorMessageModel errorMessageModel = gson.fromJson(TestContext.LATEST_RESPONSE.asString(), ErrorMessageModel.class);
        Assert.assertEquals(message, errorMessageModel.getMensagem());
    }
}
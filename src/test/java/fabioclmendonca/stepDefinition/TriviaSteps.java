package fabioclmendonca.stepDefinition;

import fabioclmendonca.common.QuestaoTrivia;
import fabioclmendonca.common.TipoQuestao;
import fabioclmendonca.driverManager.DriverManager;
import fabioclmendonca.driverManager.DriverManagerFactory;
import fabioclmendonca.driverManager.DriverType;
import fabioclmendonca.pageObject.TriviaApiPage;
import fabioclmendonca.pageObject.TriviaLandingPage;
import fabioclmendonca.pageObject.TriviaSearchPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

public class TriviaSteps {

    DriverManager driverManager;
    private WebDriver driver;
    private TriviaLandingPage triviaLandingPage;
    private TriviaSearchPage triviaSearchPage;
    private TriviaApiPage triviaApiPage;
    private QuestaoTrivia data;

    @Before
    public void setup(){
        this.driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        this.driver = this.driverManager.getDriver();
        triviaLandingPage = new TriviaLandingPage(driver);
        triviaSearchPage = new TriviaSearchPage(driver);
        triviaApiPage = new TriviaApiPage(driver);
        data = new QuestaoTrivia();
    }

    @After
    public void teardown(){
        this.driverManager.quitDriver();
    }

    @Dado("Eu navego para {string}")
    public void navegoPara(String url){
        this.driver.get(url);
    }

    @Dado("que navego para a página de busca do banco de questões")
    public void navegoParaBusca(){
        this.triviaLandingPage.getBrowseBtn().click();
    }

    @E("digito {string} no campo de busca")
    public void digitoCampoBusca(String busca){
        triviaSearchPage.getSearchFieldTxt().sendKeys(busca);
    }

    @Quando("clico no botão de buscar")
    public void clicoBotaoBusca(){
        triviaSearchPage.getSearchBtn().click();
    }

    @Então("visualizo uma mensagem de erro com o texto {string}")
    public void validoMensagemErro(String mensagem){
        String mensagemErro = triviaSearchPage.getErrorMessage().getText();
        Assert.assertEquals(mensagemErro, mensagem);
    }

    @E("altero o filtro do tipo de busca para {string}")
    public void alterarFiltroTipo(String filtro){
        Select filtroTipo = new Select(triviaSearchPage.getSearchTypeSlc());
        filtroTipo.selectByVisibleText(filtro);
    }

    @Então("Visualizo {string} itens encontrados na lista de questões")
    public void validoMaxItens(String maximoItens){
        Integer p = Integer.parseInt(maximoItens);
        Assert.assertEquals(triviaSearchPage.getTableLinesCount(), p);
    }

    @E("visualizo os botões de passagem de pagina")
    public void visualizoPaginacao(){
        triviaSearchPage.getPaginationSection().isDisplayed();
        triviaSearchPage.getPaginationSection().isEnabled();
    }

    @Dado("que navego para a página de API do banco de questões")
    public void navagoApi(){
        this.triviaLandingPage.getApiBtn().click();
    }

    @E("digito {string} no campo de número de questões")
    public void digitoNumeroQuestoes(String numeroDeQuestoes){
        this.data.setNumeroDeQuestoes(Integer.parseInt(numeroDeQuestoes));
        this.triviaApiPage.getNumberOfQuestionTxt().clear();
        this.triviaApiPage.getNumberOfQuestionTxt().sendKeys(numeroDeQuestoes);
    }

    @E("seleciono a categoria {string}")
    public void selecionoCategoria(String categoria){
        this.data.setCategoria(categoria);
        Select categoriaSlc = new Select(this.triviaApiPage.getCategorySlc());
        categoriaSlc.selectByVisibleText(categoria);
    }

    @E("seleciono a dificuldade {string}")
    public void selecionoDificuldade(String dificuldade){
        this.data.setDificuldade(dificuldade);
        Select dificuldadeSlc = new Select(this.triviaApiPage.getDificultySlc());
        dificuldadeSlc.selectByVisibleText(dificuldade);
    }

    @E("seleciono o tipo {string}")
    public void selecionoTipo(String tipo){
        this.data.setTipo(tipo);
        Select tipoSlc = new Select(this.triviaApiPage.getTypeSlc());
        tipoSlc.selectByVisibleText(tipo);
    }

    @Quando("clico no botão generate API URL")
    public void clicoGerarApi(){
        this.triviaApiPage.getGenerateApiBtn().click();
    }

    @Então("visualizo a URL do serviço gerada")
    public void visualizoUrlGerada(){
        this.triviaApiPage.getGeneratedUrl().isDisplayed();
    }

    @E("executo a chamada ao serviço utilizando a URL gerada validando se dados retornados no JSON conferem com filtros de entrada")
    public void executoChamadaServico(){
        TipoQuestao tipo = TipoQuestao.getTipoQuestaoPorValorTela(this.data.getTipo());
        String url = this.triviaApiPage.getGeneratedUrl().getAttribute("value");
        get(url)
                .then().statusCode(200).assertThat()
                .body("results", hasItem(allOf(hasEntry("category",this.data.getCategoria()))))
                .body("results", hasItem(allOf(hasEntry("difficulty",this.data.getDificuldade().toLowerCase()))))
                .body("results", hasItem(allOf(hasEntry("type",tipo.getValorJson()))))
                .body("results", hasSize(this.data.getNumeroDeQuestoes()));

    }
}

package modulos.produtos;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;
import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach()    {
        // Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        // Vou maximizar a Tela
        this.navegador.manage().window().maximize();

        // Definir um tempo de espera padrao de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegar para a pagina de Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Não e permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoIgualaZero() {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informaraASenha("admin")
                .submeterformularioDeLogin()
                .acessarFormulariodeAdicaoNovoProduto()
                .informarNomeDoProduto("Mackbook Pro")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("preto, branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor maior que 7.000,00")
    public void testNaoEPermitidoRegistrarProdutoAcimaDeSeteMil()   {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informaraASenha("admin")
                .submeterformularioDeLogin()
                .acessarFormulariodeAdicaoNovoProduto()
                .informarNomeDoProduto("IPhone")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("preto, azul")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de 0,01")
    public void testPossoAdicionarProdutosComValorDeUmCentavo()    {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informaraASenha("admin")
                .submeterformularioDeLogin()
                .acessarFormulariodeAdicaoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorDoProduto("001")
                .informarCoresDoProduto("preto")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam com limite de 7.000,00")
    public void testPossoAdicionarProdutosComValorDeSeteMilReais()  {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informaraASenha("admin")
                .submeterformularioDeLogin()
                .acessarFormulariodeAdicaoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorDoProduto("700000")
                .informarCoresDoProduto("preto")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @AfterEach
    public void afterEach() {
        navegador.quit();

    }
}

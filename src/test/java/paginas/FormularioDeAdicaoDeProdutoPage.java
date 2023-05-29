package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeAdicaoDeProdutoPage {
    private WebDriver navegador;
    public FormularioDeAdicaoDeProdutoPage(WebDriver navegador) {
        this.navegador = navegador;
    }
    public FormularioDeAdicaoDeProdutoPage informarNomeDoProduto(String produtoNome)    {
        // Preencher dados do produto e o valor sera igual a zero
        navegador.findElement(By.id("produtonome")).sendKeys(produtoNome);
    return this;
    }
    public FormularioDeAdicaoDeProdutoPage informarValorDoProduto(String produtoValor)  {
        navegador.findElement(By.name("produtovalor")).sendKeys(produtoValor);
    return this;
    }
    public FormularioDeAdicaoDeProdutoPage informarCoresDoProduto(String produtoCores)  {
        navegador.findElement(By.id("produtocores")).sendKeys(produtoCores);
        return this;
    }
    public ListaDeProdutosPage submeterFormularioDeAdicaoComErro()  {
        // Vou submeter o formulario com adicao com erro
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new ListaDeProdutosPage(navegador);
    }
    public FormularioDeEdicaoDeProdutoPage submeterFormularioDeAdicaoComSucesso() {
        // Vou submeter o formulario com adicao com sucesso
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new FormularioDeEdicaoDeProdutoPage(navegador);
    }
}

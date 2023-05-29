package paginas;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver navegador;
     public LoginPage(WebDriver navegador)   {
        this.navegador = navegador;
    }
    public LoginPage informarOUsuario(String usuario)   {
        // Fazer login
        navegador.findElement(By.cssSelector("label[for='usuario']")).click();
        navegador.findElement(By.id("usuario")).sendKeys(usuario);
        // Design Patters do Fluent Pages "Sempre retornar a proxima pagina, nem que seja a atual"
        return this;
    }
    public LoginPage informaraASenha(String senha)  {
        // Submeter o formulario
        navegador.findElement(By.cssSelector("label[for=senha]")).click();
        navegador.findElement(By.id("senha")).sendKeys("admin");
        return this;
    }
    // Quando submeter o formulario e ir para proxima pagina o return muda para return new pagina (navegador)
    public ListaDeProdutosPage submeterformularioDeLogin()  {
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new ListaDeProdutosPage(navegador);
    }
}

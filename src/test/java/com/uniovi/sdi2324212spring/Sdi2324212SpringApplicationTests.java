package com.uniovi.sdi2324212spring;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.openqa.selenium.WebElement;
import com.uniovi.sdi2324212spring.pageobjects.*;
import com.uniovi.sdi2324212spring.util.SeleniumUtils;
import java.util.List;
import org.openqa.selenium.By;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Sdi2324212SpringApplicationTests {
    //static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    static String PathFirefox = "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";
    //static String Geckodriver = "C:\\Users\\ruben\\Desktop\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";
    static String Geckodriver = "C:\\Users\\uo276255\\Desktop\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";
    //static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
//static String Geckodriver = "/Users/USUARIO/selenium/geckodriver-v0.30.0-macos";
// Para la versión de Firefox 121 en adelante la ruta de firefo en MAC es
//static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox";
//Común a Windows y a MACOSX
    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";
    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);

        driver = new FirefoxDriver();
        return driver;
    }
    @BeforeEach
    public void setUp(){
        driver.navigate().to(URL);
    }
    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown(){
        driver.manage().deleteAllCookies();
    }
    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {}
    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }
    @Test
    @Order(1)
    void PR01A() {
        PO_HomeView.checkWelcomeToPage(driver, PO_Properties.getSPANISH());
    }
    @Test
    @Order(2)
    void PR01B() {
        List<WebElement> welcomeMessageElement = PO_HomeView.getWelcomeMessageText(driver,
                PO_Properties.getSPANISH());
        Assertions.assertEquals(welcomeMessageElement.get(0).getText(),
                PO_HomeView.getP().getString("welcome.message", PO_Properties.getSPANISH()));
    }

    @Test
    @Order(3)
    public void PR02() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
    }
    //PR03. Opción de navegación. Pinchar en el enlace Identifícate en la página home
    @Test
    @Order(4)
    public void PR03() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
    }
    @Test
    @Order(5)
    public void PR04() {
        PO_HomeView.checkChangeLanguage(driver, "btnSpanish", "btnEnglish",
                PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
    }

    @Test
    @Order(6)
    public void PR05() {
        //Vamos al formulario de registro
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        //Rellenamos el formulario.
        PO_SignUpView.fillForm(driver, "77777778A", "Josefo", "Perez", "77777", "77777");
        //Comprobamos que entramos en la sección privada y nos nuestra el texto a buscar
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
    @Test
    @Order(7)
    public void PR06A() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "99999990A", "Josefo", "Perez", "77777", "77777");
        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.dni.duplicate",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de DNI repetido.
        String checkText = PO_HomeView.getP().getString("Error.signup.dni.duplicate",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }
    //PR06B. Prueba del formulario de registro. Nombre corto.
// Propiedad: Error.signup.dni.length
    @Test
    @Order(8)
    public void PR06B() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "99999990B", "Jose", "Perez", "77777", "77777");
        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.name.length",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de Nombre corto de nombre corto .
        String checkText = PO_HomeView.getP().getString("Error.signup.name.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    @Test
    @Order(9)
    public void PR07() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "99999990A", "123456");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(10)
    public void PR08() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "99999993D", "123456");
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(11)
    public void PR09() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "99999988F", "123456");
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(12)
    public void PR10() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "99999990A", "1234567");
        String checkText = "Identifícate";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }


    @Test
    @Order(13)
    public void PR11() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "99999990A", "123456");
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
        By boton = By.xpath("/html/body/nav/div/div[2]/ul/li[2]/a/span");
        driver.findElement(boton).click();
    }
    @Test
    @Order(14)
    public void PR12() {
        PO_PrivateView.login(driver, "99999990A", "123456","Notas del usuario");
        //Contamos el número de filas de notas
        List<WebElement> markList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(4, markList.size());
        //Ahora nos desconectamos y comprobamos que aparece el menú de registro
        String loginText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);


    }

    @Test
    @Order(15)
    public void PR13() {
        PO_PrivateView.login(driver, "99999990A", "123456","Notas del usuario");
        //SeleniumUtils.esperarSegundos(driver, 1);
        //Contamos las notas
        By enlace = By.xpath("//td[contains(text(), 'Nota A2')]/following-sibling::*[2]");
        driver.findElement(enlace).click();
        //Esperamos por la ventana de detalle
        String checkText = "Detalles de la nota";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
        //Ahora nos desconectamos comprobamas que aparece el menu de registrarse
        PO_PrivateView.logout(driver);
    }

    @Test
    @Order(16)
    public void PR14() {
        //Vamos al formulario de login.
        PO_PrivateView.login(driver, "99999993D", "123456","99999993D");
        //Pinchamos en la opción de menú de Notas: //li[contains(@id, 'marks-menu')]/a
        PO_PrivateView.searchAndClick(driver, "free", "//li[contains(@id, 'marks-menu')]/a", 0);
        PO_PrivateView.searchAndClick(driver, "free", "//a[contains(@href, 'mark/add')]", 0);
        //Ahora vamos a rellenar la nota. //option[contains(@value, '4')]
        String checkText = "Nota Nueva 1";
        PO_PrivateView.fillFormAddMark(driver, 3, checkText, "8");
        PO_PrivateView.searchAndClick(driver, "free", "//a[contains(@class, 'page-link')]", 4);
        //Comprobamos que aparece la nota en la página
        List<WebElement> elements = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, elements.get(0).getText());
        //Ahora nos desconectamos y comprobamos que aparece el menú de registrarse
        String loginText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);
    }

    @Test
    @Order(17)
    public void PR15() {
        PO_PrivateView.login(driver, "99999993D", "123456","99999993D");
        //Pinchamos en la opción de menú de Notas: //li[contains(@id, 'marks-menu')]/a
        PO_PrivateView.searchAndClick(driver, "free", "//li[contains(@id, 'marks-menu')]/a", 0);
        //Pinchamos en la opción de lista de notas.
        PO_PrivateView.searchAndClick(driver, "free", "//a[contains(@href, 'mark/list')]", 0);
        //Esperamos a que se muestren los enlaces de paginación la lista de notas
        PO_PrivateView.searchAndClick(driver, "free", "//a[contains(@class, 'page-link')]", 4);
        //Esperamos a que aparezca la Nueva nota en la última página
        PO_PrivateView.searchAndClick(driver, "free", "//td[contains(text(), 'Nota Nueva 1')]/following-sibling::*/a[contains(@href, 'mark/delete')]", 0);
        //Volvemos a la última página
        PO_PrivateView.searchAndClick(driver, "free", "//a[contains(@class, 'page-link')]", 4);
        //Y esperamos a que NO aparezca la última "Nueva Nota 1"
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "Nota Nueva 1",PO_View.getTimeout());
        //Ahora nos desconectamos comprobamos que aparece el menú de registrarse
        String loginText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);
    }
}

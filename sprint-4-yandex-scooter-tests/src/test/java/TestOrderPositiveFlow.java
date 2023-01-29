//Задание: Протестировать:
// Заказ самоката. Весь флоу позитивного сценария.
// Обрати внимание, что есть две точки входа в сценарий: кнопка «Заказать» вверху страницы и внизу.
//Из чего состоит позитивный сценарий:
//1. Нажать кнопку «Заказать». На странице две кнопки заказа.
//2. Заполнить форму заказа.
//3. Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.model.MainPage;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.yandex.praktikum.model.OrderCreationForm.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.startsWith;
import org.hamcrest.MatcherAssert;

import static ru.yandex.praktikum.model.MainPage.HOME_PAGE;
import static ru.yandex.praktikum.model.OrderCreationForm.*;


public class TestOrderPositiveFlow {
    private WebDriver driver;
    @Before
    public void startUpOnFirefox() {
        System.setProperty("webdriver.gecko.driver","/Users/daria.pushko/WebDriver/bin/geckodriver");
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /*public void startUpOnChrome() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);*/
    }
    @Test
    public void testOrderCreationHeaderButton() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.open();
        objMainPage.acceptCookies();
        objMainPage.selectHeaderOrderOption();
        Assert.assertEquals(ORDER_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    public void testOrderCreationWithMiddleButton() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.open();
        objMainPage.acceptCookies();
        objMainPage.verticalScrollToElement();
        objMainPage.selectMiddleOrderOption();
        Assert.assertEquals(ORDER_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
        public void testOrderCreationForm() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.open();
        objMainPage.acceptCookies();
        objMainPage.selectHeaderOrderOption();
        objMainPage.fillOrderFormFirstPage();
        objMainPage.fillOrderFormSecondPage();
        objMainPage.fillOrderConfirmation();
        Assert.assertTrue("Заказ не был создан", driver.findElement(CONFIRMED_ORDER_FORM).isDisplayed());
        Assert.assertTrue("Данные заказа отсутсвуют", driver.findElement(CONFIRMED_ORDER_DATA).isDisplayed());
        MatcherAssert.assertThat(driver.findElement(CONFIRMED_ORDER_MESSAGE).getText(), startsWith("Заказ оформлен"));

    }

    @After
    public void tearDown() {

        driver.quit();
    }
}

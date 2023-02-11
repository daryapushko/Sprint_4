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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.model.MainPage;
import org.openqa.selenium.By;
import ru.yandex.praktikum.model.OrderPage;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class TestOrderButtons {
    private WebDriver driver;
    //private final int number;
    private final By orderButtonLocator;
    private final String orderPageUrl;
    // Верхняя кнопка "Заказать"
    private static final By HEADER_ORDER_BUTTON = By.xpath("//div[@class='Header_Nav__AGCXC']/button[text() ='Заказать']");
    // Нижняя кнопка "Заказать"
    private static final By MIDDLE_ORDER_BUTTON = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button[text() ='Заказать']");
    private static final String ORDER_PAGE_URL = "https://qa-scooter.praktikum-services.ru/order";

    public TestOrderButtons(By orderButtonLocator, String orderPageUrl) {
        this.orderButtonLocator = orderButtonLocator;
        this.orderPageUrl = orderPageUrl;
    }
    @Parameterized.Parameters
    public static Object[][] getExpectedButton() {
        return new Object[][]{
                {HEADER_ORDER_BUTTON, ORDER_PAGE_URL},
                {MIDDLE_ORDER_BUTTON, ORDER_PAGE_URL},
        };
    }

    @Before
    public void startUpOnFirefox() {
        System.setProperty("webdriver.gecko.driver", "/Users/daria.pushko/WebDriver/bin/geckodriver");
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
    public void shouldCreateOrderOnAnyOrderButton() {
        MainPage objPage = new MainPage(driver);
        objPage.open();
        objPage.acceptCookiesOnMainPage();
        objPage.verticalScrollToFAQElement();
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.selectOrderButton(orderButtonLocator);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, orderPageUrl);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

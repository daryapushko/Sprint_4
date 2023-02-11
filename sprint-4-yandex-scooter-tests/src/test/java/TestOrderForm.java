import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.model.OrderPage;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class TestOrderForm {
    private WebDriver driver;
    //поля формы заказа
    private final String name;
    private final String lastname;
    private final String city;
    private final String metro;
    private final String phone;
    private final String date;
    private final String rentalPeriod;

    public TestOrderForm(String name, String lastname, String city, String metro, String phone, String date, String rentalPeriod) {
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Ян", "Иванов", "Москва", "Бульвар Рокоссовского", "+7999999999", "28.02.2023", "сутки"},
                {"абдурахмангаджи", "ХРИСТОРОЖДЕСТВЕНСКИЙ", "Кубинка-1", "Лихоборы", "+799999999999", "31.12.2023", "семеро суток"},
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
    public void shouldCreateOrder() {
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.open();
        objOrderPage.acceptCookiesOnOrderPage();
        objOrderPage.fillOrderFirstPage(name, lastname, city, metro, phone);
        objOrderPage.transferToSecondPage();
        objOrderPage.fillOrderSecondPage(date, rentalPeriod);
        objOrderPage.fillOrderConfirmation();
        Assert.assertTrue("Заказ не был создан", objOrderPage.confirmOrder().isDisplayed());
        Assert.assertTrue("Данные заказа отсутсвуют", objOrderPage.confirmOrderData().isDisplayed());
        Assert.assertEquals("Неверный статус", "Заказ оформлен", objOrderPage.getConfirmationMessage().substring(0,14));
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}

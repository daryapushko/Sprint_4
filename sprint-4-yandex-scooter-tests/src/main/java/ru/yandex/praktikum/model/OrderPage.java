package ru.yandex.praktikum.model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    // Форма заказа и ссылка на него
    private static final String ORDER_PAGE_URL = "https://qa-scooter.praktikum-services.ru/order";
    private static final By ORDER_PAGE = By.className("App_App__15LM-");
    private static final By ORDER_FORM = By.className("Order_Form__17u6u");
    //поля формы заказа 1 и 2 страниц
    private static final By NAME_INPUT_FIELD = By.xpath("//input[@placeholder ='* Имя']");
    private static final By LASTNAME_INPUT_FIELD = By.xpath("//input[@placeholder ='* Фамилия']");
    private static final By ADDRESS_INPUT_FIELD = By.xpath("//input[@placeholder ='* Адрес: куда привезти заказ']");
    private static final By METRO_STATION_DROPDOWN = By.className("select-search");
    private static final By PHONE_INPUT_FIELD = By.xpath("//input[@placeholder ='* Телефон: на него позвонит курьер']");
    private static final By NEXT_ORDER_BUTTON = By.xpath("//button[text() ='Далее']");
    private static final By DATE_INPUT_FIELD = By.xpath("//input[@placeholder ='* Когда привезти самокат']");
    private static final By RENTAL_PERIOD_MENU = By.className("Dropdown-arrow");
    //поля раздела подтверждения заказа
    private static final By FORM_ORDER_BUTTON = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text() ='Заказать']");
    private static final By REQUEST_FOR_CONFIRMATION = By.className("Order_Modal__YZ-d3");
    private static final By YES_BUTTON_CONFIRMATION = By.xpath("//button[text() ='Да']");
    private static final By CONFIRMED_ORDER_MESSAGE = By.className("Order_ModalHeader__3FDaJ");
    private static final By CONFIRMED_ORDER_FORM = By.className("Order_Modal__YZ-d3");
    private static final By CONFIRMED_ORDER_DATA = By.className("Order_Text__2broi");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(ORDER_PAGE_URL);
    }

    public void acceptCookiesOnOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(ORDER_PAGE));
        driver.findElement(By.className("App_CookieButton__3cvqF")).click();
    }

    //нажимаем Заказать на главной странице
    public void selectOrderButton(By orderButtonLocator) {
        WebElement orderButton = driver.findElement(orderButtonLocator);
        orderButton.click();
    }

   //заполняем данными обязательные поля страницы №1
   public void fillOrderFirstPage(String name, String lastname, String city, String metro, String phone) {
        driver.findElement(NAME_INPUT_FIELD).clear();
        driver.findElement(NAME_INPUT_FIELD).sendKeys(name);
        driver.findElement(LASTNAME_INPUT_FIELD).clear();
        driver.findElement(LASTNAME_INPUT_FIELD).sendKeys(lastname);
        driver.findElement(ADDRESS_INPUT_FIELD).clear();
        driver.findElement(ADDRESS_INPUT_FIELD).sendKeys(city);
        driver.findElement(METRO_STATION_DROPDOWN).click();
        By metroLocator = By.xpath("//div[@class='Order_Text__2broi' and text()='" + metro + "']");
        driver.findElement(metroLocator).click();
        driver.findElement(PHONE_INPUT_FIELD).clear();
        driver.findElement(PHONE_INPUT_FIELD).sendKeys(phone);
    }

    //ожидаем загрузки страницы №2
    public void transferToSecondPage() {
        driver.findElement(NEXT_ORDER_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(ORDER_FORM));
    }

    //заполняем данными обязательные поля страницы №2
    public void fillOrderSecondPage(String date, String rentalPeriod) {
        driver.findElement(DATE_INPUT_FIELD).click();
        driver.findElement(DATE_INPUT_FIELD).sendKeys(date);
        //вводим количество дней
        driver.findElement(RENTAL_PERIOD_MENU).click();
        By rentalPeriodValue = By.xpath(".//*[contains(text(), '" + rentalPeriod + "') and starts-with(@class, 'Dropdown')]");
        driver.findElement(rentalPeriodValue).click();
        //подтверждаем заказ
        driver.findElement(FORM_ORDER_BUTTON).click();
    }

    //подтверждаем заказ
    public void fillOrderConfirmation() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(REQUEST_FOR_CONFIRMATION));
        driver.findElement(YES_BUTTON_CONFIRMATION).click();
    }

    public WebElement confirmOrder() {
        return driver.findElement(CONFIRMED_ORDER_FORM);
    }

    public WebElement confirmOrderData() {
        return driver.findElement(CONFIRMED_ORDER_DATA);
    }

    public String getConfirmationMessage() {
        return driver.findElement(CONFIRMED_ORDER_MESSAGE).getText();
    }
}

package ru.yandex.praktikum.model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static ru.yandex.praktikum.model.OrderCreationForm.*;

public class MainPage {
    // Ссылка на главную страницу
    private static final String MAIN_PAGE_URL="https://qa-scooter.praktikum-services.ru/";
    // Главная страница
    public static final By HOME_PAGE=By.className("Home_HomePage__ZXKIX");
    //Подзаголовок "Вопросы о важном"
    public static final By FAQ_SUBHEADER=By.className("Home_SubHeader__zwi_E");


    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    //открыть главную страницу тестируемого сайта Яндеккс Самокат
    public MainPage open() {
        driver.get(MAIN_PAGE_URL);
        return this;
    }
    //принять условия и закрыть секцию с куками
    public void acceptCookies(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(HOME_PAGE));
        driver.findElement(By.className("App_CookieButton__3cvqF")).click();
    }
    //прокрутить до раздела с вопросами "О важном"
    public void verticalScrollToElement(){
        WebElement element_to_scroll = driver.findElement(FAQ_SUBHEADER);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element_to_scroll);

    }

    public void selectHeaderOrderOption(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(HOME_PAGE));
        driver.findElement(HEADER_ORDER_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(ORDER_FORM));
    }

    public void selectMiddleOrderOption() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(HOME_PAGE));
        driver.findElement(MIDDLE_ORDER_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(ORDER_FORM));
    }

    public void fillOrderFormFirstPage() {
        driver.findElement(NAME_INPUT_FIELD).clear();
        driver.findElement(NAME_INPUT_FIELD).sendKeys("Иван");
        driver.findElement(LASTNAME_INPUT_FIELD).clear();
        driver.findElement(LASTNAME_INPUT_FIELD).sendKeys("Иванов");
        driver.findElement(ADDRESS_INPUT_FIELD).clear();
        driver.findElement(ADDRESS_INPUT_FIELD).sendKeys("Москва");
        //driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]")).isEnabled();
        driver.findElement(METRO_STATION_DROPDOWN).click();
        driver.findElement(METRO_STATION_OPTION).click();

        driver.findElement(PHONE_INPUT_FIELD).clear();
        driver.findElement(PHONE_INPUT_FIELD).sendKeys("+7999999999");
        driver.findElement(NEXT_ORDER_BUTTON).click();
        //ожидаем загрузки страницы 2
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Form__17u6u")));
    }

    public void fillOrderFormSecondPage(){
        driver.findElement(DATE_INPUT_FIELD).click();
        driver.findElement(DATE_INPUT_FIELD).sendKeys("25.02.2023");
        //вводим количество дней
        driver.findElement(RENTAL_PERIOD_MENU).click();
        driver.findElement(RENTAL_PERIOD_1_DAY).click();
        //подтверждаем заказ
        driver.findElement(FORM_ORDER_BUTTON).click();
    }
    public void fillOrderConfirmation(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(REQUEST_FOR_CONFIRMATION));
        driver.findElement(YES_BUTTON_CONFIRMATION).click();
    }
}

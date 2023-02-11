package ru.yandex.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    // Ссылка на главную страницу
    private static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    // Главная страница
    private static final By HOME_PAGE = By.className("Home_HomePage__ZXKIX");
    //Элемент секции "О важном"
    private static final By FAQ_ITEM = By.className("accordion__item");
    //Кнопка вопроса "О важном"
    private static final By FAQ_QUESTION_BUTTON = By.className("accordion__heading");
    //Текст ответа "О важном"
    private static final By FAQ_ANSWER_SECTION_TEXT = By.tagName("p");
    //Секция видимого ответа "О важном"
    private static final By FAQ_VISIBLE_ANSWER_SECTION = By.xpath("//div[@class='accordion__panel' and not(@hidden)]");
    //Подзаголовок "Вопросы о важном"
    private static final By FAQ_SUBHEADER = By.className("Home_SubHeader__zwi_E");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //открыть главную страницу тестируемого сайта Яндеккс Самокат
    public void open() {
        driver.get(MAIN_PAGE_URL);
    }

    //принять условия и закрыть секцию с куками
    public void acceptCookiesOnMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(HOME_PAGE));
        driver.findElement(By.className("App_CookieButton__3cvqF")).click();
    }

    //прокрутить до раздела с вопросами "О важном"
    public void verticalScrollToFAQElement() {
        WebElement element_to_scroll = driver.findElement(FAQ_SUBHEADER);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element_to_scroll);
    }

    //получить элемент списка секции "О важном"
    private WebElement getFAQElement(int number) {
        List<WebElement> listFAQ = driver.findElements(FAQ_ITEM);
        return listFAQ.get(number);
    }

    //нажать на вопрос секции "О важном"
    public void openQuestionOnClick(int number) {
        WebElement item = getFAQElement(number);
        WebElement questionElement = item.findElement(FAQ_QUESTION_BUTTON);
        questionElement.click();
    }

    //получить текст вопроса секции "О важном"
    public String getQuestionText(int number) {
        WebElement item = getFAQElement(number);
        WebElement questionElement = item.findElement(FAQ_QUESTION_BUTTON);
        return questionElement.getText();
    }

    //получить текст ответа секции "О важном"
    public String getAnswerText(int number) {
        WebElement item = getFAQElement(number);
        WebElement answerElement = item.findElement(FAQ_ANSWER_SECTION_TEXT);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(answerElement));
        return answerElement.getText();
    }

    //получить количество открытых ответов секции "О важном"
    public int sizeOfFAQ() {
        return driver.findElements(FAQ_VISIBLE_ANSWER_SECTION).size();
    }
}
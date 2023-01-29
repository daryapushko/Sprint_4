//Задание: Протестировать:
// Выпадающий список в разделе «Вопросы о важном».
// Тебе нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.model.MainPage;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.notNullValue;
import static ru.yandex.praktikum.model.AccordionFAQ.*;
import static ru.yandex.praktikum.model.AccordionFAQ.expectedAccordion;
import org.hamcrest.MatcherAssert;

public class TestFAQFunctionality {
    private WebDriver driver;

    @Before
    public void startUpOnChrome() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }
    /*@Before
    //Для запуска на другом браузере, разкомментируйте эту секцию и закоментируйте предыдущую секцию c аннотацией Before
    public void startUpOnFirefox() {
        System.setProperty("webdriver.gecko.driver","/Users/daria.pushko/WebDriver/bin/geckodriver");
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }*/


    //Проверить демонстрацию одного ответа при нажатии на кнопку вопроса
    @Test
    public void openFAQSectionOnClick() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.open();
        objMainPage.acceptCookies();
        objMainPage.verticalScrollToElement();

        List<WebElement> listFAQ = driver.findElements(FAQ_ITEM);
        /*В цикле нажимаем на каждую секции вопроса и проверяем:
        - открытие одного и только одного ответа,
        - наличие его значения (что ответ не пустой)*/
        for (WebElement question : listFAQ ){
            WebElement questionElement = question.findElement(FAQ_QUESTION_BUTTON);
            questionElement.click();
            WebElement answerElement = question.findElement(FAQ_ANSWER_SECTION);
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOf(answerElement));
            Assert.assertTrue(answerElement.isDisplayed());
            MatcherAssert.assertThat(FAQ_VISIBLE_ANSWER_SECTION, notNullValue());
            Assert.assertEquals(1, driver.findElements(FAQ_VISIBLE_ANSWER_SECTION).size());
                }
            }


    //Проверить соответствие вопроса и ответа ожидаемому набору данных
    @Test
    public void openCheckAnswersForQuestions() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.open();
        objMainPage.acceptCookies();
        objMainPage.verticalScrollToElement();

        //Создаем двумерный массив строк для хранения 8 пар: вопрос-ответов блока FAQ
        String[][] listOfList = new String[8][2];
        List<WebElement> listFAQ = driver.findElements(FAQ_ITEM);
        //В цикле находим значения ответа соответстующего вопросу и сравниваем с ожидаемым набором значений
        for (int i = 0; i < listFAQ.size(); i++) {

            WebElement item = listFAQ.get(i);
            WebElement questionElement = item.findElement(FAQ_QUESTION_BUTTON);
            String questionText = questionElement.getText();
            questionElement.click();
            WebElement answerElement = item.findElement(FAQ_ANSWER_SECTION_TEXT);
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOf(answerElement));
            String answerText = answerElement.getText();
            listOfList[i][0] = questionText;
            listOfList[i][1] = answerText;
        }
        Assert.assertEquals("Ошибка в соответствии вопроса и ответа по индексу", expectedAccordion, listOfList);
    }

    @After
    public void tearDown() {

        driver.quit();
    }
}
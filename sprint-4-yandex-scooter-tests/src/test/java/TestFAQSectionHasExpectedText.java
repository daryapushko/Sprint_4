//Задание: Протестировать:
// Выпадающий список в разделе «Вопросы о важном».
// Тебе нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.model.MainPage;

@RunWith(Parameterized.class)
public class TestFAQSectionHasExpectedText {
    private WebDriver driver;
    //порядковый номер секции "О важном"
    private final int number;
    //проверяемый вопрос
    private final String checkedQuestionText;
    //проверяемый ответ
    private final String checkedAnswerText;

    public TestFAQSectionHasExpectedText(int number, String checkedQuestionText, String checkedAnswerText) {
        this.number = number;
        this.checkedQuestionText = checkedQuestionText;
        this.checkedAnswerText = checkedAnswerText;
    }
    @Parameterized.Parameters
    public static Object[][] getExpectedText() {
            return new Object[][]{
        // ожидаемая таблица значений секции FAQ
        {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
        {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
        {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
        {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
        {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
        {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
        {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
        {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
    };
}

    @Before
    public void startUpOnChrome() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        /*Для запуска на другом браузере, разкомментируйте эту секцию и закоментируйте предыдущую секцию c аннотацией Before
    public void startUpOnFirefox() {
        System.setProperty("webdriver.gecko.driver","/Users/daria.pushko/WebDriver/bin/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);*/
    }

    //Проверить открытие секции ответа по клику на вопрос, соответствие вопроса и ответа ожидаемому набору данных
   @Test
    public void shouldCheckFAQContent() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.open();
        objMainPage.acceptCookiesOnMainPage();
        objMainPage.verticalScrollToFAQElement();
        objMainPage.openQuestionOnClick(number);
        String questionText = objMainPage.getQuestionText(number);
        String answerText = objMainPage.getAnswerText(number);
        Assert.assertEquals(1, objMainPage.sizeOfFAQ());
        Assert.assertEquals("Показан неверный вопрос", checkedQuestionText, questionText);
        Assert.assertEquals("Показан неверный ответ", checkedAnswerText, answerText);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
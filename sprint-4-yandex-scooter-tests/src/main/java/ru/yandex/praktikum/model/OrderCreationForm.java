package ru.yandex.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderCreationForm {
    private WebDriver driver;
    //Страница с формой заказа
    public static final String ORDER_PAGE_URL="https://qa-scooter.praktikum-services.ru/order";
    // Верхняя кнопка "Заказать"
    public static final By HEADER_ORDER_BUTTON=By.className("Button_Button__ra12g");
    // Нижняя кнопка "Заказать"
    public static final By MIDDLE_ORDER_BUTTON=By.xpath("//button[text() ='Заказать']");
    // Форма заказа
    public static final By ORDER_FORM=By.className("Order_Form__17u6u");
    public static final By NAME_INPUT_FIELD=By.xpath("//input[@placeholder ='* Имя']");
    public static final By LASTNAME_INPUT_FIELD=By.xpath("//input[@placeholder ='* Фамилия']");
    public static final By ADDRESS_INPUT_FIELD=By.xpath("//input[@placeholder ='* Адрес: куда привезти заказ']");
    public static final By METRO_STATION_DROPDOWN=By.className("select-search");
    public static final By METRO_STATION_OPTION=By.className("select-search__select");
    public static final By PHONE_INPUT_FIELD=By.xpath("//input[@placeholder ='* Телефон: на него позвонит курьер']");
    public static final By NEXT_ORDER_BUTTON=By.xpath("//button[text() ='Далее']");
    public static final By DATE_INPUT_FIELD=By.xpath("//input[@placeholder ='* Когда привезти самокат']");
    public static final By RENTAL_PERIOD_MENU=By.className("Dropdown-arrow");
    public static final By RENTAL_PERIOD_1_DAY=By.className("Dropdown-option");
    public static final By FORM_ORDER_BUTTON=By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text() ='Заказать']");
    public static final By REQUEST_FOR_CONFIRMATION=By.className("Order_Modal__YZ-d3");
    public static final By YES_BUTTON_CONFIRMATION=By.xpath("//button[text() ='Да']");
    public static final By CONFIRMED_ORDER_MESSAGE=By.className("Order_ModalHeader__3FDaJ");
    public static final By CONFIRMED_ORDER_FORM=By.className("Order_Modal__YZ-d3");
    public static final By CONFIRMED_ORDER_DATA=By.className("Order_Text__2broi");
}

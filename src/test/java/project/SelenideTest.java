package project;

import com.codeborne.selenide.*;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

  String firstName = "firstName";
  String lastName = "lastName";
  String email = "test@email.kz";
  String mobileNumber = "1234567890";
  String address = "Улица Пушкина, дом Колотушкина";
  String fileName = "test.png";
  String state = "NCR";
  String city = "Delhi";
  //String subject = "Biology";

  @BeforeAll
  static void setup(){
    Configuration.startMaximized = true;
  }

  @Test
  void demoQaTest() throws InterruptedException {
    open("https://demoqa.com/automation-practice-form");
    $x("//input[@id='firstName']").setValue(firstName);
    $x("//input[@id='lastName']").setValue(lastName);
    $x("//input[@id='userEmail']").setValue(email);
    $x("//label[@for='gender-radio-1']").click();
    $x("//input[@id='userNumber']").setValue(mobileNumber);
    $x("//input[@id='dateOfBirthInput']").click();
    $x("//select[@class='react-datepicker__month-select']").selectOptionContainingText("July");
    $x("//select[@class='react-datepicker__year-select']").selectOptionContainingText("2010");
    $x("//div[contains(@class, 'react-datepicker__day--014')]").click();
    $x("//label[@for='hobbies-checkbox-2' and text()='Reading']").click();

    // к сожалению не получилось победить subject. Смотрел решения других ребят. Их локаторы тоже не подходят и
    // не работают на маке.
    //$x("//input[@id='subjectsInput']").setValue(subject);
    //$("#subjectsInput").click();
    //$("#subjectsInput").val(subject);
    //$$("div[id^=\"react-select-2-option\"]").find(text(subject)).click();
    //$x("//div[text()='English']").click();

    $x("//input[@id='uploadPicture']")
            .uploadFile(new File("src/test/resources/" + fileName));
    $x("//textarea[@id='currentAddress']").setValue(address);
    $("#state").scrollTo().click();
    $(byText(state)).click();
    $("#city").click();
    $(byText(city)).click();

    $("#subjectsInput").val("e");

    $x("//button[@id='submit']").click();

    $x("//td[text()='Student Name']/following-sibling::td[1]")
            .shouldHave(text(firstName + " " + lastName));
    $x("//td[text()='Student Email']/following-sibling::td[1]")
                .shouldHave(text(email));
    $x("//td[text()='Gender']/following-sibling::td[1]")
                .shouldHave(text("Male"));
    $x("//td[text()='Mobile']/following-sibling::td[1]")
                .shouldHave(text(mobileNumber));
    $x("//td[text()='Date of Birth']/following-sibling::td[1]")
                .shouldHave(text("14 July,2010"));
    $x("//td[text()='Hobbies']/following-sibling::td[1]")
                    .shouldHave(text("Reading"));
    $x("//td[text()='Picture']/following-sibling::td[1]")
                        .shouldHave(text(fileName));
    $x("//td[text()='Address']/following-sibling::td[1]")
            .shouldHave(text(address));
    $x("//td[text()='State and City']/following-sibling::td[1]")
                .shouldHave(text(state + " " + city));
  }

  @Test
  void successfulRegistrationTest() {
    Faker faker = new Faker();
    FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String userEmail = fakeValuesService.bothify("????##@gmail.com");
    String userNumber = fakeValuesService.regexify("[0-9]{10}");
    String address = faker.address().fullAddress();
    String subject = "Biology";
    String gender = "Male";
    String hobbies = "Sports";
    String date = "03 December,2020";
    String state = "NCR";
    String city = "Delhi";

    open("https://demoqa.com/automation-practice-form");
    $("#firstName").val(firstName);
    $("#lastName").val(lastName);
    $("#userEmail").val(userEmail);
    $x("//*[@for='gender-radio-1']").click();
    $("#userNumber").val(userNumber);
    $("#dateOfBirthInput").click();
    $(".react-datepicker__day--003").click();
    $("#subjectsInput").click();
    $("#subjectsInput").val(subject);
    $$("div[id^=\"react-select-2-option\"]").find(text(subject)).click();
    $x("//*[@for='hobbies-checkbox-1']").click();

    $("#currentAddress").val(address);
    $("#state").scrollTo().click();
    $(byText(state)).click();
    $("#city").click();
    $(byText(city)).click();
    $("#submit").scrollTo().click();

    $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    $x("*//tr[1]/td[2]").shouldHave(text(firstName + " " + lastName));
    $x("*//tr[2]/td[2]").shouldHave(text(userEmail));
    $x("*//tr[3]/td[2]").shouldHave(text(gender));
    $x("*//tr[4]/td[2]").shouldHave(text(userNumber));
    $x("*//tr[5]/td[2]").shouldHave(text(date));
    $x("*//tr[6]/td[2]").shouldHave(text(subject));
    $x("*//tr[7]/td[2]").shouldHave(text(hobbies));
    $x("*//tr[9]/td[2]").shouldHave(text(address));
    $x("*//tr[10]/td[2]").shouldHave(text(state + " " + city));
  }

}

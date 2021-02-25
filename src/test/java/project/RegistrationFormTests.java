package project;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTests {

    String firstName = "firstName";
    String lastName = "lastName";
    String email = "test@email.kz";
    String mobileNumber = "1234567890";
    String address = "Тестовый адрес. Тестовый город. Тестовый дом.";
    String fileName = "test.png";
    String state = "NCR";
    String city = "Delhi";
    String subject = "Biology";

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void checkAllFormsFieldsTest() {
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

        $("#subjectsInput").click();
        $("#subjectsInput").val(subject);
        $$("div[id^=\"react-select-2-option\"]").find(text(subject)).click();

        $x("//input[@id='uploadPicture']")
                .uploadFile(new File("src/test/resources/" + fileName));
        $x("//textarea[@id='currentAddress']").setValue(address);
        $("#state").scrollTo().click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();

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
        $x("//td[text()='Subjects']/following-sibling::td[1]")
                .shouldHave(text(subject));
        $x("//td[text()='State and City']/following-sibling::td[1]")
                .shouldHave(text(state + " " + city));
    }
}
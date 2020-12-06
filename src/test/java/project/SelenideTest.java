package project;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;

import java.io.File;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

  String firstName = "firstName";
  String lastName = "lastName";
  String email = "test@email.kz";
  String mobileNumber = "1234567890";
  String address = "Улица Пушкина, дом Колотушкина";
  String subject = "English";


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
    //$x("//input[@id='subjectsInput']").setValue(subject);
    $("#subjectsInput").setValue(subject);
    $x("//div[text()='English']").click();
    $x("//input[@id='uploadPicture']").uploadFile(new File("src/test/resources/test.png"));
    $x("//textarea[@id='currentAddress']").setValue(address);
    $x("//div[text()='NCR']").selectOptionContainingText("Haryana");
    $x("//div[text()='Select City']").selectOptionContainingText("Panipat");
    $x("//button[@id='submit']").click();
    //Thread.sleep(20000);

  }
}

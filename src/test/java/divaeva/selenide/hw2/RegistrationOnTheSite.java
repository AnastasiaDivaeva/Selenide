package divaeva.selenide.hw2;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class RegistrationOnTheSite {
    public static void main(String[] args) {
        openSite();
        findRegistrationButton();
        fillInRegistrationInfo();
        submitRegistration();
        SelenideElement registerSMS = $x("//h3[text()='СМС-підтвердження']")
                .shouldBe(Condition.visible);
        String registerSMSText = registerSMS.getText();
        Assert.assertEquals(registerSMSText, "СМС-підтвердження");
        Selenide.closeWebDriver();
    }

    private static void openSite() {
        Selenide.open(TestUtils.STARY_LEV_URL);
        getWebDriver().manage().window().maximize();
    }

    private static void findRegistrationButton() {
        $x("//button[text()='Вхід | Реєстрація']")
                .should(Condition.interactable)
                .click();
        $x("//button[text()='Зареєструватися']")
                .shouldBe(Condition.interactable)
                .click();
    }

    private static void fillInRegistrationInfo() {
        $("div >input[name='firstName']").setValue("Лера");
        $(By.name("lastName")).setValue("Секач");
        $(By.name("email")).setValue("sekach873@gmail.com");
        $(By.name("phone")).setValue("380953005787");
        $x("//input[@name='password']").setValue("qazwsxedc");
        $(By.className("ant-checkbox-input")).click();
    }

    private static void submitRegistration() {
        $x("//button[text()='Зареєструватись']").click();
    }
}

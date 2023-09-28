package divaeva.selenide.hw2;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ExitStaryLev {
    public static void main(String[] args) {
        openSite();
        doLogin();
        openMyAccount();
        logoutFromMyAccount();
        SelenideElement buttonLoginAfterExit = $x("//button[text()='Вхід | Реєстрація']");
        Assert.assertEquals(buttonLoginAfterExit.getText(), "Вхід | Реєстрація");
        Selenide.closeWebDriver();
    }

    private static void openSite() {
        Selenide.open(TestUtils.STARY_LEV_URL);
        getWebDriver().manage().window().maximize();
    }

    private static void doLogin() {
        $x("//button[text()='Вхід | Реєстрація']").shouldBe(Condition.visible).click();
        $x("//input[@name='email']").click(ClickOptions.usingDefaultMethod())
                .setValue(TestUtils.USERNAME);
        $x("//input[@name='password']").click(ClickOptions.usingDefaultMethod())
                .setValue(TestUtils.PASSWORD);
        $x("//button[text()='Увійти']").click();
    }

    private static void openMyAccount() {
        $x("//a[text()='Мій кабінет']").click();
    }

    private static void logoutFromMyAccount() {
        $x("//button[text()='Вихід']").click();
    }
}

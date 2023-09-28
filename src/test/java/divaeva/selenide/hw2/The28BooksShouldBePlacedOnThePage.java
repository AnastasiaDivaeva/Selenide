package divaeva.selenide.hw2;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class The28BooksShouldBePlacedOnThePage {
    public static void main(String[] args) {
        openSite();
        doLogin();
        searchBooks();
        ElementsCollection books = getBooksElements();
        Assert.assertEquals(books.size(), 28);
        Selenide.closeWebDriver();
    }

    private static void openSite() {
        Selenide.open(TestUtils.STARY_LEV_URL);
        getWebDriver().manage().window().maximize();
    }

    private static void doLogin() {
        $x("//button[text()='Вхід | Реєстрація']").click();
        $x("//input[@name='email']").click(ClickOptions.usingDefaultMethod())
                .setValue(TestUtils.USERNAME);
        $x("//input[@name='password']").click(ClickOptions.usingDefaultMethod())
                .setValue(TestUtils.PASSWORD);
        $x("//button[text()='Увійти']").click();
    }

    private static void searchBooks() {
        $x("//a[@class='gilroy-h4']").shouldBe(Condition.visible).click();
    }

    private static ElementsCollection getBooksElements() {
        SelenideElement booksCatalog = $x("//div[contains(@class, 'catalogue-products')]").shouldBe(Condition.visible);
        return booksCatalog.$$x(".//div[contains(@class, 'product-card')]");
    }
}


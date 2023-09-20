package divaeva.selenide.hw2;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SearchBookByTittle {
    public static void main(String[] args) {
        openSite();
        doLogin();
        prepareSearchQuery();
        ElementsCollection searchResult = submitSearchQuery();

        Assert.assertEquals(searchResult.size(), 4);
        for (SelenideElement element : searchResult) {
            String resultText = element.getText();
            Assert.assertTrue(resultText.contains("Тема для медитації"));
        }
        Selenide.closeWebDriver();
    }

    private static void openSite() {
        Selenide.open(TestUtils.STARY_LEV_URL);
    }

    private static void doLogin() {
        $x("//button[text()='Вхід | Реєстрація']").should(Condition.interactable).click();
        SelenideElement emailInput = $x("//input[@name='email']");
        emailInput.click();
        emailInput.setValue(TestUtils.USERNAME);
        SelenideElement passwordInput = $x("//input[@name='password']");
        passwordInput.click();
        passwordInput.setValue(TestUtils.PASSWORD);
        $x("//button[text()='Увійти']").click();
    }

    private static void prepareSearchQuery() {
        $x("//input[@placeholder='Шукати...']").setValue("Тема для медитації");
    }

    private static ElementsCollection submitSearchQuery() {
        $x("//a[ text()='Переглянути більше']").shouldBe(Condition.visible).click();
        SelenideElement allResults = $(By.className("Search_search__results__YQ7KH"));
        return allResults
                .$$x(".//div[contains(@class, 'Search_search__result-item__7')]")
                .shouldBe(CollectionCondition.sizeGreaterThan(0));
    }
}

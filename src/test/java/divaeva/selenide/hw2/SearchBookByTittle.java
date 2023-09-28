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
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SearchBookByTittle {
    public static void main(String[] args) {
        int expectedSearchResultsCount = 4;
        openSite();
        doLogin();
        prepareSearchQuery();
        submitSearchQuery();
        ElementsCollection searchResult = getSearchResult();
        Assert.assertEquals(searchResult.size(), expectedSearchResultsCount);
        for (SelenideElement element : searchResult) {
            String resultText = element.getText();
            Assert.assertTrue(resultText.contains("Тема для медитації"));
        }
        Selenide.closeWebDriver();
    }

    private static void openSite() {
        Selenide.open(TestUtils.STARY_LEV_URL);
        getWebDriver().manage().window().maximize();
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
        SelenideElement searchInput = $x("//input[@placeholder='Шукати...']").setValue("Тема для медитації");
    }

    private static void submitSearchQuery() {
        $x("//a[ text()='Переглянути більше']").shouldBe(Condition.visible).click();
    }

    private static ElementsCollection getSearchResult() {
        SelenideElement allResults = $(By.className("Search_search__results__YQ7KH"));
        return allResults
                .$$x(".//div[contains(@class, 'Search_search__result-item__7')]")
                .shouldBe(CollectionCondition.sizeGreaterThan(0));
    }
}

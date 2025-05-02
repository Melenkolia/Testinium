package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MainPage extends PageMethodConfiguration {
    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    private final String mainPageLocalator ="[alt='Beymen']";
    private final String acceptCookiesLocalator = "onetrust-accept-btn-handler";
    private final String genderManLocalator = "genderManButton";
    private final String searchWrapperLocalator = "//div[@class='o-header__search bwi-search-o']//input[@class='o-header__search--input']";
    private final String exitIconLocalator = ".o-header__search--close";
    private final String getSearchWrapperLocalator2 = "//input[@id='o-searchSuggestion__input']";
    private final String searchTitleLocalator = "//span[.='ARAMA ÖNERİLERİ']";
    private final String accountIconLocalator = ".icon.icon-account";
    private final String favoriteIconLocalator = "//a[@title='Favorilerim']//*[name()='svg']";
    private final String cartIconLocalator = ".icon-cart";
    private final String liveSupportIconLocalator = "//a[@class='live-support']";
    private final String languageTopHeaderLocalator = "//span[@class='o-headerTop__language']";
    private final String kampanyalarTopHeaderLocalator = "//a[contains(text(),'Kampanyalar')]";

    public void verifyMainPageCheck(){

        isDisplayed(By.cssSelector(mainPageLocalator));
        isDisplayed(By.cssSelector(accountIconLocalator));
        isDisplayed(By.xpath(favoriteIconLocalator));
        isDisplayed(By.cssSelector(cartIconLocalator));
        isDisplayed(By.xpath(searchWrapperLocalator));
//        isDisplayed(By.xpath(liveSupportIcon));
        isDisplayed(By.xpath(languageTopHeaderLocalator));
        isDisplayed(By.xpath(kampanyalarTopHeaderLocalator));

        String expectedUrl = "https://www.beymen.com/tr";
        String actualUrl = webDriver.getCurrentUrl();
        assertEquals("Unexpected URL!", expectedUrl, actualUrl);

    }
    public void acceptCookies(){ click(By.id(acceptCookiesLocalator));}
    public void selectGenderFromPopUp() { click(By.id(genderManLocalator)); }
    public void searchFirstText() throws IOException {
        find(By.xpath(searchWrapperLocalator)).sendKeys(readExcelData("src/main/resources/testdata/Beymen.xlsx", "Sheet1", 0, 0));
        waitInSeconds(1);
    }
    public void clearSearchBox() { click(By.cssSelector(exitIconLocalator));
        waitInSeconds(2);
    }
    public void searchSecondText() throws IOException {
        WebElement searchBox = find(By.xpath(getSearchWrapperLocalator2));

        String secondText = readExcelData("src/main/resources/testdata/Beymen.xlsx", "Sheet1", 0, 1);
        searchBox.sendKeys(secondText);

        searchBox.sendKeys(Keys.ENTER);

        waitInSeconds(1);

    }
    public void searchTitleDisplayed(){
        isDisplayed(By.xpath(searchTitleLocalator));
    }

}

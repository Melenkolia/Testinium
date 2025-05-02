package Test;

import Pages.MainPage;
import Pages.ProductPage;
import Pages.SearchPage;
import Pages.ShoppingBasketPage;
import Utils.TestBase;
import org.junit.After;
import org.junit.Test;
import java.io.IOException;

public class BeymenTest extends TestBase {

    private MainPage mainPage;
    private SearchPage searchPage;
    private ProductPage productPage;
    private ShoppingBasketPage shoppingBasketPage;

    @Test
    public  void beymenTest() throws IOException {
        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.selectGenderFromPopUp();
        mainPage.verifyMainPageCheck();
        mainPage.searchFirstText();
        mainPage.searchTitleDisplayed();
        mainPage.clearSearchBox();
        mainPage.searchSecondText();

        searchPage = new SearchPage(driver);
        searchPage.selectRandomProduct();

        productPage = new ProductPage(driver);
        productPage.writeProductInfoToFile("ProductDetail.txt");
        productPage.selectRandomVisibleSize();
        productPage.scrollDownPage();
        productPage.click();
        productPage.addBasket();
        productPage.scrollUpPage();
        productPage.clickBasketIcon();

        shoppingBasketPage = new ShoppingBasketPage(driver);
        shoppingBasketPage.waitShoppingBasketPage();
        shoppingBasketPage.verifyShoppingBasketPage();
        shoppingBasketPage.verifyProductPrice();
        shoppingBasketPage.selectProductAmount();
        shoppingBasketPage.removeCartItemButton();
        shoppingBasketPage.verifyNullBasket();

    }
    @After
    public void  tearDown(){
        //webDriver.close();
    }
}

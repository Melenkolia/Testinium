package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Pages.SearchPage.productPrice;

public class ShoppingBasketPage extends PageMethodConfiguration {

    public ShoppingBasketPage(WebDriver webDriver) {super(webDriver);}
    public final String basketPageHeaderLocalator = "//h3[@class='m-basket__header--title']";
    public final String productAmountLocalator = "//select[@class='a-selectControl -small']";
    public final String pieceLocalator = "//*[@id=\"quantitySelect0-key-0\"]/option[2]";
    public final String basketProductPriceLocalator = ".priceBox__salePrice";
    public final String removeCartItemLocalator = "removeCartItemBtn0-key-0";
    public final String emptyMessageTitleLocalator = "//strong[contains(text(),'Favorilerinizde Ürün Bulunmamaktadır')]";
    public final String emptyMessageLocalator = "//div[@id='emtyFavoriteMessage']//p[@class='m-empty__messageText'][contains(text(),'Birbirinden seçkin markaların, binlerce ürününü he')]";
    public final String messageButtonLocalator = "//a[@title='Alışverişe Başla']";
    public final String favoritaTabLocalator = "favoriteTab";
    public final String historyTabLocalator = "historyTab";

    public void verifyShoppingBasketPage(){
        isDisplayed(By.xpath(basketPageHeaderLocalator));
    }
    public void selectProductAmount(){
        WebElement dropdown = find(By.cssSelector("select.a-selectControl.-small"));
        dropdown.click();

        List<WebElement> options = dropdown.findElements(By.tagName("option"));

        boolean increased = false;
        for (WebElement option : options) {
            String value = option.getAttribute("value");
            String disabled = option.getAttribute("disabled");

            if (!value.equals("1") && disabled == null) {
                option.click();
                System.out.println("Adet arttırıldı: " + option.getText());
                increased = true;
                break;
            }
        }

        if (!increased) {
            System.out.println("Adet arttırılamadı, ya 1 adet sipariş limiti var ya da seçenekler pasif.");
        }
        waitInSeconds(1);
    }
    public void waitShoppingBasketPage(){
        waitForElement(basketPageHeaderLocalator);
    }

    public void verifyProductPrice(){
        String basketPriceText = find(By.cssSelector(basketProductPriceLocalator)).getText();

        double expected = normalizePriceToDouble(productPrice);
        double actual = normalizePriceToDouble(basketPriceText);

        Assert.assertEquals("Product price on the detail page does not match the price in the basket!", expected, actual, 0.01);

        System.out.println("Sayfa Fiyatı : " + productPrice);
        System.out.println("Sepet Fiyatı : " + basketPriceText);

    }

    public void removeCartItemButton(){
        find(By.id(removeCartItemLocalator)).click();
    }
    public void verifyNullBasket(){
        isDisplayed(By.xpath(emptyMessageTitleLocalator));
        isDisplayed(By.xpath(emptyMessageLocalator));
        isDisplayed(By.xpath(messageButtonLocalator));
        isDisplayed(By.id(favoritaTabLocalator));
        isDisplayed(By.id(historyTabLocalator));


    }
}

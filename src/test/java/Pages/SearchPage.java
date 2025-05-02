package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;


public class SearchPage extends PageMethodConfiguration {
    public static String productPrice;
    public SearchPage(WebDriver webDriver) {
        super(webDriver);
    }

        public static WebElement selectRandomProduct() {
            List<WebElement> productList = webDriver.findElements(By.cssSelector("#productList > div"));

            if (productList.isEmpty()) {
                System.out.println("Sayfada ürün bulunamadı.");
                return null;
            }

            Random random = new Random();
            int randomIndex = random.nextInt(productList.size());

            WebElement randomProduct = productList.get(randomIndex);

            try {
                WebElement priceElement = randomProduct.findElement(By.cssSelector(".m-productCard__newPrice"));
                productPrice = priceElement.getText().trim();
                System.out.println("Seçilen ürün fiyatı = " + productPrice);
            } catch (NoSuchElementException e) {
                System.out.println("Fiyat elementi bulunamadı.");
            }

            randomProduct.click();
            waitInSeconds(1);

            return randomProduct;
        }
    }




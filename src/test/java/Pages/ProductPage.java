package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductPage extends PageMethodConfiguration {
    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }
    public final String addBasketLocalator ="//button[@id='addBasket']";
    public final String addBasketLocalator1 =".m-addBasketFavorite";
    public final String basketLocalator = ".bwi-cart-o use";

    public static void writeProductInfoToFile(String fileName) {
        List<WebElement> productList = webDriver.findElements(By.xpath("//span[@class='o-productDetail__description']"));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (WebElement product : productList) {
                String productName = product.findElement(By.xpath("//ins[@id='priceNew']")).getText();
                String productPrice = product.findElement(By.xpath("//span[@class='o-productDetail__description']")).getText();

                writer.write("Ürün Adı: " + productName + "\n");
                writer.write("Ürün Fiyatı: " + productPrice + "\n");
                writer.write("\n");
            }

            System.out.println("Ürün bilgileri " + fileName + " dosyasına yazıldı.");
            waitInSeconds(1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void selectRandomVisibleSize() {
        By sizeOptionsLocator = By.xpath("//span[contains(@class, 'm-variation__item')]");
        List<WebElement> sizeOptions = webDriver.findElements(sizeOptionsLocator);

        List<WebElement> availableSizes = new ArrayList<>();
        for (WebElement size : sizeOptions) {
            String classAttr = size.getAttribute("class");
            if (!classAttr.contains("disabled")) {
                availableSizes.add(size);
            }
        }

        List<WebElement> visibleAndClickableSizeOptions = getVisibleAndClickableElements(webDriver, availableSizes);

        if (!visibleAndClickableSizeOptions.isEmpty()) {
            Random random = new Random();
            WebElement selectedSize = visibleAndClickableSizeOptions.get(random.nextInt(visibleAndClickableSizeOptions.size()));
            selectedSize.click();
            System.out.println("Rastgele bir stokta olan beden seçildi: " + selectedSize.getText());
        } else {
            System.out.println("Stokta olan ve tıklanabilir beden bulunamadı.");
        }

        waitInSeconds(1);
    }

    private static List<WebElement> getVisibleAndClickableElements(WebDriver driver, List<WebElement> elements) {
        return elements.stream()
                .filter(element -> {
                    try {
                        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.elementToBeClickable(element));
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .toList();
    }
    public void scrollDownPage(){
        scrollIntoView(By.cssSelector(addBasketLocalator1));
    }

    public void scrollUpPage(){
        scrollUp(By.cssSelector(basketLocalator));
    }
    public void addBasket(){
        find(By.cssSelector(addBasketLocalator1)).click();
    }

    public void click(){
        click(By.xpath(addBasketLocalator));
    }
    public void clickBasketIcon(){
        find(By.cssSelector(basketLocalator)).click();
    }

}




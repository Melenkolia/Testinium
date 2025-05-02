package Utils;

import org.junit.After;
import org.junit.Before;

import org.openqa.selenium.WebDriver;

public class TestBase {
    protected WebDriver driver;
    @Before
    public void setUp() {
        driver = Driver.getDriver(); // WebDriver'ı başlatır
        driver.get("https://www.beymen.com");
        System.out.println("The page title is : " + driver.getTitle());

    }

    @After
    public void tearDown() {
        Driver.quitDriver(); // Testlerin ardından WebDriver'ı kapatır
    }
}

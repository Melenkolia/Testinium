package Pages;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class PageMethodConfiguration {
    static WebDriver webDriver;
    public PageMethodConfiguration(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public static String readExcelData(String filePath, String sheetName, int rowNum, int colNum) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);
//        System.out.println("Sheet Name: " + sheet);

        XSSFRow row = (XSSFRow) sheet.getRow(rowNum);
        XSSFCell cell = (XSSFCell) ((Row) row).getCell(colNum);

        String cellData = getCellValueAsString(cell);

        workbook.close();
        fileInputStream.close();

//        System.out.println(cellData);

        return cellData;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
    public WebElement find(By locator) {
        return webDriver.findElement(locator);
    }

    public void click(By locator) {
        find(locator).click();
    }


    public Boolean isDisplayed(By locator) {
        return find(locator).isDisplayed();
    }

    public void waitForElement(String elementID) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15L));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementID)));
    }

    public void scrollIntoView(By elementLocator) {
        WebElement element = webDriver.findElement(elementLocator);

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);

    }

    public void scrollUp(By elementLocator) {
        WebElement element = webDriver.findElement(elementLocator);

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView({block: 'start', inline: 'start'});", element);
    }

    public static void waitInSeconds(int secVal) {
        try {
            Thread.sleep(secVal * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static double normalizePriceToDouble(String price) {
        String cleanedPrice = price.replace(".", "")
                .replace(",", ".")
                .replaceAll("[^0-9.]", "")
                .trim();
        return Double.parseDouble(cleanedPrice);
    }

}





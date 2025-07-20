package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    
    @BeforeAll
    static void setUpBase() {
        driver = DriverFactory.createDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    
    @AfterAll
    static void tearDownBase() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    // Helper methods for common test operations
    protected void navigateToUrl(String url) {
        driver.get(url);
    }
    
    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    protected String getPageTitle() {
        return driver.getTitle();
    }
    
    protected void refreshPage() {
        driver.navigate().refresh();
    }
    
    protected void goBack() {
        driver.navigate().back();
    }
    
    protected void goForward() {
        driver.navigate().forward();
    }
}
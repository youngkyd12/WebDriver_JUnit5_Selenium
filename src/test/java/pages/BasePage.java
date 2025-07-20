package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    // Wait for visibility
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be clickable
    protected WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Click safely
    protected void click(By locator) {
        waitForClickability(locator).click();
    }

    // Send keys safely
    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Get text
    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    // Check if element is displayed
    protected boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    // Scroll to element
    protected void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Hover over element
    protected void hoverOverElement(By locator) {
        WebElement element = waitForVisibility(locator);
        actions.moveToElement(element).perform();
    }

    // Check if text is present on page
    protected boolean isTextPresent(String text) {
        return driver.getPageSource().contains(text);
    }

    // Navigate to URL
    protected void navigateTo(String url) {
        driver.get(url);
    }

    // Get current URL
    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Get page title
    protected String getPageTitle() {
        return driver.getTitle();
    }

    // Wait for page to load
    protected void waitForPageLoad() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    // Select from dropdown by visible text
    protected void selectByVisibleText(By locator, String text) {
        WebElement dropdown = waitForVisibility(locator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    // Select from dropdown by value
    protected void selectByValue(By locator, String value) {
        WebElement dropdown = waitForVisibility(locator);
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    // Accept alert
    protected void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    // Dismiss alert
    protected void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    // Get alert text
    protected String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }
}
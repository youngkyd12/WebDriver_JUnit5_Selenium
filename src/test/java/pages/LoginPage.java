package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    
    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By successMsg = By.cssSelector(".flash.success");
    private By errorMsg = By.cssSelector(".flash.error");

    // Actions
    public void navigate() {
        navigateTo("https://the-internet.herokuapp.com/login");
    }

    public void login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
    }

    public By getSuccessLocator() {
        return successMsg;
    }

    public By getErrorLocator() {
        return errorMsg;
    }
    
    // Additional helper methods using BasePage functionality
    public String getSuccessMessage() {
        return getText(successMsg);
    }
    
    public String getErrorMessage() {
        return getText(errorMsg);
    }
    
    public boolean isLoginSuccessful() {
        return isElementDisplayed(successMsg);
    }
    
    public boolean isLoginFailed() {
        return isElementDisplayed(errorMsg);
    }
}
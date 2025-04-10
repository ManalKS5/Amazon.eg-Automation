package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    By phoneInput = By.id("ap_email_login");
    By countryDropdownToggle = By.id("claim-input-dropdown");
    By continueButton = By.id("continue");
    By passwordInput = By.id("ap_password");
    By signInButton = By.id("signInSubmit");


    public void enterPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void selectCountryCode(String countryCode) throws InterruptedException {
        driver.findElement(countryDropdownToggle).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(countryDropdownToggle));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("""
            const select = document.getElementById('claim-input-dropdown-select-element');
            if (select) {
                for (let option of select.options) {
                    if (option.value === 'SA') {
                        option.selected = true;
                        const event = new Event('change', { bubbles: true });
                        select.dispatchEvent(event);
                        break;
                    }
                }
            }
        """);
        Thread.sleep(3000);
    }

    public void clickContinue() throws InterruptedException {
        driver.findElement(continueButton).click();
        Thread.sleep(1000);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void submitLogin() throws InterruptedException {
        driver.findElement(signInButton).click();
        Thread.sleep(10000); // Allow time for manual OTP entry
    }

}

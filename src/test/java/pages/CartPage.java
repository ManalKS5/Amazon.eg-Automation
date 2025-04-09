package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static pages.HomePage.jsClick;
import static pages.HomePage.jsScrollIntoView;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart")));

        jsScrollIntoView(driver, cartIcon);
        jsClick(driver, cartIcon);
    }

    public void proceedToCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement proceedBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("sc-buy-box-ptc-button")));
            proceedBtn.click();
        } catch (TimeoutException e) {
            System.out.println("❌ Proceed to Checkout button not found.");
        }
    }

    public void check(){
        WebElement cartCount = driver.findElement(By.id("nav-cart-count"));
        System.out.println("🧮 Cart count: " + cartCount.getText());
    }

    public double getDisplayedCartTotal() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement totalSpan = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'sc-price') and contains(text(), 'جنيه')]")));
            String totalText = totalSpan.getText();

            String cleanText = totalText.replaceAll("[^\\d.,]", "").replace(",", "");

            double total = Double.parseDouble(cleanText);
            System.out.println("🛒 Cart Page Total (Parsed): " + total);
            return total;

        } catch (NoSuchElementException | NumberFormatException e) {
            System.out.println("❌ Could not read total from cart: " + e.getMessage());
            return -1;
        }
    }

}

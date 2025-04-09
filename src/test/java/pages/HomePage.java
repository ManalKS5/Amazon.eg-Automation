package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openHamburgerMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement hamburgerMenu = driver.findElement(By.id("nav-hamburger-menu"));
        jsClick(driver, hamburgerMenu);
    }

    public void clickShowAll() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.id("nav-hamburger-menu")).click();
        Thread.sleep(3000);
        List<WebElement> menuItems = driver.findElements(By.cssSelector(".hmenu-item"));
        for (WebElement item : menuItems) {
            if (item.getText().contains("عرض الكل")) {
                item.click();
                break;
            }
        }
        Thread.sleep(3000);
    }

    public void goToVideoGames() throws InterruptedException {
        WebElement videoGamesLink = driver.findElement(By.xpath("//a[contains(@class, 'hmenu-item')]//div[text()='ألعاب الفيديو']"));
        jsClick(driver, videoGamesLink);
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement allVideoGames = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class, 'hmenu-item') and text()='جميع ألعاب الفيديو']")));
        jsClick(driver, allVideoGames);
        Thread.sleep(7000);
    }

    public static void jsClick(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static void jsScrollIntoView(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
}

package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static pages.HomePage.jsClick;
import static pages.HomePage.jsScrollIntoView;

public class VideoGamesPage {

    WebDriver driver;
    WebDriverWait wait;
    public static double itemsTotal = 0.0;

    public VideoGamesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By pages = By.cssSelector(".s-pagination-item");

    public void freeShippingFilter() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement freeShippingFilter = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'p_n_free_shipping_eligible')]")));
        jsScrollIntoView(driver, freeShippingFilter);
        jsClick(driver, freeShippingFilter);
        Thread.sleep(5000);
    }

    public void newProductsFilter() throws InterruptedException {
        WebElement newProductsFilter = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class, 'a-link-normal') and contains(@class, 's-navigation-item') and .//span[text()='جديد']]")));
        jsScrollIntoView(driver, newProductsFilter);
        jsClick(driver, newProductsFilter);
        Thread.sleep(3000);
    }

    public void sort() throws InterruptedException {
        WebElement sortFilter = driver.findElement(By.id("s-result-sort-select"));
        jsScrollIntoView(driver, sortFilter);
        ((JavascriptExecutor) driver).executeScript("""
            let select = arguments[0];
            select.value = "price-desc-rank";
            select.dispatchEvent(new Event('change', { bubbles: true }));
        """, sortFilter);
        Thread.sleep(3000);
    }

    public void processCurrentPage() throws InterruptedException {
        List<WebElement> products = getAllProductElements();
        System.out.println("Products found: " + products.size());

        for (WebElement product : products) {
            if (productHasValidPrice(product)) {
                double price = extractPrice(product);
                System.out.println("Final Price Parsed: " + price);

                if (price < 15000) {
                    tryToAddToCart(product, price);
                }
            }
        }
    }

    private List<WebElement> getAllProductElements() {
        return driver.findElements(By.cssSelector("div.s-main-slot div[data-component-type='s-search-result']"));
    }

    private boolean productHasValidPrice(WebElement product) {
        try {
            product.findElement(By.cssSelector(".a-price .a-price-whole"));
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("price missing!");
            return false;
        }
    }

    private double extractPrice(WebElement product) {
        String wholeText = product.findElement(By.cssSelector(".a-price .a-price-whole")).getText().replaceAll("[^\\d]", "");
        String fractionText = "";
        try {
            fractionText = product.findElement(By.cssSelector(".a-price .a-price-fraction")).getText().replaceAll("[^\\d]", "");
        } catch (NoSuchElementException ignored) {}

        String fullPriceText = wholeText + "." + (fractionText.isEmpty() ? "00" : fractionText);
        return Double.parseDouble(fullPriceText);
    }

    private void tryToAddToCart(WebElement product, double price) throws InterruptedException {
        List<WebElement> cartBtns = product.findElements(By.xpath(".//button[contains(text(),'إضافة إلى عربة التسوق')]"));
        if (cartBtns.isEmpty()) {
            System.out.println("Add to Cart button not found!");
            return;
        }

        WebElement addToCartBtn = cartBtns.get(0);
        jsScrollIntoView(driver, addToCartBtn);
        jsClick(driver, addToCartBtn);
        Thread.sleep(3000);

        itemsTotal += price;
        System.out.println("🧾 Running Total Updated: " + itemsTotal);

        String cartCount = driver.findElement(By.id("nav-cart-count")).getText();
        System.out.println("🛒 Cart count after click: " + cartCount);
    }

    public void moveThroughPages(int maxPages) throws InterruptedException {
        List<WebElement> allPages = driver.findElements(pages);
        for (int i = 0; i < maxPages && i < allPages.size(); i++) {
            if (i != 0) {
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allPages.get(i));
                    Thread.sleep(3000);
                } catch (Exception e) {
                    continue;
                }
            }
            processCurrentPage();
        }
    }

}
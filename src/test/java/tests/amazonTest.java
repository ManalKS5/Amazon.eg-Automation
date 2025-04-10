package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.VideoGamesPage;
import pages.CartPage;

public class amazonTest {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    VideoGamesPage videoGamesPage;
    CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.eg/");

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        videoGamesPage = new VideoGamesPage(driver);
        cartPage = new CartPage(driver);

    }

    @Test
    public void testCase1() throws InterruptedException {
        homePage.openHamburgerMenu();
        homePage.clickShowAll();
        homePage.goToVideoGames();
        videoGamesPage.freeShippingFilter();
        videoGamesPage.newProductsFilter();
        videoGamesPage.sort();
        videoGamesPage.processCurrentPage();
        videoGamesPage.moveThroughPages(3);
        cartPage.check();
        cartPage.openCart();
        double expectedTotal = VideoGamesPage.itemsTotal;
        double actualTotal = cartPage.getDisplayedCartTotal();
        System.out.println("Calculated manually: " + expectedTotal);
        System.out.println("Shown in Cart: " + actualTotal);
        Assert.assertEquals(actualTotal, expectedTotal, 0.01, "Mismatch between added items total and cart total!");
        cartPage.proceedToCheckout();
        loginPage.enterPhone("55XXXXXXX"); // Replace with test data
        loginPage.selectCountryCode("SA");
        loginPage.clickContinue();
        loginPage.enterPassword("Password"); // Replace with test data
        loginPage.submitLogin();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}

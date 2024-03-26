package Annotations;

import common.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;

public class TC_02 extends BaseTest {

    @BeforeMethod
    public static void openBrowser() {
        createDriver();
        driver.get("https://anhtester.com/");
    }

    @Test
    public void RunTest() {
        String expectedTitle = "Anh Tester Automation Testing";
        String originalTitle = driver.getTitle();

        System.out.println("*** Checking For The Title ***");

        Assert.assertEquals(originalTitle, expectedTitle);
        driver.quit();
    }

    @Test
    public void dropdown() throws InterruptedException  {
        WebElement toolOptions = driver.findElement(By.xpath("(//span[@id='categories'])[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(toolOptions).clickAndHold().perform();
        Boolean isDisplayed = toolOptions.isDisplayed();
        Assert.assertTrue(isDisplayed, "Dropdown is displayed");

        WebElement toolDropdown = driver.findElement(By.xpath("//a[normalize-space()='All Courses']"));
        toolDropdown.click();
        Thread.sleep(10);
        String textTitle = driver.findElement(By.cssSelector(".section-heading")).getText();
        Assert.assertEquals(textTitle,"All Courses");
    }

    @AfterMethod
    public void quitBrowser() {
        closeDriver();
    }
}

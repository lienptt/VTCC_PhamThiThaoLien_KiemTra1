package AddCategory;

import common.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;

import java.util.List;

public class TC_AddCategory extends BaseTest {

    @BeforeMethod
    public static void openBrowser() {
        createDriver();
        login();
        //Redirect category tab
        driver.findElement(By.xpath("//span[normalize-space()='Products']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Category']")).click();
    }

    @Test (priority = 0)
    public void createNewCategory() throws InterruptedException {
        //Click button Add New Category
        driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();

        WebElement addNewCategoryForm = driver.findElement(By.cssSelector(".form-horizontal"));
        Boolean formIsDisplayed = addNewCategoryForm.isDisplayed();
        Assert.assertTrue(formIsDisplayed, "Assert Add new category form is displayed");

       driver.findElement(By.id("name")).sendKeys("T-shirt");

       // Choose Parent Category
        driver.findElement(By.cssSelector("button[title='No Parent']")).click();
        driver.findElement(By.xpath("(//input[@aria-label='Search'])[1]")).sendKeys("aka",Keys.ENTER);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();

        // Input Order level
        driver.findElement(By.id("order_level")).sendKeys("2");

        // Choose Type
        Select select = new Select(driver.findElement(By.xpath("//select[@name='digital']")));
        select.selectByVisibleText("Digital");

        // Choose Filtering Attributes
        driver.findElement(By.cssSelector("button[title='Nothing selected']")).click();
        select = new Select(driver.findElement(By.xpath("//select[@name='filtering_attributes[]']")));
        select.selectByValue("3");

        // Input meta title
        WebElement metaTileTextbox = driver.findElement(By.xpath("//input[@placeholder='Meta Title']"));
        metaTileTextbox.sendKeys("Test Add Category");

        // Choose banner
        driver.findElement(By.xpath("(//div[contains(text(),'Browse')])[1]")).click();
        List<WebElement> imageItems = driver.findElements(By.cssSelector(".aiz-uploader-select"));
        imageItems.get(1).click();
        WebElement buttonAddFile = driver.findElement(By.xpath("//button[normalize-space()='Add Files']"));
        buttonAddFile.click();
        Thread.sleep(10);

        // Choose icon
        driver.findElement(By.xpath("(//div[contains(text(),'Browse')])[2]")).click();
        List<WebElement> imageItem = driver.findElements(By.cssSelector(".aiz-uploader-select"));
        imageItem.get(2).click();
        driver.findElement(By.xpath("//button[normalize-space()='Add Files']")).click();

        //Input meta description
        driver.findElement(By.xpath("//textarea[contains(@name,'meta_description')]")).sendKeys("Category Description");

        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Test (priority = 1)
    public void assertSearchItem() throws InterruptedException {
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("T-shirt", Keys.ENTER);
        sleep(30);
        Boolean categoryNameIsDisplayedInSeach = driver.findElement(By.xpath("(//td[contains(text(),'T-shirt')])[1]")).isDisplayed();
        Assert.assertTrue(categoryNameIsDisplayedInSeach);
    }

    @AfterMethod
    public void quitBrowser() {
        closeDriver();
    }
}

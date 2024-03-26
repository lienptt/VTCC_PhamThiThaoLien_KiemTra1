package AddCategory;

import common.BaseTest;
import org.openqa.selenium.*;
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
        WebElement productTab = driver.findElement(By.xpath("//span[normalize-space()='Products']"));
        productTab.click();
        WebElement categoryTab = driver.findElement(By.xpath("//span[normalize-space()='Category']"));
        categoryTab.click();
    }

    @Test
    public void createNewCategory() {
        WebElement addNewCategoryButton = driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
        addNewCategoryButton.click();
        WebElement addNewCategoryForm = driver.findElement(By.cssSelector(".form-horizontal"));
        Boolean formIsDisplayed = addNewCategoryForm.isDisplayed();
        Assert.assertTrue(formIsDisplayed, "Assert Add new category form is displayed");

        WebElement nameInput = driver.findElement(By.id("name"));
        nameInput.sendKeys("T-shirt");

        WebElement orderingNumber = driver.findElement(By.id("order_level"));
        orderingNumber.sendKeys("2");

        Select select = new Select(driver.findElement(By.xpath("//select[@name='digital']")));
        select.selectByVisibleText("Digital");

        driver.findElement(By.cssSelector("button[title='Nothing selected']")).click();
        select = new Select(driver.findElement(By.xpath("//select[@name='filtering_attributes[]']")));
        select.selectByValue("3");

        WebElement metaTileTextbox = driver.findElement(By.xpath("//input[@placeholder='Meta Title']"));
        metaTileTextbox.sendKeys("Test Add Category");

        WebElement bannerChooseFile = driver.findElement(By.xpath("(//div[contains(text(),'Browse')])[1]"));
        bannerChooseFile.click();
        List<WebElement> imageItems = driver.findElements(By.cssSelector(".aiz-uploader-select"));
        imageItems.get(1).click();
        WebElement buttonAddFile = driver.findElement(By.xpath("//button[normalize-space()='Add Files']"));
        buttonAddFile.click();

        WebElement metaDes = driver.findElement(By.xpath("//textarea[contains(@name,'meta_description')]"));
        metaDes.sendKeys("Category Description");

        WebElement buttonSave = driver.findElement(By.cssSelector("button[type='submit']"));
        buttonSave.click();
    }

    @Test
    public void assertSearchItem() {
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("T-shirt", Keys.ENTER);
        WebElement categoryNameSearch = driver.findElement(By.xpath("(//td[contains(text(),'T-shirt')])[1]"));
        Boolean categoryNameIsDisplayed = categoryNameSearch.isDisplayed();
        Assert.assertTrue(categoryNameIsDisplayed,"Assert Category Name is Displayed");
    }

    @AfterMethod
    public void quitBrowser() {
        closeDriver();
    }
}

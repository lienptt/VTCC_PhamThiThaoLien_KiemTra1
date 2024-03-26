package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class BaseTest {
    public static WebDriver driver;

    public static void login(){
        driver.get("https://cms.anhtester.com/login");

        // Đăng nhập
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("admin@example.com");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("123456");
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
        loginButton.click();
    }
    public static void createDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    public static void closeDriver() {
        driver.quit();
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkElementExist(By by) {
        List<WebElement> elements = driver.findElements(by);
        if (elements.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}

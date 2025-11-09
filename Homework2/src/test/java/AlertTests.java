import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertTests {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testAlertWithTextbox() {
        // Open the page
        driver.get("https://demo.automationtesting.in/Alerts.html");

        // Click on "Alert with Textbox" tab
        WebElement alertWithTextboxTab = driver.findElement(By.xpath("//a[@href='#Textbox']"));
        alertWithTextboxTab.click();

        // Click the button that throws alert with prompt
        WebElement promptButton = driver.findElement(By.xpath("//button[@onclick='promptbox()']"));
        promptButton.click();

        // Wait for alert to be present and switch to it
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Send keys to alert (your name and surname)
        String nameToSend = "Anzori Sakhelashvili";
        alert.sendKeys(nameToSend);

        // Accept the alert
        alert.accept();

        // Get the result text
        WebElement resultText = driver.findElement(By.id("demo1"));
        String actualText = resultText.getText();

        // Assert that the text contains the name provided
        String expectedText = "Hello " + nameToSend + " How are you today";
        Assert.assertEquals(actualText, expectedText, "Result text does not match the expected value!");

        System.out.println("Alert test passed! Result text: " + actualText);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
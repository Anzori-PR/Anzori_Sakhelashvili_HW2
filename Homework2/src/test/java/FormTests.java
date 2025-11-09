import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class FormTests {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testFormSubmission() {
        driver.get("https://demoqa.com/automation-practice-form");

        // Fill First Name
        driver.findElement(By.id("firstName")).sendKeys("Anzori");

        // Fill Last Name
        driver.findElement(By.id("lastName")).sendKeys("Sakhelashvili");

        // Fill Email
        driver.findElement(By.id("userEmail")).sendKeys("anzori@gmail.com");

        // Select Gender (Male)
        WebElement genderMale = driver.findElement(By.cssSelector("label[for='gender-radio-1']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", genderMale);

        // Fill Mobile Number
        driver.findElement(By.id("userNumber")).sendKeys("1234567890");

        // Fill Date of Birth
        WebElement dateOfBirthInput = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirthInput.click();

        // Select Month
        WebElement monthDropdown = driver.findElement(By.className("react-datepicker__month-select"));
        monthDropdown.click();
        driver.findElement(By.xpath("//option[@value='7']")).click(); // August

        // Select Year
        WebElement yearDropdown = driver.findElement(By.className("react-datepicker__year-select"));
        yearDropdown.click();
        driver.findElement(By.xpath("//option[@value='2004']")).click();

        // Select Day
        driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='26']")).click();

        // Fill Subject
        WebElement subjectInput = driver.findElement(By.id("subjectsInput"));
        subjectInput.sendKeys("Maths");
        subjectInput.sendKeys(Keys.ENTER);

        // Select Hobby (Sports)
        WebElement hobbySports = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-1']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hobbySports);

        // Fill Current Address
        WebElement address = driver.findElement(By.id("currentAddress"));
        address.sendKeys("Tbilisi, Georgia");

        // Scroll to State dropdown
        WebElement stateDropdown = driver.findElement(By.id("state"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stateDropdown);

        // Select State
        stateDropdown.click();
        WebElement stateOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select-3-option') and text()='NCR']")));
        stateOption.click();

        // Select City
        WebElement cityDropdown = driver.findElement(By.id("city"));
        cityDropdown.click();
        WebElement cityOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select-4-option') and text()='Delhi']")));
        cityOption.click();

        // Scroll to Submit button and click
        WebElement submitButton = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);

        // Wait for modal popup
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));

        // Assertions
        Assert.assertTrue(modal.isDisplayed(), "Modal popup not displayed!");
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Student Name']/following-sibling::td[text()='Anzori Sakhelashvili']")).isDisplayed(), "Name not visible!");
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Student Email']/following-sibling::td[text()='anzori@gmail.com']")).isDisplayed(), "Email not visible!");
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td[text()='Male']")).isDisplayed(), "Gender not visible!");
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Mobile']/following-sibling::td[text()='1234567890']")).isDisplayed(), "Phone not visible!");
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Date of Birth']/following-sibling::td[text()='26 August,2004']")).isDisplayed(), "Date of Birth not visible!");
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Subjects']/following-sibling::td[text()='Maths']")).isDisplayed(), "Subjects not visible!");
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Hobbies']/following-sibling::td[text()='Sports']")).isDisplayed(), "Hobbies not visible!");
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td[text()='Tbilisi, Georgia']")).isDisplayed(), "Address not visible!");
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='State and City']/following-sibling::td[text()='NCR Delhi']")).isDisplayed(), "State and City not visible!");
        System.out.println("All submitted data verified successfully!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @Test
    public void testCase01() throws InterruptedException{
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement nameTextBox = driver.findElement(By.xpath("(//input[contains(@class , 'zHQkBf')])[1]"));
        //nameTextBox.click();
        Wrappers.sendKeys(nameTextBox, "Crio Learner");

        WebElement ansTextBox = driver.findElement(By.xpath("//textarea[contains(@class , 'tL9Q4c')]"));
        //ansTextBox.click();
        String PractisingAutomationText = "I want to be the best QA Engineer!";
        String epochTimeString = Wrappers.getEpochTimeAsString();
        Thread.sleep(5000);
        System.out.println("wait 1");
        Wrappers.sendKeys(ansTextBox, PractisingAutomationText +" "+epochTimeString);

       //select radio button as per automation experience
       Wrappers.radioButton(driver, "0 - 2");
       Thread.sleep(2000);
       System.out.println("wait 2");

       //select checkbox for skillsets
       Wrappers.checkBox(driver, "Java");
       Wrappers.checkBox(driver, "Selenium");
       Wrappers.checkBox(driver, "TestNG");
       Thread.sleep(3000);
       System.out.println("wait 3");


       WebElement dropdownElement = driver.findElement(By.xpath("//div[contains(@class , 'DEh1R')]"));
       Wrappers.clickElement(driver, dropdownElement);
       Thread.sleep(3000);
       Wrappers.dropDownClick(driver, "Ms");

        //Enter date 7 days ago
        WebElement dateElement = driver.findElement(By.xpath("//input[@type = 'date']"));
        dateElement.sendKeys("23/11/2024");
        //String dateSevenDaysAgo = Wrappers.getDateSevenDaysAgo();
        Thread.sleep(3000);
        System.out.println("wait 4");
        //Wrappers.sendKeys(dateElement, dateSevenDaysAgo);

        //Enter current time in HH:MM
        WebElement hourElement = driver.findElement(By.xpath("//input[@aria-label= 'Hour']"));
        WebElement minElement  = driver.findElement(By.xpath("//input[@aria-label= 'Minute']"));

        Wrappers.sendKeys(hourElement, "07");
        Wrappers.sendKeys(minElement, "30");

        //click on submit button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label = 'Submit']")));
        WebElement submitButton = driver.findElement(By.xpath("//div[@aria-label = 'Submit']"));
        Wrappers.clickElement(driver, submitButton);
        Thread.sleep(3000);
        System.out.println("wait 5");

        WebElement successMessage = driver.findElement(By.xpath("//div[@class = 'vHW8K']"));
        String expectedSuccessMessage = "Thanks for your response, Automation Wizard!";
        Assert.assertEquals(successMessage.getText(), expectedSuccessMessage);

    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}
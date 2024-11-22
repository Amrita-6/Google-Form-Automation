package demo.wrappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

     public static void sendKeys(WebElement element , String text){

        try{
            element.click();
            //element.clear();
            element.sendKeys(text);
        }catch(Exception e){
            e.printStackTrace();
        }

     }

     public static void radioButton(ChromeDriver driver , String expInYear){
        try{
            WebElement element = driver.findElement(By.xpath("//div[@data-value = '"+expInYear+"']"));
            element.click();

        }catch(Exception e){
            e.printStackTrace();
        }
       
     }

     public static void checkBox(ChromeDriver driver , String tool){
         try{
            WebElement element = driver.findElement(By.xpath("//div[@aria-label = '"+tool+"']"));
            element.click();
             
         }catch(Exception e){
             e.printStackTrace();
         }

     }

     public static void dropDownClick(ChromeDriver driver , String dropdownValue){
         try{
            WebElement option = driver.findElement(By.xpath("//div[contains(@class , 'ncFHed')]/div/span[text() = '"+dropdownValue+"']"));
            //WebElement option = driver.findElement(By.xpath("//div[contains(@class , 'ncFHed')]/div/span[text() = 'Ms']"));
            option.click();
            Thread.sleep(2000);

         }catch(Exception e){
            e.printStackTrace();
         }

     }

     public static void clickElement(ChromeDriver driver , WebElement element){
         try{
            JavascriptExecutor js  = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click();", element);
         }catch(Exception e){
            e.printStackTrace();
         }

     }

     public static String getDateSevenDaysAgo(){
         LocalDate currentDate = LocalDate.now(); //get current date
         //subtract 7 days
         LocalDate dateMinus7Days= currentDate.minusDays(7);
         //Define the desired format
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
         //Format the current date
         String formattedDate = dateMinus7Days.format(formatter);

         return formattedDate;

     } 

     public static String getEpochTimeAsString(){
        //create epoch time string
        long epochTime = System.currentTimeMillis()/1000;
        String epochTimeString = String.valueOf(epochTime);
        return epochTimeString;

     }


     










}
